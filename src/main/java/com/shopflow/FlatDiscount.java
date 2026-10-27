package com.shopflow;

public class FlatDiscount implements Discount {
    private double amount;

    public FlatDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double calculateDiscount(Order order) {
        // Rule: total kabhi 0 se neeche nahi jaye
        return Math.min(amount, order.getTotal());
    }

    @Override
    public String getDescription() {
        return "Rs. " + amount + " OFF";
    }
}