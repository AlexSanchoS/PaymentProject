package ozamkovyi.db.dao;

import org.junit.Assert;
import org.junit.Test;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.PaymentBean;
import ozamkovyi.db.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentDaoTest {

    @Test
    public void PaymentMapperTest() throws SQLException {
        PaymentDao.PaymentMapper paymentMapper = new PaymentDao.PaymentMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        int number = 12;
        String date = "2020-01-08 21:02:30";
        double amount = 123.23;
        int paymentStatusId = 1;
        int recipientCardId = 2;
        int senderCardId = 3;

        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE)).thenReturn(date);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER)).thenReturn(number);
        when(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT)).thenReturn(amount);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID)).thenReturn(paymentStatusId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD)).thenReturn(senderCardId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD)).thenReturn(recipientCardId);
        Payment payment = paymentMapper.mapRow(rs);

        Assert.assertEquals(id, payment.getId());
        Assert.assertEquals(number, payment.getNumber());
        Assert.assertEquals(amount, payment.getAmount(), 0.001);
        Assert.assertEquals(paymentStatusId, payment.getPaymentStatusId());
        Assert.assertEquals(recipientCardId, payment.getRecipientCardId());
        Assert.assertEquals(senderCardId, payment.getSenderCardId());
    }

    @Test
    public void PaymentBeanMapperTest() throws SQLException {
        PaymentDao.PaymentBeanMapper paymentMapper = new PaymentDao.PaymentBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        int number = 12;
        String date = "2020-01-08 21:02:30";
        double amount = 123.23;
        int paymentStatusId = 1;
        int recipientCardId = 2;
        int senderCardId = 3;

        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE)).thenReturn(date);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER)).thenReturn(number);
        when(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT)).thenReturn(amount);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID)).thenReturn(paymentStatusId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD)).thenReturn(senderCardId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD)).thenReturn(recipientCardId);
        PaymentBean payment = paymentMapper.mapRow(rs);

        Assert.assertEquals(id, payment.getId());
        Assert.assertEquals(number, payment.getNumber());
        Assert.assertEquals(amount, payment.getAmount(), 0.001);
        Assert.assertEquals(paymentStatusId, payment.getPaymentStatusId());
        Assert.assertEquals(recipientCardId, payment.getRecipientCardId());
        Assert.assertEquals(senderCardId, payment.getSenderCardId());
    }

    @Test
    public void PaymentBeanMapperForGetPaymentList() throws SQLException {
        PaymentDao.PaymentBeanMapper paymentMapper = new PaymentDao.PaymentBeanMapper();
        ResultSet rs = mock(ResultSet.class);
        int id = 1;
        int number = 12;
        String date = "2020-01-08 21:02:30";
        double amount = 123.23;
        int paymentStatusId = 1;
        int recipientCardId = 2;
        int senderCardId = 3;
        String statusName = "block";

        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID)).thenReturn(id);
        when(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE)).thenReturn(date);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER)).thenReturn(number);
        when(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT)).thenReturn(amount);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID)).thenReturn(paymentStatusId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD)).thenReturn(senderCardId);
        when(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD)).thenReturn(recipientCardId);
        when(rs.getString(Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS)).thenReturn(statusName);
        PaymentBean payment = paymentMapper.mapRowForGetPaymentList(rs);

        Assert.assertEquals(id, payment.getId());
        Assert.assertEquals(number, payment.getNumber());
        Assert.assertEquals(amount, payment.getAmount(), 0.001);
        Assert.assertEquals(paymentStatusId, payment.getPaymentStatusId());
        Assert.assertEquals(recipientCardId, payment.getRecipientCardId());
        Assert.assertEquals(senderCardId, payment.getSenderCardId());
        Assert.assertEquals(statusName, payment.getStatusName());
    }
}
