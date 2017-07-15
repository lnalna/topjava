package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Nikolay Lobachev.
 */
public interface MealRepository {

    Meal get(int id);
    List<Meal> getAll();
    void delete(int id);
    Meal create(Meal meal);
}
