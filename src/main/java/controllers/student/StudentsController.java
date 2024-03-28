package controllers.student;

import db.DB_StudentsManager;
import entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentsController", urlPatterns = "/students")
public class StudentsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = DB_StudentsManager.getAllActiveStudents();
        req.setAttribute("activeStudents", students);
        req.getRequestDispatcher("WEB-INF/students.jsp").forward(req, resp);
    }
}
