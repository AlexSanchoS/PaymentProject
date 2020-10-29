package ozamkovyi.web.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for login exception page
 * @author  O. Zamkovyi
 */
public class ErrorServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ErrorServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Exception servlet");
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");
        logger.error(exception);
        PrintWriter out = resp.getWriter();
        out.write("<html>\n" +
                "<body>\n" +
                "<h3>"+
                "Something was wrong"+
                "</h3>"+
                "</body>\n" +
                "</html>");
    }
}
