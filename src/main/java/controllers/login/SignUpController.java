package controllers.login;

import db.DB_SignUpManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpController", urlPatterns = "/sign-up")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginSignUp");
        String password = req.getParameter("passwordSignUp");
        Integer role = Integer.valueOf((req.getParameter("roleSignUp")));

        login = login.replaceAll(" ", "");
        password = password.replaceAll(" ", "");
//        role = role.replaceAll(" ", "");

        if (login == null || password == null || role == 0 ||
                login.isEmpty() || password.isEmpty() || role.equals(null)) {
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
            return;
        }
//        PrintWriter out = resp.getWriter();
//        out.print(login);
//        out.print(password);
//        out.print(role);
        DB_SignUpManager.createAnUser(login, password, role);
        req.getSession().setAttribute("isLogin", "1");
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("role", role);
        resp.sendRedirect("/");
    }
}
