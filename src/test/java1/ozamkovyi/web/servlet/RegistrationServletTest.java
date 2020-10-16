package ozamkovyi.web.servlet;

import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
