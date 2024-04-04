package controllers.term;

import db.DB_DisciplinesManager;
import db.DB_TermsManager;
import entities.Discipline;
import entities.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermModifyController", urlPatterns = "/modify-term")
public class TermModifyController extends HttpServlet {

    /*

    1 - надо взять id семестр(он должен быть взят со страницы семестры и передан при нажатии кнопки модифицировать семестр)

    2 - надо передать все активные дисциплины для показа

    3 - надо передать id семестра, чтобы показать длительность семестра

     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //   ***   1   ***   //
        String idOfTermToModify = req.getParameter("hiddenModifyID");
        Term term = DB_TermsManager.getTermByID(idOfTermToModify);
        req.setAttribute("hiddenTermId", term);


        //   ***   2   ***   //
        List<Discipline> disciplines = DB_DisciplinesManager.getAllActiveDisciplines();
        req.setAttribute("allActiveDisciplines", disciplines);

        //   ***   3   ***   //
        int selectedTermDuration = DB_TermsManager.getTermDurationBy(Integer.parseInt(idOfTermToModify));
        req.setAttribute("termDuration", selectedTermDuration);

//        if ("1".equals(req.getParameter("message"))) {
//            req.setAttribute("message", "1");
//        }

        req.getRequestDispatcher("WEB-INF/terms/term-modify.jsp").forward(req, resp);
    }

    //  --- надо менять только длительность семестра и дисциплины ---


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*

    1 - надо взять id и длительность семестра из request-a

    2 - надо поменять длительность с помощью id из request-a

    3 - надо очистить строки дисциплин из таблицы в бд по id семестра

    4 - надо взять id выбранных дисциплин

    5 - надо передать id семестра и id выбранных дисциплин в метод в бд


     */

        //   ***   1   ***   //

        String idOfTermToModify = req.getParameter("hiddenTermId");
        String selectedTermDuration = req.getParameter("termDuration");
//        if (selectedTermDuration == null || selectedTermDuration.isEmpty()) {
////            req.setAttribute("message", "1");
////            req.getRequestDispatcher("WEB-INF/terms/term-modify.jsp").forward(req, resp);
//            resp.sendRedirect("/modify-term?message=1");
//            return;
//        }


        //   ***   2   ***   //
        DB_TermsManager.changeDurationBy(idOfTermToModify, selectedTermDuration);

        //   ***   3   ***   //

        DB_TermsManager.clearAllDisciplinesInTermBy(idOfTermToModify);

        //   ***   4   ***   //
        String idsOfSelectedDisciplines = req.getParameter("hiddenIdsOfSelectedDisciplines");

        String[] ids = idsOfSelectedDisciplines.split(" ");

        //   ***   5   ***   //
        for (String idOfSelectedDiscipline : ids) {
            DB_TermsManager.updateDisciplinesInTermBy(idOfTermToModify, idOfSelectedDiscipline);
        }

        resp.sendRedirect("/terms");
    }
}
