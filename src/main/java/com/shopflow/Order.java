package com.shopflow;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.values()[0];
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void addItem(OrderItem item) { this.items.add(item); }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void printReceiptWithDiscount(Discount discount) {
        double subtotal = this.getTotal();
        double discountAmount = 0;
        String desc = "No Discount";
        if (discount != null) {
            discountAmount = discount.calculateDiscount(this);
            desc = discount.getDescription();
            if (discountAmount > subtotal) discountAmount = subtotal;
            if (discountAmount < 0) discountAmount = 0;
        }
        double finalTotal = subtotal - discountAmount;
        System.out.println("\n-------- RECEIPT --------");
        System.out.println("Order ID: " + this.id);
        System.out.println("-------------------------");
        for (OrderItem item : items) {
            System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + " = Rs. " + (item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("-------------------------");
        System.out.println("Subtotal: Rs. " + subtotal);
        System.out.println("Discount Applied (" + desc + "): -Rs. " + discountAmount);
        System.out.println("Final Total: Rs. " + finalTotal);
        System.out.println("-------------------------\n");
    }
}