package ozamkovyi.db.dao;

import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.CreditCardBean;
import ozamkovyi.db.entity.Client;
import ozamkovyi.db.entity.CreditCard;
import ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;
import ozamkovyi.db.DBManager;
import ozamkovyi.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Data access object for credit card entity.
 *
 * @author O.Zamkovyi
 */

public class CreditCardDao {

    private static final Logger logger = Logger.getLogger(CreditCardDao.class);


    private static final String SQL__FIND_CARD_BY_ID_SORT_BY_CURRENCY_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " join " + Fields.TABLE__ACCOUNT_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT_BY_CURRENCY_DESC_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " join " + Fields.TABLE__ACCOUNT_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + " DESC limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT_BY_BALANCE_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " join " + Fields.TABLE__ACCOUNT_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + " = " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ? " +
                    " ORDER BY " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + " limit 10 offset ?";

    private static final String SQL__FIND_CARD_BY_ID_SORT_BY_BALANCE_DESC_LIMIT =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + "," +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + ", " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + ", " +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME + ", " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS + ", " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS +
                    " FROM " + Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT +
                    " join " + Fields.TABLE__CURRENCY + " join " + Fields.TABLE__CARD_STATUS +
                    " join " + Fields.TABLE__ACCOUNT_STATUS +
                    " on " + Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " = " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__ACCOUNT_STATUS_ID + " = " +
                    Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__ID + " and " +
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

    private static final String SQL_GET_ALL_CARD_FOR_ACCOUNT =
            "select " + Fields.CREDIT_CARD__ID + " from " + Fields.TABLE__CREDIT_CARD + " where " + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + " = ?";

    private static final String getAllUnblockCreditCard =
            "SELECT " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + ", " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER + " FROM " +
                    Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__CARD_STATUS +
                    " on " + Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID + " = " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__ID + " and " +
                    Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS + " = ? and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID + " = ?";

    private static final String SQL_GET_CREDIT_CARD_BY_ID =
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
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " = ? ";

    private static final String SQL_GET_UAH_BALANCE_BY_ID =
            "SELECT " + Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE + "*" +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__RATE + " FROM " +
                    Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " on " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + "=" +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + "=" +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " = ?";

    /**
     * Returns a unblock credit card list for current client
     * @param client      current client.
     * @return ArrayList of CreditCard.
     */

    public ArrayList<CreditCard> getAllUnblockCreditCard(Client client) {
        ArrayList<CreditCard> listOfCreditCard = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(getAllUnblockCreditCard);
            pstmt.setString(1, Fields.CARD_STATUS__UNBLOCKED);
            pstmt.setInt(2, client.getId());
            CreditCardMapper mapper = new CreditCardMapper();
            logger.trace("SQL in 'getAllUnblockCreditCard' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CreditCard creditCard = mapper.mapRowForGetAllUnblockCreditCard(rs);
                listOfCreditCard.add(creditCard);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get all unblock credit card", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfCreditCard;
    }

    /**
     * Returns number new credit card
     * generates a credit card number and checks if it is not used
     *
     * @return number for credit card
     */
    private String generatorNewCardNumber() {
        String re = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 10;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_IS_NUMBER_ALREADY_EXISTING);
            while (rez != 0) {
                re = CreditCardBean.generatorCardNumber();
                pstmt.setString(1, re);
                logger.trace("SQL in 'generatorNewCardNumber' request = " + pstmt.toString());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    rez = rs.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            logger.error("Can't generate credit card number", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return re;
    }

    /**
     * Blocks all credit cards linked to a current bank account
     * @param bankAccount current bank account
     */
    public void blockAllCardForAccount(BankAccountBean bankAccount) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_CARD_FOR_ACCOUNT);
            pstmt.setString(1, bankAccount.getNumber());
            logger.trace("SQL in 'blockAllCardForAccount' request = " + pstmt.toString());
            pstmt.executeUpdate();
            while (rs.next()) {
                CreditCardBean creditCard = new CreditCardBean();
                creditCard.setId(rs.getInt(1));
                blocCard(creditCard);
            }
        } catch (SQLException throwables) {
            logger.error("Can't block all credit card fer bank account", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Adds new credit card to the database
     * @param account bank account for new credit card
     */

    public void addNewCard(BankAccountBean account) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = DBManager.getInstance().getConnection();
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__BLOCKED);
            String newCardNumber = generatorNewCardNumber();
            pstmt = con.prepareStatement(SQL_ADD_NEW_CREDIT_CARD);
            String newValidity = CalendarProcessing.getValidityForNewCard();
            pstmt.setString(1, newCardNumber);
            pstmt.setString(2, newValidity);
            pstmt.setString(3, account.getNumber());
            pstmt.setInt(4, account.getUserId());
            pstmt.setInt(5, newStatusId);
            logger.trace("SQL in 'addNewCard' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Returns count of client credit card
     * @param id current client id
     * @return count of client credit card
     */
    public int getCountCardByClient(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_CARD_BY_CLIENT_ID);
            pstmt.setInt(1, id);
            logger.trace("SQL in 'getCountCardByClient' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get count credit card", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    /**
     * Returns a credit card list for current client
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param id       Client id for search.
     * @param pageNumber     Page number
     * @param sortType Sorting type
     * @return ArrayList of CreditCardBaen.
     */
    public ArrayList<CreditCardBean> getCardList(int id, int pageNumber, int sortType) {
        ArrayList<CreditCardBean> listOfCreditCard = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL__FIND_CARD_BY_ID_SORT_BY_CURRENCY_LIMIT;
                break;
            case 2:
                sort = SQL__FIND_CARD_BY_ID_SORT_BY_CURRENCY_DESC_LIMIT;
                break;
            case 3:
                sort = SQL__FIND_CARD_BY_ID_SORT_BY_BALANCE_LIMIT;
                break;
            case 4:
                sort = SQL__FIND_CARD_BY_ID_SORT_BY_BALANCE_DESC_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            CreditCardBeanMapper mapper = new CreditCardBeanMapper();
            pstmt.setInt(1, id);
            pstmt.setInt(2, pageNumber * 10 - 10);
            logger.trace("SQL in 'getCardList' request = " + pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CreditCardBean creditCard = mapper.mapRowForGetCardList(rs);
                listOfCreditCard.add(creditCard);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get credit card list", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfCreditCard;
    }

    /**
     * Returns id for credit card status
     * @param value current credit card status
     * @return id
     */
    private int getIdStatusForCreditCard(String value) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_STATUS_CREDIT_CARD);
            pstmt.setString(1, value);
            logger.trace("SQL in 'getIdStatusForCreditCard' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getInt(1);
            }

        } catch (SQLException throwables) {
            logger.error("Can't status id for credit card", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    /**
     * Returns credit card be id
     * @param id id for current credit card
     * @return CreditCardBean entity
     */
    public CreditCardBean getCardById(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CreditCardBean creditCard = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_CREDIT_CARD_BY_ID);
            CreditCardBeanMapper mapper = new CreditCardBeanMapper();
            pstmt.setInt(1, id);
            logger.trace("SQL in 'getCardById' request = " + pstmt.toString());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                creditCard = mapper.mapRowForGetCardById(rs);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get credit card by id", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return creditCard;
    }

    /**
     * Blocks credit card
     * @param card credit card for blocking
     */
    public void blocCard(CreditCardBean card) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__BLOCKED);
            pstmt = con.prepareStatement(SQL_BLOC_UNBLOCK_CREDIT_CARD);
            pstmt.setInt(1, newStatusId);
            pstmt.setInt(2, card.getId());
            logger.trace("SQL in 'blocCard' request = " + pstmt.toString());
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't block credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Unblocks credit card
     * @param card credit card for unblocking
     */
    public void unblockCard(CreditCardBean card) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            int newStatusId = getIdStatusForCreditCard(Fields.CARD_STATUS__UNBLOCKED);
            pstmt = con.prepareStatement(SQL_BLOC_UNBLOCK_CREDIT_CARD);
            pstmt.setInt(1, newStatusId);
            pstmt.setInt(2, card.getId());
            logger.trace("SQL in 'unblockCard' request = " + pstmt.toString());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Can't unblock credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }

    }

    /**
     * Returns credit card balance in UAH
     * @param id credit card id
     * @return balance in UAH
     */
    public int getUAHBalance(int id) {
        int rez = 0;
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_UAH_BALANCE_BY_ID);
            pstmt.setInt(1, id);
            logger.trace("SQL in 'getUAHBalance' request = " + pstmt.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = (int) rs.getDouble(1);
            }

        } catch (SQLException throwables) {
            logger.error("Can't UAH balance for credit card", throwables);
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
     * Extracts a CreditCard from the result set row.
     */
    static class CreditCardMapper implements EntityMapper<CreditCard> {

        @Override
        public CreditCard mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow CreditCard' = " + rs.toString());
                CreditCard creditCard = new CreditCard();
                Calendar date = CalendarProcessing.string2Date(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY));
                creditCard.setId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID));
                creditCard.setNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                creditCard.setValidity(date);
                creditCard.setBankAccountNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER));
                creditCard.setUserId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID));
                creditCard.setCardStatusId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID));

                return creditCard;
            } catch (SQLException e) {
                logger.error("Can't map credit card", e);
                throw new IllegalStateException(e);
            }
        }

        public CreditCard mapRowForGetAllUnblockCreditCard(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetAllUnblockCreditCard' = " + rs.toString());
                CreditCardBean creditCard = new CreditCardBean();
                creditCard.setId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID));
                creditCard.setNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                return creditCard;
            } catch (SQLException e) {
                logger.error("Can't map credit card", e);
                throw new IllegalStateException(e);
            }
        }

    }

    /**
     * Extracts a CreditCardBean from the result set row.
     */
    static class CreditCardBeanMapper implements EntityMapper<CreditCardBean> {

        @Override
        public CreditCardBean mapRow(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRow CreditCardBean' = " + rs.toString());
                CreditCardBean creditCard = new CreditCardBean();
                Calendar date = CalendarProcessing.string2Date(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY));
                creditCard.setId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID));
                creditCard.setNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                creditCard.setValidity(date);
                creditCard.setBankAccountNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER));
                creditCard.setUserId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID));
                creditCard.setCardStatusId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID));
                return creditCard;
            } catch (SQLException e) {
                logger.error("Can't map credit card", e);
                throw new IllegalStateException(e);
            }
        }

        public CreditCardBean mapRowForGetCardById(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetCardById' = " + rs.toString());
                CreditCardBean creditCard = new CreditCardBean();
                Calendar date = CalendarProcessing.string2Date(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY));
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
                logger.error("Can't map credit card", e);
                throw new IllegalStateException(e);
            }
        }

        public CreditCardBean mapRowForGetCardList(ResultSet rs) {
            try {
                logger.trace("Result set in 'mapRowForGetCardList' = " + rs.toString());
                CreditCardBean creditCard = new CreditCardBean();
                Calendar date = CalendarProcessing.string2Date(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__VALIDITY));
                creditCard.setId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID));
                creditCard.setNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__NUMBER));
                creditCard.setValidity(date);
                creditCard.setBankAccountNumber(rs.getString(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER));
                creditCard.setUserId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__USER_ID));
                creditCard.setCardStatusId(rs.getInt(Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__CARD_STATUS_ID));
                creditCard.setCardStatusName(rs.getString(Fields.TABLE__CARD_STATUS + "." + Fields.CARD_STATUS__STATUS));
                creditCard.setBalance(rs.getLong(Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__BALANCE));
                creditCard.setCurrency(rs.getString(Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__NAME));
                creditCard.setAccountStatusName(rs.getString(Fields.TABLE__ACCOUNT_STATUS + "." + Fields.ACCOUNT_STATUS__STATUS));
                return creditCard;
            } catch (SQLException e) {
                logger.error("Can't map credit card", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
