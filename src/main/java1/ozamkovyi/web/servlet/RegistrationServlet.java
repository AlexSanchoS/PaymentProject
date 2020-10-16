package ozamkovyi.web.servlet;

import ozamkovyi.db.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {


    private final int COOKIE_MAX_AGE = 10 * 60;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client client = new Client();
        req.setCharacterEncoding("UTF-8");
        client.createNewClientByRequest(req, (String) session.getAttribute("locale"));
        if (client.clientIsAdults()) {
            session.setAttribute("clientNotAdults", "true");
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else {
            client.addNewClientToDB();
            session.setAttribute("currentUser", client);
            Cookie cookieLogin = new Cookie("login", req.getParameter("loginLabel"));
            Cookie cookiePassword = new Cookie("password", req.getParameter("passwordLabel"));
            cookieLogin.setMaxAge(COOKIE_MAX_AGE);
            cookiePassword.setMaxAge(COOKIE_MAX_AGE);
            resp.addCookie(cookieLogin);
            resp.addCookie(cookiePassword);
            getServletContext().getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
        }

    }

}
