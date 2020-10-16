package java1.ozamkovyi.db.entity;

import java1.ozamkovyi.db.Fields;
import java1.ozamkovyi.web.CalendarProcessing;

import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

public class CreditCard extends Entity {

    public static final String FIRST_PART_FOR_CARD_NUMBER = "510621";

    private int id;

    private String number;

    private Calendar validity;

    private String bankAccountNumber;

    private int userId;

    private int cardStatusId;

    private String currency;

    private long balance;

    private String cardStatusName;

    private String accountStatusName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Calendar getValidity() {
        return validity;
    }

    public void setValidity(Calendar validity) {
        this.validity = validity;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardStatusId() {
        return cardStatusId;
    }

    public void setCardStatusId(int cardStatusId) {
        this.cardStatusId = cardStatusId;
    }

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

    public String getDisabled() {
        if ((accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED) && isValid())) {
            return "";
        } else {
            return "disabled";
        }
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
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

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", validity=" + validity +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", userId=" + userId +
                ", cardStatusId=" + cardStatusId +
                '}';
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
