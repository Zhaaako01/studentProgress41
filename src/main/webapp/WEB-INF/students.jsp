<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/css/style.css">
    <script type="text/javascript" src="../resources/js/functions.js"></script>

</head>

<body class="other_pages">

<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="../index.jsp">
            <img src="../resources/images/logo.png" alt="logo">
        </a>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link log-out" href="sign_in_and_up.html">log out</a>
            </li>
        </ul>
    </div>
    </div>
</nav>


<section class="std-list">

    <div class="col-sm-4">
        <a href="student_progress.html" class="button-28" type="button" id="sdt_progress">Посмотреть успеваемость
            выбранных студентов</a>
        <a href="/student-create" class="button-28" type="button" id="create_sdt">Создать студента...</a>
        <a onclick="studentModify()" class="button-28" type="submit" id="modify_sdt">Модифицировать выбранного
            студента...</a>
        <button class="button button-28" type="button" id="delete_sdt">Удалить выбранных студентов</button>
    </div>

    <div class="col-sm-8">
        <h2 class="std-list-name">Список студентов</h2>
        <ul class="std-table">
            <li class="table-header">
                <div class="col col-1"></div>
                <div class="col col-2">Фамилия</div>
                <div class="col col-3">Имя</div>
                <div class="col col-4">Группа</div>
                <div class="col col-5">Дата поступления</div>
            </li>

            <c:forEach items="${activeStudents}" var="st">
                <li class="table-row">
                    <div class="col col-1" data-label="slct"><input class="checkbox" type="checkbox" value="${st.id}"></div>
                    <div class="col col-2" data-label="std-fullname">${st.surname}</div>
                    <div class="col col-3" data-label="std-name">${st.name}</div>
                    <div class="col col-4" data-label="std-group">${st.group}</div>
                    <div class="col col-5" data-label="std-date"><fmt:formatDate value="${st.date}"
                                                                                 pattern="dd/MM/yyyy"/></div>
                </li>
            </c:forEach>
        </ul>
    </div>

</section>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>


<form action="/student-modify" method="get" id="formToModify">
<input type="hidden" name="hiddenModifyID" id = "hiddenToModify">

</form>


</body>


</html>
