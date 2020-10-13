package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.BankAccount;
import ozamkovyi.db.entity.Client;
import ozamkovyi.web.CalendarProcessing;

import java.sql.*;
import java.util.ArrayList;

public class ClientDao {

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
        return client;
    }

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
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }

    }

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
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
    }

    public static ArrayList<Client> getListOfClientForAdmin(int page, int sortType) {
        ArrayList<Client> listOfClient = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        BankAccount bankAccount = null;
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
            pstmt.setInt(1, page * 10 - 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt(Fields.CLIENT__ID));
                client.setName(rs.getString(Fields.CLIENT__NAME));
                client.setStatus(rs.getString(Fields.CLIENT__STATUS));
                client.setCreditCardCount(CreditCardDao.getCountCardByUser(client));
                client.setAccountCount(BankAccountDao.getCountBankAccountByUser(client));
                listOfClient.add(client);
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
        return listOfClient;
    }

    public static int getCountClient(){
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

     public static void setStatus(Client client, String status){
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
             throwables.printStackTrace();
         } finally {
             dbManager.commitAndClose(con);
         }
     }


    private static class ClientMapper implements EntityMapper<Client> {

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
                throw new IllegalStateException(e);
            }
        }
    }
}
