package controllers.term;

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

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        1 - Получаем семестр
        (если мы перешли с главной страницы - то выбранным является ПЕРВЫЙ из всех семестров)
        (если мы нажали на кнопку ВЫБРАТЬ - тогда мы ДОЛЖНЫ ОЖИДАТЬ id выбранного семестра)

        2 - вытягиваем длительность выбранного семестра

        Экстра - спрягать слово неделя смотря на длительность выбранного семестра

        3 - вытягиваем все дисциплины выбранного семестра
         */


        // получаем все семестры
        List<Term> terms = DB_TermsManager.getAllActiveTerms();
        req.setAttribute("terms", terms);

        // Если был переход из страницы "СЕМЕСТРЫ" - тогда выбранный - первый из terms
        // Если был переход из кнопки "ВЫБРАТЬ" - тогда нам должны ПЕРЕДАТЬ id выбранного

        Term selectedTerm = null;
        // Нам передали id выбранного?
        if (req.getParameter("idSelectedTerm") != null) {
            selectedTerm = DB_TermsManager.getTermByID(req.getParameter("idSelectedTerm"));
        } else {
            // вытягивать из всех активных семестров первый
            selectedTerm = terms.get(0);
        }

        req.setAttribute("selectedTerm", selectedTerm);

        //   ***   2   ***   //

        int selectedTermDuration = DB_TermsManager.getTermDurationBy(selectedTerm.getId());

        //   ***   Экстра - чтобы спрягать слово неделя   ***   //
        String wordWeek;
        if (selectedTermDuration % 10 == 1) {
            wordWeek = " неделя";
        }
        else if (selectedTermDuration % 10 == 2 || selectedTermDuration % 10 == 3 || selectedTermDuration % 10 == 4){
            wordWeek = " недели";
        } else {
            wordWeek = " недель";
        }

        req.setAttribute("wordWeek", wordWeek);
        req.setAttribute("selectedTermDuration", selectedTermDuration);


        //   ***   3   ***   //

        List<Discipline> disciplines = DB_TermsManager.getDisciplinesByTermID(selectedTerm.getId());
        req.setAttribute("disciplines", disciplines);

        req.getRequestDispatcher("WEB-INF/terms/terms.jsp").forward(req, resp);
    }
}
