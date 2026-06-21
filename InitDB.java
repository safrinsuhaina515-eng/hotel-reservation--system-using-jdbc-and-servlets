import java.sql.Connection;
import java.sql.Statement;

public class InitDB {
    public static void main(String[] args) throws Exception {
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS reservations (id INTEGER IDENTITY, guest_name VARCHAR(100), room_type VARCHAR(50), check_in DATE, check_out DATE)");
        stmt.close();
        con.close();
        System.out.println("Database initialized.");
    }
}