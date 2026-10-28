package com.shopflow;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return id + " - " + name + " - " + email;
    }

    // BUSINESS RULE: Same email means same customer to the business.
    // Two Customer objects with same email (case-insensitive) are the same person.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return email.equalsIgnoreCase(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}