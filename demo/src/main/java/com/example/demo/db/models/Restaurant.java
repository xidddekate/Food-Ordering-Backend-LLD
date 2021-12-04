package com.example.demo.db.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Restaurant {
    private final List<Order> orders;
    private final int maxCookingSlots;
    private int currentCookingSlots;
    private final double maxDeliveryTimeInMinutes;

    public Restaurant(int maxCookingSlots, int currentCookingSlots, List<Order> orders, int maxDeliveryTimeInMinutes) {
        this.maxCookingSlots = maxCookingSlots;
        this.currentCookingSlots = currentCookingSlots;
        this.orders = orders;
        this.maxDeliveryTimeInMinutes = maxDeliveryTimeInMinutes;
    }
}
