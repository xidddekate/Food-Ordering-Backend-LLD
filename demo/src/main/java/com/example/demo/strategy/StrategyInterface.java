package com.example.demo.strategy;

import com.example.demo.db.models.Order;
import com.example.demo.db.models.Restaurant;

import java.util.List;

public interface StrategyInterface {
    public String getOrderDeliverTime(Restaurant restaurant);
}
