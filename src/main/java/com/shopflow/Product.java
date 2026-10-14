package com.shopflow;

public class Product {
    private final String code; // FINAL = ab kabhi change nahi hoga, Task ka main point
    private String name;
    private String category;
    private double price;
    private int stock;

    // Constructor jo code, name, price ke bina banna impossible kar de
    public Product(String code, String name, double price) {
        if (code == null || code.trim().isEmpty()) throw new IllegalArgumentException("Code required");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name required");
        if (price <= 0) throw new IllegalArgumentException("Price must be > 0");
        this.code = code.trim();
        this.name = name.trim();
        this.price = price;
        this.category = "General";
        this.stock = 0;
    }

    public Product(String code, String name, String category, double price, int stock) {
        this(code, name, price);
        this.category = category;
        this.stock = stock;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // Setters - Code ka setter NAI hai
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { if(price > 0) this.price = price; }
    public void setStock(int stock) { if(stock >= 0) this.stock = stock; }

    // Behaviour inside class - Sir ka hint
    public double getStockValue() {
        return price * stock;
    }

    // Debugging ke liye toString
    @Override
    public String toString() {
        return "Product{code='" + code + "', name='" + name + "', category='" + category + "', price=" + price + ", stock=" + stock + ", value=" + getStockValue() + "}";
    }
}