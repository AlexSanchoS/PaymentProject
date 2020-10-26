package ozamkovyi.db.dao;

import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.bean.BankAccountBean;
import ozamkovyi.db.bean.ClientBean;
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

public class CreditCardDao {

    private static final Logger logger = Logger.getLogger(CreditCardDao.class);


    private static final String SQL__FIND_CARD_BY_ID_SORT1_LIMIT =
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

    private static final String SQL__FIND_CARD_BY_ID_SORT2_LIMIT =
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

    private static final String SQL__FIND_CARD_BY_ID_SORT3_LIMIT =
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

    private static final String SQL__FIND_CARD_BY_ID_SORT4_LIMIT =
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
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__COURSE + " FROM " +
                    Fields.TABLE__CREDIT_CARD + " join " + Fields.TABLE__BANK_ACCOUNT + " join " +
                    Fields.TABLE__CURRENCY + " on " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__BANK_ACCOUNT_NUMBER + "=" +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__NUMBER + " and " +
                    Fields.TABLE__BANK_ACCOUNT + "." + Fields.BANK_ACCOUNT__CURRENCY_ID + "=" +
                    Fields.TABLE__CURRENCY + "." + Fields.CURRENCY__ID + " and " +
                    Fields.TABLE__CREDIT_CARD + "." + Fields.CREDIT_CARD__ID + " = ?";

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

    public void blockAllCardForAccount(BankAccountBean bankAccount) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL_GET_ALL_CARD_FOR_ACCOUNT);
            pstmt.setString(1, bankAccount.getNumber());
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

    public void addNewCard(BankAccountBean account) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
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
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't add new credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public int getCountCardByUser(int id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_CARD_BY_CLIENT_ID);
            pstmt.setInt(1, id);
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

    public ArrayList<CreditCardBean> getCardList(int id, int pageNumber, int sortType) {
        ArrayList<CreditCardBean> listOfCreditCard = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
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
            CreditCardBeanMapper mapper = new CreditCardBeanMapper();
            pstmt.setInt(1, id);
            pstmt.setInt(2, pageNumber * 10 - 10);
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
            pstmt.executeUpdate();

        } catch (SQLException throwables) {
            logger.error("Can't block credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

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
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Can't unblock credit card", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }

    }

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

    private void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                logger.error("Can't close autoCloseable object", e);
            }
        }
    }


    static class CreditCardMapper implements EntityMapper<CreditCard> {

        @Override
        public CreditCard mapRow(ResultSet rs) {
            try {
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

    static class CreditCardBeanMapper implements EntityMapper<CreditCardBean> {

        @Override
        public CreditCardBean mapRow(ResultSet rs) {
            try {
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
