package ozamkovyi.web.servlet.clientServlets;

import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.PaymentBean;
import ozamkovyi.db.dao.PaymentDao;

import ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ClientPaymentMenuServlet extends HttpServlet {
    /**
     * Servlet for client payment menu page
     *
     * @author O. Zamkovyi
     */
    private static final Logger logger = Logger.getLogger(ClientPaymentMenuServlet.class);

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
        // set session attributes 'countPayment' with the number of payment
        logger.debug("set session attribute 'countPayment'");
        session.setAttribute("countPayment", new PaymentDao().getCountPaymentByClient(currentUser));
        // set session attributes 'listOfPayment' with array list of payment
        logger.debug("set session attribute 'listOfPayment'");
        session.setAttribute("listOfPayment", new PaymentDao().getPaymentList(currentUser, pageNumber, sortType));
        logger.info("Forward to /jsp/clientPaymentMenu.jsp");
        req.getRequestDispatcher("/jsp/clientPaymentMenu.jsp").forward(req, resp);
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
            logger.info("Set new page number and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }
        // if next page button is pressed then decrement session attribute "page number"
        if (req.getParameter("previousPage") != null) {
            logger.debug("Previous page button is pressed");
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            logger.trace("Old page number = " +pageNumber);
            logger.info("Set new page number and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
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
            logger.info("Set new sort type and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }
        // if sort by date button is pressed then if session attribute 'sortType' = 3 set 4 else set 3
        if (req.getParameter("sortByDate") != null) {
            logger.debug("Sort by date button is pressed");
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
            logger.info("Set new sort type and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }
        // if sort by amount button is pressed then if session attribute 'sortType' = 5 set 6 else set 5
        if (req.getParameter("sortByAmount") != null) {
            logger.debug("Sort by amount button is pressed");
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 5) {
                    logger.trace("Set session attribute 'sortType' = 5 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 6);
                } else {
                    logger.trace("Set session attribute 'sortType' = 6 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 5);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            logger.info("Set new sort type and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
        }
        // if sort by status button is pressed then if session attribute 'sortType' = 7 set 8 else set 7
        if (req.getParameter("sortByStatus") != null) {
            logger.debug("Sort by status button is pressed");
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 7) {
                    logger.trace("Set session attribute 'sortType' = 8 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 8);
                } else {
                    logger.trace("Set session attribute 'sortType' = 7 and 'pageNumber' = 1");
                    session.setAttribute("sortType", 7);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            logger.info("Set new sort type and redirect to /clientPaymentMenu");
            resp.sendRedirect("/clientPaymentMenu");
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

        //chek all payment for confirm/repeat requests
        ArrayList<PaymentBean> listOfPayment = (ArrayList<PaymentBean>) session.getAttribute("listOfPayment");
        for (PaymentBean payment : listOfPayment) {
            if (req.getParameter("blocButton " + payment.getId()) != null) {
                if (payment.getStatusName().equals(Fields.PAYMENT_STATUS__PREPARED)) {
                    logger.debug("Confirm button is pressed for payment " + payment.getId());
                    if (payment.transactionIfValid()) {
                        logger.debug("Change payment " + payment.getId()+" status to sent DB");
                        new PaymentDao().updatePaymentStatus(payment, Fields.PAYMENT_STATUS__SENT);
                    } else {
                        logger.debug("Change payment " + payment.getId()+" status to rejected DB");
                        new PaymentDao().updatePaymentStatus(payment, Fields.PAYMENT_STATUS__REJECTED);
                    }
                    logger.info("Redirect to /clientPaymentMenu");
                    resp.sendRedirect("/clientPaymentMenu");
                } else {
                    logger.debug("Confirm repeat is pressed for payment " + payment.getId());
                    session.setAttribute("currentPayment", payment);
                    logger.info("Redirect to /clientAddPayment");
                    resp.sendRedirect("/clientAddPayment");
                }
            }
        }

        //add new payment
        if (req.getParameter("add_payment") != null) {
            logger.debug("Add new payment button is pressed");
            logger.info("Redirect to /clientAddPayment");
            resp.sendRedirect("/clientAddPayment");
        }
    }
}
