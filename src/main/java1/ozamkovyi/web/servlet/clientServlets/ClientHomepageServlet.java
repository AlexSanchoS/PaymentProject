package ozamkovyi.web.servlet.clientServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientHomepageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if (req.getParameter("buttonMyCard") != null) {
            resp.sendRedirect("/clientCardMenu");
        }
        if (req.getParameter("buttonMyAccount") != null) {
            resp.sendRedirect("/clientAccountMenu");
        }
        if (req.getParameter("buttonMyPayment") != null) {
            resp.sendRedirect("/clientPaymentMenu");
        }


    }
}
