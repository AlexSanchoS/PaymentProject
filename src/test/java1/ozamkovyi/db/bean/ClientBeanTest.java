package ozamkovyi.db.bean;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.ClientDao;
import ozamkovyi.db.entity.Client;
import ozamkovyi.web.CalendarProcessing;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientBeanTest {

    @Test
    public void cleanGetAndSetTest() {
        Client client = new Client();
        int id = 1;
        String login = "testLogin";
        String password = "testPassword";
        String name = "testName";
        Calendar date = Calendar.getInstance();
        String language = "ua";
        String status = Fields.CLIENT_STATUS__UNBLOCK;

        client.setId(id);
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setDate(date);
        client.setLanguage(language);
        client.setStatus(status);

        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(login, client.getLogin());
        Assert.assertEquals(password, client.getPassword());
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(date, client.getDate());
        Assert.assertEquals(language, client.getLanguage());
        Assert.assertEquals(status, client.getStatus());

    }

    @Test
    public void cleanBeanGetAndSetTest() {
        ClientBean client = new ClientBean();
        int accountCount = 10;
        int creditCardCount = 12;

        client.setAccountCount(accountCount);
        client.setCreditCardCount(creditCardCount);

        Assert.assertEquals(accountCount, client.getAccountCount());
        Assert.assertEquals(creditCardCount, client.getCreditCardCount());
    }

    @Mock
    HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    public void createNewClientByRequestTest() {
        Client client = new Client();
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("date")).thenReturn("1997-01-08");
        client.createNewClientByRequest(request, "ua");
        Assert.assertEquals("login", client.getLogin());
    }

    @Test
    public void clientIsAdultsTest() {
        Client client = new Client();
        Calendar calendar = new GregorianCalendar();
        //CalendarProcessing.string2Date("1997-01-01");
        calendar.set(2001, 1, 1);
        client.setDate(calendar);
        Assert.assertTrue(client.clientIsAdults());
        calendar.set(2004, 1, 1);
        client.setDate(calendar);
        Assert.assertFalse(client.clientIsAdults());
        calendar.set(2002, 1, 1);
        client.setDate(calendar);
        Assert.assertTrue(client.clientIsAdults());
        calendar.set(2002, 12, 1);
        client.setDate(calendar);
        Assert.assertFalse(client.clientIsAdults());
        calendar.set(2002, 10, 28);
        client.setDate(calendar);
        Assert.assertTrue(client.clientIsAdults());
        calendar.set(2004, 10, 1);
        client.setDate(calendar);
        Assert.assertFalse(client.clientIsAdults());
    }

    @Test
    public void getBlocButtonTest(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
        ClientBean clientBean = new ClientBean();
        clientBean.setStatus(Fields.CLIENT_STATUS__BLOCK);
        Assert.assertEquals("Unblock", clientBean.getBlocButton(resourceBundle));
        clientBean.setStatus(Fields.CLIENT_STATUS__UNBLOCK);
        Assert.assertEquals("Block", clientBean.getBlocButton(resourceBundle));

    }
}
