package ozamkovyi.web.filter;

import org.junit.Test;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.User;
import ozamkovyi.web.servlet.LoginServlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

public class AuthenticationFilterTest {


    @Test
    public void loginTestWhenUserNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = null;
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForLoginOrRegistration", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, user, chain);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void loginTestWhenUserAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Admin admin = new Admin();
        admin.setLanguage("ua");
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForLoginOrRegistration", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, admin, chain);
        verify(resp).sendRedirect("/adminHomepage");
    }

    @Test
    public void checkForAdminWhenUserNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = null;
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForAdmin", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, user, chain);
        verify(resp).sendRedirect("/login");
    }

    @Test
    public void checkForAdminWhenAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Admin admin = new Admin();
        admin.setLanguage("ua");
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForAdmin", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, admin, chain);
        verify(chain).doFilter(request, response);
    }

    @Test
    public void checkForClientWhenUserNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = null;
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForClient", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, user, chain);
        verify(resp).sendRedirect("/login");
    }

    @Test
    public void checkForClientWhenUserAdmin() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ServletException {
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        Admin admin = new Admin();
        admin.setLanguage("ua");
        FilterChain chain = mock(FilterChain.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        Class<?>[] params = new Class<?>[]{ServletRequest.class, ServletResponse.class, HttpServletResponse.class, HttpSession.class, User.class, FilterChain.class};
        Method m = authenticationFilter.getClass().getDeclaredMethod("checkForClient", params);
        m.setAccessible(true);
        m.invoke(authenticationFilter, request, response, resp, session, admin, chain);
        verify(resp).sendRedirect("/adminHomepage");
    }
}
