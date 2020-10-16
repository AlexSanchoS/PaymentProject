package java1.ozamkovyi.db.dao;

import java1.ozamkovyi.db.DBManager;
import java1.ozamkovyi.db.EntityMapper;
import java1.ozamkovyi.db.Fields;
import java1.ozamkovyi.db.entity.Client;
import java1.ozamkovyi.db.entity.Payment;
import java1.ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class PaymentDao {

    private static final Logger logger = Logger.getLogger(PaymentDao.class);

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT1_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER +
                    " limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT2_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER +
                    " DESC limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT3_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE +
                    " limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT4_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE +
                    " DESC limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT5_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT +
                    " limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT6_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT +
                    " DESC limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT7_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID +
                    " limit 10 offset ?";

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT8_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS + " FROM " +
                    Fields.TABLE__PAYMENT + " join " +
                    Fields.TABLE__PAYMENT_STATUS + " join " +
                    Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__ID + " = " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    "ORDER BY " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID +
                    " DESC limit 10 offset ?";

    private static final String setRecipientCardNumberAndName =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + ", " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + " FROM " +
                    Fields.TABLE__PAYMENT + " join " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__CLIENT + " on " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + " = ?";

    private static final String setSenderCardNumber =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + " FROM " +
                    Fields.TABLE__PAYMENT + " join " + Fields.TABLE__CREDIT_CARD + " on " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + " = ?";

    private static final String SQL_GET_COUNT_PAYMENT_BY_CLIENT_ID = "SELECT COUNT(" +
            Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ") " +
            " FROM " + Fields.TABLE__PAYMENT + " JOIN " + Fields.TABLE__CREDIT_CARD + " ON " +
            Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
            Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
            Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ?";

    private static final String SQL_UPDATE_PAYMENT_STATUS =
            "update " + Fields.TABLE__PAYMENT + " set " + Fields.PAYMENT__PAYMENT_STATUS_ID + " = ? where " +
                    Fields.PAYMENT__ID + " = ?";

    private static final String SQL_GET_PAYMENT_STATUS_ID_BY_NAME =
            "SELECT " + Fields.PAYMENT_STATUS__ID + " FROM " +
                    Fields.TABLE__PAYMENT_STATUS + " WHERE " + Fields.PAYMENT_STATUS__STATUS + " =?";


    private static final String SQL_CREATE_NEW_PAYMENT =
            "insert into " + Fields.TABLE__PAYMENT + " (" +
                    Fields.PAYMENT__NUMBER + ", " + Fields.PAYMENT__DATE + ", " + Fields.PAYMENT__AMOUNT + ", " +
                    Fields.PAYMENT__PAYMENT_STATUS_ID + ", " + Fields.PAYMENT__SENDER_CREDIT_CARD + ", " + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ")" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_GET_MAX_PAYMENT_NUMBER_FOR_CLIENT =
            "SELECT max(" + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ") FROM " +
                    Fields.TABLE__PAYMENT + " join " + Fields.TABLE__CREDIT_CARD + " ON " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ?";

    private static final String SQL_GET_CREDIT_CARD_ID_BY_NUMBER =
            "SELECT " + Fields.CREDIT_CARD__ID + " FROM " + Fields.TABLE__CREDIT_CARD + " WHERE " +
                    Fields.CREDIT_CARD__NUMBER + " = ?";


    private static void getRecipientCardNumberAndName(Payment payment) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(setRecipientCardNumberAndName);
            pstmt.setInt(1, payment.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                payment.setRecipientCardNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                payment.setRecipientName(rs.getString(Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME));
            }
        } catch (SQLException throwables) {
            logger.error("Can't get recipient card number", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
    }

    private static void getSenderCardNumber(Payment payment) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(setSenderCardNumber);
            pstmt.setInt(1, payment.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                payment.setSenderCardNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
            }
        } catch (SQLException throwables) {
            logger.error("Can't get sender card number", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
    }

    public static ArrayList<Payment> getPaymentList(Client client, int page, int sortType) {
        ArrayList<Payment> listOfPayment = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Payment payment = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT4_LIMIT;
                break;
            case 5:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT5_LIMIT;
                break;
            case 6:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT6_LIMIT;
                break;
            case 7:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT7_LIMIT;
                break;
            case 8:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT8_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            BankAccountMapper mapper = new BankAccountMapper();
            pstmt.setInt(1, client.getId());
            pstmt.setInt(2, page * 10 - 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                payment = mapper.mapRow(rs);
                listOfPayment.add(payment);
                getRecipientCardNumberAndName(payment);
                getSenderCardNumber(payment);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get list of payment", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfPayment;
    }

    public static int getCountPaymentByUser(Client client) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_PAYMENT_BY_CLIENT_ID);
            pstmt.setInt(1, client.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get count of payment", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    private static int getPaymentStatusIdByName(String status) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_PAYMENT_STATUS_ID_BY_NAME);
            pstmt.setString(1, status);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get payment status", throwables);
        }finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    private static int getCreditCardIdByNumber(String number) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_CREDIT_CARD_ID_BY_NUMBER);
            pstmt.setString(1, number);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get credit card id", throwables);
        }finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    private static int getNewPaymentNumber(Client client) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_MAX_PAYMENT_NUMBER_FOR_CLIENT);
            pstmt.setInt(1, client.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get payment number", throwables);
        }finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return ++rez;
    }

    public static void createNewPayment(Client client, String senderNumber, String recipientNumber, double amount) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_NEW_PAYMENT);
            pstmt.setInt(1, getNewPaymentNumber(client));
            pstmt.setString(2, CalendarProcessing.getFullCurrentDate());
            pstmt.setDouble(3, amount);
            pstmt.setInt(4, getPaymentStatusIdByName(Fields.PAYMENT_STATUS__PREPARED));
            pstmt.setInt(5, getCreditCardIdByNumber(senderNumber));
            pstmt.setInt(6, getCreditCardIdByNumber(recipientNumber));

            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new payment", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public static void updatePaymentStatus(Payment payment, String newStatus) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PAYMENT_STATUS);
            pstmt.setInt(1, getPaymentStatusIdByName(newStatus));
            pstmt.setInt(2, payment.getId());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't update payment status", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    private static void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                logger.error("Can't close autoCloseable object", e);
            }
        }
    }

    private static class BankAccountMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet rs) {
            try {
                Payment payment = new Payment();
                Calendar calendar = CalendarProcessing.string2FullDate(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE));
                payment.setId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID));
                payment.setNumber(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER));
                payment.setDate(calendar);
                payment.setAmount(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT));
                payment.setPaymentStatusId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setSenderCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));
                payment.setStatusName(rs.getString(Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS));

                return payment;
            } catch (SQLException e) {
                logger.error("Can't map payment", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
