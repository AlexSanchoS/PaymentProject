package ozamkovyi.web.servlet.clientServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.dao.CurrencyDao;
import ozamkovyi.db.entity.Currency;
import ozamkovyi.db.dao.BankAccountDao;

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

public class ClientAccountMenuServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ClientAccountMenuServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
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
        // set session attributes 'countAccount' with the number of bank accounts
        logger.debug("set session attribute 'countAccount'");
        session.setAttribute("countAccount", new BankAccountDao().getCountBankAccountByUser(currentUser.getId()));
        // set session attributes 'listOfBankAccount' with array list of bank accounts
        logger.debug("set session attribute 'listOfBankAccount'");
        session.setAttribute("listOfBankAccount", new BankAccountDao().getAccountList(currentUser.getId(), pageNumber, sortType));
        // set session attributes 'listOfCurrencyForNewAccount' with array list of available currencies for new bank accounts
        logger.debug("set session attribute 'listOfCurrencyForNewAccount'");
        session.setAttribute("listOfCurrencyForNewAccount", new CurrencyDao().getAllCurrency());
        logger.info("forward to /jsp/clientAccountMenu.jsp");
        req.getRequestDispatcher("/jsp/clientAccountMenu.jsp").forward(req, resp);


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
            logger.trace("Old page number = " + pageNumber);
            logger.info("Set new page number and redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " + pageNumber);
            logger.info("Set new page number and redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
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
            logger.info("Set new sort type and redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
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
            logger.info("Set new sort type and redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
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
            logger.info("Set new sort type and redirect to /clientAccountMenu");
            resp.sendRedirect("/clientAccountMenu");
        }

        // if my card button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /clientCardMenu
        if (req.getParameter("buttonMyCard") != null) {
            logger.debug("My card button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /clientCardMenu");
            resp.sendRedirect("/clientCardMenu");
        }
        // if my payment button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /clientPaymentMenu
        if (req.getParameter("buttonMyPayment") != null) {
            logger.debug("My card button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
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
                logger.info("Redirect to /clientAccountMenu");
                resp.sendRedirect("/clientAccountMenu");
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
                logger.info("Redirect to /clientAccountMenu");
                resp.sendRedirect("/clientAccountMenu");
            }
        }

        // if add account button is pressed then
        // find selected currency for new account and add account to DB. redirect to /clientAccountMenu
        if (req.getParameter("add_account") != null) {
            logger.debug("Add account button is pressed");
            ArrayList<Currency> listOfCurrency = (ArrayList<Currency>) session.getAttribute("listOfCurrencyForNewAccount");
            for (Currency currency : listOfCurrency) {
                String selectOption = req.getParameter("currencyForNewAccount");
                logger.trace("Selected currency = " + selectOption);
                if (selectOption.equals(currency.getName())) {
                    ClientBean client = (ClientBean) session.getAttribute("currentUser");
                    logger.debug("Add to db account with currency" + currency.getName());
                    new BankAccountDao().addNewAccount(currency, client);
                    logger.info("Redirect to /clientAccountMenu");
                    resp.sendRedirect("/clientAccountMenu");
                }
            }
        }

    }
}
