# CKU Restaurant Management System

The **CKU Restaurant Management System** is a Java-based console application designed to unify and streamline daily restaurant operations. It centralizes workflows for users, tables, reservations, dishes, menus, and orders into a single, role-based system for managers, waiters, and customers.

## 🚀 Key Features

*   **Role-Based Access:** Dedicated consoles for Managers, Waiters, and Customers, ensuring each user only sees relevant actions.
*   **Smart Persistence:** Utilizes a **PostgreSQL** database with a unique "Update-then-SELECT" pattern to guarantee data consistency between the application and the server.
*   **Centralized Authentication:** Integrated with **LDAP** for secure identity management and role-based permissions.
*   **Allergen Safety:** Automated allergen-aware behavior that filters or flags dishes based on customer profiles stored in LDAP.
*   **Infrastructure-Ready:** Designed to run on **AWS** with **Docker** containers, featuring **VPN** security and **NFS** for resilient data storage.

## 🛠️ Technology Stack

*   **Language:** Java.
*   **Database:** PostgreSQL 16 (Relational storage with triggers and constraints).
*   **Directory Service:** OpenLDAP (User authentication and allergen profiles).
*   **Infrastructure:** AWS EC2, Docker & Docker Compose.
*   **Storage:** NFS (Network File System) for remote database persistence.
*   **Security:** WireGuard VPN (wg-easy) for encrypted access to infrastructure.
*   **Libraries:** PostgreSQL JDBC Driver, UnboundID LDAP SDK, Hamcrest.

## 🏗️ System Architecture

The project follows a **Layered Architecture** to ensure maintainability and separation of concerns:

1.  **Presentation Layer:** Role-specific CLI menus (Main, ManagerConsole, WaiterConsole, CustomerConsole).
2.  **Business Logic Layer:** "The Brain" of the system (ReservationManager, WaiterService, etc.).
3.  **Persistence Layer:** Data access via JDBC Repositories and LDAP access classes.
4.  **Infrastructure Layer:** The underlying AWS servers, Docker containers, and VPN tunnels.

## 📋 Role Overview

*   **Managers:** Handle user management (LDAP), dish/menu maintenance, reservation oversight, and generate daily billing reports.
*   **Waiters:** Manage table assignments, create/modify orders, and process payments (which automatically updates reservation statuses).
*   **Customers:** Access self-registration, book shift-based reservations with capacity validation, and place allergen-safe orders.

## ⚙️ Setup & Requirements

1.  **VPN:** Connect to the infrastructure via **WireGuard** to access the private AWS network.
2.  **Infrastructure:** Ensure **Docker** and **Docker Compose** are installed. Services (LDAP, PostgreSQL) are deployed using the provided `docker-compose.yml` files.
3.  **Database:** Initialize the PostgreSQL schema using the provided SQL scripts (`fpDatabase.sql`).
4.  **Java Environment:** Requires the Java runtime and the libraries located in the `lib` directory.

## 👥 Contributors

*   **Carles Conesa Mañosa:** Programming (Waiter logic, reservations, menu management, manager workflows).
*   **Uriel Neves Silva:** Computer Systems, Documentation & Programming (Infrastructure, SQL schema, diagrams, project coordination).
*   **Kadiatou Diallo:** Programming Support (Enums, Dish models, auxiliary support).