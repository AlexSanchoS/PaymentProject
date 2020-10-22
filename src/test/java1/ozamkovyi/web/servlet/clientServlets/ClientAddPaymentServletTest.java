package ozamkovyi.web.servlet.clientServlets;

import org.junit.Test;
import ozamkovyi.db.bean.PaymentBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ClientAddPaymentServletTest {
    @Test
    public void shouldRedirectToClientAccountMenuNextPage() throws ServletException, IOException {
        ClientAddPaymentServlet servlet = new ClientAddPaymentServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setSenderCardNumber("1234123412341234");

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/jsp/addPayment.jsp")).thenReturn(dispatcher);
        when(session.getAttribute("currentPayment")).thenReturn(paymentBean);
        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

}
