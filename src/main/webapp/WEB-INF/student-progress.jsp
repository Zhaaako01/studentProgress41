<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Progress</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker({dateFormat: 'dd/mm/yy' });
        } );
    </script>

    <script type="text/javascript" src="../resources/js/functions.js?v=12"></script>
    <link rel="stylesheet" href="../resources/css/style.css">

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


            </ul>

            <ul class="navbar-nav">

                <li class="nav-item">
                    <a class="nav-link log-out" href="sign_in_and_up.html">log out</a>
                    <a class="nav-link go-back" href="students">Назад</a>
                </li>

            </ul>
        </div>
    </div>
</nav>


<section class="std_progress">

    <div class="show_std_info">
        <h2 class="std_progress_h2">Отображена успеваемость для следующего студента:</h2>
        <ul class="std-progress-table">
            <li class="std-prg-table-header">
                <div class="col col-1"></div>
                <div class="col col-2">Фамилия</div>
                <div class="col col-3">Имя</div>
                <div class="col col-4">Группа</div>
                <div class="col col-5">Дата поступления</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">${student.surname}</div>
                <div class="col col-3" data-label="std-name">${student.name}</div>
                <div class="col col-4" data-label="std-group">${student.group}</div>
                <div class="col col-5" data-label="std-date"><fmt:formatDate value="${student.date}"
                                                                             pattern="dd/MM/yyyy"/></div>
            </li>

        </ul>
    </div>


    <div class="std-progr-list">

        <div class="col-sm-6_1">

            <ul class="std-prog-table">
                <li class="table-header">
                    <div class="col col-2">Дисциплина</div>
                    <div class="col col-3">Оценка</div>
                </li>

                <c:forEach items="${marks}" var="m">
                <li class="table-row">
                    <div class="col col-2" data-label="dscp-name">${m.discipline.discipline_name}</div>
                    <div class="col col-2" data-label="std-grade">${m.mark}</div>
                </li>
                </c:forEach>

            </ul>
        </div>

        <div class="col-sm-6_2">

            <div class="select">
                <select name="format" id="format">
                    <option selected disabled>Выбрать семестр:</option>
                    <option value="1">Семестр 1</option>
                    <option value="2">Семестр 2</option>
                </select>

            </div>
            <button class="button button-28" type="button" id="slc_semestr"
                    style="margin-top: 10px">Выбрать</button>

            <p class="std_avr_grd_label">Средняя оценка за семестр: <span id="std-average-grade"><c:out value="${averageMark}" default="Оценки не поставлены"></c:out> </span> балла
            </p>

        </div>

    </div>

</section>
