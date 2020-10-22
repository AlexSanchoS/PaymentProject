package ozamkovyi.web.servlet.clientServlets;

import org.junit.Test;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.PaymentBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ClientPaymentMenuServletTest {
    @Test
    public void shouldRedirectToClientPaymentMenuNextPage() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuPreviousPage() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByNumber() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientPaymentMenu");

    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByNumber2() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByNumberNull() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByDate() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByDate")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientPaymentMenu");

    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByDate2() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByDate")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByDateNull() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByDate")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByAmount() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByAmount")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(5);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/clientPaymentMenu");

    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByAmount2() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByAmount")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(6);
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientPaymentMenuSortByAmountNull() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByAmount")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientPaymentMenu");
    }

    @Test
    public void shouldRedirectToClientAccountMenu() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<PaymentBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyAccount")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAccountMenu");
    }

    @Test
    public void shouldRedirectToClientCardMenu() throws ServletException, IOException {
        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonMyCard")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientCardMenu");
    }


    @Test
    public void shouldRedirectToClientAddPayment() throws ServletException, IOException {

        ClientPaymentMenuServlet servlet = new ClientPaymentMenuServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();


        when(request.getSession()).thenReturn(session);
        when(request.getParameter("add_payment")).thenReturn("ok");
        when(session.getAttribute("listOfPayment")).thenReturn(listOfBankAccountForUnlock);


        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/clientAddPayment");
    }
}
