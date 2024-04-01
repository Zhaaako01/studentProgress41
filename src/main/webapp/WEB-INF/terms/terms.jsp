<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <script type="text/javascript" src="../../resources/js/functions.js?v=2"></script>
</head>

<body class="other_pages">


<nav class="navbar navbar-expand-lg">
    <div class="container">

        <a class="navbar-brand" href="../index.jsp">
            <img src="../resources/images/logo.png" alt="logo">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
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
                    <a class="nav-link" href="/terms">Семестры</a>
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
                    <a class="nav-link go-back" href="/">Назад</a>
                </li>

            </ul>
        </div>
    </div>
</nav>


<section class="terms-list">


    <div class="semester-selection">
        <form action="/terms" method="get">
        <label for="semesterSelect" style="font-size: 25px; padding-right: 40px; ;">Выберите семестр</label>

        <select class="selectbox" id="semesterSelect" style="width: 250px; margin-right: 25px; height: 38px;border: 0px" name="idSelectedTerm">
            <c:forEach items="${terms}" var="t">
                <c:choose>
                <c:when test="${t.id eq selectedTerm.id}">
                    <option selected value="${t.id}">${t.termName}</option>
                </c:when>
                <c:otherwise>
                    <option value="${t.id}">${t.termName}</option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <button type="submit" class="button-40" id="slc_semestr">Выбрать</button>
        </form>
    </div>



    <h2 class="semester-info">Длительность семестра: <span id="semester_length"><c:out value="${selectedTermDuration}"></c:out></span><c:out value="${wordWeek}"></c:out>


    </h2>

    <div class="semester_dsp_list">

        <div class="col-sm-6_1">
            <h2 class="smstr-dsp-list-name">Список дисциплин семестра</h2>
            <ul class="smstr-dsp-table">
                <li class="table-header">
                    <div class="col col-1"></div>
                    <div class="col col-2">Наименование дисциплины</div>
                </li>
                <c:forEach items="${disciplines}" var="discipline">
                <li class="table-row">
                    <div class="col col-2" data-label="std-fullname">${discipline.discipline_name}</div>
                </li>
                </c:forEach>

            </ul>
        </div>

        <div class="col-sm-6_2">
            <a href="/create-term" class="button-28" type="button" id="create_dsp" style="margin-bottom: 10px;">Создать семестр...</a>
            <a onclick="termModify()" class="button-28" type="button" id="modify_dsp" style="margin-bottom: 10px;">Модифицировать текущий семестр...</a>
            <a href="#" class="button-28" type="button" id="delete_dsp">Удалить текущий семестр</a>
        </div>

    </div>




</section>












<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>


<form action="/modify-term" method="get" id="formToModify">
    <input type="hidden" name="hiddenModifyID" id="hiddenToModify">
</form>


</body>

</html>
