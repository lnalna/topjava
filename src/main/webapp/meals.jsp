<%--
  Created by Nikolay Lobachev
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Meals List</title>
    <style> <%@include file="/WEB-INF/css/meal.css" %> </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
  <table border="4" cellpadding="10" cellspacing="10">
    <thead>
    <tr>
      <th>MealId</th>
      <th>Date</th>
      <th>Description</th>
      <th>Calories</th>
    </tr>
    </thead>
    <c:forEach items="${mealsList}" var="meal">
      <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
      <tr class="${meal.exceed ? 'exceed' : 'normal'}">
        <td>${meal.mealId}</td>
        <td><%=TimeUtil.toString(meal.getDateTime())%></td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
      </tr>
    </c:forEach>

  </table>
</body>
</html>
