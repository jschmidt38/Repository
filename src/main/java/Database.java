import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lingyi on 6/19/15.
 */
public final class Database {
    /**
     * Private constructor for utility class
     */
    private Database() {

    }
    /**
     * make connection with databse
     * @return connection
     */
    public static Connection makeConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql581299?useUnicode=true&characterEncoding=utf-8", "sql581299", "eZ3!iB2!");
            if (!con.isClosed()) {
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            }
            return con;
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * close the database
     * @param con
     */
    public static void makeClosed(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

