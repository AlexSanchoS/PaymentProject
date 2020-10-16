package ozamkovyi.web.servlet.clientServlets;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.CurrencyDao;
import ozamkovyi.db.entity.BankAccount;
import ozamkovyi.db.entity.Client;
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

public class ClientAccountMenuServlet extends HttpServlet {
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
        session.setAttribute("countAccount", BankAccountDao.getCountBankAccountByUser(currentUser));
        ArrayList<BankAccount> listOfBankAccount = BankAccountDao.getAccountList(currentUser, pageNumber, sortType);
        session.setAttribute("listOfBankAccount", listOfBankAccount);
        ArrayList<Currency> listOfCurrencyForNewAccount = CurrencyDao.getAllCurrency();
        session.setAttribute("listOfCurrencyForNewAccount", listOfCurrencyForNewAccount);
        getServletContext().getRequestDispatcher("/jsp/clientAccountMenu.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/clientAccountMenu");
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
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("sortByCurrency") != null) {
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
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("sortByBalance") != null) {
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
            resp.sendRedirect("/clientAccountMenu");
        }

        if (req.getParameter("buttonMyCard") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("buttonMyPayment") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/clientPaymentMenu");
        }

        ArrayList<BankAccount> listOfBankAccount = (ArrayList<BankAccount>) session.getAttribute("listOfBankAccount");
        for (BankAccount bankAccount : listOfBankAccount) {
            if (req.getParameter("blocButton " + bankAccount.getNumber()) != null) {
                if (bankAccount.getAccountStatusName().equals(Fields.ACCOUNT_STATUS__BLOCKED)) {
                    BankAccountDao.changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__EXPECTATION);
                } else {
                    BankAccountDao.changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__BLOCKED);
                }
                resp.sendRedirect("/clientAccountMenu");
            }

            if (req.getParameter("replenishButton " + bankAccount.getNumber()) != null) {
                Object amountOdj = req.getParameter("amount " + bankAccount.getNumber());
                double amount = 0;
                if (amountOdj != null) {
                    amount = Double.parseDouble((String) amountOdj);
                }
                BankAccountDao.addToBalance(bankAccount.getNumber(), amount);
                resp.sendRedirect("/clientAccountMenu");
            }
        }

        if (req.getParameter("add_account") != null) {
            ArrayList<Currency> listOfCurrency = (ArrayList<Currency>) session.getAttribute("listOfCurrencyForNewAccount");
            for (Currency currency : listOfCurrency) {
                String selectOption = req.getParameter("currencyForNewAccount");
                if (selectOption.equals(currency.getName())) {
                    Client client = (Client) session.getAttribute("currentUser");
                    BankAccountDao.addNewAccount(currency, client);
                    resp.sendRedirect("/clientAccountMenu");
                }
            }
        }

    }
}
