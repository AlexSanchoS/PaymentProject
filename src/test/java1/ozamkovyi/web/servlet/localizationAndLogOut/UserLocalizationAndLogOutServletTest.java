package ozamkovyi.web.servlet.localizationAndLogOut;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserLocalizationAndLogOutServletTest {
    @Test
    public void logOut() throws ServletException, IOException {
        UserLocalizationAndLogOutServlet servlet = new UserLocalizationAndLogOutServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("logOut")).thenReturn("ok");
        servlet.doGet(request, response);

        verify(response, times(1)).sendRedirect(anyString());
    }
}
