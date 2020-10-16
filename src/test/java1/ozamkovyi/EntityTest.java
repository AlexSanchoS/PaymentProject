package ozamkovyi;

import org.junit.*;
import org.testng.annotations.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Client;

import java.util.Calendar;

public class EntityTest {
    @Test
    public void ClientTest() {
        Client client = new Client();
        int id = 1;
        String login = "testLogin";
        String password = "testPassword";
        String name = "testName";
        Calendar date = Calendar.getInstance();
        String language = "ua";
        String status = Fields.CLIENT_STATUS__UNBLOCK;
        int accountCount = 10;
        int creditCardCount= 10;


        client.setId(id);
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setDate(date);
        client.setLanguage(language);
        client.setAccountCount(accountCount);
        client.setCreditCardCount(creditCardCount);
        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(login, client.getLogin());
        Assert.assertEquals(password, client.getPassword());
//        Assert.assertEquals(id, client.getId());
//        Assert.assertEquals(id, client.getId());
//        Assert.assertEquals(id, client.getId());
//        Assert.assertEquals(id, client.getId());
//        Assert.assertEquals(id, client.getId());
    }
}
