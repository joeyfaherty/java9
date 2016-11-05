import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TryWithResources {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("db");
        // no need to declare a new variable in the try-with-resources statement as with Java 7 & 8
        try (connection) {
            connection.close();
        }
    }
}
