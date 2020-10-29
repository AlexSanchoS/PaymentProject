package ozamkovyi.web.servlet.localizationAndLogOut;

import org.apache.log4j.Logger;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Servlet for user localization and log out buttons
 * @author  O. Zamkovyi
 */
public class UserLocalizationAndLogOutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserLocalizationAndLogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object us = session.getAttribute("currentUser");
        if (req.getParameter("logOut") != null) {
            //if log out button is pressed then clean session and cookie and redirect to login page
            logger.debug("Log out button is pressed");
            Cookie cookieOk = new Cookie("ok", "");
            Cookie cookieLogin = new Cookie("login", "");
            Cookie cookiePassword = new Cookie("password", "");
            cookieOk.setMaxAge(0);
            cookieLogin.setMaxAge(0);
            cookiePassword.setMaxAge(0);
            resp.addCookie(cookieOk);
            resp.addCookie(cookieLogin);
            resp.addCookie(cookiePassword);
            session.invalidate();
            logger.info("Log out user");
            logger.info("Clean session and cookie");
            logger.info("Redirect to /login");
            resp.sendRedirect("/login");
        } else {
            if (us instanceof Client) {
                Client user = (Client) us;
                //if localization button is pressed then change resource bundle and user locale in DB
                if (req.getParameter("engButton") != null) {
                    logger.debug("Eng button is pressed for client");
                    session.setAttribute("locale", "en");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                    logger.info("ResourceBundle - en");
                    session.setAttribute("resourceBundle", resourceBundle);
                    user.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    logger.debug("Ua button is pressed for client");
                    session.setAttribute("locale", "ua");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
                    logger.info("ResourceBundle - ua");
                    session.setAttribute("resourceBundle", resourceBundle);
                    user.setLanguage("ua");
                }
                logger.debug("Change local for client");
                new ClientDao().setClientLocal(user);
            } else {
                Admin admin = (Admin) us;
                if (req.getParameter("engButton") != null) {
                    logger.debug("Eng button is pressed for client");
                    session.setAttribute("locale", "en");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                    logger.info("ResourceBundle - en");
                    session.setAttribute("resourceBundle", resourceBundle);
                    admin.setLanguage("en");
                }
                if (req.getParameter("uaButton") != null) {
                    logger.debug("Ua button is pressed for client");
                    session.setAttribute("locale", "ua");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
                    logger.info("ResourceBundle - ua");
                    session.setAttribute("resourceBundle", resourceBundle);
                    admin.setLanguage("ua");
                }
                logger.debug("Change local for admin");
                new AdminDao().setAdminLocal(admin);
            }
            logger.info("Redirect to "+session.getAttribute("currentURL"));
            resp.sendRedirect((String) session.getAttribute("currentURL"));
        }
    }
}
