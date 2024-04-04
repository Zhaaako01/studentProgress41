package controllers.term;

import db.DB_DisciplinesManager;
import db.DB_StudentsManager;
import db.DB_TermsManager;
import entities.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "TermsCreateController", urlPatterns = "/create-term")
public class TermsCreateController extends HttpServlet {

    /*
        1 - Название семестра? как оно должно формироваться?
        просто Семестр + авто инкремент номер
        (можно дать term.id только созданного семестра с помощью метода который будет доставать ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");)
        или можно создать семестр потом этот же семестр по сгенерированному ключу изменить название

        2 - Устанавливаем длительность семестра

        3 - Устанавливаем дисциплины со статусом 1 с база данных в форму

        4 - Выбираем дисциплины с формы через их id и отправляем в форму
         */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //   ***   3   ***   //

        List<Discipline> disciplines = DB_DisciplinesManager.getAllActiveDisciplines();
        req.setAttribute("allActiveDisciplines", disciplines);

        String message = req.getParameter("message");
        if ("1".equals(message)) {
            req.setAttribute("message", "1");
        }
        req.getRequestDispatcher("WEB-INF/terms/term-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //   ***   1   ***   //

        String termName = "Семестр";

        //   ***   2   ***   //

        String term_duration = req.getParameter("t_duration");
        if (term_duration == null || term_duration.isEmpty()) {
            resp.sendRedirect("/create-term?message=1");
            return;
        }

        //   ***   Получения id только созданного семестра   ***   //

       int idOfCreatingTerm = DB_TermsManager.createTermByUsingAndGetTermID(termName, term_duration);

        //   ***   Добавления к названию только созданного семестра id    ***   //
       DB_TermsManager.addNumberForTermName(termName, idOfCreatingTerm);

        //   ***   4   ***   //

        String idsOfSelectedDisciplines = req.getParameter("hiddenIdsOfSelectedDisciplines");

        String[] ids = idsOfSelectedDisciplines.split(" ");
        for (String idOfSelectedDiscipline : ids) {
            DB_TermsManager.createTermWithDisciplines(idOfCreatingTerm, idOfSelectedDiscipline);
        }

        resp.sendRedirect("/terms");

    }
}
