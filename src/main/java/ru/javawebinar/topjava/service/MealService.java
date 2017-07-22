package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;


public interface MealService {

    Meal save(Meal meal, int userId);

    void delete(int mealId, int userId) throws NotFoundException;

    Meal get(int mealId,int userId) throws NotFoundException;

    Meal update(Meal meal, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getMealsBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default Collection<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId){
        return getMealsBetweenDateTime(LocalDateTime.of(startDate, LocalTime.MIN),LocalDateTime.of(endDate,LocalTime.MAX),userId);
    }
}