package ozamkovyi.web.servlet;

import org.testng.annotations.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class ErrorServletTest {
    @Test
    public void whenCallDoGetThenServletPrintMassage() throws ServletException, IOException {

        final ErrorServlet servlet = new ErrorServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter out = mock(PrintWriter.class);

        when(request.getAttribute("javax.servlet.error.exception")).thenReturn(new NullPointerException());
        when(response.getWriter()).thenReturn(out);

        servlet.doGet(request, response);

        verify(request, times(1)).getAttribute(anyString());
        verify(out, times(1)).write(anyString());
    }
}
