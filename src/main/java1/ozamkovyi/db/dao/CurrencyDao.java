package ozamkovyi.db.dao;

import org.apache.log4j.Logger;
import ozamkovyi.db.DBManager;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurrencyDao {

    private static final Logger logger = Logger.getLogger(CurrencyDao.class);


    private static final String SQL_GET_ALL_CURRENCY = "SELECT " + Fields.CURRENCY__NAME + ", " +
            Fields.CURRENCY__ID + " FROM " + Fields.TABLE__CURRENCY;

    public static ArrayList<Currency> getAllCurrency() {
        ArrayList<Currency> listOfCurrency = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_CURRENCY);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Currency currency = new Currency();
                currency.setId(rs.getInt(Fields.CURRENCY__ID));
                currency.setName(rs.getString(Fields.CURRENCY__NAME));
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

    private static void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                logger.error("Can't close autoCloseable object", e);
            }
        }
    }


}
