package controllers.login;

import db.DB_LoginManager;
import db.DB_SignUpManager;
import entities.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpController", urlPatterns = "/sign-up")
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = DB_LoginManager.getAllRoles();
        req.setAttribute("roles", roles);
        String message = req.getParameter("message");
        if ("1".equals(message)) {
            req.setAttribute("message", "1");
        }
        req.getRequestDispatcher("WEB-INF/login_out/log-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginSignUp");
        String password = req.getParameter("passwordSignUp");
        Integer role = Integer.valueOf((req.getParameter("roleSignUp")));

        login = login.replaceAll(" ", "");
        password = password.replaceAll(" ", "");

        if (login == null || password == null || role == 0 ||
                login.isEmpty() || password.isEmpty() || role.equals(null)) {
            resp.sendRedirect("/sign-up?message=1");
            return;
        }
        DB_SignUpManager.createAnUser(login, password, role);
        req.getSession().setAttribute("isLogin", "1");
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("role", role);
        resp.sendRedirect("/");
    }
}
