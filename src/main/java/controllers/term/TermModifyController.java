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

    2 - надо передать id семестра для показа всех дисциплин в выбранном семестре

    3 - надо передать id семестра, чтобы показать длительность семестра

     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //   ***   1   ***   //
        String idOfTermToModify = req.getParameter("hiddenModifyID");
        Term term = DB_TermsManager.getTermByID(idOfTermToModify);
        req.setAttribute("hiddenTermId", term);


        //   ***   2   ***   //
//        List<Discipline> disciplines = DB_TermsManager.getDisciplinesByTermID(Integer.parseInt(idOfTermToModify));
//        req.setAttribute("disciplinesOfTerm", disciplines);
        List<Discipline> disciplines = DB_DisciplinesManager.getAllActiveDisciplines();
        req.setAttribute("allActiveDisciplines", disciplines);

        //   ***   3   ***   //
        int selectedTermDuration = DB_TermsManager.getTermDurationBy(Integer.parseInt(idOfTermToModify));
        req.setAttribute("termDuration", selectedTermDuration);

        req.getRequestDispatcher("WEB-INF/terms/term-modify.jsp").forward(req, resp);
    }

    //  --- надо менять только длительность семестра и дисциплины ---


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*

    1 - надо взять id и длительность семестра из request-a

    2 - надо поменять длительность с помощью id из request-a

    3 - надо взять id выбранных дисциплин

    4 - надо передать id семестра и id выбранных дисциплин в метод в бд

    5 - Если у семестра уже есть такие дисциплины тогда не добавляем если нет то

     */

        //   ***   1   ***   //

        String idOfTermToModify = req.getParameter("hiddenTermId");
        ;
        int selectedTermDuration = Integer.parseInt(req.getParameter("termDuration"));


        //   ***   2   ***   //
//        DB_TermsManager.changeDurationBy(idOfTermToModify, selectedTermDuration);

        //   ***   3   ***   //
        String idsOfSelectedDisciplines = req.getParameter("hiddenIdsOfSelectedDisciplines");
//        System.out.println(idsOfSelectedDisciplines);

        String[] ids = idsOfSelectedDisciplines.split(" ");
        for (String idOfSelectedDiscipline : ids) {
//            DB_TermsManager.createTermWithDisciplines(idOfCreatingTerm, idOfSelectedDiscipline);
            System.out.println(idOfSelectedDiscipline);
        }
    }
}
