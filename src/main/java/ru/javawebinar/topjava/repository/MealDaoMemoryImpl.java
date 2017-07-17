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
public class MealDaoMemoryImpl implements MealDao {

    private Map<Integer,Meal> mealDaoMap = new ConcurrentHashMap<>();
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
        return mealDaoMap.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealDaoMap.values();
    }

    @Override
    public void delete(int id) {
        mealDaoMap.remove(id);
    }

    @Override
    public Meal createOrUpdate(Meal meal) {

        if(meal.isNullId()){
            meal.setId(mealId.incrementAndGet());
        }

        return mealDaoMap.put(meal.getId(), meal);
    }
}
