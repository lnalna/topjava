package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

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
        Map<String,Integer> mapCaloriesPerDay = new HashMap<>();


        for(UserMeal userMeal : mealList){

            String day = userMeal.getDateTime().toString().substring(0,10);

            if (mapCaloriesPerDay.containsKey(day)) {

                int tempCalories = mapCaloriesPerDay.get(day);
                tempCalories+=userMeal.getCalories();
                mapCaloriesPerDay.put(day, tempCalories);
            }
            else {
                mapCaloriesPerDay.put(day, userMeal.getCalories());
            }
        }


        for(UserMeal userMeal : mealList) {

            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {

                String day = userMeal.getDateTime().toString().substring(0,10);
                boolean exceed = false;

                if (mapCaloriesPerDay.containsKey(day))
                    exceed = mapCaloriesPerDay.get(day) > caloriesPerDay ? true : false;

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
