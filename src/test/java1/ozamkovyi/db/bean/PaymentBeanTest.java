package ozamkovyi.db.bean;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.dao.BankAccountDao;
import ozamkovyi.db.dao.CreditCardDao;
import ozamkovyi.db.entity.Payment;
import ozamkovyi.web.CalendarProcessing;

import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentBeanTest {

    @Test
    public void paymentGetAndSetTest(){
        Payment payment = new Payment();
        int id = 1;
        int number = 12;
        Calendar date = Calendar.getInstance();
        double amount = 123.23;
        int paymentStatusId = 1;
        int recipientCardId = 2;
        int senderCardId = 3;
        payment.setId(id);
        payment.setNumber(number);
        payment.setDate(date);
        payment.setAmount(amount);
        payment.setPaymentStatusId(paymentStatusId);
        payment.setRecipientCardId(recipientCardId);
        payment.setSenderCardId(senderCardId);

        Assert.assertEquals(id, payment.getId());
        Assert.assertEquals(number, payment.getNumber());
        Assert.assertEquals(date, payment.getDate());
        Assert.assertEquals(amount, payment.getAmount(), 0.001);
        Assert.assertEquals(paymentStatusId, payment.getPaymentStatusId());
        Assert.assertEquals(recipientCardId, payment.getRecipientCardId());
        Assert.assertEquals(senderCardId, payment.getSenderCardId());
    }

    @Test
    public void paymentBeanGetAndSetTest(){
        PaymentBean paymentBean = new PaymentBean();
        String statusName = "bl";
        String recipientCardNumber="1234123412341234";
        String recipientName = "Alex";
        String senderCardNumber = "4321432143214321";
        Calendar date = Calendar.getInstance();
        String date2 = CalendarProcessing.fullDate2String(date);

        paymentBean.setStatusName(statusName);
        paymentBean.setRecipientCardNumber(recipientCardNumber);
        paymentBean.setRecipientName(recipientName);
        paymentBean.setSenderCardNumber(senderCardNumber);
        paymentBean.setDate(date);

        Assert.assertEquals(statusName, paymentBean.getStatusName());
        Assert.assertEquals(recipientCardNumber, paymentBean.getRecipientCardNumber());
        Assert.assertEquals(recipientName, paymentBean.getRecipientName());
        Assert.assertEquals(senderCardNumber, paymentBean.getSenderCardNumber());
        Assert.assertEquals(date2, paymentBean.getStringDate());
    }

    @Test
    public void getButtonNameByStatusTest(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("res", new Locale("en"));
        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setStatusName(Fields.PAYMENT_STATUS__PREPARED);
        Assert.assertEquals("Confirm", paymentBean.getButtonNameByStatus(resourceBundle));
        paymentBean.setStatusName(Fields.PAYMENT_STATUS__SENT);
        Assert.assertEquals("Repeat", paymentBean.getButtonNameByStatus(resourceBundle));
    }

}
