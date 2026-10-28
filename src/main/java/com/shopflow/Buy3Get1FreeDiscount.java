package com.shopflow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buy3Get1FreeDiscount extends Discount {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        List<BigDecimal> allUnitPrices = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                allUnitPrices.add(item.getProduct().getPrice());
            }
        }
        Collections.sort(allUnitPrices);
        int freeCount = allUnitPrices.size() / 4;
        BigDecimal discount = BigDecimal.ZERO;
        for (int i = 0; i < freeCount; i++) {
            discount = discount.add(allUnitPrices.get(i));
        }
        return discount.setScale(2, RoundingMode.HALF_UP);
    }
}