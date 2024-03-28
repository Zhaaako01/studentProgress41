package controllers.discipline;

import db.DB_DisciplinesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineCreateController", urlPatterns = "/discipline-create")
public class DisciplineCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/disciplines/discipline-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String discipline_name = req.getParameter("dsp-name");

        discipline_name = discipline_name.trim();

        if (discipline_name == null || discipline_name.isEmpty()) {
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/disciplines/discipline-create.jsp").forward(req, resp);
            return;
        }

        DB_DisciplinesManager.createNewDiscipline(discipline_name);

        resp.sendRedirect("/disciplines");
    }
}
