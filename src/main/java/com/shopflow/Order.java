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
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item) { items.add(item); }
    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }

    public double getTotal() {
        double total = 0;
        for (OrderItem i : items) {
            total += i.getProduct().getPrice() * i.getQuantity();
        }
        return total;
    }
}