package java1.ozamkovyi.web.servlet.adminServlets;

import java1.ozamkovyi.db.Fields;
import java1.ozamkovyi.db.dao.BankAccountDao;
import java1.ozamkovyi.db.dao.ClientDao;
import java1.ozamkovyi.db.entity.BankAccount;
import java1.ozamkovyi.db.entity.Client;

import java1.ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AllClientServlet extends HttpServlet {

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
        session.setAttribute("countClient", ClientDao.getCountClient());
        ArrayList<Client> listOfClient = ClientDao.getListOfClientForAdmin(pageNumber, sortType);
        session.setAttribute("listOfClient", listOfClient);
        getServletContext().getRequestDispatcher("/jsp/allClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("nextPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber + 1);
            }
            resp.sendRedirect("/adminAllUsers");
        }
        if (req.getParameter("previousPage") != null) {
            Object pageNumber = session.getAttribute("pageNumber");
            if (pageNumber != null) {
                session.setAttribute("pageNumber", (int) pageNumber - 1);
            }
            resp.sendRedirect("/adminAllUsers");
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
            resp.sendRedirect("/adminAllUsers");
        }
        if (req.getParameter("sortByStatus") != null) {
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
            resp.sendRedirect("/adminAllUsers");
        }

        ArrayList<Client> listOfClient = (ArrayList<Client>) session.getAttribute("listOfClient");
        for (Client client : listOfClient) {
            if (req.getParameter("allAccount " + client.getId()) != null) {
                session.setAttribute("pageNumber", null);
                session.setAttribute("sortType", null);
                session.setAttribute("currentClient", client);
                resp.sendRedirect("/adminAllAccountsForUser");
            }
            if (req.getParameter("allCard " + client.getId()) != null) {
                session.setAttribute("pageNumber", null);
                session.setAttribute("sortType", null);
                session.setAttribute("currentClient", client);
                resp.sendRedirect("/adminAllCardsForUser");
            }
            if (req.getParameter("unblockButton " + client.getId()) != null) {
                if (client.getStatus().equals(Fields.CLIENT_STATUS__BLOCK)) {
                    ArrayList<BankAccount> listOfAcc = BankAccountDao.getAccountList(client, 1, 1);
                    for (BankAccount account : listOfAcc) {
                        BankAccountDao.changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__EXPECTATION);
                    }
                    ClientDao.setStatus(client, Fields.CLIENT_STATUS__UNBLOCK);
                } else {
                    ArrayList<BankAccount> listOfAcc = BankAccountDao.getAccountList(client, 1, 1);
                    for (BankAccount account : listOfAcc) {
                        BankAccountDao.changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__BLOCKED);
                    }
                    ClientDao.setStatus(client, Fields.CLIENT_STATUS__BLOCK);
                }
                resp.sendRedirect("/adminAllUsers");
            }
        }

        if (req.getParameter("buttonUnlockRequests") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminHomepage");
        }
    }
}


