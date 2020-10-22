package ozamkovyi.web.servlet.adminServlets;

import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
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

public class AllCardsForUserServlet extends HttpServlet {
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
        ClientBean currentClient = (ClientBean) session.getAttribute("currentClient");
        CreditCardDao creditCardDao = new CreditCardDao();
        session.setAttribute("countCard", creditCardDao.getCountCardByUser(currentClient));
        ArrayList<CreditCardBean> listOfCreditCard = creditCardDao.getCardList(currentClient, pageNumber, sortType);
        session.setAttribute("listOfCreditCard", listOfCreditCard);
        ArrayList<BankAccountBean> listOfAccountForCreditCard = new BankAccountDao().getAllAccount(currentClient);
        session.setAttribute("listOfAccountForCreditCard", listOfAccountForCreditCard);


        getServletContext().getRequestDispatcher("/jsp/allCardsForUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/allCardsForUser");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/allCardsForUser");
        }

        if (req.getParameter("currency") != null) {
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
            resp.sendRedirect("/allCardsForUser");
        }
        if (req.getParameter("balance") != null) {
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
            resp.sendRedirect("/allCardsForUser");
        }

        ArrayList<CreditCardBean> listOfCreditCard = (ArrayList<CreditCardBean>) session.getAttribute("listOfCreditCard");
        for (CreditCardBean creditCard : listOfCreditCard) {
            if (req.getParameter("blocButton " + creditCard.getId()) != null) {
                if (creditCard.getCardStatusName().equals(Fields.CARD_STATUS__BLOCKED)) {
                    new CreditCardDao().unblockCard(creditCard);
                } else {
                    new CreditCardDao().blocCard(creditCard);
                }
                resp.sendRedirect("/allCardsForUser");
            }
        }

        if (req.getParameter("buttonAllUsers") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminAllUsers");
        }
        if (req.getParameter("buttonUnlockRequests") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminHomepage");
        }
    }

}
