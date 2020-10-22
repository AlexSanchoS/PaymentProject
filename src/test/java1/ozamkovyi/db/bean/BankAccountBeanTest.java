package ozamkovyi.db.bean;

import org.junit.Assert;
import org.testng.annotations.Test;
import ozamkovyi.db.Fields;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class BankAccountBeanTest {

    @Test
    public void bankAccountBeanGetAndSetTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        String accountStatusName = "block";
        String currencyName = "UAH";
        String clientName = "Alex";
        bankAccountBean.setAccountStatusName(accountStatusName);
        bankAccountBean.setCurrencyName(currencyName);
        bankAccountBean.setClientName(clientName);
        assertEquals(accountStatusName, bankAccountBean.getAccountStatusName());
        assertEquals(currencyName, bankAccountBean.getCurrencyName());
        assertEquals(clientName, bankAccountBean.getClientName());
    }

    @Test
    public void getBalanceDoubleTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setBalance(123L);
        assertEquals(1.23, bankAccountBean.getBalanceDouble(),0.001);
    }

    @Test
    public void getButtonBlocTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__UNBLOCKED);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
        assertEquals("Bloc", bankAccountBean.getButtonBloc(resourceBundle));
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__BLOCKED);
        assertEquals("Unblock", bankAccountBean.getButtonBloc(resourceBundle));
    }

    @Test
    public void getDisableTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__UNBLOCKED);
        assertEquals("", bankAccountBean.getDisable());
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__EXPECTATION);
        assertEquals("disabled", bankAccountBean.getDisable());
    }

    @Test
    public void getAccountForNewCardTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        String number = "12345678912345678963";
        bankAccountBean.setNumber(number);
        bankAccountBean.setCurrencyName("UAH");
        bankAccountBean.setBalance(123);
        assertEquals("12345678912345678963 UAH 1.23", bankAccountBean.getAccountForNewCard());
    }

    @Test
    public void generatorCardNumberTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        Assert.assertTrue(bankAccountBean.generatorCardNumber().length() == 20);
    }


    @Test
    public void getDisableRefillTest() {
        BankAccountBean bankAccountBean = new BankAccountBean();
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__UNBLOCKED);
        assertEquals("", bankAccountBean.getDisableRefill());
        bankAccountBean.setAccountStatusName(Fields.ACCOUNT_STATUS__EXPECTATION);
        assertEquals("disabled", bankAccountBean.getDisableRefill());
    }
}
