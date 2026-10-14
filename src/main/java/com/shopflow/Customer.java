package com.shopflow;

public class Customer {
    private final String id;
    private String name;
    private String email;
    private String phone;

    public Customer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return id + " - " + name + " (" + email + ")";
    }
}