package ozamkovyi.web.servlet.clientServlets;

import org.testng.annotations.Test;
import ozamkovyi.web.servlet.LoginServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ClientHomepageServletTest {
    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        ClientHomepageServlet servlet = new ClientHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/jsp/clientHomepage.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/jsp/clientHomepage.jsp");
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void shouldRedirectToClientAccountMenu() throws ServletException, IOException {

        ClientHomepageServlet servlet = new ClientHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("buttonMyAccount")).thenReturn("ok");

        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenu() throws ServletException, IOException {

        ClientHomepageServlet servlet = new ClientHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("buttonMyCard")).thenReturn("ok");

        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenu() throws ServletException, IOException {

        ClientHomepageServlet servlet = new ClientHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("buttonMyPayment")).thenReturn("ok");

        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }
}
