package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.CreditCard;
import ozamkovyi.web.CalendarProcessing;

import java.util.Random;
import java.util.ResourceBundle;

public class CreditCardBean extends CreditCard {
    private String currency;

    private long balance;

    private String cardStatusName;

    private String accountStatusName;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCardStatusName() {
        return cardStatusName;
    }

    public void setCardStatusName(String cardStatusName) {
        this.cardStatusName = cardStatusName;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public String getDisabled() {
        if ((accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED) && isValid())) {
            return "";
        } else {
            return "disabled";
        }
    }

    public String getButtonBloc(ResourceBundle bundle) {
        if (cardStatusName.equals(Fields.CARD_STATUS__BLOCKED)) {
            return bundle.getString("clientCardMenu_jsp.button.unblock");
        } else {
            return bundle.getString("clientCardMenu_jsp.button.bloc");
        }
    }

    public double getBalanceDouble() {
        return ((double) balance) / 100;
    }

    public static String generatorCardNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(FIRST_PART_FOR_CARD_NUMBER);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append((random.nextInt(9)));
        }
        return sb.toString();
    }

    public boolean isValid() {
        return CalendarProcessing.isCardValid(this);
    }
}
