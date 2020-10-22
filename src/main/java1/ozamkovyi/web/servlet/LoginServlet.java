package ozamkovyi.web.servlet;

import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {

    private final int COOKIE_MAX_AGE = 10 * 60;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("loginButton") != null) {
            String login = req.getParameter("loginLabel");
            String password = req.getParameter("passwordLabel");
            Cookie cookieLogin = new Cookie("login", login);
            Cookie cookiePassword = new Cookie("password", password);
            cookieLogin.setMaxAge(COOKIE_MAX_AGE);
            cookiePassword.setMaxAge(COOKIE_MAX_AGE);
            Admin admin = new AdminDao().findAdminByLoginAndPassword(login, password);
            if (admin != null) {
                resp.addCookie(cookieLogin);
                resp.addCookie(cookiePassword);
                session.setAttribute("currentUser", admin);
                resp.sendRedirect("/adminHomepage");
            } else {
                Client client = new ClientDao().findClientByLoginAndPassword(login, password);
                if (client != null) {
                    resp.addCookie(cookieLogin);
                    resp.addCookie(cookiePassword);
                    session.setAttribute("currentUser", client);
                    resp.sendRedirect("/clientHomepage");
                } else {
                    session.setAttribute("wrongLogin", "true");
                    getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                }
            }
        }
        if (req.getParameter("RegistrationButton") != null) {
            resp.sendRedirect("/registration");
        }


    }
}
