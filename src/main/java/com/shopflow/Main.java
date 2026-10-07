package com.shopflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> products = new ArrayList<>();

    // MAIN AB SIRF 15 LINES KA SUMMARY HAI
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": printProducts(); break;
                case "2": addProduct(); break;
                case "3": searchProduct(); break;
                case "4": running = false; break;
                default: System.out.println("Invalid option. Choose 1-4");
            }
        }
        System.out.println("Goodbye!");
    }

    // 1. Menu dikhana
    private static void showMenu() {
        System.out.println("\n=== SHOPFLOW MENU ===");
        System.out.println("1. View All Products");
        System.out.println("2. Add a New Product");
        System.out.println("3. Search Product by Code");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    // 2. TABLE FORMATTING - SIRF 1 JAGAH
    // Agar column width change karni hai to sirf yahan karo
    private static void printHeader() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-10s | %-8s |%n", "CODE", "NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println("--------------------------------------------------------------------------------");
    }

    private static void printRow(Product p) {
        System.out.printf("| %-10s | %-20s | %-15s | %-10.2f | %-8d |%n",
                p.getCode(), p.getName(), p.getCategory(), p.getPrice(), p.getStock());
    }

    // 3. Products print karna
    private static void printProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        printHeader();
        for (Product p : products) {
            printRow(p);
        }
        System.out.println("--------------------------------------------------------------------------------");
        printSummary();
    }

    // 4. Product add karna
    private static void addProduct() {
        System.out.print("Enter Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter Stock: ");
        int stock = Integer.parseInt(scanner.nextLine().trim());

        products.add(new Product(code, name, category, price, stock));
        System.out.println("Product added successfully! -> " + name);
    }

    // 5. Search karna
    private static void searchProduct() {
        System.out.print("\nEnter product code to search: ");
        String searchCode = scanner.nextLine().trim();
        boolean found = false;

        for (Product p : products) {
            if (p.getCode().equalsIgnoreCase(searchCode)) {
                printHeader();
                printRow(p);
                System.out.println("--------------------------------------------------------------------------------");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found with code: " + searchCode);
        }
    }

    // 6. Summary
    private static void printSummary() {
        System.out.println("Total products: " + products.size());
    }
}