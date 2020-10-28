package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.bean.ClientBean;
import ozamkovyi.db.entity.Client;
import ozamkovyi.web.CalendarProcessing;
import org.apache.log4j.Logger;
import ozamkovyi.db.Fields;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data access object for Client entity.
 *
 * @author O.Zamkovyi
 */

public class ClientDao {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(ClientDao.class);

    private static final String SQL__FIND_CLIENT_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM " + Fields.TABLE__CLIENT + " WHERE " + Fields.CLIENT__LOGIN + "=? and " + Fields.CLIENT__PASSWORD + " =?";

    private static final String SQL__SET_CLIENT_LOCAL =
            " UPDATE " + Fields.TABLE__CLIENT + " SET " + Fields.CLIENT__LANGUAGE + " =? WHERE " + Fields.CLIENT__ID + " =?";

    private static final String SQL__SET_CLIENT_STATUS =
            " UPDATE " + Fields.TABLE__CLIENT + " SET " + Fields.CLIENT__STATUS + " =? WHERE " + Fields.CLIENT__ID + " =?";

    private static final String SQL__ADD_NEW_CLIENT =
            "INSERT INTO " + Fields.TABLE__CLIENT + "(" + Fields.CLIENT__LOGIN + ", " + Fields.CLIENT__PASSWORD + ", " +
                    Fields.CLIENT__NAME + ", " + Fields.CLIENT__DATE_OF_BIRTH + ", " + Fields.CLIENT__LANGUAGE + "," +
                    Fields.CLIENT__STATUS + ") VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT1_LIMIT =
            "SELECT " + Fields.CLIENT__ID + ", " + Fields.CLIENT__NAME + "," +
                    Fields.CLIENT__STATUS + " from " + Fields.TABLE__CLIENT +
                    " ORDER BY " + Fields.CLIENT__NAME + " limit 10 offset ?";

    private static final String SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT2_LIMIT =
            "SELECT " + Fields.CLIENT__ID + ", " + Fields.CLIENT__NAME + "," +
                    Fields.CLIENT__STATUS + " from " + Fields.TABLE__CLIENT +
                    " ORDER BY " + Fields.CLIENT__NAME + " DESC limit 10 offset ?";

    private static final String SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT3_LIMIT =
            "SELECT " + Fields.CLIENT__ID + ", " + Fields.CLIENT__NAME + "," +
                    Fields.CLIENT__STATUS + " from " + Fields.TABLE__CLIENT +
                    " ORDER BY " + Fields.CLIENT__STATUS + " limit 10 offset ?";

    private static final String SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT4_LIMIT =
            "SELECT " + Fields.CLIENT__ID + ", " + Fields.CLIENT__NAME + "," +
                    Fields.CLIENT__STATUS + " from " + Fields.TABLE__CLIENT +
                    " ORDER BY " + Fields.CLIENT__STATUS + " DESC limit 10 offset ?";

    private static final String SQL_GET_COUNT_CLIENT = "SELECT COUNT(" + Fields.CLIENT__ID + ") " +
            " FROM " + Fields.TABLE__CLIENT;

    private static final String SQL_GET_STATUS_BY_ID = "SELECT " + Fields.CLIENT__STATUS +
            " FROM " + Fields.TABLE__CLIENT + " WHERE " + Fields.CLIENT__ID + " = ?";


    /**
     * Returns a client with the given login and password.
     *
     * @param login    User login.
     * @param password User password.
     * @return Client entity.
     */

    public Client findClientByLoginAndPassword(String login, String password) {
        Client client = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_CLIENT_BY_LOGIN_AND_PASSWORD);
            ClientMapper mapper = new ClientMapper();
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                client = mapper.mapRow(rs);
            }
        } catch (SQLException throwables) {
            logger.error("Can't find client", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return client;
    }

    /**
     * Adds a new client to the database
     *
     * @param client Client to add.
     */
    public void addNewClientToDB(Client client) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {

            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__ADD_NEW_CLIENT, Statement.RETURN_GENERATED_KEYS);
            String date = CalendarProcessing.date2String(client.getDate());
            pstmt.setString(1, client.getLogin());
            pstmt.setString(2, client.getPassword());
            pstmt.setString(3, client.getName());
            pstmt.setString(4, date);
            pstmt.setString(5, client.getLanguage());
            pstmt.setString(6, client.getStatus());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            logger.error("Can't add new client", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }

    }

    /**
     * Update client local.
     *
     * @param client Client to update.
     */

    public void setClientLocal(Client client) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__SET_CLIENT_LOCAL);
            pstmt.setString(1, client.getLanguage());
            pstmt.setInt(2, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Can't set client local", throwables);
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    /**
     * Returns a client list
     * use sort and limit.
     * used for pagination
     * Every page has 10 records
     *
     * @param page     Page number
     * @param sortType Sorting type
     * @return ArrayList of ClientBean.
     */

    public ArrayList<ClientBean> getListOfClientForAdmin(int page, int sortType) {
        ArrayList<ClientBean> listOfClient = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sort = null;
        switch (sortType) {
            case 1:
                sort = SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT1_LIMIT;
                break;
            case 2:
                sort = SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT2_LIMIT;
                break;
            case 3:
                sort = SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT3_LIMIT;
                break;
            case 4:
                sort = SQL__GET_CLIENT_LIST_FOR_ADMIN_SORT4_LIMIT;
                break;
        }
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sort);
            ClientBeanMapper mapper = new ClientBeanMapper();
            pstmt.setInt(1, page * 10 - 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ClientBean client = mapper.mapRowForListOfClientForAdmin(rs);
                client.setCreditCardCount(new CreditCardDao().getCountCardByClient(client.getId()));
                client.setAccountCount(new BankAccountDao().getCountBankAccountByUser(client.getId()));
                listOfClient.add(client);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get list of client", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return listOfClient;
    }

    /**
     * @return count of Client
     */

    public int getCountClient() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int rez = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_COUNT_CLIENT);
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
     * Update client status.
     *
     * @param client Client to update.
     * @param status new client status.
     */
    public void setStatus(Client client, String status) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__SET_CLIENT_STATUS);
            pstmt.setString(1, status);
            pstmt.setInt(2, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Can't set client status", throwables);
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
     * Returns status of client
     * @param client current client
     * @return status of client
     */

    public String getClientStatus(Client client) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String rez = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_GET_STATUS_BY_ID);
            pstmt.setInt(1, client.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rez = rs.getString(1);
            }
        } catch (SQLException throwables) {
            logger.error("Can't get client status", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return rez;
    }

    /**
     * Extracts a Client from the result set row.
     */
    static class ClientMapper implements EntityMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs) {
            try {
                Client client = new Client();
                client.setId(rs.getInt(Fields.CLIENT__ID));
                client.setLogin(rs.getString(Fields.CLIENT__LOGIN));
                client.setPassword(rs.getString(Fields.CLIENT__PASSWORD));
                client.setName(rs.getString(Fields.CLIENT__NAME));
                client.setDate(CalendarProcessing.string2Date(rs.getString(Fields.CLIENT__DATE_OF_BIRTH)));
                client.setLanguage(rs.getString(Fields.CLIENT__LANGUAGE));
                client.setStatus(rs.getString(Fields.CLIENT__STATUS));
                return client;
            } catch (SQLException e) {
                logger.error("Can't map client", e);
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a ClientBean from the result set row.
     */
    static class ClientBeanMapper implements EntityMapper<ClientBean> {

        @Override
        public ClientBean mapRow(ResultSet rs) {
            try {
                ClientBean client = new ClientBean();
                client.setId(rs.getInt(Fields.CLIENT__ID));
                client.setLogin(rs.getString(Fields.CLIENT__LOGIN));
                client.setPassword(rs.getString(Fields.CLIENT__PASSWORD));
                client.setName(rs.getString(Fields.CLIENT__NAME));
                client.setDate(CalendarProcessing.string2Date(rs.getString(Fields.CLIENT__DATE_OF_BIRTH)));
                client.setLanguage(rs.getString(Fields.CLIENT__LANGUAGE));
                client.setStatus(rs.getString(Fields.CLIENT__STATUS));
                return client;
            } catch (SQLException e) {
                logger.error("Can't map client", e);
                throw new IllegalStateException(e);
            }
        }

        public ClientBean mapRowForListOfClientForAdmin(ResultSet rs) {
            try {
                ClientBean client = new ClientBean();
                client.setId(rs.getInt(Fields.CLIENT__ID));
                client.setName(rs.getString(Fields.CLIENT__NAME));
                client.setStatus(rs.getString(Fields.CLIENT__STATUS));
                return client;
            } catch (SQLException e) {
                logger.error("Can't map client", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
