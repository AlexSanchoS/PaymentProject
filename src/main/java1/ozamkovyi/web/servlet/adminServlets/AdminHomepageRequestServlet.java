package ozamkovyi.web.servlet.adminServlets;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.entity.BankAccount;

import ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminHomepageRequestServlet extends HttpServlet {
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

        session.setAttribute("countAccount", BankAccountDao.getCountBankAccountForUnlock());
        ArrayList<BankAccount> listOfBankAccountForUnlock = BankAccountDao.getAccountListForUnlock(pageNumber, sortType);
        session.setAttribute("listOfBankAccountForUnlock", listOfBankAccountForUnlock);
        getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/adminHomepage");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/adminHomepage");
        }

        if (req.getParameter("sortByName") != null) {
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
            resp.sendRedirect("/adminHomepage");
        }
        if (req.getParameter("sortByNumber") != null) {
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
            resp.sendRedirect("/adminHomepage");
        }

        ArrayList<BankAccount> listOfBankAccountForUnlock = (ArrayList<BankAccount>) session.getAttribute("listOfBankAccountForUnlock");
        for (BankAccount bankAccount : listOfBankAccountForUnlock) {
            if (req.getParameter("unblockButton " + bankAccount.getNumber()) != null) {
                BankAccountDao.changeStatusFotBankAccount(bankAccount, Fields.ACCOUNT_STATUS__UNBLOCKED);
                resp.sendRedirect("/adminHomepage");
            }
        }

        if (req.getParameter("buttonAllUsers") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminAllUsers");
        }
    }
}
