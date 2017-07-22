package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));



            System.out.println("\n--MealRestController--\n");
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            System.out.println("\n--MealRestController.getAll()--\n");
            mealRestController.getAll().forEach(System.out::println);

            System.out.println("\n--MealRestController.get(mealId)--\n");
            System.out.println(mealRestController.get(1).toString());

            System.out.println("\n--MealRestController.delete(mealId)--\n");
            mealRestController.delete(1);

            System.out.println("\n--MealRestController.create()--\n");
            System.out.println(mealRestController.create(new Meal(LocalDateTime.of(2017, Month.JULY, 22, 20, 0), "Ужин", 510)).toString());

            System.out.println("\n--MealRestController.update--\n");
            mealRestController.update(new Meal(LocalDateTime.of(2017, Month.JULY, 22, 14, 0), "Обед", 5100), 2);
            mealRestController.getAll().forEach(System.out::println);

            System.out.println("\n--MealRestController.getMealsBetweenDateTime--\n");
            List<MealWithExceed> mealsWithExceed = mealRestController.getMealsBetweenDateTime(
                    LocalDate.of(2017, Month.JULY,22), LocalTime.of(10,0),LocalDate.of(2017,Month.JULY,22),LocalTime.of(23,0));

            mealsWithExceed.forEach(System.out::println);
        }
    }
}
