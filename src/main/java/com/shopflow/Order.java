package com.shopflow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final Customer customer;
    private final LocalDate date;
    private final List<OrderItem> items;

    public Order(String id, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Order can never exist without a customer");
        }
        this.id = id;
        this.customer = customer;
        this.date = LocalDate.now();
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public LocalDate getDate() { return date; }
    public List<OrderItem> getItems() { return items; }

    // Total khud nikalega - yehi task ka rule hai
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }
}