package ozamkovyi.web.servlet.localizationAndLogOut;

import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Client;
import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ClientLocalizationAndLogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Localization localization = (Localization) session.getAttribute("localization");
        Client client = (Client) session.getAttribute("currentUser");
        ClientDao clientDao = new ClientDao();
        if (req.getParameter("logOut") != null) {
            session.invalidate();
            resp.sendRedirect("/login");
        } else {
            if (req.getParameter("engButton") != null) {
                localization.setLocal("en");
                client.setLanguage("en");
            }
            if (req.getParameter("uaButton") != null) {
                localization.setLocal("ua");
                client.setLanguage("ua");
            }
            clientDao.setClientLocal(client);
            resp.sendRedirect((String) session.getAttribute("currentURL"));
        }
    }
}
