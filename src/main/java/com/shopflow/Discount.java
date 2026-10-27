package com.shopflow;

public interface Discount {
    double calculateDiscount(Order order);
    String getDescription();
}