package com.example.demo.db.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Meal {
    private final String mealName;
    private final int mealCookingSlots;
    private final double mealCookingTimeInMinutes;
}
