package ozamkovyi.web.servlet.adminServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.dao.BankAccountDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminHomepageServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminHomepageServlet.class);

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
        // set session attributes 'countAccount' with the number of bank accounts
        logger.debug("set session attribute 'countAccount'");
        session.setAttribute("countAccount", new BankAccountDao().getCountBankAccountForUnlock());
        // set session attributes 'listOfCurrencyForNewAccount' with array list of available currencies for new bank accounts
        logger.debug("set session attribute 'listOfCurrencyForNewAccount'");
        session.setAttribute("listOfBankAccountForUnlock", new BankAccountDao().getAccountListForUnlock(pageNumber, sortType));
        logger.info("forward to /jsp/adminHomepage.jsp");
        req.getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
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
            logger.info("Set new page number and redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }

        // if sort by name button is pressed then if session attribute 'sortType' = 1 set 2 else set 1
        if (req.getParameter("sortByName") != null) {
            logger.debug("Sort by name button is pressed");
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
            logger.info("Set new sort type and redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }
        // if sort by number button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("sortByNumber") != null) {
            logger.debug("Sort by number button is pressed");
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
            logger.info("Set new sort type and redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }

        // id group block button is pressed
        if (req.getParameter("groupBlockButton") != null) {
            logger.debug("Group block button is pressed");
            ArrayList<BankAccountBean> listOfBankAccountForUnlock = (ArrayList<BankAccountBean>) session.getAttribute("listOfBankAccountForUnlock");
            for (BankAccountBean bankAccount : listOfBankAccountForUnlock) {
                logger.trace("Account is selected " + bankAccount.getNumber());
                if (req.getParameter("check " + bankAccount.getNumber()) != null) {
                    logger.debug("Set bank account " + bankAccount.getNumber() + " status unblocked");
                    new BankAccountDao().changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__UNBLOCKED);
                }
            }
            logger.info("Redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
        }

        //chek all account for unblock requests
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = (ArrayList<BankAccountBean>) session.getAttribute("listOfBankAccountForUnlock");
        for (BankAccountBean bankAccount : listOfBankAccountForUnlock) {
            if (req.getParameter("unblockButton " + bankAccount.getNumber()) != null) {
                logger.debug("Unblock block button is pressed for account "+ bankAccount.getNumber());
                logger.debug("Set bank account " + bankAccount.getNumber() + " status unblocked");
                new BankAccountDao().changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__UNBLOCKED);
                logger.info("Redirect to /adminHomepage");
                resp.sendRedirect("/adminHomepage");
            }
        }

        // if all users button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminAllUsers
        if (req.getParameter("buttonAllUsers") != null) {
            logger.debug("All users button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
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
