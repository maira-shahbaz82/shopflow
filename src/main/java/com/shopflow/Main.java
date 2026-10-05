package com.shopflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("P001", "Laptop Dell XPS 13", "Electronics", 1200.00, 5),
                new Product("P002", "Wireless Mouse Logitech", "Electronics", 25.50, 50),
                new Product("P003", "Office Chair Ergonomic with Extra Long Name Test", "Furniture", 199.99, 10),
                new Product("P004", "Pinar Milk 0.5% Fat UHT 1L", "Grocery", 1.75, 100),
                new Product("P005", "USB-C Cable 2m", "Electronics", 9.99, 200),
                new Product("P006", "Notebook A4 Lined 100 Pages", "Stationery", 3.20, 150),
                new Product("P007", "Water Bottle Steel 750ml", "Kitchen", 15.00, 75),
                new Product("P008", "Desk Lamp LED", "Furniture", 45.75, 20)
        ));

        System.out.println("==========================================================================================");
        System.out.printf("%-10s %-35s %-15s %12s %8s%n", "CODE", "NAME", "CATEGORY", "PRICE", "STOCK");
        System.out.println("------------------------------------------------------------------------------------------");

        double totalStockValue = 0;
        for (Product p : products) {
            String priceStr = String.format("$%.2f", p.price);
            System.out.printf("%-10s %-35s %-15s %12s %8d%n", p.code, p.name, p.category, priceStr, p.stock);
            totalStockValue += p.getStockValue();
        }

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("Total Products: %d | Total Stock Value: $%.2f%n", products.size(), totalStockValue);
        System.out.println("==========================================================================================");
    }
}