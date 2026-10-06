# FAQ: CKU Restaurant Management System

This document provides answers to frequently asked questions regarding the **CKU Restaurant Management System**, its features, and its technical infrastructure.

## General Information

### What is the CKU Restaurant Management System?
It is a **Java-based console application** designed to unify restaurant workflows—including user management, table reservations, and order processing—into a single, role-based system. It aims to reduce operational mistakes by centralizing data that is typically split across different tools.

### Who are the different users of the system?
The system is built for three specific roles, each with its own interface:
*   **Managers:** Oversee users, menus, reservations, and financial reports.
*   **Waiters:** Manage active tables, create and modify orders, and process payments.
*   **Customers:** Can self-register, book shift-based reservations, and place allergen-safe orders.

---

## Features & Functionality

### How does the system handle food allergies?
The system features **allergen-aware behavior**. Customer allergen profiles are stored in the **LDAP directory**. During the ordering process, the application compares the customer's profile against the allergens listed for each dish in the database to ensure safe service.

### Can customers book tables for any time?
Reservations are **shift-based**. The system validates table capacity to ensure, for example, that a party of four is not booked at a table meant for two. It also prevents booking conflicts by checking availability for specific dates and shifts.

### How is daily revenue calculated?
Managers can extract a **daily billing report**. This is implemented via a manager-facing query that aggregates order data persisted in the PostgreSQL database.

---

## Technical Infrastructure

### What technologies power the system?
The project relies on a modern stack including:
*   **Java:** The core programming language and "brain" of the app.
*   **PostgreSQL:** Used for persistent storage of operational data like orders and dishes.
*   **LDAP:** The authoritative source for user identity, roles, and allergen data.
*   **Docker:** Used for consistent deployment of infrastructure services.
*   **AWS:** Provides the cloud instances where the servers reside.

### How is data kept consistent between the app and the database?
The system uses a unique **Update-then-SELECT pattern**. Instead of relying on local memory, Java immediately runs a SELECT query after every write (INSERT/UPDATE/DELETE). This ensures the application always works with the **"single point of truth"** from the database, including any changes made by automatic database triggers.

### Why is an NFS server used?
The **NFS (Network File System)** server runs on a separate AWS machine to host the physical PostgreSQL data files. This design protects critical restaurant data; if the main application machine fails, the database files remain safe on the remote NFS host.

---

## Security & Access

### How do I connect to the system securely?
Access to the AWS infrastructure is secured via a **WireGuard VPN** (deployed via wg-easy). Users must connect to this encrypted tunnel to reach the PostgreSQL and LDAP services, which are not exposed directly to the public internet.

### How are user passwords protected?
The system follows enterprise security standards by encrypting all passwords in the LDAP directory using an **MD5 hash in base64 format**. Plain-text passwords are never stored.

### What happens if I provide invalid data to the database?
The database acts as a **second layer of protection**. It uses built-in **constraints and triggers** to refuse invalid data—such as a reservation for a non-existent table—before the Java application even processes it.