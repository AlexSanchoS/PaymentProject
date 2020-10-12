package ozamkovyi.web.servlet;

import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.*;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ClientCardMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Entity currentUser = (Entity) session.getAttribute("currentUser");
        if (currentUser == null) {
            Localization localization = new Localization();
            session.setAttribute("localization", localization);
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            Localization localization = (Localization) session.getAttribute("localization");
            if (currentUser instanceof Client) {
                localization.setLocal(((Client) currentUser).getLanguage());
                Object page = session.getAttribute("pageNumber");
                int pageNumber = 0;
                int sortType = 0;
                if (page == null) {
                    session.setAttribute("pageNumber", 1);
                    session.setAttribute("sortType", 1);
                    System.out.println("page = null");
                    sortType = 1;
                    pageNumber = 1;
                } else {
                    pageNumber = (int) page;
                    sortType = (int) session.getAttribute("sortType");
                }
                CreditCardDao creditCardDao = new CreditCardDao();
                session.setAttribute("countCard", creditCardDao.getCountCardByUser((Client) currentUser));
                ArrayList<CreditCard> listOfCreditCard = creditCardDao.getCardList((Client) currentUser, pageNumber, sortType);
                session.setAttribute("listOfCreditCard", listOfCreditCard);
                ArrayList<BankAccount> listOfAccountForCreditCard = BankAccountDao.getAllAccount((Client) currentUser);
                session.setAttribute("listOfAccountForCreditCard", listOfAccountForCreditCard);
                getServletContext().getRequestDispatcher("/jsp/clientCardMenu.jsp").forward(req, resp);
            } else {
                localization.setLocal(((Admin) currentUser).getLanguage());
                getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/clientCardMenu");
        }

        if (req.getParameter("currency") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int)sortType;
                if (sort==1){
                    session.setAttribute("sortType", 2);
                }
                else{
                    session.setAttribute("sortType", 1);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("balance") != null) {
            Object sortType = session.getAttribute("sortType");
            if (sortType != null) {
                int sort = (int)sortType;
                if (sort==3){
                    session.setAttribute("sortType", 4);
                }
                else{
                    session.setAttribute("sortType", 3);
                }
            } else {
                session.setAttribute("sortType", null);
            }
            session.setAttribute("pageNumber", 1);
            resp.sendRedirect("/clientCardMenu");
        }

        ArrayList<CreditCard> listOfCreditCard = (ArrayList<CreditCard>) session.getAttribute("listOfCreditCard");
        for (CreditCard creditCard : listOfCreditCard) {
            if (req.getParameter("blocButton " + creditCard.getId()) != null) {
                if (creditCard.getCardStatusName().equals(Fields.CARD_STATUS__BLOCKED)) {
                    CreditCardDao.unblockCard(creditCard);
                } else {
                    CreditCardDao.blocCard(creditCard);
                }
                resp.sendRedirect("/clientCardMenu");
            }
        }

        if (req.getParameter("buttonMyAccount") != null){
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("buttonMyPayment") != null){
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientPaymentMenu");
        }


        if (req.getParameter("add_card")!=null){
            ArrayList<BankAccount> listOfAccountForCreditCard = (ArrayList<BankAccount>) session.getAttribute("listOfAccountForCreditCard");
            for (BankAccount account : listOfAccountForCreditCard) {
                String selectOption = req.getParameter("accountForNewCard");
                if (selectOption.equals(account.getAccountForNewCard())) {
                    CreditCardDao.addNewCard(account);
                    resp.sendRedirect("/clientCardMenu");
                }
            }
        }
    }
}
