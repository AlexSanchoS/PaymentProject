package ozamkovyi.web.servlet;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoginServletTest {
    private final static String path = "/jsp/login.jsp";

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final LoginServlet servlet = new LoginServlet();

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
    public void shouldRedirectToRegistration()throws ServletException, IOException{
        LoginServlet servlet = new LoginServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("RegistrationButton")).thenReturn("ok");
        servlet.doPost(request, response);


        verify(request, times(1)).getSession();
        verify(response).sendRedirect("/registration");
    }
}
