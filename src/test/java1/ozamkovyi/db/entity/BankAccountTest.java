package ozamkovyi.db.entity;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class BankAccountTest {
    @Test
    public void bankAccountGetAndSetTest() {
        BankAccount bankAccount = new BankAccount();
        String number = "12123123123123123123";
        long balance = 123L;
        int currencyId = 1;
        int userId = 2;
        int accountStatusId = 3;

        bankAccount.setNumber(number);
        bankAccount.setBalance(balance);
        bankAccount.setCurrencyId(currencyId);
        bankAccount.setUserId(userId);
        bankAccount.setAccountStatusId(accountStatusId);
        assertEquals(number, bankAccount.getNumber());
        assertEquals(balance, bankAccount.getBalance());
        assertEquals(currencyId, bankAccount.getCurrencyId());
        assertEquals(userId, bankAccount.getUserId());
        assertEquals(accountStatusId, bankAccount.getAccountStatusId());
    }
}
