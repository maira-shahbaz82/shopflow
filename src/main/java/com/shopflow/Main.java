package com.shopflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int customerCounter = 1;
    static int orderCounter = 1;

    public static void main(String[] args) {
        products.add(new Product("P01", "Tea", 100, 10));
        products.add(new Product("P02", "Sugar", 200, 5));
        products.add(new Product("P03", "Milk", 150, 3));

        while (true) {
            System.out.println("\n--- Shopflow ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Create Order");
            System.out.println("3. View Orders");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) addCustomer();
            else if (choice == 2) createOrder();
            else if (choice == 3) viewOrders();
            else if (choice == 4) break;
        }
    }

    static void addCustomer() {
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        Customer c = new Customer("C" + customerCounter++, name);
        customers.add(c);
        System.out.println("Customer Added: " + c.getId());
    }

    static void createOrder() {
        if (customers.isEmpty()) {
            System.out.println("First add customer!");
            return;
        }
        System.out.print("Enter Customer ID: ");
        String cid = sc.nextLine();
        Customer cust = null;
        for (Customer c : customers) {
            if (c.getId().equals(cid)) { cust = c; break; }
        }
        if (cust == null) {
            System.out.println("Customer not found!");
            return;
        }
        Order order = new Order("O" + orderCounter++, cust);
        while (true) {
            System.out.println("\nProducts:");
            for (Product p : products) {
                System.out.println(p.getId() + " - " + p.getName() + " - Rs." + p.getPrice() + " - Stock:" + p.getStock());
            }
            System.out.print("Enter Product ID (or 'done'): ");
            String pid = sc.nextLine();
            if (pid.equalsIgnoreCase("done")) break;
            Product prod = null;
            for (Product p : products) {
                if (p.getId().equals(pid)) { prod = p; break; }
            }
            if (prod == null) {
                System.out.println("Product not found!");
                continue;
            }
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();
            if (prod.getStock() >= qty) {
                order.addItem(new OrderItem(prod, qty));
                prod.reduceStock(qty);
            } else {
                System.out.println("Not enough stock!");
            }
        }
        orders.add(order);
        System.out.println("Order Created: " + order.getId() + " Status: " + order.getStatus());
    }

    static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet!");
            return;
        }
        for (Order o : orders) {
            System.out.println("\n--- RECEIPT ---");
            System.out.println("Order ID: " + o.getId());
            System.out.println("Customer: " + o.getCustomer().getName());
            System.out.println("Status: " + o.getStatus());
            System.out.println("Items:");
            for (OrderItem item : o.getItems()) {
                System.out.println("  " + item.getProduct().getName() + " x " + item.getQuantity() + " = " + (item.getProduct().getPrice() * item.getQuantity()));
            }
            System.out.println("Total: Rs." + o.getTotal());
            System.out.println("---------------");
        }
    }
}