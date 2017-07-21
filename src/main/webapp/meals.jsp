<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list user1</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals1}" var="meal1">
            <jsp:useBean id="meal1" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal1.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal1.dateTime)}
                </td>
                <td>${meal1.description}</td>
                <td>${meal1.calories}</td>
                <td><a href="meals?action=update&id=${meal1.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal1.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<br><br><br>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list user2</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals2}" var="meal2">
            <jsp:useBean id="meal2" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal2.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal2.dateTime)}
                </td>
                <td>${meal2.description}</td>
                <td>${meal2.calories}</td>
                <td><a href="meals?action=update&id=${meal2.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal2.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>


</body>
</html>
