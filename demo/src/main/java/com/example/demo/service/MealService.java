package com.example.demo.service;

import com.example.demo.db.dao.MealSlotsDao;
import com.example.demo.db.models.pairs.MealCookingSlotsTimePair;

import java.util.HashMap;

public class MealService {
    public HashMap<String, MealCookingSlotsTimePair> getMealCookingSlots(){return new MealSlotsDao().getMealSlots();}
}
