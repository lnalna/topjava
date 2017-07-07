package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
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
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> listUserMealWithExceeds = new ArrayList<>();
        Map<LocalDate,Integer> mapCaloriesPerDay = new HashMap<>();


        for(UserMeal userMeal : mealList){

            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            int tempCalories = mapCaloriesPerDay.getOrDefault(localDate,0);
            tempCalories+=userMeal.getCalories();
            mapCaloriesPerDay.put(localDate, tempCalories);
        }


        for(UserMeal userMeal : mealList) {

            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {

                LocalDate localDate = userMeal.getDateTime().toLocalDate();

                boolean exceed = mapCaloriesPerDay.getOrDefault(localDate,0) > caloriesPerDay ? true : false;

                listUserMealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed));
            }
        }
//for testing , must be deleted
        for(UserMealWithExceed userMealWithExceeds : listUserMealWithExceeds ){

            System.out.println(userMealWithExceeds.getDateTime()+" - "+userMealWithExceeds.getDescription()+" - "+userMealWithExceeds.getCalories()+" - "+userMealWithExceeds.isExceed());
        }

        // TODO return filtered list with correctly exceeded field
        return listUserMealWithExceeds;
    }
}
