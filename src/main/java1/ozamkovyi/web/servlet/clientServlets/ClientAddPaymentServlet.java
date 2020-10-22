package ozamkovyi.web.servlet.clientServlets;

import ozamkovyi.db.bean.PaymentBean;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.dao.PaymentDao;

import ozamkovyi.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ClientAddPaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client currentUser = (Client) session.getAttribute("currentUser");
        if (session.getAttribute("currentPayment") == null) {
            ArrayList<CreditCard> listOfCreditCard = new CreditCardDao().getAllUnblockCreditCard( currentUser);
            session.setAttribute("listOfCreditCard", listOfCreditCard);
            getServletContext().getRequestDispatcher("/jsp/addNewPayment.jsp").forward(req, resp);
        } else {
            ((PaymentBean) session.getAttribute("currentPayment")).getSenderCardNumber();
            req.getRequestDispatcher("/jsp/addPayment.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String senderNumber = req.getParameter("senderNumber");
        String recipientCardNumber = req.getParameter("recipientCardNumber");
        double amount = Double.parseDouble(req.getParameter("amount"));
        Client client = (Client) session.getAttribute("currentUser");
        new PaymentDao().createNewPayment(client, senderNumber, recipientCardNumber, amount);
        session.setAttribute("currentPayment", null);
        resp.sendRedirect("/clientPaymentMenu");


    }
}
