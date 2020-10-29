package ozamkovyi.web.servlet.clientServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.CreditCardBean;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for client card menu page
 *
 * @author O. Zamkovyi
 */
public class ClientCardMenuServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ClientCardMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
        // if session attribute 'pageNumber' = null
        // then set session attributes 'pageNumber' and 'sortType' with values 1
        Object page = session.getAttribute("pageNumber");
        int pageNumber = 0;
        int sortType = 0;
        ArrayList<CreditCardBean> listOfCreditCard = null;
        if (page == null) {
            logger.trace("page number = null");
            session.setAttribute("pageNumber", 1);
            session.setAttribute("sortType", 1);
            System.out.println("page = null");
            sortType = 1;
            pageNumber = 1;
            listOfCreditCard = new CreditCardDao().getCardList(currentUser.getId(), pageNumber, sortType);
            for (CreditCardBean creditCard : listOfCreditCard) {
                if (!creditCard.isValid()) {
                    new CreditCardDao().blocCard(creditCard);
                }
            }
        } else {
            pageNumber = (int) page;
            sortType = (int) session.getAttribute("sortType");
            listOfCreditCard = new CreditCardDao().getCardList(currentUser.getId(), pageNumber, sortType);
            logger.trace("page number =" + pageNumber + " sort type = " + sortType);
        }

        // set session attributes 'countAccount' with the number of credit card
        logger.debug("set session attribute 'countCard'");
        session.setAttribute("countCard", new CreditCardDao().getCountCardByClient(currentUser.getId()));
        // set session attributes 'listOfCreditCard' with array list of credit card
        logger.debug("set session attribute 'listOfCreditCard'");
        session.setAttribute("listOfCreditCard", listOfCreditCard);
        // set session attributes 'listOfAccountForCreditCard' with array list of available account for new credit card
        logger.debug("set session attribute 'listOfAccountForCreditCard'");
        session.setAttribute("listOfAccountForCreditCard",  new BankAccountDao().getAllAccount(currentUser));
        logger.info("forward to /jsp/clientCardMenu.jsp");
        req.getRequestDispatcher("/jsp/clientCardMenu.jsp").forward(req, resp);


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
            logger.info("Set new page number and redirect to /clientCardMenu");
            resp.sendRedirect("/clientCardMenu");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /clientCardMenu");
            resp.sendRedirect("/clientCardMenu");
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
            logger.info("Redirect to /clientCardMenu");
            resp.sendRedirect("/clientCardMenu");
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
            logger.info("Redirect to /clientCardMenu");
            resp.sendRedirect("/clientCardMenu");
        }

        //chek all card for block/unblock requests
        ArrayList<CreditCardBean> listOfCreditCard = (ArrayList<CreditCardBean>) session.getAttribute("listOfCreditCard");
        for (CreditCardBean creditCard : listOfCreditCard) {
            if (req.getParameter("blocButton " + creditCard.getId()) != null) {
                logger.debug("Block/unblock button is pressed for card " + creditCard.getId());
                logger.trace("Account status = " + creditCard.getCardStatusName());
                if (creditCard.getCardStatusName().equals(Fields.CARD_STATUS__BLOCKED)) {
                    logger.debug("Set credit card " + creditCard.getId() + " status unblock");
                    new CreditCardDao().unblockCard(creditCard);
                } else {
                    logger.debug("Set credit card " + creditCard.getId() + " status block");
                    new CreditCardDao().blocCard(creditCard);
                }
                logger.info("Redirect to /clientCardMenu");
                resp.sendRedirect("/clientCardMenu");
            }
        }

        // if my account button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /clientAccountMenu
        if (req.getParameter("buttonMyAccount") != null) {
            logger.debug("My account button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
        }
        // if my payment button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /clientPaymentMenu
        if (req.getParameter("buttonMyPayment") != null) {
            logger.debug("My payment button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }

        // if add card button is pressed then
        // find selected account for new card and add card to DB. redirect to /clientCardMenu
        if (req.getParameter("add_card") != null) {
            logger.debug("Add card button is pressed");
            ArrayList<BankAccountBean> listOfAccountForCreditCard = (ArrayList<BankAccountBean>) session.getAttribute("listOfAccountForCreditCard");
            for (BankAccountBean account : listOfAccountForCreditCard) {
                String selectOption = req.getParameter("accountForNewCard");
                logger.trace("Selected account = " + selectOption);
                if (selectOption.equals(account.getAccountForNewCard())) {
                    logger.debug("Add to db new card for account " + account.getAccountForNewCard());
                    new CreditCardDao().addNewCard(account);
                    logger.info("Redirect to /clientCardMenu");
                    resp.sendRedirect("/clientCardMenu");
                }
            }
        }
    }
}
