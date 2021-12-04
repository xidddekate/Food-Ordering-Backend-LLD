package com.example.demo.db.dao;

import com.example.demo.db.models.Order;
import com.example.demo.db.models.Restaurant;

import java.util.List;

public class RestaurantDao{
    public Restaurant getRestaurant(List<Order> orders) {
        return new Restaurant(7,0, orders, 180);
    }
}
