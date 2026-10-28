package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return id + " - " + name + " - " + price + " - stock: " + stock;
    }

    // BUSINESS RULE: A product is the same product if its productCode (id) is same.
    // Price, name, stock can change over time, but id is the identity.
    // e.g., KB-100 Wireless Keyboard should exist only once in catalogue.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}