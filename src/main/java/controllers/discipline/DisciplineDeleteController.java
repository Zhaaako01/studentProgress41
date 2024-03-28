package controllers.discipline;

import db.DB_DisciplinesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineDeleteController", urlPatterns = "/discipline-delete")
public class DisciplineDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsToDelete = req.getParameter("hiddenIdsToDelete");
        String[] ids = idsToDelete.split(" ");
        for (String id : ids) {
            DB_DisciplinesManager.deleteDiscipline(id);
        }
        resp.sendRedirect("/disciplines");
    }
}
