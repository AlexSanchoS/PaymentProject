package ozamkovyi.web.servlet.localizationAndLogOut;

import org.junit.Test;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.web.servlet.clientServlets.ClientCardMenuServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoginRegistrationLocalizationServletTest {
    @Test
    public void shouldRedirect() throws ServletException, IOException {
        LoginRegistrationLocalizationServlet servlet = new LoginRegistrationLocalizationServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("engButton")).thenReturn("ok");
        when(request.getParameter("uaButton")).thenReturn("ok");
        when(session.getAttribute("currentURL")).thenReturn("123");
        servlet.doGet(request, response);

        verify(response, times(1)).sendRedirect(anyString());
    }
}
