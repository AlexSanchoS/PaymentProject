package ozamkovyi.web.servlet.localizationAndLogOut;

import ozamkovyi.web.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginRegistrationLocalizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Localization localization = (Localization) session.getAttribute("localization");
        if (req.getParameter("engButton") != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", new Locale("en", "UK"));
            session.setAttribute("resourceBundle", resourceBundle);
        }
        if (req.getParameter("uaButton") != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", new Locale("ru", "RU"));
            session.setAttribute("resourceBundle", resourceBundle);
        }

        resp.sendRedirect((String) session.getAttribute("currentURL"));

    }
}
