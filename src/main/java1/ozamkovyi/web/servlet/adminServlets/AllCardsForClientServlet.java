package ozamkovyi.web.servlet.adminServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.bean.CreditCardBean;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for allCardsForClient page
 *
 * @author O. Zamkovyi
 */
public class AllCardsForClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllCardsForClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object page = session.getAttribute("pageNumber");
        // if session attribute 'pageNumber' = null
        // then set session attributes 'pageNumber' and 'sortType' with values 1
        int pageNumber = 0;
        int sortType = 0;
        if (page == null) {
            logger.trace("page number = null");
            session.setAttribute("pageNumber", 1);
            session.setAttribute("sortType", 1);
            sortType = 1;
            pageNumber = 1;
        } else {
            pageNumber = (int) page;
            sortType = (int) session.getAttribute("sortType");
            logger.trace("page number =" + pageNumber + " sort type = " + sortType);
        }
        ClientBean currentClient = (ClientBean) session.getAttribute("currentClient");
        // set session attributes 'countCard' with the number of credit card
        logger.debug("set session attribute 'countCard'");
        session.setAttribute("countCard", new CreditCardDao().getCountCardByClient(currentClient.getId()));
        // set session attributes 'listOfCreditCard' with array list of credit card
        logger.debug("set session attribute 'listOfCreditCard'");
        session.setAttribute("listOfCreditCard", new CreditCardDao().getCardList(currentClient.getId(), pageNumber, sortType));
        logger.info("forward to /jsp/allCardsForClient.jsp");
        req.getRequestDispatcher("/jsp/allCardsForClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // if next page button is pressed then increment session attribute "page number"
        if (req.getParameter("nextPage") != null) {
            logger.debug("Next page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /allCardsForUser");
            resp.sendRedirect("/allCardsForUser");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /allCardsForUser");
            resp.sendRedirect("/allCardsForUser");
        }

        // if sort by currency button is pressed then if session attribute 'sortType' = 1 set 2 else set 1
        if (req.getParameter("currency") != null) {
            logger.debug("Sort by currency button is pressed");
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 1) {
                    logger.trace("Set session attribute 'sortType' = 2 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 2);
                } else {
                    logger.trace("Set session attribute 'sortType' = 1 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 1);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            logger.info("Set new sort type and redirect to /allCardsForUser");
            resp.sendRedirect("/allCardsForUser");
        }
        // if sort by balance button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("balance") != null) {
            logger.debug("Sort by balance button is pressed");
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 3) {
                    logger.trace("Set session attribute 'sortType' = 4 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 4);
                } else {
                    logger.trace("Set session attribute 'sortType' = 3 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 3);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            logger.info("Set new sort type and redirect to /allCardsForUser");
            resp.sendRedirect("/allCardsForUser");
        }

        //chek all account for block/unblock requests
        ArrayList<CreditCardBean> listOfCreditCard = (ArrayList<CreditCardBean>) session.getAttribute("listOfCreditCard");
        for (CreditCardBean creditCard : listOfCreditCard) {
            if (req.getParameter("blocButton " + creditCard.getId()) != null) {
                logger.debug("Block/unblock button is pressed for card " + creditCard.getId());
                logger.trace("Credit card status = " + creditCard.getCardStatusName());
                if (creditCard.getCardStatusName().equals(Fields.CARD_STATUS__BLOCKED)) {
                    logger.debug("Set credit card " + creditCard.getId() + " status unblocked");
                    new CreditCardDao().unblockCard(creditCard);
                } else {
                    logger.debug("Set credit card " + creditCard.getId() + " status blocked");
                    new CreditCardDao().blocCard(creditCard);
                }
                logger.info("Redirect to /allCardsForUser");
                resp.sendRedirect("/allCardsForUser");
            }
        }

        // if all user button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminAllUsers
        if (req.getParameter("buttonAllUsers") != null) {
            logger.debug("All users button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Set new sort type and redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
        }
        // if unblock requests button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminHomepage
        if (req.getParameter("buttonUnlockRequests") != null) {
            logger.debug("Unlock requests button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }
        // if exchange rate button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminExchangeRate
        if (req.getParameter("buttonExchangeRate") != null) {
            logger.debug("Exchange rate button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /adminExchangeRate");
            resp.sendRedirect("/adminExchangeRate");
        }
    }

}
