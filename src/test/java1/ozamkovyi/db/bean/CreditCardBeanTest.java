package ozamkovyi.db.bean;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.CreditCard;
import ozamkovyi.web.CalendarProcessing;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class CreditCardBeanTest {

    @Test
    public void creditCartGetAndSetTest() {
        CreditCard creditCard = new CreditCard();
        int id = 1;
        String number = "1234123412341234";
        Calendar validity = CalendarProcessing.string2Date("2021-02-010");
        String bankAccountNumber = "12345123451234512345";
        int userId = 2;
        int cardStatusId = 3;
        creditCard.setId(id);
        creditCard.setNumber(number);
        creditCard.setValidity(validity);
        creditCard.setBankAccountNumber(bankAccountNumber);
        creditCard.setUserId(userId);
        creditCard.setCardStatusId(cardStatusId);

        Assert.assertEquals(id, creditCard.getId());
        Assert.assertEquals(number, creditCard.getNumber());
        Assert.assertEquals(validity, creditCard.getValidity());
        Assert.assertEquals(bankAccountNumber, creditCard.getBankAccountNumber());
        Assert.assertEquals(userId, creditCard.getUserId());
        Assert.assertEquals(cardStatusId, creditCard.getCardStatusId());
    }

    @Test
    public void creditCartBeanGetAndSetTest() {
        CreditCardBean creditCardBean = new CreditCardBean();
        String currency = "UAH";
        long balance = 123L;
        String cardStatusName = "block";
        String accountStatusName = "unblock";
        creditCardBean.setCurrency(currency);
        creditCardBean.setBalance(balance);
        creditCardBean.setCardStatusName(cardStatusName);
        creditCardBean.setAccountStatusName(accountStatusName);

        Assert.assertEquals(currency, creditCardBean.getCurrency());
        Assert.assertEquals(balance, creditCardBean.getBalance());
        Assert.assertEquals(cardStatusName, creditCardBean.getCardStatusName());
        Assert.assertEquals(accountStatusName, creditCardBean.getAccountStatusName());
    }

    @Test
    public void getDisabledTest() {
        CreditCardBean creditCardBean = new CreditCardBean();
        creditCardBean.setAccountStatusName(Fields.ACCOUNT_STATUS__UNBLOCKED);
        Calendar validity = CalendarProcessing.string2Date("2021-02-010");
        creditCardBean.setValidity(validity);
        Assert.assertEquals("", creditCardBean.getDisabled());
        creditCardBean.setAccountStatusName(Fields.ACCOUNT_STATUS__BLOCKED);
        Assert.assertEquals("disabled", creditCardBean.getDisabled());
    }

    @Test
    public void getButtonBlocTest() {
        CreditCardBean creditCardBean = new CreditCardBean();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
        creditCardBean.setCardStatusName(Fields.CARD_STATUS__BLOCKED);
        Assert.assertEquals("Unblock", creditCardBean.getButtonBloc(resourceBundle));
        creditCardBean.setCardStatusName(Fields.CARD_STATUS__UNBLOCKED);
        Assert.assertEquals("Bloc", creditCardBean.getButtonBloc(resourceBundle));
    }

    @Test
    public void getBalanceDoubleTest() {
        CreditCardBean creditCardBean = new CreditCardBean();
        creditCardBean.setBalance(123L);
        Assert.assertEquals(1.23, creditCardBean.getBalanceDouble(), 0.001);
    }

    @Test
    public void generatorCardNumberTest() {
        CreditCardBean creditCardBean = new CreditCardBean();
        Assert.assertTrue(creditCardBean.generatorCardNumber().length() == 16);
    }
}
