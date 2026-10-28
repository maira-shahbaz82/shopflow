package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = OrderStatus.NEW;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public List<OrderItem> getItems() { return items; }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getLineTotal());
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public void printReceiptWithDiscount(Discount discount) {
        BigDecimal subtotal = calculateTotal();
        BigDecimal discountAmount = discount.calculateDiscount(this).setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalTotal = subtotal.subtract(discountAmount);
        if (finalTotal.compareTo(BigDecimal.ZERO) < 0) {
            finalTotal = BigDecimal.ZERO;
        }
        finalTotal = finalTotal.setScale(2, RoundingMode.HALF_UP);

        System.out.println("--- Receipt ---");
        System.out.println("Subtotal: Rs. " + subtotal);
        System.out.println("Discount: Rs. " + discountAmount);
        System.out.println("Final Total: Rs. " + finalTotal);
    }
}