package com.shopflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        products.add(new Product("P001", "Laptop Dell XPS 13", "Electronics", 150000, 10));
        products.add(new Product("P002", "Wireless Mouse Logitech", "Electronics", 2000, 50));
        products.add(new Product("P003", "Office Chair Ergonomic", "Furniture", 12000, 15));
        products.add(new Product("P004", "Pinar Milk 0.5% Fat UHT 1L", "Grocery", 50, 100));
        products.add(new Product("P005", "USB-C Cable 2m", "Electronics", 500, 200));
        products.add(new Product("P006", "Notebook A4 Lined 100 Pages", "Stationery", 150, 300));
        products.add(new Product("P007", "Water Bottle Steel 750ml", "Kitchen", 800, 80));
        products.add(new Product("P008", "Desk Lamp LED", "Furniture", 2500, 25));

        while (true) {
            System.out.println("\n=== SHOPFLOW MENU ===");
            System.out.println("1. View All Products");
            System.out.println("2. Add a New Product");
            System.out.println("3. Search Product by Code");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine().trim();
            if (choice.isEmpty()) {
                System.out.println("Please enter a choice (1-4).");
                continue;
            }

            switch (choice) {
                case "1":
                    System.out.println("\n--- All Products ---");
                    for (Product p : products) {
                        System.out.println(p.code + " | " + p.name + " | " + p.category + " | Price: " + p.price + " | Stock: " + p.stock + " | Value: " + p.getStockValue());
                    }
                    break;
                case "2":
                    System.out.println("\n--- Add New Product ---");
                    System.out.print("Enter Product Code: ");
                    String code = scanner.nextLine().trim();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine().trim();
                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter Stock: ");
                    int stock = Integer.parseInt(scanner.nextLine().trim());
                    products.add(new Product(code, name, category, price, stock));
                    System.out.println("Product added successfully! -> " + name);
                    break;
                case "3":
                    System.out.print("\nEnter product code to search: ");
                    String searchCode = scanner.nextLine().trim();
                    boolean found = false;
                    for (Product p : products) {
                        if (p.code.equalsIgnoreCase(searchCode)) {
                            System.out.println("Found: " + p.code + " | " + p.name + " | " + p.category);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("No product found with code: " + searchCode);
                    break;
                case "4":
                    System.out.println("Exiting ShopFlow. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please choose 1, 2, 3 or 4.");
                    break;
            }
        }
    }
}