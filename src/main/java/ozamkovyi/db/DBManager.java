package ozamkovyi.db;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {

    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;

    }

    private DBManager(){}
    
    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */


    public Connection getConnection() throws SQLException{
        Connection con = null;
        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) envContext.lookup("jdbc/PaymentDB");
            con = ds.getConnection();
        } catch (NamingException e) {
            logger.error("Cannot obtain a connection from the pool", e);
        }
        return con;
    }

    public void commitAndClose(Connection con){
        try {
            if (con!=null) {
                con.commit();
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con){
        try {
            if (con!=null){
                con.rollback();
                con.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
