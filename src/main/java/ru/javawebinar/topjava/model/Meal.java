package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Created by Nikolay Lobachev.
 */

public class Meal {

    private Integer mealId;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;



    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.mealId = null;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(Integer mealId,LocalDateTime dateTime, String description, int calories) {
        this.mealId = mealId;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public boolean isNullMealId(){
        return mealId == null;
    }
}
