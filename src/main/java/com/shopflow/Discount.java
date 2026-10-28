package com.shopflow;

import java.math.BigDecimal;

public abstract class Discount {
    public abstract BigDecimal calculateDiscount(Order order);
}