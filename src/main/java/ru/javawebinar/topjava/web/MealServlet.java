package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealDao;
import ru.javawebinar.topjava.repository.MealDaoMemoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Nikolay Lobachev.
 */

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        super.init();
        mealDao = new MealDaoMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals.jsp");

        try{
             switch(request.getParameter("action")){

                case "delete":
                    mealDao.delete(Integer.valueOf(request.getParameter("id")));
                    response.sendRedirect("meals");
                    break;

                case "create":
                    Meal mealCreate = new Meal(LocalDateTime.now(),"",2000);
                    request.setAttribute("meal", mealCreate);
                    request.getRequestDispatcher("mealEdit.jsp").forward(request,response);
                    break;

                case "update":
                    Meal mealUpdate = mealDao.get(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("meal",mealUpdate);
                    request.getRequestDispatcher("mealEdit.jsp").forward(request,response);
                    break;
             }

        }catch (NullPointerException e){
            request.setAttribute("mealsList", MealsUtil.getFilteredWithExceeded(mealDao.getAll(),LocalTime.MIN,LocalTime.MAX,2000));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id =request.getParameter("id");

        mealDao.createOrUpdate(new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories"))));

        response.sendRedirect("meals");
    }
}
