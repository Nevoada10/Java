\c template1

-- Terminate all active connections to the database before dropping it
SELECT pg_terminate_backend(pg_stat_activity.pid) 
FROM pg_stat_activity 
WHERE pg_stat_activity.datname = 'fpdatabase' 
  AND pid <> pg_backend_pid();

-- Drop and recreate the database
DROP DATABASE IF EXISTS fpdatabase;
CREATE DATABASE fpdatabase OWNER admin;
SET ROLE admin;
\c fpdatabase

-- Tables
CREATE TABLE  tables (
    id VARCHAR(10) PRIMARY KEY,
    capacity INTEGER NOT NULL
);

-- Dishes
CREATE TABLE dishes (
    name VARCHAR(100) PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    category VARCHAR(20) NOT NULL,
    allergens VARCHAR(200)
);

-- Menus
CREATE TABLE  menus (
    name VARCHAR(100) PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    dishes VARCHAR(200)
);

-- Menu courses (relacion menu - dish)
CREATE TABLE  menu_courses (
    menu_name VARCHAR(100) REFERENCES menus(name),
    dish_name VARCHAR(100) REFERENCES dishes(name),
    PRIMARY KEY (menu_name, dish_name)
);

-- Reservations
CREATE TABLE reservations (
    id VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(50) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    table_id VARCHAR(10) REFERENCES tables(id),
    num_seats INTEGER NOT NULL,
    shift VARCHAR(20) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT valid_reservation_status CHECK (status IN ('PENDING', 'ACTIVE', 'CANCELLED', 'COMPLETED', 'FRAUDULENT', 'EXPIRED'))
);

-- Orders
CREATE TABLE orders (
    id VARCHAR(20) PRIMARY KEY,
    reservation_id VARCHAR(20) NOT NULL REFERENCES reservations(id),
    table_id VARCHAR(10) REFERENCES tables(id),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

-- Order items
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id VARCHAR(20) REFERENCES orders(id),
    type VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity INTEGER NOT NULL,
    supplement VARCHAR(200)
);

-- Order item chosen courses (platos escogidos cuando el item es un menú)
CREATE TABLE order_item_chosen_courses (
    order_item_id INTEGER REFERENCES order_items(id),
    dish_name VARCHAR(100) REFERENCES dishes(name),
    PRIMARY KEY (order_item_id, dish_name)
);

-- Audit log (tracks INSERT/UPDATE/DELETE done on business tables)
CREATE TABLE audit_log (
    id BIGSERIAL PRIMARY KEY,
    table_name VARCHAR(100) NOT NULL,
    operation VARCHAR(10) NOT NULL,
    row_pk VARCHAR(200),
    old_data JSONB,
    new_data JSONB,
    changed_at TIMESTAMP NOT NULL DEFAULT NOW(),
    changed_by VARCHAR(100) NOT NULL DEFAULT CURRENT_USER
);

-- Generic trigger function to capture row-level changes
CREATE OR REPLACE FUNCTION log_row_change()
RETURNS TRIGGER AS $$
DECLARE
    old_json JSONB;
    new_json JSONB;
    pk_value VARCHAR(200);
BEGIN
    IF TG_OP = 'DELETE' THEN
        old_json := to_jsonb(OLD);
        new_json := NULL;
        pk_value := COALESCE(old_json->>'id', old_json->>'name', old_json->>'menu_name', old_json->>'order_item_id');
    ELSIF TG_OP = 'UPDATE' THEN
        old_json := to_jsonb(OLD);
        new_json := to_jsonb(NEW);
        pk_value := COALESCE(new_json->>'id', new_json->>'name', new_json->>'menu_name', new_json->>'order_item_id',
                             old_json->>'id', old_json->>'name', old_json->>'menu_name', old_json->>'order_item_id');
    ELSE
        old_json := NULL;
        new_json := to_jsonb(NEW);
        pk_value := COALESCE(new_json->>'id', new_json->>'name', new_json->>'menu_name', new_json->>'order_item_id');
    END IF;

    INSERT INTO audit_log (table_name, operation, row_pk, old_data, new_data)
    VALUES (TG_TABLE_NAME, TG_OP, pk_value, old_json, new_json);

    IF TG_OP = 'DELETE' THEN
        RETURN OLD;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- When a new order is created, mark its reservation as ACTIVE.
CREATE OR REPLACE FUNCTION activate_reservation_on_order_insert()
RETURNS TRIGGER AS $$
BEGIN
        UPDATE reservations
        SET status = 'ACTIVE'
        WHERE id = NEW.reservation_id
            AND status = 'PENDING';

        RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- When an order is marked as PAID, complete its reservation.
CREATE OR REPLACE FUNCTION complete_reservation_on_order_paid()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status = 'PAID' AND OLD.status IS DISTINCT FROM NEW.status THEN
        UPDATE reservations
        SET status = 'COMPLETED'
        WHERE id = NEW.reservation_id
          AND status = 'ACTIVE';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Enforces immutability of non-PENDING reservation states.
-- Only PENDING reservations can have their state modified manually.
-- System-driven transitions: PENDING → ACTIVE (on order creation), ACTIVE → COMPLETED (on order payment).
-- All other state transitions from ACTIVE, EXPIRED, CANCELLED, COMPLETED, or FRAUDULENT are blocked.
CREATE OR REPLACE FUNCTION prevent_terminal_reservation_updates()
RETURNS TRIGGER AS $$
BEGIN
    -- Terminal states cannot be modified at all
    IF OLD.status IN ('EXPIRED', 'CANCELLED', 'COMPLETED', 'FRAUDULENT') THEN
        RAISE EXCEPTION 'Cannot modify a reservation with status: %', OLD.status;
    END IF;

    -- ACTIVE can only stay ACTIVE or transition to COMPLETED (system-driven by order payment)
    -- Allows idempotent ACTIVE → ACTIVE updates (from Java code retrying after DB trigger)
    IF OLD.status = 'ACTIVE' AND NEW.status NOT IN ('ACTIVE', 'COMPLETED') THEN
        RAISE EXCEPTION 'Cannot modify reservation: ACTIVE reservations can only auto-complete when orders are paid';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Triggers for all main tables
CREATE TRIGGER trg_audit_tables
AFTER INSERT OR UPDATE OR DELETE ON tables
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_dishes
AFTER INSERT OR UPDATE OR DELETE ON dishes
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_menus
AFTER INSERT OR UPDATE OR DELETE ON menus
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_menu_courses
AFTER INSERT OR UPDATE OR DELETE ON menu_courses
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_reservations
AFTER INSERT OR UPDATE OR DELETE ON reservations
FOR EACH ROW EXECUTE FUNCTION log_row_change();

-- Prevent modifications to terminal (non-modifiable) reservation states
CREATE TRIGGER trg_prevent_terminal_reservation_updates
BEFORE UPDATE ON reservations
FOR EACH ROW EXECUTE FUNCTION prevent_terminal_reservation_updates();

CREATE TRIGGER trg_audit_orders
AFTER INSERT OR UPDATE OR DELETE ON orders
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_order_items
AFTER INSERT OR UPDATE OR DELETE ON order_items
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_audit_order_item_chosen_courses
AFTER INSERT OR UPDATE OR DELETE ON order_item_chosen_courses
FOR EACH ROW EXECUTE FUNCTION log_row_change();

CREATE TRIGGER trg_activate_reservation_on_order_insert
AFTER INSERT ON orders
FOR EACH ROW EXECUTE FUNCTION activate_reservation_on_order_insert();

CREATE TRIGGER trg_complete_reservation_on_order_paid
AFTER UPDATE OF status ON orders
FOR EACH ROW EXECUTE FUNCTION complete_reservation_on_order_paid();

-- Marks PENDING reservations as EXPIRED when their shift window has passed without any order.
-- Call this at application startup: SELECT expire_stale_reservations();
CREATE OR REPLACE FUNCTION expire_stale_reservations()
RETURNS void AS $$
BEGIN
    UPDATE reservations
    SET status = 'EXPIRED'
    WHERE status = 'PENDING'
      AND (
        date < CURRENT_DATE
        OR (date = CURRENT_DATE AND (
              (shift = 'LUNCH_1'  AND LOCALTIME > TIME '14:00')
           OR (shift = 'LUNCH_2'  AND LOCALTIME > TIME '16:00')
           OR (shift = 'DINNER_1' AND LOCALTIME > TIME '21:00')
           OR (shift = 'DINNER_2' AND LOCALTIME > TIME '23:00')
        ))
      );
END;
$$ LANGUAGE plpgsql;

-- Tables
INSERT INTO tables (id, capacity) VALUES
('T1', 2), ('T2', 2), ('T3', 2),
('T4', 4), ('T5', 4), ('T6', 4), ('T7', 4),
('T8', 6), ('T9', 6),
('T10', 8), ('T11', 8),
('T12', 10), ('T13', 1);

-- Dishes
INSERT INTO dishes (name, price, category, allergens) VALUES
('GAZPACHO', 4.00, 'STARTER', NULL),
('CAESAR SALAD', 6.50, 'STARTER', 'GLUTEN|EGG'),
('BREAD WITH TOMATO', 3.00, 'STARTER', 'GLUTEN'),
('BRUSCHETTA', 4.50, 'STARTER', 'GLUTEN'),
('HUMMUS WITH PITA', 5.00, 'STARTER', 'GLUTEN'),
('CAPRESE SALAD', 6.00, 'STARTER', 'LACTOSE'),
('FRENCH ONION SOUP', 5.50, 'STARTER', 'GLUTEN|LACTOSE'),
('GARLIC PRAWNS', 8.00, 'STARTER', 'SHELLFISH'),
('STUFFED MUSHROOMS', 5.50, 'STARTER', 'LACTOSE'),
('BEEF CARPACCIO', 9.00, 'STARTER', 'EGG'),
('TUNA TARTARE', 10.00, 'STARTER', 'FISH'),
('SPRING ROLLS', 5.00, 'STARTER', 'GLUTEN|SOY'),
('FISH SOUP', 7.00, 'MAIN', 'FISH|SHELLFISH'),
('PAELLA', 14.00, 'MAIN', 'SHELLFISH|FISH'),
('GRILLED CHICKEN', 12.00, 'MAIN', NULL),
('BEEF STEAK', 16.00, 'MAIN', NULL),
('PASTA CARBONARA', 11.00, 'MAIN', 'GLUTEN|EGG|LACTOSE'),
('VEGGIE BURGER', 10.00, 'MAIN', 'GLUTEN|EGG|SOY'),
('GRILLED SALMON', 15.00, 'MAIN', 'FISH'),
('LAMB CHOPS', 18.00, 'MAIN', NULL);

-- Menus
INSERT INTO menus (name, price, dishes) VALUES
('MENÚ 1', 12, 'GAZPACHO,CAESAR SALAD,PAELLA,GRILLED CHICKEN'
);

-- Menu courses
INSERT INTO menu_courses (menu_name, dish_name) VALUES
('MENÚ 1', 'GAZPACHO'),
('MENÚ 1', 'CAESAR SALAD'),
('MENÚ 1', 'HUMMUS WITH PITA'),
('MENÚ 1', 'GARLIC PRAWNS'),
('MENÚ 1', 'FISH SOUP'),
('MENÚ 1', 'PAELLA');

