package ozamkovyi.web.servlet.localizationAndLogOut;

import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserLocalizationAndLogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object us = session.getAttribute("currentUser");
        ClientDao clientDao = new ClientDao();
        if (req.getParameter("logOut") != null) {
            Cookie cookieOk = new Cookie("ok", "");
            Cookie cookieLogin = new Cookie("login", "");
            Cookie cookiePassword = new Cookie("password", "");
            cookieOk.setMaxAge(0);
            cookieLogin.setMaxAge(0);
            cookiePassword.setMaxAge(0);
            cookieOk.setMaxAge(0);
            cookieLogin.setMaxAge(0);
            cookiePassword.setMaxAge(0);
            resp.addCookie(cookieOk);
            resp.addCookie(cookieLogin);
            resp.addCookie(cookiePassword);
            session.invalidate();
            resp.sendRedirect("/login");
        } else {

            if (us instanceof Client) {
                Client user = (Client) us;
                if (req.getParameter("engButton") != null) {
                    session.setAttribute("locale", "en");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                    session.setAttribute("resourceBundle", resourceBundle);
                    user.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    session.setAttribute("locale", "ua");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
                    session.setAttribute("resourceBundle", resourceBundle);
                    user.setLanguage("ua");
                }
                clientDao.setClientLocal(user);
            } else {
                Admin admin = (Admin) us;
                if (req.getParameter("engButton") != null) {
                    session.setAttribute("locale", "en");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                    session.setAttribute("resourceBundle", resourceBundle);
                    admin.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    session.setAttribute("locale", "ua");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
                    session.setAttribute("resourceBundle", resourceBundle);
                    admin.setLanguage("ua");
                }
                AdminDao.setAdminLocal(admin);
            }
            resp.sendRedirect((String) session.getAttribute("currentURL"));
        }
    }
}
