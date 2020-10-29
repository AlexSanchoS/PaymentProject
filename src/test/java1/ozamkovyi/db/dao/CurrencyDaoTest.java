package ozamkovyi.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyDaoTest {
    @Test
    public void ClientMapperTest() throws SQLException {
        CurrencyDao.CurrencyMapper currencyMapper = new CurrencyDao.CurrencyMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String name = "Alex";
        float course = 12.3f;
        when(rs.getInt(Fields.CURRENCY__ID)).thenReturn(id);
        when(rs.getString(Fields.CURRENCY__NAME)).thenReturn(name);
        when(rs.getFloat(Fields.CURRENCY__RATE)).thenReturn(course);
        Currency currency = currencyMapper.mapRow(rs);

        Assert.assertEquals(id, currency.getId());
        Assert.assertEquals(name, currency.getName());
        Assert.assertEquals(course, currency.getCourse(), 0.001);
    }

    @Test
    public void ClientMapperForGetAllCurrency() throws SQLException {
        CurrencyDao.CurrencyMapper currencyMapper = new CurrencyDao.CurrencyMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String name = "Alex";
        when(rs.getInt(Fields.CURRENCY__ID)).thenReturn(id);
        when(rs.getString(Fields.CURRENCY__NAME)).thenReturn(name);
        Currency currency = currencyMapper.mapRowForGetAllCurrency(rs);

        Assert.assertEquals(id, currency.getId());
        Assert.assertEquals(name, currency.getName());
    }
}
