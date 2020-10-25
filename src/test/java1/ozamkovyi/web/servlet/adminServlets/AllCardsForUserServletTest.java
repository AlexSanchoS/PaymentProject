package ozamkovyi.web.servlet.adminServlets;

import org.junit.Test;
import ozamkovyi.db.bean.CreditCardBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class AllCardsForUserServletTest {
    @Test
    public void shouldRedirectToAllCardForUserNextPage() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }

    @Test
    public void shouldRedirectToAllCardForUserPreviousPage() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }

    @Test
    public void shouldRedirectToAdminAllUsers() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonAllUsers")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }

    @Test
    public void shouldRedirectToAdminExchangeRate() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonExchangeRate")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }

    @Test
    public void shouldRedirectToAdminHomepage() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonUnlockRequests")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }

    @Test
    public void shouldRedirectToAllCardForUserSortByCurrency() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allCardsForUser");

    }

    @Test
    public void shouldRedirectToAllCardForUserSortByCurrency2() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }
    @Test
    public void shouldRedirectToAllCardForUserSortByCurrencyNull() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }


    @Test
    public void shouldRedirectToAllCardForUserSortByBalance() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allCardsForUser");

    }

    @Test
    public void shouldRedirectToAllCardForUserSortByBalance2() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }
    @Test
    public void shouldRedirectToAllCardForUserSortByBalanceNull() throws ServletException, IOException {
        AllCardsForUserServlet servlet = new AllCardsForUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<CreditCardBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allCardsForUser");
    }

}
