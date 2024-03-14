package controllers;

import db.DBManager;
import entity.Mark;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // 1 to do
        String idStud = req.getParameter("hiddenIdToProgress");
        Student student = DBManager.getStudentById(idStud);
        req.setAttribute("student", student);

        // 2 to do
        List<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("terms", terms);

        // 3 to do
        // Если был переход из страницы "ВСЕ СТУДЕНТЫ" - тогда выбранный - первый из terms
        // Если был переход из кнопки "ВЫБРАТЬ" - тогда нам должны ПЕРЕДАТЬ id выбранного

        Term selectedTerm = null;
        // Нам передали id выбранного?
        if (req.getParameter("idSelectedTerm") != null) {
            // вытягивать с базы семестр по id
            selectedTerm = DBManager.getTermByID(req.getParameter("idSelectedTerm"));
        } else {
            // вытягивать из всех активных семестров первый
            selectedTerm = terms.get(0);
        }

        req.setAttribute("selectedTerm", selectedTerm);


        // 4

        List<Mark> marks = DBManager.getMarksByStudentAndTerm(idStud, selectedTerm.getId());
        req.setAttribute("marks", marks);

        //5
        double summMarks = 0;
        for (Mark m : marks) {
            summMarks = summMarks + m.getMark();
        }
        double averageMark = summMarks / marks.size();
        req.setAttribute("averageMark", averageMark);

        req.getRequestDispatcher("WEB-INF/student-progress.jsp").forward(req, resp);
    }
}
