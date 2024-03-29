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
    <script type="text/javascript" src="../resources/js/functions.js?v=12"></script>
</head>

<body class="other_pages">

<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="../index.jsp">
            <img src="../resources/images/logo.png" alt="logo">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="/students">Студенты</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/disciplines">Дисциплины</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="term_list.html">Семестры</a>
                </li>

            </ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${isLogin eq 1}">
                            <a class="nav-link log-out" href="/log-out">Log out ${login}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link log-out" href="/login">Log in ${login}</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>


<section class="std-list">

    <div class="col-sm-4">
        <a onclick="studentProgress()" class="button-28" type="submit" id="sdt_progress">Посмотреть успеваемость
            выбранных студентов</a>
        <c:if test="${role eq 1}">
            <a href="/student-create" class="button-28" type="button" id="create_sdt">Создать студента...</a>
        </c:if>
        <c:if test="${role eq 1}">
            <a onclick="studentModify()" class="button-28" type="submit" id="modify_sdt">Модифицировать выбранного
                студента...</a>
        </c:if>
        <c:if test="${role eq 1}">
            <a onclick="studentDelete()" class="button-28" type="submit" id="delete_sdt">Удалить выбранных
                студентов...</a>
        </c:if>
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
                    <div class="col col-1" data-label="slct"><input class="checkbox" type="checkbox" value="${st.id}">
                    </div>
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
    <input type="hidden" name="hiddenModifyID" id="hiddenToModify">
</form>

<form action="/student-delete" method="get" id="formToDelete">
    <input type="hidden" name="hiddenIdsToDelete" id="hiddenIdsToDelete">
</form>

<form action="/student-progress" method="get" id="formToProgress">
    <input type="hidden" name="hiddenIdToProgress" id="hiddenIdToProgress">
</form>

</body>

</html>
