package ozamkovyi.web.servlet.adminServlets;

import org.junit.Assert;
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

public class AdminHomepageServletTest {

    @Test
    public void shouldRedirectToAdminHomepageNextPage() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
        Assert.assertEquals(1, session.getAttribute("pageNumber"));
    }

    @Test
    public void shouldRedirectToAdminHomepagePreviousPage() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
        Assert.assertEquals(2, session.getAttribute("pageNumber"));
    }

    @Test
    public void shouldRedirectToAdminAllUsers() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonAllUsers")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }

    @Test
    public void shouldRedirectToAdminExchangeRate() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonExchangeRate")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminExchangeRate");
    }

    @Test
    public void shouldRedirectToAdminHomepageSortByName() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminHomepage");

    }

    @Test
    public void shouldRedirectToAdminHomepageSortByName2() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }
    @Test
    public void shouldRedirectToAdminHomepageSortByNameNull() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }

    @Test
    public void shouldRedirectToAdminHomepageSortByNumber() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminHomepage");

    }

    @Test
    public void shouldRedirectToAdminHomepageSortByNumber2() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }
    @Test
    public void shouldRedirectToAdminHomepageSortByNumberNull() throws ServletException, IOException {
        AdminHomepageServlet servlet = new AdminHomepageServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<BankAccountBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByNumber")).thenReturn("ok");
        when(session.getAttribute("listOfBankAccountForUnlock")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }



}
