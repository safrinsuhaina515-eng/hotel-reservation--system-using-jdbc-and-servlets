import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        return DriverManager.getConnection(
            "jdbc:hsqldb:file:hotel_db;shutdown=true",
            "SA",
            ""); // HSQLDB default
    }
}
