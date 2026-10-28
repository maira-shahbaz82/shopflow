package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FlatDiscount extends Discount {
    private BigDecimal amount;

    public FlatDiscount(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateDiscount(Order order) {
        return amount;
    }
}