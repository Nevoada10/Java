Hoje • 14:27
Create an user manual of this program. Return an .md
CKU Restaurant Management System - User Manual
The CKU Restaurant Management System is a Java-based console application designed to unify restaurant workflows, including user management, table reservations, and order processing, into a single role-based interface.
1. Getting Started
1.1 Access and Infrastructure
To use the system in a production environment, you must first connect to the secure VPN (WireGuard) to access the AWS infrastructure where the LDAP and PostgreSQL servers reside. Once the tunnel is active, the application can communicate with the centralized database and user directory.
1.2 Starting the Program
Run the application through the terminal. The Main entry point will initialize the necessary services and present the primary menu. From here, you can choose to:
Login: For existing Managers, Waiters, and Customers.
Self-Registration: For new Customers only.

--------------------------------------------------------------------------------
2. User Roles and Menus
The system is strictly role-based, meaning your menu options will change depending on your account type (Manager, Waiter, or Customer).
2.1 Manager Console
The Manager oversees the entire operation and has the highest level of control.
User Management: Add or remove waiters and customers from the LDAP directory.
Dish & Menu Maintenance: Create, modify, or delete dishes and bundled menus in the database.
Reservation Oversight: View and manage all reservations across the system.
Daily Billing: Generate revenue reports to see the total earnings for the current day.
2.2 Waiter Console
Waiters handle the active service flow and table management.
Table Management: Assign, reassign, or unassign tables based on reservation context.
Order Creation: Create new orders for active tables, including both individual dishes and set menus.
Order Modification: Add or remove items from an existing order as long as it has not been paid.
Payment Processing: Mark orders as PAID. This action automatically triggers a state change in the database to complete the associated reservation.
2.3 Customer Console
Customers can manage their own dining experience through a dedicated portal.
Self-Registration: New users can create an account, setting their password and identifying any allergens.
Shift-Based Reservations: Book a table for specific dates and shifts. The system automatically validates table capacity (e.g., a party of 4 cannot book a table for 2).
Allergen-Aware Ordering: When browsing the menu, the system uses your LDAP-stored allergen profile to filter or flag dishes that are unsafe for you to consume.

--------------------------------------------------------------------------------
3. Key Operational Rules
Allergen Safety: Customer allergen profiles are stored in LDAP and compared against dish data during the ordering process to ensure safe service.
Reservation States: Reservations move through states like PENDING, ACTIVE, and COMPLETED. A reservation automatically becomes ACTIVE when an order is first placed and COMPLETED when the order is marked as paid.
Data Consistency: The system uses an Update-then-SELECT pattern. This means every time you save a change (like a new order), the system immediately re-reads the data from the database to ensure you are seeing the most accurate, "final" version of the record.
Security: All user passwords are encrypted (MD5 hash) before being stored in the LDAP directory.

--------------------------------------------------------------------------------
4. Troubleshooting
If the application cannot connect to the services:
Verify your VPN connection is active.
Ensure the PostgreSQL and LDAP servers are running on the AWS instances.
Check that your user credentials are correct; the system will deny access if LDAP authentication fails.
