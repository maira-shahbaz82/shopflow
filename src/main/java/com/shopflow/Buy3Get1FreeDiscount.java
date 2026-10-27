package com.shopflow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Buy3Get1FreeDiscount implements Discount {

    @Override
    public double calculateDiscount(Order order) {
        List<Double> allPrices = new ArrayList<>();

        for (OrderItem item : order.getItems()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                allPrices.add(item.getProduct().getPrice());
            }
        }

        if (allPrices.size() < 4) return 0;

        allPrices.sort(Comparator.naturalOrder());
        int freeCount = allPrices.size() / 4;
        double discount = 0;
        for (int i = 0; i < freeCount; i++) {
            discount += allPrices.get(i);
        }
        return discount;
    }

    @Override
    public String getDescription() {
        return "Buy 3 Get 1 Free (Cheapest)";
    }
}