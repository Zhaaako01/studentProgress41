package controllers.login;

import db.DB_LoginManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogInController", urlPatterns = "/login")
public class LogInController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        login = login.replaceAll(" ", "");
        password = password.replaceAll(" ", "");
        role = role.replaceAll(" ", "");

        if (login == null || password == null || role == null ||
                login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
            return;
        }

        if (!DB_LoginManager.canLogin(login, password, role)) {
            req.setAttribute("message", "2");
            req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("isLogin", "1");
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("role", role);
        resp.sendRedirect("/");
    }
}

