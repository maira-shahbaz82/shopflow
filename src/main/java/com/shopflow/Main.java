package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        // FIX: BigDecimal hamesha String se banao "500.00" - double se nahi
        products.add(new Product("P01", "Ring", new BigDecimal("500.00"), 100));
        products.add(new Product("P02", "Bracelet", new BigDecimal("800.00"), 50));
        products.add(new Product("P03", "Necklace", new BigDecimal("1199.00"), 20));
        products.add(new Product("P04", "Earring", new BigDecimal("0.10"), 1000));

        customers.add(new Customer("C01", "Maira"));

        System.out.println("=== Day 12 - Money Bug Fix Test ===");

        // Test 1: Adding 0.10 ten times should be exactly 1.00 (Finance bug)
        Order test1 = new Order("TEST1", customers.get(0));
        test1.addItem(products.get(3), 10);
        System.out.println("Test 0.10 x 10 = " + test1.calculateTotal() + " -> Expected: 1.00");

        // Test 2: 10% discount on 1199 should be 119.90, not 119.89
        Order test2 = new Order("TEST2", customers.get(0));
        test2.addItem(products.get(2), 1);
        System.out.println("\nTest 1199 with 10% discount:");
        test2.printReceiptWithDiscount(new PercentageDiscount(new BigDecimal("10")));
        // Expected: Subtotal 1199.00, Discount 119.90, Final 1079.10

        // Test 3: No long decimal tail
        Order test3 = new Order("TEST3", customers.get(0));
        test3.addItem(products.get(0), 3);
        test3.addItem(products.get(1), 2);
        System.out.println("\nTest Stock Order Total (should not have .999999):");
        System.out.println("Total: " + test3.calculateTotal());

        // Stock Value Report
        System.out.println("\n--- Stock Value Report ---");
        BigDecimal totalStockValue = BigDecimal.ZERO;
        for (Product p : products) {
            BigDecimal val = p.getStockValue();
            System.out.println(p.getName() + ": Rs. " + val);
            totalStockValue = totalStockValue.add(val);
        }
        System.out.println("Total Stock Value: Rs. " + totalStockValue.setScale(2, RoundingMode.HALF_UP));

        System.out.println("\n=== All tests passed if no .999999 values ===");
    }
}