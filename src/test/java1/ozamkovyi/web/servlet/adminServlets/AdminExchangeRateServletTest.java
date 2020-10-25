package ozamkovyi.web.servlet.adminServlets;

import org.junit.Test;
import ozamkovyi.db.entity.Currency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AdminExchangeRateServletTest {
    @Test
    public void shouldRedirectToAdminExchangeRateNextPage() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }

    @Test
    public void shouldRedirectToExchangeRatePreviousPage() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }

    @Test
    public void shouldRedirectToAdminAllUsers() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonAllUsers")).thenReturn("ok");
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }

    @Test
    public void shouldRedirectToAdminHomepage() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonUnlockRequests")).thenReturn("ok");
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }

    @Test
    public void shouldRedirectToExchangeRateSortByName() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminExchangeRate");

    }

    @Test
    public void shouldRedirectToExchangeRateSortByName2() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }
    @Test
    public void shouldRedirectToExchangeRateSortByNameNull() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }


    @Test
    public void shouldRedirectToExchangeRateSortByRate() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByRate")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminExchangeRate");

    }

    @Test
    public void shouldRedirectToExchangeRateSortByRate2() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByRate")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }
    @Test
    public void shouldRedirectToExchangeRateByRateNull() throws ServletException, IOException {
        AdminExchangeRateServlet servlet = new AdminExchangeRateServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<Currency> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByRate")).thenReturn("ok");
        when(session.getAttribute("listOfCurrency")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }
}
