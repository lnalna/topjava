package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nikolay Lobachev.
 */
public class MealRepositoryImpl implements MealRepository {

    private Map<Integer,Meal> mealRepository = new ConcurrentHashMap<>();
    private AtomicInteger mealId = new AtomicInteger(0);


    {
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
          createOrUpdate(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }
    @Override
    public Meal get(int id) {
        return mealRepository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealRepository.values();
    }

    @Override
    public void delete(int id) {
        mealRepository.remove(id);
    }

    @Override
    public Meal createOrUpdate(Meal meal) {

        if(meal.isNullMealId()){
            meal.setMealId(mealId.incrementAndGet());
        }

        return mealRepository.put(meal.getMealId(), meal);
    }
}
