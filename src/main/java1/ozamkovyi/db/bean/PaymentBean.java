package ozamkovyi.db.bean;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.entity.Payment;
import ozamkovyi.web.CalendarProcessing;

import java.util.ResourceBundle;

public class PaymentBean extends Payment {

    private String statusName;

    private String statusNameUkr;

    private String recipientCardNumber;

    private String recipientName;

    private String senderCardNumber;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(String recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getStringDate() {
        return CalendarProcessing.fullDate2String(date);
    }

    public String getStatusNameUkr() {
        return statusNameUkr;
    }

    public void setStatusNameUkr(String statusNameUkr) {
        this.statusNameUkr = statusNameUkr;
    }

    /**
     * Return payment status different locales
     * @param locale
     *      current locale
     * @return payment status
     */

    public String getStatusByLocal(String locale) {
        if (locale.equals("ua")) {
            return statusNameUkr;
        } else {
            return statusName;
        }
    }

    /**
     * Returns content for confirm/repeat button in jsp
     *
     * @param bundle Current bundle
     * @return content for button
     */

    public String getButtonNameByStatus(ResourceBundle bundle) {
        if (statusName.equals(Fields.PAYMENT_STATUS__PREPARED)) {
            return bundle.getString("clientPaymentMenu_jsp.button.confirm");
        } else {
            return bundle.getString("clientPaymentMenu_jsp.button.repeat");
        }
    }

    /**
     * Check transaction valid
     * transaction is valid if:
     * Sender credit card is unblock and valid
     * Recipient credit card is unblock and valid
     * Sender card has enough money
     * @return validation for transaction
     */

    public boolean transactionIfValid() {
        CreditCardBean senderCreditCard = new CreditCardDao().getCardById(senderCardId);
        CreditCardBean recipientCreditCard = new CreditCardDao().getCardById(recipientCardId);
        if (!senderCreditCard.isValid() || !recipientCreditCard.isValid()) {
            return false;
        }
        int senderCardBalance = new CreditCardDao().getUAHBalance(senderCardId);
        if (senderCardBalance < amount * 100) {
            return false;
        }
        new BankAccountDao().addToBalance(senderCreditCard.getBankAccountNumber(), amount * (-1));
        new BankAccountDao().addToBalance(recipientCreditCard.getBankAccountNumber(), amount);
        return true;
    }
}
