package ozamkovyi.web.servlet;


import org.apache.log4j.Logger;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for registration management
 * @author  O. Zamkovyi
 */
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);


    /**
     * Max age for cookie in second
     */
    private final int COOKIE_MAX_AGE = 10 * 60;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Forward to /jsp/registration.jsp");
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client client = new Client();
        req.setCharacterEncoding("UTF-8");
        client.createNewClientByRequest(req, (String) session.getAttribute("locale"));
        // if client not adults show message and not register the user
        if (!client.clientIsAdults()) {
            logger.debug("Client is not adults");
            session.setAttribute("clientNotAdults", "true");
            logger.info("Forward to /jsp/registration.jsp");
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else {
            Client client1 = new ClientDao().findClientByLoginAndPassword(client.getLogin(), client.getPassword());
            //if the combination of login and password is already in the database
            // show massage and do not register the user
            // else add new client to DB and forward to clientHomepage.jsp
            if (client1 == null) {
                logger.trace("New client");
                logger.debug("Add new client to bd");
                client.addNewClientToDB();
                session.setAttribute("currentUser", client);
                Cookie cookieLogin = new Cookie("login", req.getParameter("loginLabel"));
                Cookie cookiePassword = new Cookie("password", req.getParameter("passwordLabel"));
                cookieLogin.setMaxAge(COOKIE_MAX_AGE);
                cookiePassword.setMaxAge(COOKIE_MAX_AGE);
                resp.addCookie(cookieLogin);
                resp.addCookie(cookiePassword);
                logger.info("Forward to /jsp/clientHomepage.jsp");
                req.getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
            }else{
                logger.debug("combination of login and password is already exists");
                session.setAttribute("wrongRegistrationDate", "true");
                logger.info("Forward to /jsp/registration.jsp");
                req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
            }
        }

    }

}
