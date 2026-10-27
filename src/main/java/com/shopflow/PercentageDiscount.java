package com.shopflow;

public class PercentageDiscount implements Discount {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double calculateDiscount(Order order) {
        return order.getTotal() * (percent / 100.0);
    }

    @Override
    public String getDescription() {
        return percent + "% OFF";
    }
}