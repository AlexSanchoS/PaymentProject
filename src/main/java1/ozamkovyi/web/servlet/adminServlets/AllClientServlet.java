package ozamkovyi.web.servlet.adminServlets;

import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.ClientDao;

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
        session.setAttribute("countClient", new ClientDao().getCountClient());
        session.setAttribute("listOfClient", new ClientDao().getListOfClientForAdmin(pageNumber, sortType));
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

        ArrayList<ClientBean> listOfClient = (ArrayList<ClientBean>) session.getAttribute("listOfClient");
        for (ClientBean client : listOfClient) {
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
                    ArrayList<BankAccountBean> listOfAcc = new BankAccountDao().getAccountList(client.getId(), 1, 1);
                    for (BankAccountBean account : listOfAcc) {
                        new BankAccountDao().changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__EXPECTATION);
                    }
                    new ClientDao().setStatus(client, Fields.CLIENT_STATUS__UNBLOCK);
                } else {
                    ArrayList<BankAccountBean> listOfAcc = new BankAccountDao().getAccountList(client.getId(), 1, 1);
                    for (BankAccountBean account : listOfAcc) {
                        new BankAccountDao().changeStatusFotBankAccount(account, Fields.ACCOUNT_STATUS__BLOCKED);
                    }
                    new ClientDao().setStatus(client, Fields.CLIENT_STATUS__BLOCK);
                }
                resp.sendRedirect("/adminAllUsers");
            }
        }

        if (req.getParameter("buttonUnlockRequests") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminHomepage");
        }
        if (req.getParameter("buttonExchangeRate") != null) {
            session.setAttribute("pageNumber", null);
            session.setAttribute("sortType", null);
            resp.sendRedirect("/adminExchangeRate");
        }
    }
}


