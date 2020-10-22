package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.BankAccount;

import java.util.Random;
import java.util.ResourceBundle;

public class BankAccountBean extends BankAccount {

    protected String accountStatusName;

    protected String currencyName;

    protected String ClientName;


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
}
