package com.shopflow;

public enum OrderStatus {
    NEW,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        if (this == NEW) {
            return next == PAID || next == CANCELLED;
        }
        if (this == PAID) {
            return next == SHIPPED || next == CANCELLED;
        }
        if (this == SHIPPED) {
            return next == DELIVERED;
        }
        return false;
    }
}