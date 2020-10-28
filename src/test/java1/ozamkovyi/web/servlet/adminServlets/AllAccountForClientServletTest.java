package ozamkovyi.web.servlet.adminServlets;

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

public class AllAccountForClientServletTest {
    @Test
    public void shouldRedirectToAllAccountForUserNextPage() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }

    @Test
    public void shouldRedirectToAllAccountForUserPreviousPage() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }

    @Test
    public void shouldRedirectToAdminAllUsers() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonAllUsers")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }
    @Test
    public void shouldRedirectToAdminExchangeRate() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonExchangeRate")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }



    @Test
    public void shouldRedirectToAdminHomepage() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonUnlockRequests")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }

    @Test
    public void shouldRedirectToAllAccountForUserSortByCurrency() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allAccountsForUser");

    }

    @Test
    public void shouldRedirectToAllAccountForUserSortByCurrency2() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }
    @Test
    public void shouldRedirectToAllAccountForUserSortByCurrencyNull() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByCurrency")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }


    @Test
    public void shouldRedirectToAllAccountForUserSortByNumber() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allAccountsForUser");

    }

    @Test
    public void shouldRedirectToAllAccountForUserSortByNumber2() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }
    @Test
    public void shouldRedirectToAllAccountForUserSortByNumberNull() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/allAccountsForUser");
    }

    @Test
    public void shouldRedirectToAllAccountForUserSortByBalance() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(5);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allAccountsForUser");
    }
    @Test
    public void shouldRedirectToAllAccountForUserSortByBalance2() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(6);
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allAccountsForUser");
    }

    @Test
    public void shouldRedirectToAllAccountForUserSortByBalanceNull() throws ServletException, IOException {
        AllAccountForClientServlet servlet = new AllAccountForClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByBalance")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccount")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/allAccountsForUser");

    }
}
