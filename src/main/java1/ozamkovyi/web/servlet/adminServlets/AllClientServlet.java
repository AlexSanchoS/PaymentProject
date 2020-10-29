package ozamkovyi.web.servlet.adminServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.ClientDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for all client page
 *
 * @author O. Zamkovyi
 */

public class AllClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object page = session.getAttribute("pageNumber");
        int pageNumber = 0;
        int sortType = 0;
        if (page == null) {
            session.setAttribute("pageNumber", 1);
            session.setAttribute("sortType", 1);
            sortType = 1;
            pageNumber = 1;
        } else {
            pageNumber = (int) page;
            sortType = (int) session.getAttribute("sortType");
        }
        session.setAttribute("countClient", new ClientDao().getCountClient());
        session.setAttribute("listOfClient", new ClientDao().getListOfClientForAdmin(pageNumber, sortType));
        getServletContext().getRequestDispatcher("/jsp/allClient.jsp").forward(req, resp);
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
            logger.info("Set new page number and redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
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
            logger.info("Set new sort type and redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
        }
        // if sort by status button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("sortByStatus") != null) {
            logger.debug("Sort by status button is pressed");
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
            logger.info("Set new sort type and redirect to /adminAllUsers");
            resp.sendRedirect("/adminAllUsers");
        }

        //chek all client for all account/all card and block/unblock requests
        ArrayList<ClientBean> listOfClient = (ArrayList<ClientBean>) session.getAttribute("listOfClient");
        for (ClientBean client : listOfClient) {
            if (req.getParameter("allAccount " + client.getId()) != null) {
                logger.debug("All account button for client "+client.getId());
                logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
                session.setAttribute("pageNumber", null);
                session.setAttribute("sortType", null);
                session.setAttribute("currentClient", client);
                logger.info("Redirect to /adminAllAccountsForUser");
                resp.sendRedirect("/adminAllAccountsForUser");
            }
            if (req.getParameter("allCard " + client.getId()) != null) {
                logger.debug("All card button for client "+client.getId());
                logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
                session.setAttribute("pageNumber", null);
                session.setAttribute("sortType", null);
                session.setAttribute("currentClient", client);
                logger.info("Redirect to /adminAllCardsForUser");
                resp.sendRedirect("/adminAllCardsForUser");
            }

            if (req.getParameter("unblockButton " + client.getId()) != null) {
                if (client.getStatus().equals(Fields.CLIENT_STATUS__BLOCK)) {
                    ArrayList<BankAccountBean> listOfAcc = new BankAccountDao().getAccountList(client.getId(), 1, 1);
                    for (BankAccountBean account : listOfAcc) {
                        new BankAccountDao().changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__EXPECTATION);
                    }
                    new ClientDao().setStatus(client, Fields.CLIENT_STATUS__UNBLOCK);
                } else {
                    ArrayList<BankAccountBean> listOfAcc = new BankAccountDao().getAccountList(client.getId(), 1, 1);
                    for (BankAccountBean account : listOfAcc) {
                        new BankAccountDao().changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__BLOCKED);
                    }
                    new ClientDao().setStatus(client, Fields.CLIENT_STATUS__BLOCK);
                }
                resp.sendRedirect("/adminAllUsers");
            }
        }

        // if unblock request button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminHomepage
        if (req.getParameter("buttonUnlockRequests") != null) {
            logger.debug("Unblock request button is pressed");
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


