package ozamkovyi.web.servlet.adminServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.dao.CurrencyDao;
import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for allAccountForClient page
 *
 * @author O. Zamkovyi
 */
public class AllAccountForClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllAccountForClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // if session attribute 'pageNumber' = null
        // then set session attributes 'pageNumber' and 'sortType' with values 1
        Object page = session.getAttribute("pageNumber");
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
        // set session attributes 'countAccount' with the number of bank accounts
        logger.debug("set session attribute 'countAccount'");
        session.setAttribute("countAccount", new BankAccountDao().getCountBankAccountByUser(currentClient.getId()));
        // set session attributes 'listOfBankAccount' with array list of bank accounts
        logger.debug("set session attribute 'listOfBankAccount'");
        session.setAttribute("listOfBankAccount", new BankAccountDao().getAccountList(currentClient.getId(), pageNumber, sortType));
        logger.info("forward to /jsp/allAccountForClient.jsp");
        req.getRequestDispatcher("/jsp/allAccountForClient.jsp").forward(req, resp);
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
            logger.info("Set new page number and redirect to /allAccountsForUser");
            resp.sendRedirect("/allAccountsForUser");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /allAccountsForUser");
            resp.sendRedirect("/allAccountsForUser");
        }

        // if sort by number button is pressed then if session attribute 'sortType' = 1 set 2 else set 1
        if (req.getParameter("sortByNumber") != null) {
            logger.debug("Sort by number button is pressed");
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
            logger.info("Set new sort type and redirect to /allAccountsForUser");
            resp.sendRedirect("/allAccountsForUser");
        }
        // if sort by currency button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("sortByCurrency") != null) {
            logger.debug("Sort by currency button is pressed");
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
            logger.info("Set new sort type and redirect to /allAccountsForUser");
            resp.sendRedirect("/allAccountsForUser");
        }
        // if sort by balance button is pressed then if session attribute 'sortType' = 5 set 6 else set 5
        if (req.getParameter("sortByBalance") != null) {
            logger.debug("Sort by balance button is pressed");
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 5) {
                    logger.trace("Set session attribute 'sortType' = 6 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 6);
                } else {
                    logger.trace("Set session attribute 'sortType' = 5 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 5);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            logger.info("Set new sort type and redirect to /allAccountsForUser");
            resp.sendRedirect("/allAccountsForUser");
        }


        //chek all account for block/unblock and refill requests
        ArrayList<BankAccountBean> listOfBankAccount = (ArrayList<BankAccountBean>) session.getAttribute("listOfBankAccount");
        for (BankAccountBean bankAccount : listOfBankAccount) {
            //block/unblock account
            if (req.getParameter("blocButton " + bankAccount.getNumber()) != null) {
                logger.debug("Block/unblock button is pressed for account " + bankAccount.getNumber());
                logger.trace("Account status = " + bankAccount.getAccountStatusName());
                if (bankAccount.getAccountStatusName().equals(Fields.ACCOUNT_STATUS__BLOCKED)) {
                    logger.debug("Set bank account " + bankAccount.getNumber() + " status expectation");
                    new BankAccountDao().changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__EXPECTATION);
                } else {
                    logger.debug("Set bank account " + bankAccount.getNumber() + " status blocked");
                    new BankAccountDao().changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__BLOCKED);
                }
                logger.info("Redirect to /allAccountsForUser");
                resp.sendRedirect("/allAccountsForUser");
            }

            //refill account
            if (req.getParameter("replenishButton " + bankAccount.getNumber()) != null) {
                logger.debug("Refill button is pressed for account " + bankAccount.getNumber());
                Object amountOdj = req.getParameter("amount " + bankAccount.getNumber());
                double amount = 0;
                if (amountOdj != null) {
                    amount = Double.parseDouble((String) amountOdj);
                }
                logger.debug("Refill bank account " + bankAccount.getNumber() + " for amount " + amount);
                new BankAccountDao().addToBalance(bankAccount.getNumber(), amount);
                logger.info("Redirect to /allAccountsForUser");
                resp.sendRedirect("/allAccountsForUser");
            }
        }

        // if all user button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminAllUsers
        if (req.getParameter("buttonAllUsers") != null) {
            logger.debug("All users button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
        }
        // if unlock requests button is pressed then set session attributes 'sortType' = null
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
