package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    // Map<userId, Map<mealId,meal>> repository
    private Map<Integer, Map<Integer,Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),1);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),1);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),1);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),2);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),2);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),2);
    }


    @Override
    public Meal save(Meal meal, int userId) {

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());

        } else if (get(meal.getId(), userId) == null)
            return null;

        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        meals.put(meal.getId(),meal);

        return meal;
    }

    @Override
    public boolean delete(int mealId, int userId) {

        Map<Integer,Meal> meals = repository.get(userId);

        return !meals.isEmpty() && meals.remove(mealId) != null;

    }

    @Override
    public Meal get(int mealId, int userId) {

        Map<Integer,Meal> meals = repository.get(userId);

        return meals.isEmpty() ? null: meals.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {

        return repository.get(userId).values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getMealsBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAll(userId)
                .stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }
}

