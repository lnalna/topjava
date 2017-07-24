package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal,userId);
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException{
        return checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException{
        checkNotFoundWithId(repository.delete(mealId,userId), mealId);
    }

    @Override
    public Meal get(int mealId, int userId) throws NotFoundException{
        return checkNotFoundWithId(repository.get(mealId,userId),mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Meal> getMealsBetweenDate(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.getMealsBetweenDate(startDate,endDate,userId);
    }


}