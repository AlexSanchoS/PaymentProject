package ozamkovyi.web.servlet.clientServlets;

import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.CreditCardBean;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.Fields;

import ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientCardMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
        Object page = session.getAttribute("pageNumber");
        int pageNumber = 0;
        int sortType = 0;
        ArrayList<CreditCardBean> listOfCreditCard = null;
        if (page == null) {
            session.setAttribute("pageNumber", 1);
            session.setAttribute("sortType", 1);
            System.out.println("page = null");
            sortType = 1;
            pageNumber = 1;
            listOfCreditCard = new CreditCardDao().getCardList(currentUser, pageNumber, sortType);
            for (CreditCardBean creditCard : listOfCreditCard) {
                if (!creditCard.isValid()) {
                    new CreditCardDao().blocCard(creditCard);
                }
            }
        } else {
            pageNumber = (int) page;
            sortType = (int) session.getAttribute("sortType");
            listOfCreditCard = new CreditCardDao().getCardList(currentUser, pageNumber, sortType);
        }

        session.setAttribute("countCard", new CreditCardDao().getCountCardByUser(currentUser));
        session.setAttribute("listOfCreditCard", listOfCreditCard);
        listOfCreditCard.get(0).getButtonBloc((ResourceBundle) session.getAttribute("resourceBundle"));
        ArrayList<BankAccountBean> listOfAccountForCreditCard = new BankAccountDao().getAllAccount(currentUser);
        session.setAttribute("listOfAccountForCreditCard", listOfAccountForCreditCard);
        getServletContext().getRequestDispatcher("/jsp/clientCardMenu.jsp").forward(req, resp);


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
            resp.sendRedirect("/clientCardMenu");
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
            resp.sendRedirect("/clientCardMenu");
        }

        ArrayList<CreditCardBean> listOfCreditCard = (ArrayList<CreditCardBean>) session.getAttribute("listOfCreditCard");
        for (CreditCardBean creditCard : listOfCreditCard) {
            if (req.getParameter("blocButton " + creditCard.getId()) != null) {
                if (creditCard.getCardStatusName().equals(Fields.CARD_STATUS__BLOCKED)) {
                    new CreditCardDao().unblockCard(creditCard);
                } else {
                    new CreditCardDao().blocCard(creditCard);
                }
                resp.sendRedirect("/clientCardMenu");
            }
        }

        if (req.getParameter("buttonMyAccount") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("buttonMyPayment") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientPaymentMenu");
        }


        if (req.getParameter("add_card") != null) {
            ArrayList<BankAccountBean> listOfAccountForCreditCard = (ArrayList<BankAccountBean>) session.getAttribute("listOfAccountForCreditCard");
            for (BankAccountBean account : listOfAccountForCreditCard) {
                String selectOption = req.getParameter("accountForNewCard");
                if (selectOption.equals(account.getAccountForNewCard())) {
                    new CreditCardDao().addNewCard(account);
                    resp.sendRedirect("/clientCardMenu");
                }
            }
        }
    }
}
