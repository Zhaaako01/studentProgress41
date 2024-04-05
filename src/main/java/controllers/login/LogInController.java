package controllers.login;

import db.DB_LoginManager;
import entities.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogInController", urlPatterns = "/login")
public class LogInController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = DB_LoginManager.getAllRoles();
        req.setAttribute("roles", roles);
        String message = req.getParameter("message");
        if ("1".equals(message)) {
            req.setAttribute("message", "1");
        } else if ("2".equals(message)) {
            req.setAttribute("message", "2");
        }
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
            resp.sendRedirect("/login?message=1");
            return;
        }

        if (!DB_LoginManager.canLogin(login, password, role)) {
            resp.sendRedirect("/login?message=2");
            return;
        }
        req.getSession().setAttribute("isLogin", "1");
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("role", role);
        resp.sendRedirect("/");
    }
}

