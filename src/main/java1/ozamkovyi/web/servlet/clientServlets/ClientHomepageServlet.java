package ozamkovyi.web.servlet.clientServlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet for client homepage
 *
 * @author O. Zamkovyi
 */
public class ClientHomepageServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ClientHomepageServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("forward to /jsp/clientHomepage.jsp");
        req.getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if (req.getParameter("buttonMyCard") != null) {
             logger.debug("My card button is pressed");
             logger.info("Redirect to /clientCardMenu");
             resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("buttonMyAccount") != null) {
            logger.debug("My account button is pressed");
            logger.info("Redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("buttonMyPayment") != null) {
            logger.debug("My payment button is pressed");
            logger.info("Redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }


    }
}
