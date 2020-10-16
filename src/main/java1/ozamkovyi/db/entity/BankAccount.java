package java1.ozamkovyi.db.entity;

import java1.ozamkovyi.db.Fields;

import java.util.Random;
import java.util.ResourceBundle;

public class BankAccount extends Entity {

    private String number;

    private long balance;

    private int currencyId;

    private int userId;

    private int accountStatusId;

    private String accountStatusName;

    private String currencyName;

    private String ClientName;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getBalanceDouble() {
        return ((double) balance) / 100;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getButtonBloc(ResourceBundle bundle) {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED)) {
            return bundle.getString("clientAccountMenu_jsp.button.bloc");
        } else {
            return bundle.getString("clientAccountMenu_jsp.button.unblock");
        }
    }

    public String getDisable() {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__EXPECTATION)) {
            return "disabled";
        } else {
            return "";
        }
    }

    public String getAccountForNewCard() {
        StringBuilder sb = new StringBuilder();
        sb.append(number + " ");
        sb.append(currencyName + " ");
        sb.append(getBalanceDouble());
        return sb.toString();
    }

    public static String generatorCardNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            sb.append((random.nextInt(9)));
        }
        return sb.toString();
    }

    public String getDisableRefill() {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED)) {
            return "";
        } else {
            return "disabled";
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", currencyId=" + currencyId +
                ", userId=" + userId +
                ", accountStatusId=" + accountStatusId +
                '}';
    }


}
