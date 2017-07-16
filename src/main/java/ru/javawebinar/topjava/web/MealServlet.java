package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Nikolay Lobachev.
 */

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealRepository mealRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealRepository = new MealRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals.jsp");

       // request.setAttribute("mealsList", MealsUtil.getFilteredWithExceededWithoutTimeLimit(MealsUtil.MEALS,2000));
       // request.getRequestDispatcher("meals.jsp").forward(request,response);
        request.setAttribute("mealsList", MealsUtil.getFilteredWithExceededWithoutTimeLimit(mealRepository.getAll(),2000));
        request.getRequestDispatcher("meals.jsp").forward(request,response);

    }
}
