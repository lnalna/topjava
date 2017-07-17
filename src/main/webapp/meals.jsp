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
<br>
<br>
<h2>Meals</h2>
<br>
<br>
<a href="meals?action=create">Add meal</a>
<br>
<br>
  <table border="4" cellpadding="10" cellspacing="10">
    <thead>
    <tr>
      <th>Date</th>
      <th>Description</th>
      <th>Calories</th>
      <th colspan="2">Action</th>
    </tr>
    </thead>
    <c:forEach items="${mealsList}" var="meal">
      <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
      <tr class="${meal.exceed ? 'exceed' : 'normal'}">
        <td><%=TimeUtil.toString(meal.getDateTime())%></td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
        <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
      </tr>
    </c:forEach>

  </table>
</body>
</html>
