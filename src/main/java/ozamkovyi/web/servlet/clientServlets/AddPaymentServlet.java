package ozamkovyi.web.servlet.clientServlets;

import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.PaymentDao;
import ozamkovyi.db.entity.*;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AddPaymentServlet extends HttpServlet {
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
                if (session.getAttribute("currentPayment") == null) {
                    ArrayList<CreditCard> listOfCreditCard = CreditCardDao.getAllUnblockCreditCard((Client) currentUser);
                    session.setAttribute("listOfCreditCard", listOfCreditCard);
                    getServletContext().getRequestDispatcher("/jsp/addNewPayment.jsp").forward(req, resp);
                }else{
                    ((Payment)session.getAttribute("currentPayment")).getSenderCardNumber();
                    getServletContext().getRequestDispatcher("/jsp/addPayment.jsp").forward(req, resp);
                }

            } else {
                localization.setLocal(((Admin) currentUser).getLanguage());
                getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String senderNumber = req.getParameter("senderNumber");
        String recipientCardNumber = req.getParameter("recipientCardNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        Client client = (Client) session.getAttribute("currentUser");
        PaymentDao.createNewPayment(client, senderNumber, recipientCardNumber, amount);
        session.setAttribute("currentPayment", null);
        resp.sendRedirect("/clientPaymentMenu");


    }
}
