package taxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "Gbhfvblf18");
        String url = "jdbc:mysql://localhost:3306/taxi?serverTimezone=UTC";
        try {
            return DriverManager.getConnection(url,dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish the connection to DB", e);
        }
    }
}
