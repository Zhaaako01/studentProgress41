<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign_in&sign_up</title>
    <link rel="stylesheet" href="../../resources/css/login_style.css">
    <script type="text/javascript" src="../../resources/js/functions.js?v=2"></script>
</head>
<body>


<div class="container" id="container">

    <div class="form-container sign-up-container">
        <form action="/sign-up" method="post">
            <h1>Создайте аккаунт</h1>
            <img src="../../resources/images/logo.png" alt="logo">
            <input name="loginSignUp" type="text" placeholder="Логин"/>
            <input name="passwordSignUp" type="password" placeholder="Пароль"/>

            <select id="roleSignUp" name="roleSignUp">
                <option value="0">Выберите роль:</option>
                <c:forEach items="${roles}" var="r">
                    <option value="${r.id}">${r.role}</option>
                </c:forEach>
            </select>

            <button type="submit" style="margin-top: 5px;">Зарегистрироваться</button>
            <c:if test="${message eq 1}">
                <div class="warning-container">
                    <span class="warning-icon">&#9888;</span>
                    <span class="warning-text" style="font-size: large">Заполните все поля!</span>
                </div>
            </c:if>
        </form>
    </div>

    <div class="form-container sign-in-container">
        <form action="/login" method="post">
            <h1>Войти в систему</h1>
            <img src="../../resources/images/logo.png" alt="logo">
            <input name="login" type="text" placeholder="Логин"/>
            <input name="password" type="text" placeholder="Пароль"/>

            <select id="role" name="role">
                <option value="0">Выберите роль:</option>
                <c:forEach items="${roles}" var="r">
                    <option value="${r.id}">${r.role}</option>
                </c:forEach>
            </select>

            <button type="submit">Войти</button>


            <c:if test="${message eq 1}">
                <div class="warning-container">
                    <span class="warning-icon">&#9888;</span>
                    <span class="warning-text" style="font-size: large">Заполните все поля!</span>
                </div>
            </c:if>

            <c:if test="${message eq 2}">
                <div class="warning-container">
                    <span class="warning-icon">&#9888;</span>
                    <span class="warning-text" style="font-size: large">Пользователь не найден!</span>
                </div>
            </c:if>

        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Добро пожаловать!</h1>
                <p>Чтобы войти в систему, нажмите ниже и укажите свои данные.</p>
                <button class="ghost" id="signIn">Войти в систему</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Привет, друг!</h1>
                <p>Если вас еще нет в системе, то нажмите ниже для регистрации</p>
                <button class="ghost" id="signUp">Зарегистрироваться</button>
            </div>
        </div>
    </div>
</div>


<script src="../../resources/js/login.js"></script>
</body>
</html>
