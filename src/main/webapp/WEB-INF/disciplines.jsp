<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Disciplines List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="style/style.css">
</head>

<body class="other_pages">

<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand" href="main_page.html">
            <img src="img/logo.png" alt="logo">
        </a>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link log-out" href="sign_in_and_up.html">log out</a>
            </li>
        </ul>
    </div>
    </div>
</nav>

<section class="disciplines-list">

    <div class="col-sm-6_1">
        <h2 class="dsp-list-name">Список дисциплин</h2>
        <ul class="dsp-table">
            <li class="table-header">
                <div class="col col-1"></div>
                <div class="col col-2">Наименование дисциплины</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">Высшая математика</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">История Науки и Техники</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">Политология</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">Информатика</div>
            </li>
            <li class="table-row">
                <div class="col col-1" data-label="slct"><input type="checkbox"></div>
                <div class="col col-2" data-label="std-fullname">Теория Алгоритмизации</div>
            </li>
        </ul>
    </div>

    <div class="col-sm-6_2">
        <a href="discipline_creating.html" class="button-28" type="button" id="create_dsp" style="margin-bottom: 10px;">Создать дисциплину...</a>
        <a href="discipline_modifying.html" class="button-28" type="button" id="modify_dsp" style="margin-bottom: 10px;">Модифицировать выбранную дисциплину...</a>
        <a href="#" class="button-28" type="button" id="delete_dsp">Удалить выбранную дисциплину...</a>
    </div>


</section>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>


</body>

</html>