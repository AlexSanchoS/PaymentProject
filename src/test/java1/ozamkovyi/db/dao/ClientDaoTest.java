package ozamkovyi.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientDaoTest {
    @Test
    public void ClientMapperTest() throws SQLException {
        ClientDao.ClientMapper clientMapperMapper = new ClientDao.ClientMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String login = "testLogin";
        String password = "testPassword";
        String name = "testName";
        String date = "1997-01-08";
        String language = "ua";
        String status = Fields.CLIENT_STATUS__UNBLOCK;

        when(rs.getInt(Fields.CLIENT__ID)).thenReturn(id);
        when(rs.getString(Fields.CLIENT__LOGIN)).thenReturn(login);
        when(rs.getString(Fields.CLIENT__PASSWORD)).thenReturn(password);
        when(rs.getString(Fields.CLIENT__NAME)).thenReturn(name);
        when(rs.getString(Fields.CLIENT__DATE_OF_BIRTH)).thenReturn(date);
        when(rs.getString(Fields.CLIENT__LANGUAGE)).thenReturn(language);
        when(rs.getString(Fields.CLIENT__STATUS)).thenReturn(status);
        Client client = clientMapperMapper.mapRow(rs);

        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(login, client.getLogin());
        Assert.assertEquals(password, client.getPassword());
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(language, client.getLanguage());
        Assert.assertEquals(status, client.getStatus());
    }

    @Test
    public void ClientBeanMapperTest() throws SQLException {
        ClientDao.ClientBeanMapper clientMapperMapper = new ClientDao.ClientBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String login = "testLogin";
        String password = "testPassword";
        String name = "testName";
        String date = "1997-01-08";
        String language = "ua";
        String status = Fields.CLIENT_STATUS__UNBLOCK;

        when(rs.getInt(Fields.CLIENT__ID)).thenReturn(id);
        when(rs.getString(Fields.CLIENT__LOGIN)).thenReturn(login);
        when(rs.getString(Fields.CLIENT__PASSWORD)).thenReturn(password);
        when(rs.getString(Fields.CLIENT__NAME)).thenReturn(name);
        when(rs.getString(Fields.CLIENT__DATE_OF_BIRTH)).thenReturn(date);
        when(rs.getString(Fields.CLIENT__LANGUAGE)).thenReturn(language);
        when(rs.getString(Fields.CLIENT__STATUS)).thenReturn(status);
        ClientBean client = clientMapperMapper.mapRow(rs);

        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(login, client.getLogin());
        Assert.assertEquals(password, client.getPassword());
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(language, client.getLanguage());
        Assert.assertEquals(status, client.getStatus());
    }

    @Test
    public void ClientBeanMapperMapRowForListOfClientForAdmin() throws SQLException {
        ClientDao.ClientBeanMapper clientMapperMapper = new ClientDao.ClientBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String name = "testName";
        String status = Fields.CLIENT_STATUS__UNBLOCK;

        when(rs.getInt(Fields.CLIENT__ID)).thenReturn(id);
        when(rs.getString(Fields.CLIENT__NAME)).thenReturn(name);
        when(rs.getString(Fields.CLIENT__STATUS)).thenReturn(status);
        ClientBean client = clientMapperMapper.mapRowForListOfClientForAdmin(rs);

        Assert.assertEquals(id, client.getId());
        Assert.assertEquals(name, client.getName());
        Assert.assertEquals(status, client.getStatus());
    }
}
