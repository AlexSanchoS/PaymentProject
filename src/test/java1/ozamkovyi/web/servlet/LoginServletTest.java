package ozamkovyi.web.servlet;

import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.entity.Admin;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginServletTest {
    private final static String path = "/jsp/login.jsp";

    @Mock
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    @Mock
    Connection mockConnection;
    @Mock
    HttpSession mockSession = mock(HttpSession.class);
    @Mock
    Context mockContext = mock(Context.class);
    @Mock
    InitialContext mockInitContext = mock(InitialContext.class);
    @Mock
    AdminDao mockAdminDao = mock(AdminDao.class);


    @BeforeTest
    public void setUp() throws SQLException {
        String login = "test";
        String password = "test";
        when(mockRequest.getSession()).thenReturn(mockSession);
        doReturn(new Admin()).when(mockAdminDao).findAdminByLoginAndPassword(login, password);
        when(mockRequest.getParameter("loginButton")).thenReturn("ok");
        when(mockRequest.getParameter("loginLabel")).thenReturn(login);
        when(mockRequest.getParameter("passwordLabel")).thenReturn(password);
    }

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final LoginServlet servlet = new LoginServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void shouldRedirectToRegistration() throws ServletException, IOException {
        LoginServlet servlet = new LoginServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("RegistrationButton")).thenReturn("ok");
        servlet.doPost(request, response);


        verify(request, times(1)).getSession();
        verify(response).sendRedirect("/registration");
    }


    @Test(enabled = false)
    public void shouldRedirectToAdminHomepage() throws ServletException, IOException {
        LoginServlet servlet = new LoginServlet();
        servlet.doPost(mockRequest, mockResponse);
        verify(mockResponse).sendRedirect("/adminHomepage");
    }
}
