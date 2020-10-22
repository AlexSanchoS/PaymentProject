package ozamkovyi.db.entity;

import java.util.Calendar;

public class Payment extends Entity {

    protected int id;

    protected int number;

    protected Calendar date;

    protected double amount;

    protected int paymentStatusId;

    protected int recipientCardId;

    protected int senderCardId;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public int getRecipientCardId() {
        return recipientCardId;
    }

    public void setRecipientCardId(int recipientCardId) {
        this.recipientCardId = recipientCardId;
    }

    public int getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(int senderCardId) {
        this.senderCardId = senderCardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
