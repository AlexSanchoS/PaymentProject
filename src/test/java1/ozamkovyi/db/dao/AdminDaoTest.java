package ozamkovyi.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminDaoTest {

    @Test
    public void adminMapperTest() throws SQLException {
        AdminDao.AdminMapper adminMapper = new AdminDao.AdminMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String login = "testLogin";
        String password = "testPassword";
        String language = "ua";
        when(rs.getInt(Fields.ADMIN__ID)).thenReturn(id);
        when(rs.getString(Fields.ADMIN__LOGIN)).thenReturn(login);
        when(rs.getString(Fields.ADMIN__PASSWORD)).thenReturn(password);
        when(rs.getString(Fields.ADMIN__LANGUAGE)).thenReturn(language);
        Admin admin = adminMapper.mapRow(rs);

        Assert.assertEquals(id, admin.getId());
        Assert.assertEquals(login, admin.getLogin());
        Assert.assertEquals(password, admin.getPassword());
        Assert.assertEquals(language, admin.getLanguage());
    }
}
