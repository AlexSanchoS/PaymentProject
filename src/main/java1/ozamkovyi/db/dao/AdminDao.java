package ozamkovyi.db.dao;

import ozamkovyi.db.DBManager;
import ozamkovyi.db.EntityMapper;
import ozamkovyi.db.Fields;
import ozamkovyi.db.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * Data access object for Admin entity.
 *
 * @author O.Zamkovyi
 */

public class AdminDao {

    private static final Logger logger = Logger.getLogger(AdminDao.class);

    private static final String SQL__FIND_ADMIN_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM " + Fields.TABLE__ADMIN + " WHERE " + Fields.ADMIN__LOGIN + "=? and " + Fields.ADMIN__PASSWORD + " =?";

    private static final String SQL__SET_CLIENT_LOCAL =
            " UPDATE " + Fields.TABLE__ADMIN + " SET " + Fields.ADMIN__LANGUAGE + " = ? WHERE " + Fields.ADMIN__ID + " =?";


    /**
     * Returns a Admin with the given login and password.
     *
     * @param login    User login.
     * @param password User password.
     * @return Admin entity.
     */
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
            logger.trace("SQL in 'findAdminByLoginAndPassword' request = " + pstmt.toString());
            if (rs.next()) {
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException throwables) {
            logger.error("Can't find admin", throwables);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return admin;
    }

    /**
     * Update Admin local.
     *
     * @param admin Admin to update.
     */
    public void setAdminLocal(Admin admin) {
        PreparedStatement pstmt = null;
        Connection con = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            pstmt = con.prepareStatement(SQL__SET_CLIENT_LOCAL);
            pstmt.setString(1, admin.getLanguage());
            pstmt.setInt(2, admin.getId());
            logger.trace("SQL in 'setAdminLocal' request = " + pstmt.toString());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Can't set admin local", throwables);
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
                logger.error("Can't close AutoCloseable object", e);
            }
        }
    }


    /**
     * Extracts a Admin from the result set row.
     */
    static class AdminMapper implements EntityMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet rs) {
            logger.trace("Result set in 'EntityMapper<Admin>' = " + rs.toString());
            try {
                Admin admin = new Admin();
                admin.setId(rs.getInt(Fields.ADMIN__ID));
                admin.setLogin(rs.getString(Fields.ADMIN__LOGIN));
                admin.setPassword(rs.getString(Fields.ADMIN__PASSWORD));
                admin.setLanguage(rs.getString(Fields.ADMIN__LANGUAGE));
                return admin;
            } catch (SQLException e) {
                logger.error("Can't map admin", e);
                throw new IllegalStateException(e);
            }
        }
    }
}
