package java1.ozamkovyi.db.dao;

import java1.ozamkovyi.db.DBManager;
import java1.ozamkovyi.db.EntityMapper;
import java1.ozamkovyi.db.Fields;
import java1.ozamkovyi.db.entity.BankAccount;
import java1.ozamkovyi.db.entity.Client;
import java1.ozamkovyi.db.entity.CreditCard;
import java1.ozamkovyi.db.entity.Currency;
import java1.ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankAccountDao {

    private static final Logger logger = Logger.getLogger(BankAccountDao.class);


    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT1_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT2_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " DESC limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT3_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT4_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " DESC limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT5_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT6_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " =? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " DESC limit 10 offset ?";

    private static final String SQL_GET_COUNT_BANK_ACCOUNT_BY_CLIENT_ID = "SELECT COUNT(" + Fields.BANK_ACCOUNT__NUMBER + ") " +
            " FROM " + Fields.TABLE__BANK_ACCOUNT + " WHERE " + Fields.BANK_ACCOUNT__USER_ID + " = ?";

    private static final String SQL_GET_COUNT_BANK_ACCOUNT_FOR_UNLOCK =
            "SELECT COUNT(" + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ") " +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " + Fields.TABLE__ACCOUNT_STATUS + " ON " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" + Fields.ACCOUNT_STATUS__EXPECTATION + "'";

    private static final String SQL_CHANGE_STATUS_ID_FOR_BANK_ACCOUNT = "UPDATE " + Fields.TABLE__BANK_ACCOUNT +
            " SET " + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " =? WHERE " + Fields.BANK_ACCOUNT__NUMBER + " =?";

    private static final String SQL_GET_STATUS_ACCOUNT_ID_BY_NAME = "SELECT " + Fields.ACCOUNT_STATUS__ID +
            " FROM " + Fields.TABLE__ACCOUNT_STATUS +
            " where " + Fields.ACCOUNT_STATUS__STATUS + " = ?";

    private static final String SQL_GET_COURSE_FOR_BANK_ACCOUNT =
            "SELECT " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__COURSE +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " + Fields.TABLE__CURRENCY +
                    " on " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + "  and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + "=?";

    private static final String SQL_CHANGE_BALANCE_FOR_BANK_ACCOUNT = "UPDATE " + Fields.TABLE__BANK_ACCOUNT +
            " SET " + Fields.BANK_ACCOUNT__BALANCE + " =? WHERE " + Fields.BANK_ACCOUNT__NUMBER + " =?";

    private static final String SQL_GET_ALL_ACCOUNT_NUMBER_AND_CURRENCY =
            "select " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " from " +
                    Fields.TABLE__BANK_ACCOUNT + " join " + Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " = ? and " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" +
                    Fields.ACCOUNT_STATUS__UNBLOCKED + "'";

    private static final String SQL_IS_NUMBER_ALREADY_EXISTING =
            "select COUNT(" + Fields.BANK_ACCOUNT__BALANCE + ") from " +
                    Fields.TABLE__BANK_ACCOUNT + " where " + Fields.BANK_ACCOUNT__NUMBER + " = ?";

    private static final String SQL_ADD_NEW_BANK_ACCOUNT =
            "INSERT INTO  " + Fields.TABLE__BANK_ACCOUNT + " ( " +
                    Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.BANK_ACCOUNT__BALANCE + ", " + Fields.BANK_ACCOUNT__CURRENCY_ID + ", " +
                    Fields.BANK_ACCOUNT__USER_ID + ", " + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " ) " +
                    " VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_GET_ACCOUNT_BALANCE_BY_NUMBER =
            "SELECT " + Fields.BANK_ACCOUNT__BALANCE + " FROM " +
                    Fields.TABLE__BANK_ACCOUNT + " WHERE " +
                    Fields.BANK_ACCOUNT__NUMBER + " = ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT1_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__CLIENT + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " = " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" +
                    Fields.ACCOUNT_STATUS__EXPECTATION +
                    "' ORDER BY " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + " limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT2_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__CLIENT + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " = " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" +
                    Fields.ACCOUNT_STATUS__EXPECTATION +
                    "' ORDER BY " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + " DESC limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT3_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__CLIENT + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " = " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" +
                    Fields.ACCOUNT_STATUS__EXPECTATION +
                    "' ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " limit 10 offset ?";

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT4_LIMIT =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME +
                    " FROM " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " join " +
                    Fields.TABLE__CLIENT + " join " +
                    Fields.TABLE__ACCOUNT_STATUS + " on " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID + " = " + Fields.TABLE__CLIENT + "." + Fields.CLIENT__ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + " = '" +
                    Fields.ACCOUNT_STATUS__EXPECTATION +
                    "' ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " DESC limit 10 offset ?";


    public static ArrayList<BankAccount> getAllAccount(Client client) {
        ArrayList<BankAccount> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_ACCOUNT_NUMBER_AND_CURRENCY);
            pstmt.setInt(1, client.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccount.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                bankAccount.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccount.setUserId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID));
                listOfBankAccount.add(bankAccount);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfBankAccount;
    }


    public static int getCountBankAccountByUser(Client client) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_BANK_ACCOUNT_BY_CLIENT_ID);
            pstmt.setInt(1, client.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account count", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    public static ArrayList<BankAccount> getAccountList(Client client, int page, int sortType) {
        ArrayList<BankAccount> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        BankAccount bankAccount = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT4_LIMIT;
                break;
            case 5:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT5_LIMIT;
                break;
            case 6:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT6_LIMIT;
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
                bankAccount = mapper.mapRow(rs);
                listOfBankAccount.add(bankAccount);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account list", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfBankAccount;
    }

    public static ArrayList<BankAccount> getAccountListForUnlock(int page, int sortType) {
        ArrayList<BankAccount> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT4_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            BankAccountMapper mapper = new BankAccountMapper();
            pstmt.setInt(1, page * 10 - 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccount.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccount.setClientName(rs.getString(Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME));
                bankAccount.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                listOfBankAccount.add(bankAccount);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account list for unlock", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfBankAccount;
    }

    private static int getStatusIdByStatus(String status) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        int statusId = 0;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_STATUS_ACCOUNT_ID_BY_NAME);
            pstmt.setString(1, status);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                statusId = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get status id for bank account", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return statusId;
    }

    public static void changeStatusFotBankAccount(BankAccount bankAccount, String newStatus) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            int newStatusId = getStatusIdByStatus(newStatus);
            pstmt = con.prepareStatement(SQL_CHANGE_STATUS_ID_FOR_BANK_ACCOUNT);
            pstmt.setInt(1, newStatusId);
            pstmt.setString(2, bankAccount.getNumber());
            if (newStatus.equals(Fields.ACCOUNT_STATUS__BLOCKED)) {
                CreditCardDao.blockAllCardForAccount(bankAccount);
            }
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't change status for bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }

    }

    private static int getAccountBalance(String number) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        int statusId = 0;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_ACCOUNT_BALANCE_BY_NUMBER);
            pstmt.setString(1, number);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                statusId = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account balance", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return statusId;
    }

    public static void addToBalance(String number, double amount) {
        double course = getCourse(number);
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_CHANGE_BALANCE_FOR_BANK_ACCOUNT);
            int currentBalance = getAccountBalance(number);
            int newBalance = (int) (currentBalance + amount / course * 100);
            pstmt.setInt(1, newBalance);
            pstmt.setString(2, number);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add to balance bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    private static double getCourse(String number) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        double rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COURSE_FOR_BANK_ACCOUNT);
            pstmt.setString(1, number);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getDouble(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get bank account count", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

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
            logger.error("Can't generate new bank account number", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return re;
    }

    public static void addNewAccount(Currency currency, Client client) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            int newStatusId = getStatusIdByStatus(Fields.ACCOUNT_STATUS__EXPECTATION);
            String newAccountNumber = generatorNewCardNumber();
            pstmt = con.prepareStatement(SQL_ADD_NEW_BANK_ACCOUNT);
            String newValidity = CalendarProcessing.getValidityForNewCard();
            pstmt.setString(1, newAccountNumber);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, currency.getId());
            pstmt.setInt(4, client.getId());
            pstmt.setInt(5, newStatusId);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public static int getCountBankAccountForUnlock() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_BANK_ACCOUNT_FOR_UNLOCK);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get cont bank account for unlock", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
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

    private static class BankAccountMapper implements EntityMapper<BankAccount> {

        @Override
        public BankAccount mapRow(ResultSet rs) {
            try {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccount.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccount.setCurrencyId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID));
                bankAccount.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                bankAccount.setAccountStatusName(rs.getString(Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS));
                bankAccount.setAccountStatusId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID));
                return bankAccount;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
