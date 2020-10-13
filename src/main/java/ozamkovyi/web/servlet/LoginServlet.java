package ozamkovyi.web.servlet;

import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Entity;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Entity currentUser = (Entity) session.getAttribute("currentUser");
        if (currentUser == null) {
            Localization localization = (Localization) session.getAttribute("localization");
            if (localization==null){
                localization = new Localization();
                session.setAttribute("localization", localization);
            }
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            if (currentUser instanceof Client) {
                getServletContext().getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
            } else {

                getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("loginButton") != null) {
            String login = req.getParameter("loginLabel");
            String password = req.getParameter("passwordLabel");
            Admin admin = new AdminDao().findAdminByLoginAndPassword(login, password);
            if (admin != null) {
                session.setAttribute("currentUser", admin);
                resp.sendRedirect("/adminHomepage");
            } else {
                Client client = new ClientDao().findClientByLoginAndPassword(login, password);
                if (client != null) {
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

//            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        }


    }
}
