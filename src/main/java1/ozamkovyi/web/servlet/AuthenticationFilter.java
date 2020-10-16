package ozamkovyi.web.servlet;


import ozamkovyi.db.entity.User;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.db.entity.Client;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User currentUser = getUser(req, session);
        String requestURI = req.getRequestURI();
        if (requestURI.contains("login")) {
            checkForLoginOrRegistration(request, response, resp, session, currentUser, chain);
            return;
        }

        if (requestURI.contains("registration")) {
            checkForLoginOrRegistration(request, response, resp, session, currentUser, chain);
            return;
        }
        if (requestURI.contains("admin")) {
            checkForAdmin(request, response, resp, session, currentUser, chain);
            return;
        }
        if (requestURI.contains("client")) {
            checkForClient(request, response, resp, session, currentUser, chain);
            return;
        }
        chain.doFilter(request, response);
    }

    private User getUser(HttpServletRequest req, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies.length > 1) {
                String login = null;
                String password = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("login")) {
                        login = cookie.getValue();
                        continue;
                    }
                    if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                        continue;
                    }
                }
                if ((login != null) && (password != null)) {
                    currentUser = ClientDao.findClientByLoginAndPassword(login, password);
                    session.setAttribute("currentUser", currentUser);
                    if (currentUser == null) {
                        currentUser = AdminDao.findAdminByLoginAndPassword(login, password);
                        session.setAttribute("currentUser", currentUser);

                    }
                }
            }
        }
        return currentUser;
    }

    private void checkForLoginOrRegistration(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            if (locale == null) {
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            chain.doFilter(request, response);
        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    resp.sendRedirect("/clientHomepage");
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                resp.sendRedirect("/adminHomepage");
            }
        }
    }

    private void checkForAdmin(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            if (locale == null) {
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            resp.sendRedirect("/login");

        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    resp.sendRedirect("/clientHomepage");
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                chain.doFilter(request, response);

            }
        }
    }

    private void checkForClient(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            if (locale == null) {
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            resp.sendRedirect("/login");
        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect("/login");
                }
            } else {
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                resp.sendRedirect("/adminHomepage");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
