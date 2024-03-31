<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Term Creating</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <script type="text/javascript" src="../../resources/js/functions.js?v=6"></script>
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
                    <a class="nav-link go-back" href="/terms">Назад</a>
                </li>

            </ul>
        </div>
    </div>
</nav>




<section class="smstr-creating-sec">


    <h2 class="smstr-crt-h2">Для создания семестра заполните следующие данные и нажмите кнопку "Создать".</h2>
    <form action="/create-term" method="post" class="smstr-for">

        <label for="duration" style="font-size: 20px;">Длительность (в неделях):</label>
        <input type="number" id="duration" name="t_duration" min="1" value="24">

        <label for="disciplines" style="font-size: 20px;">Дисциплины в
            <a href="#okno"
               class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">семестре</a>:</label>

        <select multiple id="disciplines" name="disciplines" style="height: 200px;">
            <c:forEach items="${allActiveDisciplines}" var="dscp">
            <option value="${dscp.id}">${dscp.discipline_name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="hiddenIdsOfSelectedDisciplines" id="hiddenIdsOfSelectedDisciplines">

        <button class="button-28" type="submit">Создать</button>

        <div id="okno">
            Выберите несколько с помощью кнопок Ctrl или Shift<br>
            <a href="#" class="close">Закрыть окно</a>
        </div>
    </form>


</section>










<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>


<%--<form action="/create-term" method="get" id="formToCreate">--%>
<%--    <input type="hidden" name="hiddenIdsOfSelectedDisciplines" id="hiddenIdsOfSelectedDisciplines">--%>
<%--</form>--%>



</body>

</html>