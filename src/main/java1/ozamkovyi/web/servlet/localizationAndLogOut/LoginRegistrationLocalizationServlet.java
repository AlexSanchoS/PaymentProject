package ozamkovyi.web.servlet.localizationAndLogOut;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Servlet for localization buttons
 * @author  O. Zamkovyi
 */

public class LoginRegistrationLocalizationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginRegistrationLocalizationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("engButton") != null) {
            logger.debug("Eng button is pressed");
            session.setAttribute("locale", "en");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
            logger.info("ResourceBundle - en");
            session.setAttribute("resourceBundle", resourceBundle);
        }
        if (req.getParameter("uaButton") != null) {
            logger.debug("Ua button is pressed");
            session.setAttribute("locale", "ua");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
            logger.info("ResourceBundle - ua");
            session.setAttribute("resourceBundle", resourceBundle);
        }
        logger.info("Redirect to "+session.getAttribute("currentURL"));
        resp.sendRedirect((String) session.getAttribute("currentURL"));

    }
}
