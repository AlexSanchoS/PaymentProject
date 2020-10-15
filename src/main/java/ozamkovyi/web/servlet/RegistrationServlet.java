package ozamkovyi.web.servlet;

import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Entity;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Entity currentUser = (Entity) session.getAttribute("currentUser");
        Localization localization = (Localization) session.getAttribute("localization");
        if (currentUser == null) {
            if (localization == null) {
                localization = new Localization();
                session.setAttribute("localization", localization);
            }
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else {
            if (currentUser instanceof Client) {
                localization.setLocal(((Client) currentUser).getLanguage());
                getServletContext().getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
            } else {
                localization.setLocal(((Client) currentUser).getLanguage());
                getServletContext().getRequestDispatcher("/jsp/adminHomepage.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Localization localization = (Localization) session.getAttribute("localization");
        Client client = new Client();
        req.setCharacterEncoding("UTF-8");
        client.createNewClientByRequest(req, localization);
        if (client.clientIsAdults()) {
            session.setAttribute("clientNotAdults", "true");
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else {
            client.addNewClientToDB();
            session.setAttribute("currentUser", client);
            getServletContext().getRequestDispatcher("/jsp/clientHomepage.jsp").forward(req, resp);
        }

    }

}
