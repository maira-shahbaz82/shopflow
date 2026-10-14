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
            System.out.println("\n--- ShopFlow Day 9 ---");
            System.out.println("1. Show Products");
            System.out.println("2. Register Customer");
            System.out.println("3. Place Order");
            System.out.println("4. View Order Receipt");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();
            if (choice == 1) showProducts();
            else if (choice == 2) registerCustomer();
            else if (choice == 3) placeOrder();
            else if (choice == 4) viewOrder();
            else if (choice == 0) break;
        }
    }

    static void showProducts() {
        System.out.println("\n--- Products ---");
        for (Product p : products) System.out.println(p);
    }

    static void registerCustomer() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        String id = "C" + String.format("%03d", customerCounter++);
        customers.add(new Customer(id, name, email, phone));
        System.out.println("Customer Registered: " + id + " - " + name);
    }

    static void placeOrder() {
        if (customers.isEmpty()) { System.out.println("No customers! First register (Option 2)"); return; }
        System.out.print("Enter Customer ID (e.g. C001): "); String cid = sc.nextLine();
        Customer cust = null;
        for (Customer c : customers) if (c.getId().equalsIgnoreCase(cid)) cust = c;
        if (cust == null) { System.out.println("Customer not found! Order can never exist without a customer"); return; }

        String oid = "ORD" + String.format("%03d", orderCounter++);
        Order order = new Order(oid, cust);
        System.out.println("Placing order for " + cust.getName() + ". Type 'done' to finish.");

        while (true) {
            System.out.print("Product Code (or done): "); String code = sc.nextLine();
            if (code.equalsIgnoreCase("done")) break;
            Product prod = null;
            for (Product p : products) if (p.getId().equalsIgnoreCase(code)) prod = p;
            if (prod == null) { System.out.println("Product not found"); continue; }

            System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
            if (qty <= 0) { System.out.println("❌ Zero / negative quantity not allowed"); continue; }
            if (qty > prod.getStock()) { System.out.println("❌ Not enough stock! Only " + prod.getStock() + " left. Refused."); continue; }

            OrderItem item = new OrderItem(prod, qty);
            order.addItem(item);
            prod.reduceStock(qty);
            System.out.println("✅ Added: " + prod.getName() + " x " + qty + " = " + item.getLineTotal());
        }
        if (order.getItems().isEmpty()) { System.out.println("Order empty, cancelled"); return; }
        orders.add(order);
        printReceipt(order);
    }

    static void viewOrder() {
        System.out.print("Order ID (e.g. ORD001): "); String oid = sc.nextLine();
        for (Order o : orders) if (o.getId().equalsIgnoreCase(oid)) { printReceipt(o); return; }
        System.out.println("Order not found");
    }

    static void printReceipt(Order o) {
        System.out.println("\n====== RECEIPT ======");
        System.out.println("Order: " + o.getId() + " | Date: " + o.getDate());
        System.out.println("Customer: " + o.getCustomer().getId() + " - " + o.getCustomer().getName());
        System.out.println("---------------------");
        double check = 0;
        for (OrderItem it : o.getItems()) {
            System.out.println(it.getProduct().getId() + " - " + it.getProduct().getName() + " x " + it.getQuantity() + " @ " + it.getUnitPrice() + " = " + it.getLineTotal());
            check += it.getLineTotal();
        }
        System.out.println("---------------------");
        System.out.println("Manual Sum: " + check);
        System.out.println("Order Total: " + o.getTotal());
        System.out.println("Verified: " + (check == o.getTotal() ? "YES ✅" : "NO ❌"));
        System.out.println("====================\n");
    }
}