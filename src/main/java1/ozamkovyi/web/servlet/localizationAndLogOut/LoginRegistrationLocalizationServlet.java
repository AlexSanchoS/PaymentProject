package java1.ozamkovyi.web.servlet.localizationAndLogOut;

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
        if (req.getParameter("engButton") != null) {
            session.setAttribute("locale", "en");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
            session.setAttribute("resourceBundle", resourceBundle);
        }
        if (req.getParameter("uaButton") != null) {
            session.setAttribute("locale", "ua");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("ua"));
            session.setAttribute("resourceBundle", resourceBundle);
        }

        resp.sendRedirect((String) session.getAttribute("currentURL"));

    }
}
