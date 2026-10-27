package com.shopflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int orderCounter = 1;

    public static void main(String[] args) {
        // Seed data
        products.add(new Product("P01", "Rice", 500, 100));
        products.add(new Product("P02", "Sugar", 300, 100));
        products.add(new Product("P03", "Oil", 400, 50));
        customers.add(new Customer("C01", "Ali"));

        while (true) {
            System.out.println("\n--- Shopflow Menu ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Create Order");
            System.out.println("3. View Orders");
            System.out.println("4. Apply Discount to Order");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addCustomer();
            else if (choice == 2) createOrder();
            else if (choice == 3) viewOrders();
            else if (choice == 4) applyDiscountToOrder();
            else if (choice == 5) break;
            else System.out.println("Invalid choice!");
        }
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        customers.add(new Customer(id, name));
        System.out.println("Customer added!");
    }

    static void createOrder() {
        System.out.print("Enter Customer ID: ");
        String cid = scanner.nextLine();
        Customer cust = null;
        for (Customer c : customers) if (c.getId().equals(cid)) cust = c;
        if (cust == null) { System.out.println("Customer not found!"); return; }

        Order order = new Order("ORD" + orderCounter++, cust);
        while (true) {
            System.out.print("Enter Product ID (or 'done'): ");
            String pid = scanner.nextLine();
            if (pid.equals("done")) break;
            Product prod = null;
            for (Product p : products) if (p.getId().equals(pid)) prod = p;
            if (prod == null) { System.out.println("Product not found!"); continue; }
            System.out.print("Enter Quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            if (qty > prod.getStock()) { System.out.println("Not enough stock!"); continue; }
            order.addItem(new OrderItem(prod, qty));
            prod.reduceStock(qty);
        }
        orders.add(order);
        System.out.println("Order created: " + order.getId() + " Total: " + order.getTotal());
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
                System.out.println("  " + item.getProduct().getName() + " x " + item.getQuantity());
            }
            System.out.println("Total: Rs." + o.getTotal());
            System.out.println("----------------");
        }
    }

    static void applyDiscountToOrder() {
        if (orders.isEmpty()) { System.out.println("No orders yet!"); return; }
        System.out.print("Enter Order ID: ");
        String oid = scanner.nextLine();
        Order selected = null;
        for (Order o : orders) if (o.getId().equals(oid)) selected = o;
        if (selected == null) { System.out.println("Order not found!"); return; }

        System.out.println("1. 10% OFF");
        System.out.println("2. Rs. 500 OFF");
        System.out.println("3. Buy 3 Get 1 Free");
        System.out.println("4. Test 200% (check total never negative)");
        System.out.print("Enter choice: ");
        int dChoice = scanner.nextInt();
        scanner.nextLine();

        Discount d = null;
        if (dChoice == 1) d = new PercentageDiscount(10);
        else if (dChoice == 2) d = new FlatDiscount(500);
        else if (dChoice == 3) d = new Buy3Get1FreeDiscount();
        else if (dChoice == 4) d = new PercentageDiscount(200);

        if (d != null) {
            selected.printReceiptWithDiscount(d);
        }
    }
}