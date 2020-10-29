package ozamkovyi.web.servlet.clientServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.bean.PaymentBean;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.PaymentDao;

import ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for client account menu page
 *
 * @author O. Zamkovyi
 */
public class ClientAddPaymentServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientAddPaymentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
        //if session attribute "currentPayment" == null then create new payment
        if (session.getAttribute("currentPayment") == null) {
            logger.debug("set session attribute 'countAccount'");
            // set session attributes 'listOfCreditCard' list of available credit card
            logger.debug("set session attribute 'listOfCreditCard'");
            session.setAttribute("listOfCreditCard", new CreditCardDao().getAllUnblockCreditCard( currentUser));
            logger.info("Forward to /jsp/addNewPayment.jsp");
            req.getRequestDispatcher("/jsp/addNewPayment.jsp").forward(req, resp);
        } else {
            ((PaymentBean) session.getAttribute("currentPayment")).getSenderCardNumber();
            logger.info("forward to /jsp/clientAccountMenu.jsp");
            req.getRequestDispatcher("/jsp/addPayment.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // create new payment and add to DB
        String senderNumber = req.getParameter("senderNumber");
        String recipientCardNumber = req.getParameter("recipientCardNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        Client client = (Client) session.getAttribute("currentUser");
        logger.trace("Create new payment client = " + client.getId()+ " sender card number = "+ senderNumber
                +" recipient card number = "+ recipientCardNumber+" amount "+amount);
        logger.debug("Add new payment to DB");
        new PaymentDao().createNewPayment(client, senderNumber, recipientCardNumber, amount);
        session.setAttribute("currentPayment", null);
        logger.info("Redirect to /clientPaymentMenu");
        resp.sendRedirect("/clientPaymentMenu");
    }
}
