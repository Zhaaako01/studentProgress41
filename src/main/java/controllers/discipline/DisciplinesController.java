package controllers.discipline;

import db.DB_DisciplinesManager;
import entities.Discipline;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DisciplinesController", urlPatterns = "/disciplines")
public class DisciplinesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> disciplines = DB_DisciplinesManager.getAllActiveDisciplines();
        req.setAttribute("activeDisciplines", disciplines);
        req.getRequestDispatcher("WEB-INF/disciplines/disciplines.jsp").forward(req, resp);
    }
}