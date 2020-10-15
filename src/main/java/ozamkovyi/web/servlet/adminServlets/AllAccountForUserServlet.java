package ozamkovyi.web.servlet.adminServlets;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.CurrencyDao;
import ozamkovyi.db.entity.*;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AllAccountForUserServlet extends HttpServlet {
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
                getServletContext().getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);

            } else {
                localization.setLocal(((Admin) currentUser).getLanguage());
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
                Client currentClient = (Client) session.getAttribute("currentClient");
                session.setAttribute("countAccount", BankAccountDao.getCountBankAccountByUser(currentClient));
                ArrayList<BankAccount> listOfBankAccount = BankAccountDao.getAccountList(currentClient, pageNumber, sortType);
                session.setAttribute("listOfBankAccount", listOfBankAccount);
                ArrayList<Currency> listOfCurrencyForNewAccount = CurrencyDao.getAllCurrency();
                session.setAttribute("listOfCurrencyForNewAccount", listOfCurrencyForNewAccount);


                getServletContext().getRequestDispatcher("/jsp/allAccountForUser.jsp").forward(req, resp);
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
            resp.sendRedirect("/allAccountsForUser");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/allAccountsForUser");
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
            resp.sendRedirect("/allAccountsForUser");
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
            resp.sendRedirect("/allAccountsForUser");
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
            resp.sendRedirect("/allAccountsForUser");
        }


        ArrayList<BankAccount> listOfBankAccount = (ArrayList<BankAccount>) session.getAttribute("listOfBankAccount");
        for (BankAccount bankAccount : listOfBankAccount) {
            if (req.getParameter("blocButton " + bankAccount.getNumber()) != null) {
                if (bankAccount.getAccountStatusName().equals(Fields.ACCOUNT_STATUS__BLOCKED)) {
                    BankAccountDao.changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__EXPECTATION);
                } else {
                    BankAccountDao.changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__BLOCKED);
                }
                resp.sendRedirect("/allAccountsForUser");
            }

            if (req.getParameter("replenishButton " + bankAccount.getNumber()) != null) {
                Object amountOdj = req.getParameter("amount " + bankAccount.getNumber());
                double amount = 0;
                if (amountOdj != null) {
                    amount = Double.parseDouble((String) amountOdj);
                }
                BankAccountDao.addToBalance(bankAccount.getNumber(), amount);
                resp.sendRedirect("/allAccountsForUser");
            }
        }

        if (req.getParameter("buttonAllUsers") != null){
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/allUsers");
        }
        if (req.getParameter("buttonUnlockRequests") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminHomepage");
        }

    }
}
