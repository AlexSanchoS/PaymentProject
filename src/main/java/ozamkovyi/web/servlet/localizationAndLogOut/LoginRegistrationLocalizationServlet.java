package ozamkovyi.web.servlet.localizationAndLogOut;

import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginRegistrationLocalizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Localization localization = (Localization) session.getAttribute("localization");
        if (req.getParameter("engButton") != null) {
            localization.setLocal("en");
        }
        if (req.getParameter("uaButton") != null) {
            localization.setLocal("ua");
        }

        resp.sendRedirect((String) session.getAttribute("currentURL"));

    }
}
