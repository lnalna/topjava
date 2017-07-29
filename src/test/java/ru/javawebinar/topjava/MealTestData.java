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
                    ))

    );

    public static final int MEAL1_ID = START_SEQ + 2;

   // public static final Meal MEAL1 = new Meal(100002, LocalDateTime.of(2015, Month.MAY, 28, 10, 0), "Завтрак", 500);
  //  public static final Meal MEAL2 = new Meal(100003, LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 1000);
  //  public static final Meal MEAL3 = new Meal(100004, LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 500);
  //  public static final Meal MEAL4 = new Meal(100005, LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 500);
  //  public static final Meal MEAL5 = new Meal(100006, LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 1000);
  //  public static final Meal MEAL6 = new Meal(100007, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 510);
    public static final Meal MEAL7 = new Meal(100008, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL8 = new Meal(100009, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL9 = new Meal(100010, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL10 = new Meal(100011, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL11 = new Meal(100012, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL12 = new Meal(100013, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    //public static final List<Meal> USER_MEALS = Arrays.asList(MEAL12,MEAL11,MEAL10,MEAL9,MEAL8,MEAL7,MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    public static final List<Meal> USER_MEALS = Arrays.asList(MEAL12, MEAL11, MEAL10, MEAL9, MEAL8, MEAL7);



}
