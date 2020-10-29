package ozamkovyi.web.filter;


import org.apache.log4j.Logger;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.User;
import ozamkovyi.db.dao.AdminDao;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Admin;
import ozamkovyi.web.servlet.clientServlets.ClientAddPaymentServlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Filter for access licence
 *
 * @author O. Zamkovyi
 */
public class AuthenticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User currentUser = getUser(req, session);
        logger.trace("Current user admin = " + (currentUser instanceof Admin));
        String requestURI = req.getRequestURI();
        logger.trace("RequestURI = " + requestURI);
        if (requestURI.contains("login")) {
            logger.trace("RequestURI contains login");
            checkForLoginOrRegistration(request, response, resp, session, currentUser, chain);
            return;
        }
        if (requestURI.contains("registration")) {
            logger.trace("RequestURI contains registration");
            checkForLoginOrRegistration(request, response, resp, session, currentUser, chain);
            return;
        }
        if (requestURI.contains("admin")) {
            logger.trace("RequestURI contains admin");
            checkForAdmin(request, response, resp, session, currentUser, chain);
            return;
        }
        if (requestURI.contains("client")) {
            logger.trace("RequestURI contains client");
            checkForClient(request, response, resp, session, currentUser, chain);
            return;
        }
        logger.debug("Do filter");
        chain.doFilter(request, response);
    }

    /**
     * Check cookies and session attributes and get current user
     * @param req HttpServletRequest for getting cookies
     * @param session HttpSession for getting attributes
     * @return current user
     */
    private User getUser(HttpServletRequest req, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            logger.debug("Current user = null");
            Cookie[] cookies = req.getCookies();
            if (cookies!=null) {
                logger.trace("Cookies != null");
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
                    currentUser = new ClientDao().findClientByLoginAndPassword(login, password);
                    session.setAttribute("currentUser", currentUser);
                    if (currentUser == null) {
                        logger.trace("Current user not Client");
                        currentUser = new AdminDao().findAdminByLoginAndPassword(login, password);
                        session.setAttribute("currentUser", currentUser);
                    }
                }
            }
        }
        return currentUser;
    }

    /**
     * Checks access licence and request URI
     * if current user is null then redirect to request URI
     * if current user is client then redirect to client homepage
     * if current user is admin then redirect to admin homepage
     * @param request ServletRequest
     * @param response ServletResponse
     * @param resp HttpServletResponse
     * @param session HttpSession
     * @param currentUser User
     * @param chain FilterChain
     * @throws ServletException
     * @throws IOException
     */
    private void checkForLoginOrRegistration(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            logger.trace("Current user = null");
            if (locale == null) {
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            logger.debug("do filter");
            chain.doFilter(request, response);
        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    logger.trace("Current user = client");
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    logger.debug("Redirect to /clientHomepage");
                    resp.sendRedirect("/clientHomepage");
                } else {
                    logger.trace("Client is block");
                    logger.debug("Redirect to /login");
                    resp.sendRedirect("/login");
                }
            } else {
                logger.trace("Current user = admin");
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                logger.debug("Redirect to /adminHomepage");
                resp.sendRedirect("/adminHomepage");
            }
        }
    }

    /**
     * Checks access licence and request URI
     * if current user is null then redirect to login page
     * if current user is client then redirect to client homepage
     * if current user is admin then redirect request URI
     * @param request ServletRequest
     * @param response ServletResponse
     * @param resp HttpServletResponse
     * @param session HttpSession
     * @param currentUser User
     * @param chain FilterChain
     * @throws ServletException
     * @throws IOException
     */
    private void checkForAdmin(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            logger.trace("Current user = null");
            if (locale == null) {
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            logger.debug("Redirect to /login");
            resp.sendRedirect("/login");

        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    logger.trace("Current user = client");
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    logger.debug("Redirect to /clientHomepage");
                    resp.sendRedirect("/clientHomepage");
                } else {
                    logger.trace("Client is block");
                    logger.debug("Redirect to /login");
                    resp.sendRedirect("/login");
                }
            } else {
                logger.trace("Current user = admin");
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                logger.debug("do filter");
                chain.doFilter(request, response);
            }
        }
    }
    /**
     * Checks access licence and request URI
     * if current user is null then redirect to login page
     * if current user is client then redirect to request URI
     * if current user is admin then redirect to admin homepage
     * @param request ServletRequest
     * @param response ServletResponse
     * @param resp HttpServletResponse
     * @param session HttpSession
     * @param currentUser User
     * @param chain FilterChain
     * @throws ServletException
     * @throws IOException
     */
    private void checkForClient(ServletRequest request, ServletResponse response, HttpServletResponse resp, HttpSession session, User currentUser, FilterChain chain) throws ServletException, IOException {
        Object locale = session.getAttribute("locale");
        if (currentUser == null) {
            if (locale == null) {
                logger.trace("Current user = null");
                session.setAttribute("locale", "en");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
                session.setAttribute("resourceBundle", resourceBundle);
            }
            logger.debug("Redirect to /login");
            resp.sendRedirect("/login");
        } else {
            if (currentUser instanceof Client) {
                if (((Client) currentUser).isUnblock()) {
                    logger.trace("Current user = client");
                    String locale1 = ((Client) currentUser).getLanguage();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                    session.setAttribute("locale", locale1);
                    session.setAttribute("resourceBundle", resourceBundle);
                    logger.debug("do filter");
                    chain.doFilter(request, response);
                } else {
                    logger.trace("Client is block");
                    logger.debug("Redirect to /login");
                    resp.sendRedirect("/login");
                }
            } else {
                logger.trace("Current user = admin");
                String locale1 = ((Admin) currentUser).getLanguage();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale(locale1));
                session.setAttribute("locale", locale1);
                session.setAttribute("resourceBundle", resourceBundle);
                logger.debug("Redirect to /adminHomepage");
                resp.sendRedirect("/adminHomepage");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
