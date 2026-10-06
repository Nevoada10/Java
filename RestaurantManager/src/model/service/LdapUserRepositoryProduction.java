/*
 * LdapUserRepository.java 2026-04-12
 *
 * Copyright 2026 Carles Conesa Mañosa, Uriel Neves Silva, Kadiatou Diallo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package model.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.Modification;
import com.unboundid.ldap.sdk.ModificationType;
import com.unboundid.ldap.sdk.ModifyRequest;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchScope;

import model.enums.Allergen;
import model.enums.Role;
import model.user.Customer;
import model.user.Manager;
import model.user.User;
import model.user.Waiter;

/**
 * src/model/service/LdapUserRepositoryProduction.java
 * 
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * @author Carles Conesa Mañosa (https://gitlab.com/carlesconesa34)
 * @author Kadiatou Diallo (https://gitlab.com/a243506dk)
 * 
 *         LDAP repository focused on user lookup, authentication, and
 *         persistence of role-based entries.
 */
public class LdapUserRepositoryProduction {

    /*
     * =============================================================================
     * ATTRIBUTES: These are the configuration properties and test UIDs used for
     * connecting to the LDAP server and performing operations.
     * =============================================================================
     */

    // Connection properties
    private String ldapHost = "32.193.176.159"; // IPV4 address of the LDAP server
    private int ldapPort = 389;
    private String bindDn = "cn=admin,dc=cku,dc=local"; // Base DN is used for binding (authentication)

    // Search properties
    private String baseDn = "dc=cku,dc=local"; // Base DN for searching users in the LDAP directory
    private String adminPassword = "admin123"; // Admin password for LDAP authentication
    private String managerOu = "ou=Managers,dc=cku,dc=local";
    private String waiterOu = "ou=Waiters,dc=cku,dc=local";
    private String customerOu = "ou=Customers,dc=cku,dc=local";

    /*
     * =============================================================================
     * IMPORTANT METHODS: These are the main methods that implement the core
     * functionality of the repository, such as finding users by UID, authenticating
     * users, and saving users to the LDAP directory.
     * =============================================================================
     */

    /**
     * Finds a user by their uid in the LDAP directory.
     * 
     * @param uid the unique identifier of the user to find
     * @return an Optional containing the User if found, or empty if not found or if
     *         an error occurs during the search
     */
    public Optional<User> findByUid(String uid) {
        try (LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort, bindDn, adminPassword)) {

            // Perform a search for the user with the specified uid under the base DN.
            SearchResult result = connection.search(
                    baseDn,
                    SearchScope.SUB, // Search scope SUB means we search the entire subtree under baseDn
                    "(uid=" + uid + ")");

            // GUARD CLAUSE: If no entries are found, return an empty Optional.
            if (result.getEntryCount() == 0) {
                return Optional.empty();
            }

            // If an entry is found...
            com.unboundid.ldap.sdk.Entry entry = result.getSearchEntries().get(0);
            // ... convert it to a User object and return it wrapped in an Optional.
            return mapEntryToUser(entry);

        } catch (LDAPException e) {
            System.err.println("❌ LDAP search failed: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Finds all LDAP user entries that can be mapped to domain users.
     *
     * @return List of mapped users; empty list when lookup fails.
     */
    public List<User> findAll() {
        try (LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort, bindDn, adminPassword)) {

            // Create a list to store users.
            List<User> users = new ArrayList<>();

            // Perform a search for all user entries under the base DN.
            SearchResult result = connection.search(
                    baseDn,
                    SearchScope.SUB, // Search scope SUB means we search the entire subtree under baseDn
                    "(objectClass=*)"); // Filter to match all entries

            // Iterate over each entry in the search result and convert it to a User object.
            for (com.unboundid.ldap.sdk.Entry entry : result.getSearchEntries()) {
                mapEntryToUser(entry).ifPresent(users::add);
            }

            return users;
        } catch (LDAPException e) {
            System.err.println("❌ LDAP search failed: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list if the search fails
        }
    }

    /**
     * Retrieves allergy preferences for a customer by uid.
     *
     * @param uid Customer uid.
     * @return Set of allergens configured for the customer; empty when unavailable.
     */
    public Set<Allergen> findCustomerAllergensByUid(String uid) {
        Set<Allergen> allergens = new HashSet<>();
        if (uid == null || uid.isBlank()) {
            return allergens;
        }

        Optional<User> userOpt = findByUid(uid);
        if (userOpt.isEmpty()) {
            return allergens;
        }

        User user = userOpt.get();
        if (user instanceof Customer customer && customer.getAllergens() != null) {
            allergens.addAll(customer.getAllergens());
        }

        return allergens;
    }

    /**
     * Authenticates a user by comparing the LDAP stored password with an MD5-encoded
     * version of the provided password.
     *
     * @param uid      User uid.
     * @param password Plain text password input.
     * @return {@code true} when credentials are valid, {@code false} otherwise.
     */
    public boolean authenticate(String uid, String password) {
        Optional<User> userOpt = findByUid(uid);

        if (password == null || password.isEmpty()) {
            System.out.println("❌ Authentication failed: Password cannot be null or empty.");
            return false; // Invalid input
        }

        if (userOpt.isEmpty()) {
            System.out.println("❌ Authentication failed: User not found for uid: " + uid);
            return false; // User not found
        }

        User user = userOpt.get();
        String encryptedInputPassword = encryptMD5(password);

        if (encryptedInputPassword == null) {
            System.out.println("❌ Authentication failed: Error encrypting password.");
            return false;
        }
        return user.getPassword().equals(encryptedInputPassword);

    }

    /**
     * Saves a new user to the LDAP directory. Validates the user object and checks
     * for duplicate uid before attempting to save. Determines the appropriate
     * organizational unit (OU) and gidNumber based on
     * 
     * @param user the User object to save
     * @return true if the user was successfully saved, false if validation fails,
     *         uid already exists, or an error occurs during the save operation
     */
    public boolean saveUser(User user) {
        if (user == null || user.getId() == null || user.getId().isBlank() || user.getName() == null
                || user.getName().isBlank() || user.getPassword() == null || user.getPassword().isBlank()) {
            return false;
        }

        if (findByUid(user.getId()).isPresent()) {
            System.out.println("❌ Save failed: uid already exists: " + user.getId());
            return false;
        }

        String usersOu;
        String gidNumber;
        String groupDn;
        if (user.getRole() == Role.MANAGER) {
            usersOu = "ou=Users," + managerOu;
            gidNumber = "1000";
            groupDn = "cn=Managers,ou=Groups," + managerOu;
        } else if (user.getRole() == Role.WAITER) {
            usersOu = "ou=Users," + waiterOu;
            gidNumber = "1001";
            groupDn = "cn=Waiters,ou=Groups," + waiterOu;
        } else if (user.getRole() == Role.CUSTOMER) {
            usersOu = "ou=Users," + customerOu;
            gidNumber = "1002";
            groupDn = "cn=Customers,ou=Groups," + customerOu;
        } else {
            return false;
        }

        String dn = "cn=" + user.getId() + "," + usersOu;
        String encryptedPassword = encryptMD5(user.getPassword());
        if (encryptedPassword == null) {
            return false;
        }

        int uidNumber = 2000;
        try (LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort, bindDn, adminPassword)) {
            SearchResult uidResult = connection.search(baseDn, SearchScope.SUB, "(uidNumber=*)", "uidNumber");
            for (com.unboundid.ldap.sdk.Entry entry : uidResult.getSearchEntries()) {
                String value = entry.getAttributeValue("uidNumber");
                if (value != null) {
                    try {
                        int parsed = Integer.parseInt(value);
                        if (parsed >= uidNumber) {
                            uidNumber = parsed + 1;
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignore malformed uidNumber values from external entries.
                    }
                }
            }

            com.unboundid.ldap.sdk.Entry newEntry = new com.unboundid.ldap.sdk.Entry(dn);
            newEntry.addAttribute("objectClass", "inetOrgPerson", "posixAccount", "top");
            newEntry.addAttribute("cn", user.getId());
            newEntry.addAttribute("givenName", user.getName());
            newEntry.addAttribute("sn", user.getName());
            newEntry.addAttribute("uid", user.getId());
            newEntry.addAttribute("uidNumber", String.valueOf(uidNumber));
            newEntry.addAttribute("gidNumber", gidNumber);
            newEntry.addAttribute("homeDirectory", "/home/" + user.getId());
            newEntry.addAttribute("loginShell", "/bin/bash");
            newEntry.addAttribute("userPassword", encryptedPassword);

            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                Set<Allergen> allergens = customer.getAllergens();
                if (allergens != null && !allergens.isEmpty()) {
                    StringBuilder description = new StringBuilder();
                    for (Allergen allergen : allergens) {
                        if (description.length() > 0) {
                            description.append(",");
                        }
                        description.append(allergen.name());
                    }
                    newEntry.addAttribute("description", description.toString());
                }
            }

            connection.add(newEntry);

            // Keep group membership in sync with gidNumber.
            ModifyRequest addToGroup = new ModifyRequest(
                    groupDn,
                    new Modification(ModificationType.ADD, "memberUid", user.getId()));
            connection.modify(addToGroup);

            return true;
        } catch (LDAPException e) {
            System.err.println("❌ LDAP save failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a user entry and attempts to remove their memberUid from role groups.
     *
     * @param uid User uid to delete.
     * @return {@code true} if deletion succeeds, {@code false} otherwise.
     */
    public boolean deleteUser(String uid) {
        if (uid == null || uid.isBlank()) {
            return false;
        }

        try (LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort, bindDn, adminPassword)) {
            SearchResult result = connection.search(
                    baseDn,
                    SearchScope.SUB,
                    "(uid=" + uid + ")");

            if (result.getEntryCount() == 0) {
                System.out.println("❌ Delete failed: user not found for uid: " + uid);
                return false;
            }

            com.unboundid.ldap.sdk.Entry userEntry = result.getSearchEntries().get(0);
            String dn = userEntry.getDN();

            // Remove user from role group first, then delete user entry.
            String groupDn = null;
            if (dn.contains("ou=Managers")) {
                groupDn = "cn=Managers,ou=Groups," + managerOu;
            } else if (dn.contains("ou=Waiters")) {
                groupDn = "cn=Waiters,ou=Groups," + waiterOu;
            } else if (dn.contains("ou=Customers")) {
                groupDn = "cn=Customers,ou=Groups," + customerOu;
            }

            if (groupDn != null) {
                try {
                    ModifyRequest removeFromGroup = new ModifyRequest(
                            groupDn,
                            new Modification(ModificationType.DELETE, "memberUid", uid));
                    connection.modify(removeFromGroup);
                } catch (LDAPException e) {
                    // Continue with delete even if memberUid was missing in group.
                    if (e.getResultCode() != ResultCode.NO_SUCH_ATTRIBUTE) {
                        throw e;
                    }
                }
            }

            connection.delete(dn);
            return true;
        } catch (LDAPException e) {
            System.err.println("❌ LDAP delete failed: " + e.getMessage());
            return false;
        }
    }

    /*
     * =============================================================================
     * HELPER METHODS: These are private methods that assist the main methods in
     * performing specific tasks, such as mapping LDAP entries to User objects.
     * =============================================================================
     */

    /**
     * Converts an LDAP Entry to a User object based on its attributes and DN.
     * It checks for required attributes (uid, cn, userPassword) and determines
     * the user's role based on the organizational unit (ou) in the DN. If the
     * entry is valid, it returns an Optional containing the User; otherwise, it
     * returns an empty Optional.
     *
     * @param entry the LDAP Entry to convert
     * @return Optional containing the User if conversion is successful, or
     *         empty if the entry is invalid
     */
    private Optional<User> mapEntryToUser(com.unboundid.ldap.sdk.Entry entry) {
        // Read all required LDAP attributes for our domain model.
        String id = entry.getAttributeValue("uid");
        String name = entry.getAttributeValue("givenName");
        String password = entry.getAttributeValue("userPassword");
        Role role;

        // If any required field is missing, ignore this entry.
        if (id == null || name == null || password == null) {
            return Optional.empty();
        }

        String dn = entry.getDN();

        if (dn.contains("ou=Managers")) {
            role = Role.MANAGER;
            return Optional.of(new Manager(id, name, password, role));
        } else if (dn.contains("ou=Waiters")) {
            role = Role.WAITER;
            return Optional.of(new Waiter(id, name, password, role));
        } else if (dn.contains("ou=Customers")) {
            role = Role.CUSTOMER;
            Set<Allergen> allergens = new HashSet<>();
            String description = entry.getAttributeValue("description");
            if (description != null) {
                for (String part : description.split(",")) {
                    allergens.add(Allergen.valueOf(part.trim().toUpperCase()));
                }
            }
            return Optional.of(new Customer(id, name, password, allergens));
        } else {
            // If the entry's DN does not match any known role OU, ignore it.
            return Optional.empty();
        }
    }

    /**
     * Encrypts a password using MD5 hash in the format required by LDAP.
     * Computes MD5 hash, base64 encodes it, prepends {MD5}, then base64 encodes the
     * whole string.
     * 
     * @param password the plain text password
     * @return the encrypted password string, or null if encryption fails
     */
    private String encryptMD5(String password) {
        /*
         * Step by step, dummed down in plain language:
         * 
         * 1. Use Java's MessageDigest to the MD5 hash of the password.
         * 2. Base64 encode the MD5 hash.
         * 3. Prepend "{MD5}" to the base64-encoded hash to match LDAP's expected
         * format.
         */
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            String md5Base64 = Base64.getEncoder().encodeToString(hash);
            return "{MD5}" + md5Base64;
        } catch (Exception e) {
            System.out.println("Error encrypting password: " + e.getMessage());
            return null;
        }
    }
}
