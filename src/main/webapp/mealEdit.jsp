<%--
  Created by Nikolay Lobachev
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<br>
<br>

<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">


      <input type="hidden" name="id" value="${meal.id}"/>

      <table border="0">
        <tr>
          <td>DateTime</td><td><input type="datetime-local" value="${meal.dateTime}" name="dateTime"/></td>
        </tr>
        <tr>
          <td>Description</td><td><input type="text" value="${meal.description}" name="description"/></td>
        </tr>
        <tr>
          <td>Calories</td><td><input type="number" value="${meal.calories}" name="calories"/></td>
        </tr>
      </table>

      <button type="submit">Save</button>
    </form>
</body>
</html>
