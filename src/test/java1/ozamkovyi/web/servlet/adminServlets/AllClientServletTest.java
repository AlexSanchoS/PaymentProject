package ozamkovyi.web.servlet.adminServlets;

import org.junit.Test;
import ozamkovyi.db.bean.ClientBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AllClientServletTest {
    @Test
    public void shouldRedirectToAllUserNextPage() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("nextPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(1);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }

    @Test
    public void shouldRedirectToAllUserPreviousPage() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("previousPage")).thenReturn("ok");
        when(session.getAttribute("pageNumber")).thenReturn(2);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }

    @Test
    public void shouldRedirectToAdminHomepage() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("buttonUnlockRequests")).thenReturn("ok");
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminHomepage");
    }

    @Test
    public void shouldRedirectToAllUserSortByName() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(1);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminAllUsers");

    }
    @Test
    public void shouldRedirectToAllUserSortByName2() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(2);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }
    @Test
    public void shouldRedirectToAllUserSortByCurrencyNull() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByName")).thenReturn("ok");
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }


    @Test
    public void shouldRedirectToAllUserSortByStatus() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByStatus")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(3);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response).sendRedirect("/adminAllUsers");

    }
    @Test
    public void shouldRedirectToAllUserSortByStatus2() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByStatus")).thenReturn("ok");
        when(session.getAttribute("sortType")).thenReturn(4);
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }
    @Test
    public void shouldRedirectToAllUserSortByStatusNull() throws ServletException, IOException {
        AllClientServlet servlet = new AllClientServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        ArrayList<ClientBean> listOfBankAccountForUnlock = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("sortByStatus")).thenReturn("ok");
        when(session.getAttribute("listOfClient")).thenReturn(listOfBankAccountForUnlock);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect("/adminAllUsers");
    }
}
