package ozamkovyi.web.servlet;

import org.junit.Ignore;
import org.testng.annotations.Test;
import ozamkovyi.db.DBManager;
import ozamkovyi.web.CalendarProcessing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    private final static String path = "/jsp/registration.jsp";

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final RegistrationServlet servlet = new RegistrationServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher); //var... args => OngoingStubbing<T> thenReturn(T value, T... values);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void clientNotAdultsTest() throws ServletException, IOException {

        RegistrationServlet servlet = new RegistrationServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(session.getAttribute("locale")).thenReturn("ua");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("date")).thenReturn("2006-02-03");
        servlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
    }




//    @Ignore
    @Test(enabled = false)
    public void shouldRedirectToRegistration()throws ServletException, IOException{
        RegistrationServlet servlet = new RegistrationServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DBManager dbManager = mock(DBManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn("en");
        when(request.getParameter("login")).thenReturn("ok");
        when(request.getParameter("password")).thenReturn("ok");
        when(request.getParameter("name")).thenReturn("ok");
        when(request.getParameter("date")).thenReturn("1997-08-01");
        servlet.doPost(request, response);


        verify(request, times(1)).getSession();
        verify(response).sendRedirect("/registration");
    }
}
