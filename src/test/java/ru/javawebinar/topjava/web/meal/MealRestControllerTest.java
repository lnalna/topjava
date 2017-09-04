package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.web.RootControllerTest.ADMIN_ID;
import static ru.javawebinar.topjava.web.RootControllerTest.USER_ID;


public class MealRestControllerTest extends AbstractControllerTest {

    private static final String TEST_REST_URL = MealRestController.REST_URL+'/';
    private static final String DATE_TIME_BETWEEN = "between?startDateTime=2015-05-31T10:00&endDateTime=2015-06-01T21:00";

    @Autowired
    private MealService mealService;


    @Test
    public void testCreateMealUri() throws Exception {
        AuthorizedUser.setId(USER_ID);
        Meal createdMeal = getCreated();
        ResultActions resultActions = mockMvc.perform(post(MealRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdMeal)));

        Meal returnedMeal = MATCHER.fromJsonAction(resultActions);
        createdMeal.setId(returnedMeal.getId());

        MATCHER.assertEquals(createdMeal,returnedMeal);
        MATCHER.assertListEquals(Arrays.asList(createdMeal,MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1) , mealService.getAll(USER_ID));
    }

    @Test
    public void testCreateMealUriAdmin() throws Exception {
        AuthorizedUser.setId(ADMIN_ID);
        Meal createdMeal = getCreated();
        ResultActions resultActions = mockMvc.perform(post(MealRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdMeal)));

        Meal returnedMeal = MATCHER.fromJsonAction(resultActions);
        createdMeal.setId(returnedMeal.getId());

        MATCHER.assertEquals(createdMeal,returnedMeal);
        MATCHER.assertListEquals(Arrays.asList(ADMIN_MEAL2,createdMeal,ADMIN_MEAL1) , mealService.getAll(ADMIN_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        AuthorizedUser.setId(USER_ID);
        Meal updatedMeal = getUpdated();

        mockMvc.perform(put(TEST_REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedMeal)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updatedMeal, mealService.get(MEAL1_ID,USER_ID));

    }

    @Test
    public void testUpdateAdmin() throws Exception {
        AuthorizedUser.setId(ADMIN_ID);
        Meal updatedMeal = getUpdatedAdmin();

        mockMvc.perform(put(TEST_REST_URL + ADMIN_MEAL_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedMeal)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updatedMeal, mealService.get(ADMIN_MEAL_ID,ADMIN_ID));

    }

    @Test
    public void testGet() throws Exception {
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(get(TEST_REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testGetAdmin() throws Exception{
        AuthorizedUser.setId(ADMIN_ID);
        mockMvc.perform(get(TEST_REST_URL + ADMIN_MEAL_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN_MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(delete(TEST_REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2), mealService.getAll(USER_ID));
    }

    @Test
    public void testDeleteAdmin() throws Exception {
        AuthorizedUser.setId(ADMIN_ID);
        mockMvc.perform(delete(TEST_REST_URL + ADMIN_MEAL_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(ADMIN_MEAL2), mealService.getAll(ADMIN_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(get(MealRestController.REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_EXCEED.contentListMatcher(MealsUtil.getWithExceeded(MEALS, AuthorizedUser.getCaloriesPerDay())));
    }

    @Test
    public void testGetAllAdmin() throws Exception {
        AuthorizedUser.setId(ADMIN_ID);
        mockMvc.perform(get(MealRestController.REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_EXCEED.contentListMatcher(MealsUtil.getWithExceeded(ADMIN_MEALS, AuthorizedUser.getCaloriesPerDay())));
    }

    @Test
    public void testGetBetween() throws Exception {
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(get(TEST_REST_URL + DATE_TIME_BETWEEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL6, true),
                        MealsUtil.createWithExceed(MEAL5, true),
                        MealsUtil.createWithExceed(MEAL4, true)
                ));
    }

    @Test
    public void testGetBetweenAdmin() throws Exception {
        AuthorizedUser.setId(ADMIN_ID);
        mockMvc.perform(get(TEST_REST_URL + DATE_TIME_BETWEEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(ADMIN_MEAL2, true),
                        MealsUtil.createWithExceed(ADMIN_MEAL1, true)
                ));
    }

    @Test
    public void testFilter() throws Exception{
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(get(TEST_REST_URL + "filter")
                .param("startDate", "2015-05-31").param("startTime", "10:00")
                .param("endDate", "2015-05-31").param("endTime","14:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL5, true),
                        MealsUtil.createWithExceed(MEAL4, true)
                ));
    }

    @Test
    public void testFilterAdmin() throws Exception{
        AuthorizedUser.setId(ADMIN_ID);
        mockMvc.perform(get(TEST_REST_URL + "filter")
                .param("startDate", "2015-06-01")
                .param("endDate", "2015-06-01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MATCHER_EXCEED.contentListMatcher(
                        MealsUtil.createWithExceed(ADMIN_MEAL2, true),
                        MealsUtil.createWithExceed(ADMIN_MEAL1, true)
                ));
    }
}