package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal Meal, int userId);

    boolean delete(int mealId, int userId);

    Meal get(int mealId,int userId);

    Collection<Meal> getAll(int userId);
}
