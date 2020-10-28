package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.BankAccount;

import java.util.Random;
import java.util.ResourceBundle;

/**
 * Provide records for:
 * Client account menu
 * All account list for user
 *
 * @author O.Zamkovyi
 */

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

    /**
     * Returns content for block/unblock button in jsp
     *
     * @param bundle Current bundle
     * @return content for button
     */

    public String getButtonBloc(ResourceBundle bundle) {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED)) {
            return bundle.getString("clientAccountMenu_jsp.button.bloc");
        } else {
            return bundle.getString("clientAccountMenu_jsp.button.unblock");
        }
    }

    /**
     * Returns disable for block/unblock button in jsp
     * button disabled when account has status 'expectation'
     *
     * @return disable for button
     */
    public String getDisable() {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__EXPECTATION)) {
            return "disabled";
        } else {
            return "";
        }
    }

    /**
     * Generate title for account
     * title consist of number, currency name and balance
     *
     * @return title for account
     */

    public String getAccountForNewCard() {
        StringBuilder sb = new StringBuilder();
        sb.append(number + " ");
        sb.append(currencyName + " ");
        sb.append(getBalanceDouble());
        return sb.toString();
    }

    /**
     * Generate number for nuw account
     * number consist of 20 digits
     *
     * @return account number
     */

    public static String generatorAccountNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            sb.append((random.nextInt(9)));
        }
        return sb.toString();
    }

    /**
     * Returns disable for refill button in jsp
     * button available when account has status 'unblock'
     *
     * @return disable for button
     */

    public String getDisableRefill() {
        if (accountStatusName.equals(Fields.ACCOUNT_STATUS__UNBLOCKED)) {
            return "";
        } else {
            return "disabled";
        }
    }
}
