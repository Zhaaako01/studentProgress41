package controllers.student;

import db.DB_StudentsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentDeleteController", urlPatterns = "/student-delete")
public class StudentDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 2 4
        String idsToDelete = req.getParameter("hiddenIdsToDelete");
        // {"1", "2", "3"}
        String[] ids = idsToDelete.split(" ");
        for (String id : ids) {
            DB_StudentsManager.deleteStudent(id);
        }
        resp.sendRedirect("/students");
    }
}
