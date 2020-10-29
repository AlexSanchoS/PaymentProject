package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.PaymentBean;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Payment;
import ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Data access object for payment entity.
 *
 * @author O.Zamkovyi
 */


public class PaymentDao {

    private static final Logger logger = Logger.getLogger(PaymentDao.class);

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_NUMBER_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_NUMBER_DESC_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_DATE_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_DATE_DESC_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_AMOUNT_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_AMOUNT_DESC_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_STATUS_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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

    public static final String SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_STATUS_DESC_LIMIT =
            "SELECT " + Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID + ", " +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + "," +
                    Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD + ", " +
                    Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA + ", " +
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


    /**
     * Set recipient credit card number for payment
     * @param payment current payment
     */
    private void getRecipientCardNumberAndName(PaymentBean payment) {
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

    /**
     * Set sender credit card number for payment
     * @param payment current payment
     */
    private void getSenderCardNumber(PaymentBean payment) {
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

    /**
     * Returns a payment list for current client
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param client    current client.
     * @param page     Page number
     * @param sortType Sorting type
     * @return ArrayList of PaymentBean.
     */

    public ArrayList<PaymentBean> getPaymentList(Client client, int page, int sortType) {
        ArrayList<PaymentBean> listOfPayment = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_NUMBER_LIMIT;
                break;
            case 2:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_NUMBER_DESC_LIMIT;
                break;
            case 3:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_DATE_LIMIT;
                break;
            case 4:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_DATE_DESC_LIMIT;
                break;
            case 5:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_AMOUNT_LIMIT;
                break;
            case 6:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_AMOUNT_DESC_LIMIT;
                break;
            case 7:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_STATUS_LIMIT;
                break;
            case 8:
                sort = SQL_GET_PAYMENT_BY_CLIENT_SORT_BY_STATUS_DESC_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            pstmt.setInt(1, client.getId());
            pstmt.setInt(2, page * 10 - 10);
            rs = pstmt.executeQuery();
            PaymentBeanMapper mapper = new PaymentBeanMapper();

            while (rs.next()) {
                PaymentBean payment = mapper.mapRowForGetPaymentList(rs);
                getRecipientCardNumberAndName(payment);
                getSenderCardNumber(payment);
                listOfPayment.add(payment);
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

    /**
     * Returns count of client payment
     * @param client current client
     * @return count of payment
     */
    public int getCountPaymentByClient(Client client) {
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

    /**
     * Returns id for payment status
     * @param status current status
     * @return status id
     */
    private int getPaymentStatusIdByName(String status) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_PAYMENT_STATUS_ID_BY_NAME);
            pstmt.setString(1, status);
            logger.trace("SQL in 'getPaymentStatusIdByName' request = " + pstmt.toString());
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

    /**
     * Returns credit card id by credit card number
     * @param number number of current credit card
     * @return credit card id
     */
    private int getCreditCardIdByNumber(String number) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_CREDIT_CARD_ID_BY_NUMBER);
            pstmt.setString(1, number);
            logger.trace("SQL in 'getCreditCardIdByNumber' request = " + pstmt.toString());
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

    /**
     * Returns the number for a new payment
     * Finds the number of the last payment for the current client and increases it by 1
     * @param client current client
     * @return payment number
     */
    private int getNewPaymentNumber(Client client) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_MAX_PAYMENT_NUMBER_FOR_CLIENT);
            pstmt.setInt(1, client.getId());
            logger.trace("SQL in 'getNewPaymentNumber' request = " + pstmt.toString());
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

    /**
     * Adds new payment to the database
     * @param client current client
     * @param senderNumber number of sender credit card
     * @param recipientNumber number of recipient credit card
     * @param amount amount of payment
     */
    public void createNewPayment(Client client, String senderNumber, String recipientNumber, double amount) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_NEW_PAYMENT);
            pstmt.setInt(1, getNewPaymentNumber(client));
            pstmt.setString(2, CalendarProcessing.getFullCurrentDate());
            pstmt.setDouble(3, amount);
            pstmt.setInt(4, getPaymentStatusIdByName(Fields.PAYMENT_STATUS__PREPARED));
            pstmt.setInt(5, getCreditCardIdByNumber(senderNumber));
            pstmt.setInt(6, getCreditCardIdByNumber(recipientNumber));
            logger.trace("SQL in 'createNewPayment' request = " + pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new payment", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Updates status if payment
     * @param payment current payment
     * @param newStatus new status for payment
     */
    public void updatePaymentStatus(PaymentBean payment, String newStatus) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PAYMENT_STATUS);
            pstmt.setInt(1, getPaymentStatusIdByName(newStatus));
            pstmt.setInt(2, payment.getId());
            logger.trace("SQL in 'updatePaymentStatus' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't update payment status", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Close autoClosable object
     *
     * @param forClose object for closing
     */
    private void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                logger.error("Can't close autoCloseable object", e);
            }
        }
    }

    /**
     * Extracts a Payment from the result set row.
     */
    static class PaymentMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow Payment' = " + rs.toString());
                Payment payment = new Payment();
                Calendar calendar = CalendarProcessing.string2FullDate(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE));
                payment.setId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID));
                payment.setNumber(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER));
                payment.setDate(calendar);
                payment.setAmount(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT));
                payment.setPaymentStatusId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setSenderCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));

                return payment;
            } catch (SQLException e) {
                logger.error("Can't map payment", e);
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a PaymentBaen from the result set row.
     */
    static class PaymentBeanMapper implements EntityMapper<PaymentBean> {

        @Override
        public PaymentBean mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow PaymentBean' = " + rs.toString());
                PaymentBean payment = new PaymentBean();
                Calendar calendar = CalendarProcessing.string2FullDate(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE));
                payment.setId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID));
                payment.setNumber(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER));
                payment.setDate(calendar);
                payment.setAmount(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT));
                payment.setPaymentStatusId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setSenderCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));

                return payment;
            } catch (SQLException e) {
                logger.error("Can't map payment", e);
                throw new IllegalStateException(e);
            }
        }

        public PaymentBean mapRowForGetPaymentList(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetPaymentList' = " + rs.toString());
                PaymentBean payment = new PaymentBean();
                Calendar calendar = CalendarProcessing.string2FullDate(rs.getString(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__DATE));
                payment.setId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__ID));
                payment.setNumber(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__NUMBER));
                payment.setDate(calendar);
                payment.setAmount(rs.getDouble(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT));
                payment.setPaymentStatusId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setSenderCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));
                payment.setStatusName(rs.getString(Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS));
                payment.setStatusNameUkr(rs.getString(Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS_UA));

                return payment;
            } catch (SQLException e) {
                logger.error("Can't map payment", e);
                throw new IllegalStateException(e);
            }
        }

    }
}
