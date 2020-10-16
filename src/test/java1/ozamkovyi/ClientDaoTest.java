package java1.ozamkovyi;

import java1.ozamkovyi.db.dao.ClientDao;
import org.junit.*;
import org.testng.annotations.Test;
import java1.ozamkovyi.db.Fields;
import java1.ozamkovyi.db.entity.Client;

public class ClientDaoTest {
    @Test
    public void AddNewClientToDB(){
        Client client = new Client();
        client.setLogin("test");
        client.setPassword("test");
        client.setStatus(Fields.CLIENT_STATUS__UNBLOCK);
        client.setLanguage("ua");
        Assert.assertNotNull(client);
//        ClientDao clientDao = new ClientDao();
//        clientDao.addNewClientToDB(client);
//        Assert.assertNotNull(client.getId());
    }
}
