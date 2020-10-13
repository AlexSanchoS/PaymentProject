package ozamkovyi.web.servlet.localizationAndLogOut;

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
import java.io.IOException;

public class ClientLocalizationAndLogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Localization localization = (Localization) session.getAttribute("localization");
        Object us = session.getAttribute("currentUser");
        ClientDao clientDao = new ClientDao();
        if (req.getParameter("logOut") != null) {
            session.invalidate();
            resp.sendRedirect("/login");
        } else {

            if (us instanceof Client) {
                Client user = (Client) us;
                if (req.getParameter("engButton") != null) {
                    localization.setLocal("en");
                    user.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    localization.setLocal("ua");
                    user.setLanguage("ua");
                }
                clientDao.setClientLocal(user);
            } else {
                Admin admin = (Admin) us;
                if (req.getParameter("engButton") != null) {
                    localization.setLocal("en");
                    admin.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    localization.setLocal("ua");
                    admin.setLanguage("ua");
                }
                AdminDao.setAdminLocal(admin);
            }
            resp.sendRedirect((String) session.getAttribute("currentURL"));
        }
    }
}
