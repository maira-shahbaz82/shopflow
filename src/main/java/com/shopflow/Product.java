package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(String id, String name, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }

    public BigDecimal getStockValue() {
        return price.multiply(BigDecimal.valueOf(stock)).setScale(2, RoundingMode.HALF_UP);
    }
}