package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private  ConfigurableApplicationContext appCtx;
    private MealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController = appCtx.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if(action==null) {

            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            log.info(meal.isNew() ? "Create {}" : "Update {}", meal);

            if (id.isEmpty()) {
                mealRestController.create(meal);
                response.sendRedirect("meals");
            } else {
                mealRestController.update(meal, meal.getId());
                response.sendRedirect("meals");
            }

        } else {

            try {
                if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {

                    LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                    LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

                    request.setAttribute("meals",
                            mealRestController.getMealsBetweenDate(startDate, endDate));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);

                } else if (request.getParameter("startTime") != null && request.getParameter("endTime") != null) {

                    LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
                    LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));

                    request.setAttribute("meals",
                            mealRestController.getMealBetweenTime(startTime, endTime));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);

                } else {

                    request.setAttribute("meals",
                            MealsUtil.getWithExceeded(mealRestController.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                }
            }catch (DateTimeParseException e){

                /*
                 this try/catch FOR FIREFOX and IE
                 */
                response.sendRedirect("/topjava/error.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getWithExceeded(mealRestController.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }


    @Override
    public void destroy() {
        appCtx.close();
        super.destroy();
    }
}
