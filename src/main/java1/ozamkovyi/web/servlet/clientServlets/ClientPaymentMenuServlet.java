package ozamkovyi.web.servlet.clientServlets;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
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
        session.setAttribute("countPayment", new PaymentDao().getCountPaymentByUser(currentUser));
        ArrayList<PaymentBean> listOfPayment = new PaymentDao().getPaymentList(currentUser, pageNumber, sortType);
        session.setAttribute("listOfPayment", listOfPayment);
        getServletContext().getRequestDispatcher("/jsp/clientPaymentMenu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/clientPaymentMenu");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/clientPaymentMenu");
        }

        if (req.getParameter("sortByNumber") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 1) {
                    session.setAttribute("sortType", 2);
                } else {
                    session.setAttribute("sortType", 1);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientPaymentMenu");
        }

        if (req.getParameter("sortByDate") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 3) {
                    session.setAttribute("sortType", 4);
                } else {
                    session.setAttribute("sortType", 3);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientPaymentMenu");
        }

        if (req.getParameter("sortByAmount") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 5) {
                    session.setAttribute("sortType", 6);
                } else {
                    session.setAttribute("sortType", 5);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientPaymentMenu");
        }

        if (req.getParameter("sortByStatus") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int) sortType;
                if (sort == 7) {
                    session.setAttribute("sortType", 8);
                } else {
                    session.setAttribute("sortType", 7);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientPaymentMenu");
        }

        if (req.getParameter("buttonMyCard") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientCardMenu");
        }

        if (req.getParameter("buttonMyAccount") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientAccountMenu");
        }

        ArrayList<PaymentBean> listOfPayment = (ArrayList<PaymentBean>) session.getAttribute("listOfPayment");
        for (PaymentBean payment : listOfPayment) {
            if (req.getParameter("blocButton " + payment.getId()) != null) {
                if (payment.getStatusName().equals(Fields.PAYMENT_STATUS__PREPARED)) {
                    if (payment.transactionIfValid()) {
                        new PaymentDao().updatePaymentStatus(payment, Fields.PAYMENT_STATUS__SENT);
                    } else {
                        new PaymentDao().updatePaymentStatus(payment, Fields.PAYMENT_STATUS__REJECTED);
                    }
                    resp.sendRedirect("/clientPaymentMenu");
                } else {
                    session.setAttribute("currentPayment", payment);
                    resp.sendRedirect("/clientAddPayment");
                }
            }
        }

        if (req.getParameter("add_payment") != null) {
            resp.sendRedirect("/clientAddPayment");
        }
    }
}
