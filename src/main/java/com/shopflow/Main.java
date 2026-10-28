package com.shopflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;git add .
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // FIX Day 13: Stop duplicates being added
    static boolean addProduct(Product newProduct) {
        if (products.contains(newProduct)) {
            System.out.println("REJECTED: Product with code " + newProduct.getId() + " already exists!");
            return false;
        }
        products.add(newProduct);
        System.out.println("Added: " + newProduct.getId());
        return true;
    }

    static boolean addCustomer(Customer newCustomer) {
        if (customers.contains(newCustomer)) {
            System.out.println("REJECTED: Customer with email " + newCustomer.getEmail() + " already exists!");
            return false;
        }
        customers.add(newCustomer);
        System.out.println("Added customer: " + newCustomer.getEmail());
        return true;
    }

    // Menu option: find duplicates already in data
    static void findDuplicateProducts() {
        System.out.println("\n--- Finding Duplicate Products ---");
        Set<String> seen = new HashSet<>();
        Set<String> duplicateCodes = new HashSet<>();

        for (Product p : products) {
            if (!seen.add(p.getId())) {
                duplicateCodes.add(p.getId());
            }
        }
        if (duplicateCodes.isEmpty()) {
            System.out.println("No duplicates found. Catalogue is clean!");
        } else {
            System.out.println("DUPLICATES FOUND for codes: " + duplicateCodes);
            for (Product p : products) {
                if (duplicateCodes.contains(p.getId())) {
                    System.out.println(" -> " + p);
                }
            }
        }
    }

    public static void main(String[] args) {
        // FIX: BigDecimal hamesha String se banao "500.00" - double se nahi
        addProduct(new Product("P01", "Ring", new BigDecimal("500.00"), 10));
        addProduct(new Product("P02", "Bracelet", new BigDecimal("800.00"), 5));
        addProduct(new Product("P03", "Necklace", new BigDecimal("1199.00"), 2));
        addProduct(new Product("P04", "Earring", new BigDecimal("0.10"), 100));

        customers.add(new Customer("C01", "Maira", "maira@test.com"));

        // --- Day 13 Reproduce Test ---
        System.out.println("\n--- Day 13 Reproduce Test ---");
        Product duplicateTest = new Product("P01", "Ring", new BigDecimal("500.00"), 10);
        System.out.println("Trying to add duplicate P01 again...");
        addProduct(duplicateTest); // Should be REJECTED

        // HashSet test - equals + hashCode contract
        Set<Product> set = new HashSet<>();
        set.add(new Product("KB-100", "Keyboard", new BigDecimal("1500.00"), 1));
        set.add(new Product("KB-100", "Keyboard", new BigDecimal("1600.00"), 1));
        System.out.println("HashSet size with two KB-100 objects (should be 1): " + set.size());

        // Test Customer duplicate
        System.out.println("\nTrying to add duplicate customer with same email...");
        addCustomer(new Customer("C02", "Maira Dup", "maira@test.com")); // Should be REJECTED

        // --- Simple Menu ---
        while (true) {
            System.out.println("\n--- ShopFlow Menu ---");
            System.out.println("1. Show all products");
            System.out.println("2. Show all customers");
            System.out.println("3. Find Duplicate Products");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    for (Product p : products) System.out.println(p);
                    break;
                case "2":
                    for (Customer c : customers) System.out.println(c);
                    break;
                case "3":
                    findDuplicateProducts();
                    break;
                case "0":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}