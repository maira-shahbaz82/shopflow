package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscount extends Discount {
    private BigDecimal percent;

    public PercentageDiscount(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal total = order.calculateTotal();
        return total.multiply(percent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}