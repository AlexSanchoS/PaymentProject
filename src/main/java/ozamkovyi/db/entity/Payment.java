package ozamkovyi.db.entity;

import ozamkovyi.db.Fields;
import ozamkovyi.web.CalendarProcessing;
import ozamkovyi.web.Localization;

import java.util.Calendar;

public class Payment extends Entity {

    private int id;

    private int number;

    private Calendar date;

    private long amount;

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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
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
        return CalendarProcessing.allDate2String(date);
    }

    public double getDoubleAmount(){
        return ((double) amount)/100;
    }

    public String getButtonNameByStatus(Localization localization){
        if (statusName.equals(Fields.PAYMENT_STATUS__PREPARED)){
            return localization.getClientPaymentMenuButtonConfirm();
        }else{
            return localization.getClientPaymentMenuButtonRepeat();
        }
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
