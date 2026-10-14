package com.shopflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    viewAllProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    searchProduct();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting ShopFlow. Bye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please enter 1-4.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== ShopFlow Menu =====");
        System.out.println("1. View All Products");
        System.out.println("2. Add Product");
        System.out.println("3. Search Product");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private static void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        printHeader();
        for (Product p : products) {
            printRow(p);
        }
    }

    private static void searchProduct() {
        String keyword = readNonEmptyString(scanner, "Enter name or code to search: ");
        boolean found = false;
        printHeader();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    p.getCode().toLowerCase().contains(keyword.toLowerCase())) {
                printRow(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No product found for: " + keyword);
        }
    }

    private static void addProduct() {
        System.out.println("\n--- Add New Product ---");
        String code = readNonEmptyString(scanner, "Enter Code: ");

        for (Product p : products) {
            if (p.getCode().equalsIgnoreCase(code)) {
                System.out.println("❌ Code '" + code + "' already exists. Duplicate not allowed.");
                return;
            }
        }

        String name = readNonEmptyString(scanner, "Enter Name: ");
        String category = readNonEmptyString(scanner, "Enter Category: ");
        double price = readPositivePrice(scanner, "Enter Price: ");
        int stock = readNonNegativeStock(scanner, "Enter Stock: ");

        Product newProduct = new Product(code, name, category, price, stock);
        products.add(newProduct);
        System.out.println("✅ Product added successfully! -> " + newProduct);
    }

    private static void printHeader() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-15s | %-10s | %-8s | %-5s | %-10s |\n", "Code", "Name", "Category", "Price", "Stock", "Value");
        System.out.println("--------------------------------------------------------------------------------");
    }

    private static void printRow(Product p) {
        System.out.printf("| %-8s | %-15s | %-10s | %-8.2f | %-5d | %-10.2f |\n",
                p.getCode(), p.getName(), p.getCategory(), p.getPrice(), p.getStock(), p.getStockValue());
    }

    private static String readNonEmptyString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ Cannot be empty. Please type again.");
                continue;
            }
            return input;
        }
    }

    private static double readPositivePrice(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().replace(",", "");
            try {
                double price = Double.parseDouble(input);
                if (price <= 0) {
                    System.out.println("❌ Price must be greater than 0.");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Enter like 1200 or 1200.50");
            }
        }
    }

    private static int readNonNegativeStock(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().replace(",", "");
            try {
                int stock = Integer.parseInt(input);
                if (stock < 0) {
                    System.out.println("❌ Stock cannot be negative.");
                    continue;
                }
                return stock;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid stock. Enter whole number like 20");
            }
        }
    }
}