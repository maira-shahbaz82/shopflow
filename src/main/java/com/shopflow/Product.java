package com.shopflow;

public class Product {
    String code;
    String name;
    String category;
    double price;
    int stock;

    public Product(String code, String name, String category, double price, int stock) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public double getStockValue() {
        return price * stock;
    }
}
