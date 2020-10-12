package ozamkovyi.web.servlet;

import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Entity;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ClientHomepageServlet extends HttpServlet {
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
                getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("buttonMyCard") != null){
            resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("buttonMyAccount") != null){
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("buttonMyPayment") != null){
            resp.sendRedirect("/clientPaymentMenu");
        }


    }
}
