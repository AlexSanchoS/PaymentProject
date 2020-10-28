package ozamkovyi.db.entity;

import java.util.Calendar;

/**
 * Credit card entity.
 *
 * @author O.Zamkovyi
 *
 */

public class CreditCard extends Entity {

    public static final String FIRST_PART_FOR_CARD_NUMBER = "510621";

    protected int id;

    protected String number;

    protected Calendar validity;

    protected String bankAccountNumber;

    protected int userId;

    protected int cardStatusId;


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

}
