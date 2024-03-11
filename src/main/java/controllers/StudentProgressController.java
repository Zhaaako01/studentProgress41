package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1 - достаем студента по id (придет с запросом)
        // 2 - достаем активные семестры (для выпадающего списка)
        // 3 - какой семестр сейчас выбран
        //      (если мы перешли с главной страницы - то выбранным является ПЕРВЫЙ из пункта 2)
        //      (если мы нажали на кнопку ВЫБРАТЬ - тогда мы ДОЛЖНЫ ОЖИДАТЬ id выбранного семестра)
        // 4 - вытягиваем оценки относительно выбранного семестра
        // 5 - посчитать средний балл(исходя из 4 пункта)

        // все эти переменные отправить на jsp в виде атрибутов
    }
}
