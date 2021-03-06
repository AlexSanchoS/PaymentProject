package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.entity.BankAccount;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.Currency;
import ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Data access object for BankAccount entity.
 *
 * @author O.Zamkovyi
 */
public class BankAccountDao {

    private static final Logger logger = Logger.getLogger(BankAccountDao.class);

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_NUMBER_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_NUMBER_DESC_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_CURRENCY_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_CURRENCY_DESC_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_BALANCE_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_BALANCE_DESC_LIMIT =
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
            "SELECT " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__RATE +
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_CLIENT_NAME_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_CLIENT_NAME_DESC_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORTBY_NUMBER_LIMIT =
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

    private static final String SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_NUMBER_DESC_LIMIT =
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


    /**
     * Returns a bank account list for current client.
     *
     * @param client Client for search.
     * @return ArrayList of BankAccountBean.
     */
    public ArrayList<BankAccountBean> getAllAccount(Client client) {
        ArrayList<BankAccountBean> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_ACCOUNT_NUMBER_AND_CURRENCY);
            BankAccountBeanMapper mapper = new BankAccountBeanMapper();
            pstmt.setInt(1, client.getId());
            logger.trace("SQL in 'getAllAccount' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BankAccountBean bankAccountBean = mapper.mapRowForGetAllAccount(rs);
                listOfBankAccount.add(bankAccountBean);
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


    /**
     * Returns count of bank accounts for current client.
     *
     * @param id Client id for search.
     * @return count of bank account.
     */
    public int getCountBankAccountByUser(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_BANK_ACCOUNT_BY_CLIENT_ID);
            pstmt.setInt(1, id);
            logger.trace("SQL in 'getCountBankAccountByUser' request = " + pstmt.toString());
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

    /**
     * Returns a bank account list for current client
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param id       Client id for search.
     * @param page     Page number
     * @param sortType Sorting type
     * @return ArrayList of BankAccountBean.
     */
    public ArrayList<BankAccountBean> getAccountList(int id, int page, int sortType) {
        ArrayList<BankAccountBean> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_NUMBER_LIMIT;
                break;
            case 2:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_NUMBER_DESC_LIMIT;
                break;
            case 3:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_CURRENCY_LIMIT;
                break;
            case 4:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_CURRENCY_DESC_LIMIT;
                break;
            case 5:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_BALANCE_LIMIT;
                break;
            case 6:
                sort = SQL_GET_BANK_ACCOUNT_LIST_BY_CLIENT_ID_SORT_BY_BALANCE_DESC_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            pstmt.setInt(1, id);
            BankAccountBeanMapper mapper = new BankAccountBeanMapper();
            pstmt.setInt(2, page * 10 - 10);
            logger.trace("SQL in 'getAccountList' request = " + pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BankAccountBean bankAccountBean = mapper.mapRowForGetAccountList(rs);
                listOfBankAccount.add(bankAccountBean);
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

    /**
     * Returns a blocked bank account list
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param page     Page number
     * @param sortType Sorting type
     * @return ArrayList of block BankAccountBean.
     */
    public ArrayList<BankAccountBean> getAccountListForUnlock(int page, int sortType) {
        ArrayList<BankAccountBean> listOfBankAccount = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_CLIENT_NAME_LIMIT;
                break;
            case 2:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_CLIENT_NAME_DESC_LIMIT;
                break;
            case 3:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORTBY_NUMBER_LIMIT;
                break;
            case 4:
                sort = SQL_GET_BANK_ACCOUNT_LIST_FOR_UNLOCK_SORT_BY_NUMBER_DESC_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            BankAccountBeanMapper mapper = new BankAccountBeanMapper();
            pstmt.setInt(1, page * 10 - 10);
            logger.trace("SQL in 'getAccountListForUnlock' request = " + pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BankAccountBean bankAccountBean = mapper.mapRowForGetAccountListForUnlock(rs);
                listOfBankAccount.add(bankAccountBean);
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

    /**
     * Returns id for bank account status
     *
     * @param status Bank account status.
     * @return status id.
     */
    private int getStatusIdByStatus(String status) {
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
            logger.trace("SQL in 'getStatusIdByStatus' request = " + pstmt.toString());
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

    /**
     * Changes status for bank account
     *
     * @param bankAccountBean Bank account for changing
     * @param newStatus       New status for bank account
     */
    public void changeStatusFotBankAccount(BankAccountBean bankAccountBean, String newStatus) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            int newStatusId = getStatusIdByStatus(newStatus);
            pstmt = con.prepareStatement(SQL_CHANGE_STATUS_ID_FOR_BANK_ACCOUNT);
            pstmt.setInt(1, newStatusId);
            pstmt.setString(2, bankAccountBean.getNumber());
            if (newStatus.equals(Fields.ACCOUNT_STATUS__BLOCKED)) {
                new CreditCardDao().blockAllCardForAccount(bankAccountBean);
            }
            logger.trace("SQL in 'changeStatusFotBankAccount' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't change status for bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }

    }

    /**
     * Returns balance for bank account
     *
     * @param number bank account number
     * @return bank account balance
     */
    private int getAccountBalance(String number) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        int statusId = 0;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_ACCOUNT_BALANCE_BY_NUMBER);
            pstmt.setString(1, number);
            logger.trace("SQL in 'getAccountBalance' request = " + pstmt.toString());
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

    /**
     * Adds balance to the bank account
     *
     * @param number bank account number
     * @param amount athe amount of replenishment
     */
    public void addToBalance(String number, double amount) {
        double course = getRate(number);
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_CHANGE_BALANCE_FOR_BANK_ACCOUNT);
            int currentBalance = getAccountBalance(number);
            int newBalance = (int) (currentBalance + amount / course * 100);
            pstmt.setInt(1, newBalance);
            pstmt.setString(2, number);
            logger.trace("SQL in 'addToBalance' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add to balance bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Gets rate for current currency
     *
     * @param number bank account number
     * @return rate
     */
    private double getRate(String number) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        double rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COURSE_FOR_BANK_ACCOUNT);
            pstmt.setString(1, number);
            logger.trace("SQL in 'getRate' request = " + pstmt.toString());
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

    /**
     * Returns new account number for bank account
     * generates a bank account number and checks if it is not used
     *
     * @return number for bank account
     */
    private String generatorNewBankAccountNumber() {
        String re = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 10;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_IS_NUMBER_ALREADY_EXISTING);
            while (rez != 0) {
                re = BankAccountBean.generatorAccountNumber();
                pstmt.setString(1, re);
                logger.trace("SQL in 'generatorNewBankAccountNumber' request = " + pstmt.toString());
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

    /**
     * Creates new account
     *
     * @param currency currency for new account
     * @param client   client for new account
     */
    public void addNewAccount(Currency currency, ClientBean client) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            int newStatusId = getStatusIdByStatus(Fields.ACCOUNT_STATUS__EXPECTATION);
            String newAccountNumber = generatorNewBankAccountNumber();
            pstmt = con.prepareStatement(SQL_ADD_NEW_BANK_ACCOUNT);
            String newValidity = CalendarProcessing.getValidityForNewCard();
            pstmt.setString(1, newAccountNumber);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, currency.getId());
            pstmt.setInt(4, client.getId());
            pstmt.setInt(5, newStatusId);
            logger.trace("SQL in 'addNewAccount' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * @return count of blocked BankAccount
     */
    public int getCountBankAccountForUnlock() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_BANK_ACCOUNT_FOR_UNLOCK);
            logger.trace("SQL in 'getCountBankAccountForUnlock' request = " + pstmt.toString());
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
     * Extracts a bank account from the result set row.
     */
    static class BankAccountMapper implements EntityMapper<BankAccount> {

        @Override
        public BankAccount mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow BankAccount' = " + rs.toString());
                BankAccount bankAccount = new BankAccount();
                bankAccount.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccount.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccount.setCurrencyId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID));
                bankAccount.setUserId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID));
                bankAccount.setAccountStatusId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID));
                return bankAccount;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a bank account bean from the result set row.
     */
    static class BankAccountBeanMapper implements EntityMapper<BankAccountBean> {
        @Override
        public BankAccountBean mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow BankAccountBean' = " + rs.toString());
                BankAccountBean bankAccount = new BankAccountBean();
                bankAccount.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccount.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccount.setCurrencyId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID));
                bankAccount.setUserId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID));
                bankAccount.setAccountStatusId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID));
                return bankAccount;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }

        public BankAccountBean mapRowForGetAllAccount(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetAllAccount' = " + rs.toString());
                BankAccountBean bankAccountBean = new BankAccountBean();
                bankAccountBean.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccountBean.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                bankAccountBean.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccountBean.setUserId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__USER_ID));
                return bankAccountBean;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }

        public BankAccountBean mapRowForGetAccountList(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetAccountList' = " + rs.toString());
                BankAccountBean bankAccountBean = new BankAccountBean();
                bankAccountBean.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccountBean.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccountBean.setCurrencyId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID));
                bankAccountBean.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                bankAccountBean.setAccountStatusName(rs.getString(Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS));
                bankAccountBean.setAccountStatusId(rs.getInt(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID));
                return bankAccountBean;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }

        public BankAccountBean mapRowForGetAccountListForUnlock(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetAccountListForUnlock' = " + rs.toString());
                BankAccountBean bankAccountBean = new BankAccountBean();
                bankAccountBean.setNumber(rs.getString(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER));
                bankAccountBean.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                bankAccountBean.setClientName(rs.getString(Fields.TABLE__CLIENT + "." + Fields.CLIENT__NAME));
                bankAccountBean.setCurrencyName(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                return bankAccountBean;
            } catch (SQLException e) {
                logger.error("Can't map bank account", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
