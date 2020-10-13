package ozamkovyi.db.entity;

import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.web.CalendarProcessing;
import ozamkovyi.web.Localization;

import java.util.Calendar;

public class Payment extends Entity {

    private int id;

    private int number;

    private Calendar date;

    private double amount;

    private int paymentStatusId;

    private int recipientCardId;

    private int senderCardId;

    private String statusName;

    private String recipientCardNumber;

    private String recipientName;

    private String senderCardNumber;


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

    public String getStringDate(){
        return CalendarProcessing.fullDate2String(date);
    }


    public String getButtonNameByStatus(Localization localization){
        if (statusName.equals(Fields.PAYMENT_STATUS__PREPARED)){
            return localization.getClientPaymentMenuButtonConfirm();
        }else{
            return localization.getClientPaymentMenuButtonRepeat();
        }
    }

    public boolean transactionIfValid(){
        CreditCard senderCreditCard =CreditCardDao.getCardById(senderCardId);
        CreditCard recipientCreditCard =CreditCardDao.getCardById(recipientCardId);
        if (!senderCreditCard.isValid()||!recipientCreditCard.isValid()){
            return false;
        }
        int senderCardBalance = CreditCardDao.getUAHBalance(senderCardId);
        if (senderCardBalance<amount*100){
            return false;
        }
        BankAccountDao.addToBalance(senderCreditCard.getBankAccountNumber(), amount*(-1));
        BankAccountDao.addToBalance(recipientCreditCard.getBankAccountNumber(), amount);
        return true;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "number=" + number +
                ", date=" + date +
                ", amount=" + amount +
                ", paymentStatusId=" + paymentStatusId +
                ", recipientAccountNumber=" + recipientCardId +
                ", senderAccountNumber=" + senderCardId +
                '}';
    }
}
