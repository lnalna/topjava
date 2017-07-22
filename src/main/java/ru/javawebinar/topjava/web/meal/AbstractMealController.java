package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

/**
 * Created by Nikolay Lobachev.
 **
 */

public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Collection<Meal> getAll(){
        log.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int mealId){
        log.info("get{}",mealId,AuthorizedUser.id());
        return service.get(mealId,AuthorizedUser.id());
    }

    public Meal create(Meal meal){
        log.info("create {}",meal,AuthorizedUser.id());
        checkNew(meal);
        return service.save(meal,AuthorizedUser.id());
    }

    public void delete(int mealId){
        log.info("delete {}",mealId,AuthorizedUser.id());
        service.delete(mealId,AuthorizedUser.id());
    }
    public void update(Meal meal, int mealId){
        log.info("update{} with id={}",meal,mealId);
        checkIdConsistent(meal, mealId);
        service.update(meal,AuthorizedUser.id());
    }

    public List<MealWithExceed> getMealsBetweenDateTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        return MealsUtil.getFilteredWithExceeded(service.getBetweenDates(startDate,endDate,AuthorizedUser.id()),startTime,endTime,MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
