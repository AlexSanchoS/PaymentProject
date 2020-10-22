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

public class ClientCardMenuServletTest {
    @Test
    public void shouldRedirectToClientCardMenuNextPage() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenuPreviousPage() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenuSortByCurrency() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientCardMenu");

    }

    @Test
    public void shouldRedirectToClientCardMenuSortByCurrency2() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenuSortByCurrencyNull() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("currency")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }


    @Test
    public void shouldRedirectToClientCardMenuSortByBalance() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientCardMenu");

    }

    @Test
    public void shouldRedirectToClientCardMenuSortByBalance2() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenuSortByBalanceNull() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("balance")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenu() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyAccount")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenu() throws ServletException, IOException {
        ClientCardMenuServlet servlet = new ClientCardMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyPayment")).thenReturn("ok");
        when(session.getAttribute("listOfCreditCard")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

}
