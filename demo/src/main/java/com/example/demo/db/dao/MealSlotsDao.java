package com.example.demo.db.dao;

import com.example.demo.db.models.pairs.MealCookingSlotsTimePair;

import java.util.HashMap;

public class MealSlotsDao {

    public HashMap<String,MealCookingSlotsTimePair> getMealSlots(){
        HashMap<String, MealCookingSlotsTimePair> mealSlots = new HashMap<>();
        mealSlots.put("A",new MealCookingSlotsTimePair(1,17));
        mealSlots.put("M",new MealCookingSlotsTimePair(2,29));
        return mealSlots;
    }
}
