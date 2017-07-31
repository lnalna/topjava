package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;


/**
 * Created by Nikolay Lobachev .
 */

@ContextConfiguration({
                "classpath:spring/spring-app.xml",
                "classpath:spring/spring-db.xml"
                })
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {


    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal mealActual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL,mealActual);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception{
        service.get(2,2);
    }

    @Test
    public void testSave() throws Exception {
        Meal createdMeal = new Meal(null,LocalDateTime.of(2017, Month.JULY, 31, 20, 0), "Новый ужин", 1000);
        service.save(createdMeal,ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(createdMeal,ADMIN_MEAL,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),service.getAll(ADMIN_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ADMIN_MEAL_ID,ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1),service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound()throws Exception{
        service.delete(1,1);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 31), LocalDate.of(2015, Month.MAY, 31), ADMIN_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6),
                service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 31, 19, 0),
                        LocalDateTime.of(2015, Month.MAY, 31, 20, 0),ADMIN_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(ADMIN_MEALS,service.getAll(ADMIN_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updatedMeal = new Meal(MEAL_ID,MEAL1.getDateTime(),"Обновленный завтрак Админа",1050);
        service.update(updatedMeal,ADMIN_ID);
        MATCHER.assertEquals(updatedMeal,service.get(MEAL_ID,ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        service.update(service.get(3,ADMIN_ID),ADMIN_ID);
    }

}