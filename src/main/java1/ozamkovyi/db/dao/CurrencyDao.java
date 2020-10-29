package ozamkovyi.db.dao;

import org.apache.log4j.Logger;
import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data access object for currency entity.
 *
 * @author O.Zamkovyi
 */

public class CurrencyDao {

    private static final Logger logger = Logger.getLogger(CurrencyDao.class);


    private static final String SQL_GET_ALL_CURRENCY = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + " FROM " + Fields.TABLE__CURRENCY;

    private static final String SQL_GET_ALL_CURRENCY_SORT1_LIMIT = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + ", " +
            Fields.CURRENCY__COURSE + " FROM " + Fields.TABLE__CURRENCY + " ORDER BY " +
            Fields.CURRENCY__NAME + " limit 10 offset ?";

    private static final String SQL_GET_ALL_CURRENCY_SORT2_LIMIT = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + ", " +
            Fields.CURRENCY__COURSE + " FROM " + Fields.TABLE__CURRENCY + " ORDER BY " +
            Fields.CURRENCY__NAME + " DESC limit 10 offset ?";

    private static final String SQL_GET_ALL_CURRENCY_SORT3_LIMIT = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + ", " +
            Fields.CURRENCY__COURSE + " FROM " + Fields.TABLE__CURRENCY + " ORDER BY " +
            Fields.CURRENCY__COURSE + " limit 10 offset ?";

    private static final String SQL_GET_ALL_CURRENCY_SORT4_LIMIT = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + ", " +
            Fields.CURRENCY__COURSE + " FROM " + Fields.TABLE__CURRENCY + " ORDER BY " +
            Fields.CURRENCY__COURSE + " DESC limit 10 offset ?";


    private static final String SQL_GET_COUNT_CURRENCY = "SELECT COUNT(" + Fields.CURRENCY__ID + ") " +
            " FROM " + Fields.TABLE__CURRENCY;

    private static final String SQL_CHANGE_RATE_FOR_CURRENCY = "UPDATE " + Fields.TABLE__CURRENCY +
            " SET " + Fields.CURRENCY__COURSE + " =? WHERE " + Fields.CURRENCY__ID + " =?";

    /**
     * Returns a currency list
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param page     Page number
     * @param sortType Sorting type
     * @return ArrayList of currency.
     */

    public ArrayList<Currency> getAllCurrencyForChange(int page, int sortType) {
        ArrayList<Currency> listOfCurrency = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL_GET_ALL_CURRENCY_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL_GET_ALL_CURRENCY_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL_GET_ALL_CURRENCY_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL_GET_ALL_CURRENCY_SORT4_LIMIT;
                break;
        }

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            CurrencyMapper mapper = new CurrencyMapper();
            pstmt.setInt(1, page * 10 - 10);
            logger.trace("SQL in 'getAllCurrencyForChange' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Currency currency = mapper.mapRow(rs);
                listOfCurrency.add(currency);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get all currency", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfCurrency;
    }

    /**
     * Returns count of currency.
     *
     * @return count of currency.
     */

    public int getCountCurrency() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_CURRENCY);
            logger.trace("SQL in 'getCountCurrency' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get client count", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    /**
     * Changes rate for currency
     * @param id currency id
     * @param rate new rate for currency
     */
    public void changeRate(int id, float rate) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_CHANGE_RATE_FOR_CURRENCY);
            pstmt.setFloat(1, rate);
            pstmt.setInt(2, id);
            logger.trace("SQL in 'changeRate' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't change status for bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Returns a currency list
     * @return ArrayList of Currency
     */
    public ArrayList<Currency> getAllCurrency() {
        ArrayList<Currency> listOfCurrency = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_CURRENCY);
            CurrencyMapper mapper = new CurrencyMapper();
            logger.trace("SQL in 'getAllCurrency' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Currency currency = mapper.mapRowForGetAllCurrency(rs);
                listOfCurrency.add(currency);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get all currency", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfCurrency;
    }

    /**
     * Close autoClosable object
     *
     * @param forClose object for closing
     */

    private static void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                logger.error("Can't close autoCloseable object", e);
            }
        }
    }

    /**
     * Extracts a Admin from the result set row.
     */

    static class CurrencyMapper implements EntityMapper<Currency> {

        @Override
        public Currency mapRow(ResultSet rs) {
            Currency currency = new Currency();
            try {
                logger.trace("Result set in 'mapRow' = " + rs.toString());
                currency.setId(rs.getInt(Fields.CURRENCY__ID));
                currency.setName(rs.getString(Fields.CURRENCY__NAME));
                currency.setCourse(rs.getFloat(Fields.CURRENCY__COURSE));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return currency;
        }

        public Currency mapRowForGetAllCurrency(ResultSet rs) {
            Currency currency = new Currency();
            try {
                logger.trace("Result set in 'mapRowForGetAllCurrency' = " + rs.toString());
                currency.setId(rs.getInt(Fields.CURRENCY__ID));
                currency.setName(rs.getString(Fields.CURRENCY__NAME));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return currency;
        }
    }


}
