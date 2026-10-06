# ABP - MesApp

## Introduction

Restaurant management tool without a Graphical Interface, with table
selection, orders, menus, reservation schedules, and cash register.

**Team size:** 3 people

The application must be managed through text menus, with different
options depending on the type of user (Management, waiters, customers).

------------------------------------------------------------------------

## Functions

### Management

-   User management, add/remove waiters and customers
-   Reservation management
-   Extract daily billing
-   Create / modify / delete dishes
-   Waiter tasks

### Waiters

-   Assign / Reassign / Unassign tables to customers
-   Create menu orders
-   Check prices / supplements
-   List dishes
-   List tables
-   List table orders (Total price, split, etc.)
-   Create menus (Type 1, 2, 3... with starter + dessert, starter +
    main + dessert...)
-   Create / Modify order (Add / remove items / dishes)
-   Close / Mark order as Paid / Non‑modifiable

### Customers

-   Self‑register

-   Reserve table (Each table must be reservable by shifts)

-   Allergens (Check with LDAP)

-   Limit dish requests according to allergens

------------------------------------------------------------------------

## Technical Operation

### Systems Module

#### Contents

-   LDAP Server
-   Database Server (Postgres?)
-   NFS Server
-   VPN Server

#### Service Usage

-   LDAP --- User and permissions management of the app
-   Database --- Persistent data management (Orders and dishes) from the
    app
-   NFS --- Directory centralization

------------------------------------------------------------------------

### Programming Module

#### Contents

-   Collections
-   File read / write
-   Database read / write
-   Conditional / iterative structures / exceptions

#### Functions

-   Terminal menu management according to user
-   Self‑registration form
-   Relational database functions (PostgreSQL) and non‑relational (LDAP)

------------------------------------------------------------------------

### Development Environments

#### Contents

-   Class diagrams
-   Behavior diagrams
-   Markup language documentation
-   Version control

------------------------------------------------------------------------
