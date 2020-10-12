package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.BankAccount;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.CreditCard;
import ozamkovyi.web.CalendarProcessing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class CreditCardDao {

    private static final String SQL__FIND_CARD_BY_ID_SORT1_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT2_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " DESC limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT3_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT4_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " DESC limit 10 offset ?";

    private static final String SQL_GET_COUNT_CARD_BY_CLIENT_ID = "SELECT COUNT(" + Fields.CREDIT_CARD__ID + ") " +
            " FROM " + Fields.TABLE__CREDIT_CARD + " WHERE " + Fields.CREDIT_CARD__USER_ID + " = ?";

    private static final String SQL_BLOC_UNBLOCK_CREDIT_CARD = "UPDATE " + Fields.TABLE__CREDIT_CARD +
            " SET " + Fields.CREDIT_CARD__CARD_STATUS_ID + " =? WHERE " + Fields.CREDIT_CARD__ID + " =?";

    private static final String SQL_GET_STATUS_CREDIT_CARD = "SELECT " + Fields.CARD_STATUS__ID + " FROM " + Fields.TABLE__CARD_STATUS +
            " where " + Fields.CARD_STATUS__STATUS + " = ?";

    private static final String SQL_ADD_NEW_CREDIT_CARD =
            "INSERT INTO  " + Fields.TABLE__CREDIT_CARD + " ( " +
                    Fields.CREDIT_CARD__NUMBER + ", " +
                    Fields.CREDIT_CARD__VALIDITY + ", " + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.CREDIT_CARD__USER_ID + ", " + Fields.CREDIT_CARD__CARD_STATUS_ID + " ) " +
                    " VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_IS_NUMBER_ALREADY_EXISTING =
            "select COUNT(" + Fields.CREDIT_CARD__ID + ") from " +
                    Fields.TABLE__CREDIT_CARD + " where " + Fields.CREDIT_CARD__NUMBER + " = ?";

    private static String generatorNewCardNumber() {
        String re = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 10;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_IS_NUMBER_ALREADY_EXISTING);
            while (rez != 0) {
                re = CreditCard.generatorCardNumber();
                pstmt.setString(1, re);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    rez = rs.getInt(1);
                }
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
        return re;
    }


    public static void addNewCard(BankAccount account) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__BLOCKED);
            String newCardNumber = generatorNewCardNumber();
            pstmt = con.prepareStatement(SQL_ADD_NEW_CREDIT_CARD);
            String newValidity = CalendarProcessing.getValidityForNewCard();
            pstmt.setString(1, newCardNumber);
            pstmt.setString(2, newValidity);
            pstmt.setString(3, account.getNumber());
            pstmt.setInt(4, account.getUserId());
            pstmt.setInt(5, newStatusId);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public int getCountCardByUser(Client client) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_CARD_BY_CLIENT_ID);
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

    public ArrayList<CreditCard> getCardList(Client client, int pageNumber, int sortType) {
        ArrayList<CreditCard> listOfCreditCard = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CreditCard creditCard = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL__FIND_CARD_BY_ID_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL__FIND_CARD_BY_ID_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL__FIND_CARD_BY_ID_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL__FIND_CARD_BY_ID_SORT4_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            CreditCardDao.CreditCardMapper mapper = new CreditCardMapper();
            pstmt.setInt(1, client.getId());
            pstmt.setInt(2, pageNumber * 10 - 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                creditCard = mapper.mapRow(rs);
                listOfCreditCard.add(creditCard);
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
        return listOfCreditCard;
    }

    private static int getIdStatusForCreditCard(String value) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_STATUS_CREDIT_CARD);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rez;
    }

    public static void blocCard(CreditCard card) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__BLOCKED);
            pstmt = con.prepareStatement(SQL_BLOC_UNBLOCK_CREDIT_CARD);
            pstmt.setInt(1, newStatusId);
            pstmt.setInt(2, card.getId());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public static void unblockCard(CreditCard card) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__UNBLOCKED);
            if (rs.next()) {
                newStatusId = rs.getInt(1);
            }
            pstmt = con.prepareStatement(SQL_BLOC_UNBLOCK_CREDIT_CARD);
            pstmt.setInt(1, newStatusId);
            pstmt.setInt(2, card.getId());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }

    }

    private static class CreditCardMapper implements EntityMapper<CreditCard> {

        @Override
        public CreditCard mapRow(ResultSet rs) {
            try {
                Calendar date = CalendarProcessing.string2Date(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY));
                CreditCard creditCard = new CreditCard();
                creditCard.setId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID));
                creditCard.setNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                creditCard.setValidity(date);
                creditCard.setBankAccountNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER));
                creditCard.setUserId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID));
                creditCard.setCardStatusId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID));
                creditCard.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                creditCard.setCurrency(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                creditCard.setCardStatusName(rs.getString(Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS));
                return creditCard;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
