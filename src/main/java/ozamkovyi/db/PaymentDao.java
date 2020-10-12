package ozamkovyi.db;

import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Payment;
import ozamkovyi.web.CalendarProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class PaymentDao {

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
            Fields.TABLE__PAYMENT+"."+Fields.PAYMENT__ID + ") " +
            " FROM " + Fields.TABLE__PAYMENT + " JOIN "+Fields.TABLE__CREDIT_CARD+ " ON " +
            Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD + " = " +
            Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " and " +
            Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ?";


    private static void setRecipientCardNumberAndName(Payment payment) {
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
            throwables.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static void setSenderCardNumberAndName(Payment payment) {
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
            throwables.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
                setRecipientCardNumberAndName(payment);
                setSenderCardNumberAndName(payment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
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
            throwables.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return rez;
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
                payment.setAmount(rs.getLong(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__AMOUNT));
                payment.setPaymentStatusId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setSenderCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__SENDER_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));
                payment.setRecipientCardId(rs.getInt(Fields.TABLE__PAYMENT + "." + Fields.PAYMENT__RECIPIENT_CREDIT_CARD));
                payment.setStatusName(rs.getString(Fields.TABLE__PAYMENT_STATUS + "." + Fields.PAYMENT_STATUS__STATUS));

                return payment;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
