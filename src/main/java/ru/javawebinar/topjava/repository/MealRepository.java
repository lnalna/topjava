package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by Nikolay Lobachev.
 */
public interface MealRepository {

    Meal get(int id);
    Collection<Meal> getAll();
    void delete(int id);
    Meal createOrUpdate(Meal meal);
}
