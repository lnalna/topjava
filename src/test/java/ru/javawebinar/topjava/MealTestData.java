package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            ((expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(),actual.getId())
                    && Objects.equals(expected.getCalories(),actual.getCalories())
                    && Objects.equals(expected.getDate(),actual.getDate())
                    && Objects.equals(expected.getTime(),actual.getTime())
                    && Objects.equals(expected.getDescription(),actual.getDescription())
                    )
            )

    );

    public static final int MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Meal MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL_ID+1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL_ID+2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL_ID+3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL_ID+4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL_ID+5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);


    public static final Meal ADMIN_MEAL = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2017, Month.JULY, 31, 13, 0), "Админ обед", 1500);

    public static final List<Meal> ADMIN_MEALS = Arrays.asList(ADMIN_MEAL,MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);



}
