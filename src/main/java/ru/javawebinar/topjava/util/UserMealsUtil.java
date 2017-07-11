package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Nikolay Lobachev
 * 10.07.2017.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);


        List<UserMealWithExceed> userMealWithExceedList = getFilteredWithExceededStream(mealList, LocalTime.of(7,0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate,Integer> mapCaloriesPerDay = new HashMap<>();

        for(UserMeal userMeal : mealList) {
            mapCaloriesPerDay.put(userMeal.getDateTime().toLocalDate(), mapCaloriesPerDay.getOrDefault(userMeal.getDateTime().toLocalDate(),0)+userMeal.getCalories());
        }

        List<UserMealWithExceed> listUserMealWithExceeds = new ArrayList<>();

        for(UserMeal userMeal : mealList) {

            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                listUserMealWithExceeds.add(
                        new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), mapCaloriesPerDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return listUserMealWithExceeds;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mapCaloriesPerDay = mealList
                .stream()
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList
                .stream()
                .filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .map(u -> new UserMealWithExceed(u.getDateTime(),u.getDescription(),u.getCalories(),mapCaloriesPerDay.get(u.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
