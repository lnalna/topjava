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

        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meal list user</h2>

  <section>
    <form method="post" action="meals?action=selectdate">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" max="2020-12-31" min="2000-01-01" value="2015-05-30"  name="startDate"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" max="2020-12-31" min="2000-01-01" value="2015-05-31" name="endDate"></dd>
        </dl>
        <button type="submit">FilterByDate</button>
    </form>
  </section>
  <br>
<hr>
  <br>
  <section>
    <form method="post" action="meals?action=selecttime">
        <dl>

            <dt>From Time:</dt>
            <dd><input type="time" max="23:59" min="00:00" value="10:00" name="startTime"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" max="23:59" min="00:00" value="20:00" name="endTime"></dd>
        </dl>
        <button type="submit">FilterByTime</button>
    </form>
  </section>
<br>
<hr>
<br>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
