package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.CreditCard;
import ozamkovyi.web.CalendarProcessing;

import java.util.Random;
import java.util.ResourceBundle;

/**
 * Provide records for:
 * Client card menu
 * All card list for user
 *
 * @author O.Zamkovyi
 */

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

    /**
     * Returns disable for block/unblock button in jsp
     * button available when account has status 'unblock' and card is valid
     *
     * @return disable for button
     */

    public String getDisabled() {
        if ((accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED) && isValid())) {
            return "";
        } else {
            return "disabled";
        }
    }

    /**
     * Returns content for block/unblock button in jsp
     *
     * @param bundle Current bundle
     * @return content for button
     */
    public String getButtonBloc(ResourceBundle bundle) {
        if (cardStatusName.equals(Fields.CARD_STATUS__BLOCKED)) {
            return bundle.getString("clientCardMenu_jsp.button.unblock");
        } else {
            return bundle.getString("clientCardMenu_jsp.button.bloc");
        }
    }

    /**
     * Return double card balance
     * @return double balance
     */
    public double getBalanceDouble() {
        return ((double) balance) / 100;
    }

    /**
     * Generate number for nuw card
     * number consist of 16 digits
     * the first 6 digits are the same for all cards
     * @return card number
     */

    public static String generatorCardNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(FIRST_PART_FOR_CARD_NUMBER);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append((random.nextInt(9)));
        }
        return sb.toString();
    }

    /**
     * Return valid status for card
     * @return card valid status
     */

    public boolean isValid() {
        return CalendarProcessing.isCardValid(this);
    }
}
