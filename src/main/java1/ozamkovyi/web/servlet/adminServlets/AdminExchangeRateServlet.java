package ozamkovyi.web.servlet.adminServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.dao.CurrencyDao;
import ozamkovyi.db.entity.Currency;
import ozamkovyi.web.servlet.clientServlets.ClientPaymentMenuServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Servlet for currency change rate page
 *
 * @author O. Zamkovyi
 */
public class AdminExchangeRateServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminExchangeRateServlet.class);

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
        // set session attributes 'countCurrency' with the number of currency
        logger.debug("set session attribute 'countCurrency'");
        session.setAttribute("countCurrency", new CurrencyDao().getCountCurrency());
        // set session attributes 'listOfCurrency' with array list of currency
        logger.debug("set session attribute 'listOfCurrency'");
        session.setAttribute("listOfCurrency", new CurrencyDao().getAllCurrencyForChange(pageNumber, sortType));
        logger.info("forward to /jsp/adminExchangeRate.jsp");
        req.getRequestDispatcher("/jsp/adminExchangeRate.jsp").forward(req, resp);
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
            logger.info("Set new page number and redirect to /adminExchangeRate");
            resp.sendRedirect("/adminExchangeRate");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /adminExchangeRate");
            resp.sendRedirect("/adminExchangeRate");
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
            logger.info("Set new sort type and redirect to /adminExchangeRate");
            resp.sendRedirect("/adminExchangeRate");
        }
        // if sort by rate button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("sortByRate") != null) {
            logger.debug("Sort by rate button is pressed");
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
            logger.info("Set new sort type and redirect to /adminExchangeRate");
            resp.sendRedirect("/adminExchangeRate");
        }


        //chek all currency for change reta requests
        ArrayList<Currency> listOfCurrency = (ArrayList<Currency>) session.getAttribute("listOfCurrency");
        for (Currency currency : listOfCurrency) {
            if (req.getParameter("change " + currency.getId()) != null) {
                logger.debug("Change button is pressed for account " + currency.getId());
                Object amountOdj = req.getParameter("amount " + currency.getId());
                double amount = 0;
                if (amountOdj != null) {
                    System.out.println("null");
                    amount = Double.parseDouble((String) amountOdj);
                }
                logger.debug("Change reta currency " + currency.getId() + " for amount " + amount);
                new CurrencyDao().changeRate(currency.getId(), (float) amount);
                logger.info("Redirect to /adminExchangeRate");
                resp.sendRedirect("/adminExchangeRate");
            }
        }


        // if unlock request button is pressed then set session attributes 'sortType' = null
        // and 'pageNumber' = null and redirect to /adminHomepage
        if (req.getParameter("buttonUnlockRequests") != null) {
            logger.debug("Unlock requests button is pressed");
            logger.trace("Set session attribute 'sortType' = null and 'pageNumber' = null");
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            logger.info("Set new sort type and redirect to /adminHomepage");
            resp.sendRedirect("/adminHomepage");
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
    }
}
