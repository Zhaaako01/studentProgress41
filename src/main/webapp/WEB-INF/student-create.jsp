<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Creating</title>
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
                    <a class="nav-link log-out" href="sign_in_and_up.html">log out</a>
                    <a class="nav-link go-back" href="students">Назад</a>
                </li>

            </ul>
        </div>
    </div>
</nav>


<section class="std-modifying-sec">
    <h2 class="std-mdf-h2">Для создания студента заполните все поля и нажмите кнопку "Создать".</h2>

    <form action="/student-create" method="post" class="std-mod-form">
        <label for="last-name">Фамилия:</label>
        <input type="text" id="last-name" name="last-name"><br><br>

        <label for="first-name">Имя:</label>
        <input type="text" id="first-name" name="first-name"><br><br>

        <label for="group">Группа:</label>
        <input type="text" id="group" name="group"><br><br>

        <label for="datepicker">Дата поступления:</label>
        <input type="text" id="datepicker" name="admission-date"><br><br>

        <input class="button-28" type="submit" value="Создать">

        <c:if test="${message eq 1}">
        <h3>Поля не должны быть пустыми</h3>
        </c:if>
    </form>

</section>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>


</body>

</html>