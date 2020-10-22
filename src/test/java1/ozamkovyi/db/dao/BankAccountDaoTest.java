package ozamkovyi.db.dao;

import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.entity.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankAccountDaoTest {
    @Test
    public void bankAccountMapperTest() throws SQLException {
        BankAccountDao.BankAccountMapper bankAccountMapper = new BankAccountDao.BankAccountMapper();
        ResultSet rs = mock(ResultSet.class);
        String number = "12123123123123123123";
        long balance = 123L;
        int currencyId = 1;
        int userId = 2;
        int accountStatusId = 3;

        when(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER)).thenReturn(number);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID)).thenReturn(currencyId);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID)).thenReturn(accountStatusId);
        BankAccount bankAccount = bankAccountMapper.mapRow(rs);

        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyId, bankAccount.getCurrencyId());
        assertEquals(userId, bankAccount.getUserId());
        assertEquals(accountStatusId, bankAccount.getAccountStatusId());
    }

    @Test
    public void bankAccountBeanMapperTest() throws SQLException {
        BankAccountDao.BankAccountBeanMapper bankAccountMapper = new BankAccountDao.BankAccountBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        String number = "12123123123123123123";
        long balance = 123L;
        int currencyId = 1;
        int userId = 2;
        int accountStatusId = 3;

        when(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER)).thenReturn(number);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID)).thenReturn(currencyId);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID)).thenReturn(accountStatusId);
        BankAccountBean bankAccount = bankAccountMapper.mapRow(rs);

        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyId, bankAccount.getCurrencyId());
        assertEquals(userId, bankAccount.getUserId());
        assertEquals(accountStatusId, bankAccount.getAccountStatusId());
    }

    @Test
    public void bankAccountBeanMapperMapRowForGetAllAccount() throws SQLException {
        BankAccountDao.BankAccountBeanMapper bankAccountMapper = new BankAccountDao.BankAccountBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        String number = "12123123123123123123";
        long balance = 123L;
        String currencyName = "UAH";
        int userId = 2;

        when(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER)).thenReturn(number);
        when(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME)).thenReturn(currencyName);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID)).thenReturn(userId);
        BankAccountBean bankAccount = bankAccountMapper.mapRowForGetAllAccount(rs);

        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyName, bankAccount.getCurrencyName());
        assertEquals(userId, bankAccount.getUserId());
    }

    @Test
    public void bankAccountBeanMapperMapRowForGetAccountList() throws SQLException {
        BankAccountDao.BankAccountBeanMapper bankAccountMapper = new BankAccountDao.BankAccountBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        String number = "12123123123123123123";
        long balance = 123L;
        int currencyId = 1;
        String currencyName = "UAH";
        String accountStatusName = "block";
        int accountStatusId = 2;

        when(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER)).thenReturn(number);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID)).thenReturn(currencyId);
        when(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME)).thenReturn(currencyName);
        when(rs.getString(Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS)).thenReturn(accountStatusName);
        when(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID)).thenReturn(accountStatusId);
        BankAccountBean bankAccount = bankAccountMapper.mapRowForGetAccountList(rs);

        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyId, bankAccount.getCurrencyId());
        assertEquals(currencyName, bankAccount.getCurrencyName());
        assertEquals(accountStatusName, bankAccount.getAccountStatusName());
        assertEquals(accountStatusId, bankAccount.getAccountStatusId());
    }

    @Test
    public void bankAccountBeanMapperMapRowForGetAccountListForUnlock() throws SQLException {
        BankAccountDao.BankAccountBeanMapper bankAccountMapper = new BankAccountDao.BankAccountBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        String number = "12123123123123123123";
        long balance = 123L;
        String currencyName = "UAH";
        String clientName = "Alex";

        when(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER)).thenReturn(number);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getString(Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME)).thenReturn(clientName);
        when(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME)).thenReturn(currencyName);
        BankAccountBean bankAccount = bankAccountMapper.mapRowForGetAccountListForUnlock(rs);

        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyName, bankAccount.getCurrencyName());
        assertEquals(clientName, bankAccount.getClientName());
    }
}
