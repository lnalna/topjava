package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;


public interface MealService {

    Meal save(Meal meal, int userId);

    void delete(int mealId, int userId) throws NotFoundException;

    Meal get(int mealId,int userId) throws NotFoundException;

    Meal update(Meal meal, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getMealsBetweenDate(LocalDate startDate, LocalDate endDate, int userId);


}