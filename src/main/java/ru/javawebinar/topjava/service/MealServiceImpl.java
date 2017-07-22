package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
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
    public Meal update(Meal meal, int userId) {
        return repository.save(meal,userId);
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
    public Collection<Meal> getMealsBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getMealsBetweenDateTime(startDateTime,endDateTime,userId);
    }
}