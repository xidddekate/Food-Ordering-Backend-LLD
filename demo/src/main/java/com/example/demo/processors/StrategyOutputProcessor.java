package com.example.demo.processors;

import com.example.demo.db.models.Restaurant;

public interface StrategyOutputProcessor {
    public String process(Restaurant restaurant);
}
