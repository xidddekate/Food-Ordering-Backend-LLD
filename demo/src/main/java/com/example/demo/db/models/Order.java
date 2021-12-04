package com.example.demo.db.models;


import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Order {
    private final String orderId;
    private final List<Meal> meals;
    private final CustomerLocation customerLocation;
    private final DeliveryBoy deliveryBoy;

    public Order(String orderId, List<Meal> meals, CustomerLocation customerLocation, DeliveryBoy deliveryBoy) {
        this.orderId = orderId;
        this.meals = meals;
        this.customerLocation = customerLocation;
        this.deliveryBoy = deliveryBoy;
    }
}
