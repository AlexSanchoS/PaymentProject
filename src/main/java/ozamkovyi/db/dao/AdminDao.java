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
            "SELECT * FROM "+ Fields.TABLE__ADMIN+" WHERE "+Fields.ADMIN__LOGIN+"=? and "+Fields.ADMIN__PASSWORD+" =?";

    public Admin findAdminByLoginAndPassword(String login, String password){
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
            if (rs.next()){
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
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
