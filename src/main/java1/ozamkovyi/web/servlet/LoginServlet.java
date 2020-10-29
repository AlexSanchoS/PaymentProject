package ozamkovyi.web.servlet;

import org.apache.log4j.Logger;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
/**
 * Servlet for login management
 * @author  O. Zamkovyi
 */
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);

    /**
     * Max age for cookie in second
     */
    private final int COOKIE_MAX_AGE = 10 * 60;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Forward to /jsp/login.jsp");
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("loginButton") != null) {
            // if login button is pressed set login and password cookies
            logger.debug("Login button is pressed");
            String login = req.getParameter("loginLabel");
            String password = req.getParameter("passwordLabel");
            Cookie cookieLogin = new Cookie("login", login);
            Cookie cookiePassword = new Cookie("password", password);
            cookieLogin.setMaxAge(COOKIE_MAX_AGE);
            cookiePassword.setMaxAge(COOKIE_MAX_AGE);
            //check for admin with current login and password
            Admin admin = new AdminDao().findAdminByLoginAndPassword(login, password);
            if (admin != null) {
                //if client is logged in then add cookies and redirect to /adminHomepage
                logger.info("The admin is logged in");
                resp.addCookie(cookieLogin);
                resp.addCookie(cookiePassword);
                session.setAttribute("currentUser", admin);
                logger.info("Redirect to /adminHomepage");
                resp.sendRedirect("/adminHomepage");
            } else {
                //check for client with current login and password
                Client client = new ClientDao().findClientByLoginAndPassword(login, password);
                if (client != null) {
                    //if client is logged in then add cookies and redirect to /clientHomepage
                    logger.info("The client is logged in");
                    resp.addCookie(cookieLogin);
                    resp.addCookie(cookiePassword);
                    session.setAttribute("currentUser", client);
                    logger.info("Redirect to /clientHomepage");
                    resp.sendRedirect("/clientHomepage");
                } else {
                    // if wrong login or password then show message
                    logger.debug("Wrong login or password");
                    session.setAttribute("wrongLogin", "true");
                    logger.info("Forward to /jsp/login.jsp");
                    req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                }
            }
        }
        if (req.getParameter("RegistrationButton") != null) {
            // if registration button is pressed then redirect to /registration
            logger.info("Redirect to /registration");
            resp.sendRedirect("/registration");
        }


    }
}
