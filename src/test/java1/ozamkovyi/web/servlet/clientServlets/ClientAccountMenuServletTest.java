package ozamkovyi.web.servlet.clientServlets;

import org.junit.Test;
import ozamkovyi.db.bean.BankAccountBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ClientAccountMenuServletTest {
    @Test
    public void shouldRedirectToClientAccountMenuNextPage() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenuPreviousPage() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByNumber() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientAccountMenu");

    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByNumber2() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByNumberNull() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }


    @Test
    public void shouldRedirectToClientAccountMenuSortByCurrency() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientAccountMenu");

    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByCurrency2() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByCurrencyNull() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }



    @Test
    public void shouldRedirectToClientAccountMenuSortByBalance() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientAccountMenu");

    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByBalance2() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenuSortByBalanceNull() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenu() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyCard")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenu() throws ServletException, IOException {
        ClientAccountMenuServlet servlet = new ClientAccountMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyPayment")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }



}
