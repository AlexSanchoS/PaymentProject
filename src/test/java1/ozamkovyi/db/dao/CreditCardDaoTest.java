package ozamkovyi.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.CreditCardBean;
import ozamkovyi.db.entity.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreditCardDaoTest {
    @Test
    public void CreditCardMapperTest() throws SQLException {
        CreditCardDao.CreditCardMapper creditCardMapper = new CreditCardDao.CreditCardMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String number = "1234123412341234";
        String validity = "2021-02-010";
        String bankAccountNumber = "12345123451234512345";
        int userId = 2;
        int cardStatusId = 3;


        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER)).thenReturn(number);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER)).thenReturn(bankAccountNumber);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY)).thenReturn(validity);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID)).thenReturn(cardStatusId);
        CreditCard creditCard = creditCardMapper.mapRow(rs);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
        Assert.assertEquals(bankAccountNumber, creditCard.getBankAccountNumber());
        Assert.assertEquals(userId, creditCard.getUserId());
        Assert.assertEquals(cardStatusId, creditCard.getCardStatusId());
    }

    @Test
    public void CreditCardMapperForGetAllUnblockCreditCard() throws SQLException {
        CreditCardDao.CreditCardMapper creditCardMapper = new CreditCardDao.CreditCardMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String number = "1234123412341234";

        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER)).thenReturn(number);
        CreditCard creditCard = creditCardMapper.mapRowForGetAllUnblockCreditCard(rs);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
    }

    @Test
    public void CreditCardBeanMapperTest() throws SQLException {
        CreditCardDao.CreditCardBeanMapper creditCardBeanMapper = new CreditCardDao.CreditCardBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String number = "1234123412341234";
        String validity = "2021-02-010";
        String bankAccountNumber = "12345123451234512345";
        int userId = 2;
        int cardStatusId = 3;


        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER)).thenReturn(number);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER)).thenReturn(bankAccountNumber);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY)).thenReturn(validity);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID)).thenReturn(cardStatusId);
        CreditCardBean creditCard = creditCardBeanMapper.mapRow(rs);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
        Assert.assertEquals(bankAccountNumber, creditCard.getBankAccountNumber());
        Assert.assertEquals(userId, creditCard.getUserId());
        Assert.assertEquals(cardStatusId, creditCard.getCardStatusId());
    }

    @Test
    public void CreditCardBeanMapperForGetCardById() throws SQLException {
        CreditCardDao.CreditCardBeanMapper creditCardBeanMapper = new CreditCardDao.CreditCardBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String number = "1234123412341234";
        String validity = "2021-02-010";
        String bankAccountNumber = "12345123451234512345";
        int userId = 2;
        int cardStatusId = 3;
        long balance = 322L;
        String currencyName = "UAH";
        String cardStatusName = "block";


        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY)).thenReturn(validity);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER)).thenReturn(number);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER)).thenReturn(bankAccountNumber);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID)).thenReturn(cardStatusId);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME)).thenReturn(currencyName);
        when(rs.getString(Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS)).thenReturn(cardStatusName);
        CreditCardBean creditCard = creditCardBeanMapper.mapRowForGetCardById(rs);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
        Assert.assertEquals(bankAccountNumber, creditCard.getBankAccountNumber());
        Assert.assertEquals(userId, creditCard.getUserId());
        Assert.assertEquals(cardStatusId, creditCard.getCardStatusId());
        Assert.assertEquals(balance, creditCard.getBalance());
        Assert.assertEquals(cardStatusName, creditCard.getCardStatusName());
    }

    @Test
    public void CreditCardBeanMapperForGetCardList() throws SQLException {
        CreditCardDao.CreditCardBeanMapper creditCardBeanMapper = new CreditCardDao.CreditCardBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        String number = "1234123412341234";
        String validity = "2021-02-010";
        String bankAccountNumber = "12345123451234512345";
        int userId = 2;
        int cardStatusId = 3;
        long balance = 322L;
        String cardStatusName = "block";
        String accountStatusName = "block";


        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY)).thenReturn(validity);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER)).thenReturn(number);
        when(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER)).thenReturn(bankAccountNumber);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID)).thenReturn(userId);
        when(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID)).thenReturn(cardStatusId);
        when(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE)).thenReturn(balance);
        when(rs.getString(Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS)).thenReturn(cardStatusName);
        when(rs.getString(Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS)).thenReturn(accountStatusName);
        CreditCardBean creditCard = creditCardBeanMapper.mapRowForGetCardList(rs);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
        Assert.assertEquals(bankAccountNumber, creditCard.getBankAccountNumber());
        Assert.assertEquals(userId, creditCard.getUserId());
        Assert.assertEquals(cardStatusId, creditCard.getCardStatusId());
        Assert.assertEquals(cardStatusName, creditCard.getCardStatusName());
        Assert.assertEquals(accountStatusName, creditCard.getAccountStatusName());
    }

}
