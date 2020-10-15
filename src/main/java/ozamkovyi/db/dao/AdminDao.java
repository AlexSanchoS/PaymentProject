package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    private static final String SQL__FIND_ADMIN_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM " + Fields.TABLE__ADMIN + " WHERE " + Fields.ADMIN__LOGIN + "=? and " + Fields.ADMIN__PASSWORD + " =?";

    private static final String SQL__SET_CLIENT_LOCAL =
            " UPDATE " + Fields.TABLE__ADMIN + " SET " + Fields.ADMIN__LANGUAGE + " = ? WHERE " + Fields.ADMIN__ID + " =?";


    public Admin findAdminByLoginAndPassword(String login, String password) {
        Admin admin = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL__FIND_ADMIN_BY_LOGIN_AND_PASSWORD);
            AdminMapper mapper = new AdminMapper();
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return admin;
    }

    public static void setAdminLocal(Admin admin) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__SET_CLIENT_LOCAL);
            pstmt.setString(1, admin.getLanguage());
            pstmt.setInt(2, admin.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
    }


    private static void close(AutoCloseable forClose) {
        if (forClose != null) {
            try {
                forClose.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class AdminMapper implements EntityMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet rs) {
            try {
                Admin admin = new Admin();
                admin.setId(rs.getInt(Fields.ADMIN__ID));
                admin.setLogin(rs.getString(Fields.ADMIN__LOGIN));
                admin.setPassword(rs.getString(Fields.ADMIN__PASSWORD));
                admin.setLanguage(rs.getString(Fields.ADMIN__LANGUAGE));
                return admin;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
