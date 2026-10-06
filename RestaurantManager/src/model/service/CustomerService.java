package model.service;

import java.util.Set;
import model.enums.Allergen;
import model.user.Customer;

/**
 * Handles customer self-registration operations.
 */
public class CustomerService {
    private final LdapUserRepositoryProduction repo = new LdapUserRepositoryProduction();

    /**
     * Registers a new customer in LDAP if the uid does not already exist.
     *
     * @param id        Customer uid.
     * @param name      Customer display name.
     * @param password  Customer password.
     * @param allergens Customer allergy set.
     * @return {@code true} if registration succeeded, {@code false} otherwise.
     */
    public boolean autoregister(String id, String name, String password, Set<Allergen> allergens) {
        if (id == null || name == null || password == null)
            return false;
        if (repo.findByUid(id).isPresent())
            return false;
        Customer customer = new Customer(id, name, password, allergens);

        return repo.saveUser(customer);
    }
}