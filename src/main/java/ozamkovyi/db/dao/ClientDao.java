package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Client;
import ozamkovyi.web.CalendarProcessing;

import java.sql.*;

public class ClientDao {

    private static final String SQL__FIND_CLIENT_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM " + Fields.TABLE__CLIENT + " WHERE " + Fields.CLIENT__LOGIN + "=? and " + Fields.CLIENT__PASSWORD + " =?";
    private static final String SQL__SET_CLIENT_LOCAL =
            " UPDATE "+ Fields.TABLE__CLIENT+" SET " + Fields.CLIENT__LANGUAGE + " =? WHERE "+ Fields.CLIENT__ID +" =?";
    private static final String SQL__ADD_NEW_CLIENT =
            "INSERT INTO " + Fields.TABLE__CLIENT + "(" + Fields.CLIENT__LOGIN + ", " + Fields.CLIENT__PASSWORD + ", " + Fields.CLIENT__NAME + ", " + Fields.CLIENT__DATE_OF_BIRTH + ", " + Fields.CLIENT__LANGUAGE + ") VALUES (?, ?, ?, ?, ?)";


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
        }
        finally {
            if (con!=null){
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
//            pstmt = con.prepareStatement(SQL__ADD_NEW_CLIENT);
            String date = CalendarProcessing.date2String(client.getDate());
            pstmt.setString(1, client.getLogin());
            pstmt.setString(2, client.getPassword());
            pstmt.setString(3, client.getName());
            pstmt.setString(4, date);
            pstmt.setString(5, client.getLanguage());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                client.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            dbManager.commitAndClose(con);
        }

    }

    public void setClientLocal(Client client){
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__SET_CLIENT_LOCAL);
            String date = CalendarProcessing.date2String(client.getDate());
            pstmt.setString(1, client.getLanguage());
            pstmt.setInt(2, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
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
                return client;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
