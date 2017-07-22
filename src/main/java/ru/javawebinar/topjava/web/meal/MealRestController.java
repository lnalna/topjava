package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {

    // private MealService service;

    @Override
    public Collection<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int mealId) {
        return super.get(mealId);
    }

    @Override
    public List<MealWithExceed> getMealsBetweenDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return super.getMealsBetweenDateTime(startDate, startTime, endDate, endTime);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void delete(int mealId) {
        super.delete(mealId);
    }

    @Override
    public void update(Meal meal, int mealId) {
        super.update(meal, mealId);
    }
}