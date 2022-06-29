package singleton_design_pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonHelper_CreateConnection {

    private static SingletonHelper_CreateConnection instance;
    private final String url = "jdbc:postgresql://localhost:5432/orders_management";
    private final String username = "postgres";
    private final String password = "password";
    private Connection connection;

    private SingletonHelper_CreateConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static SingletonHelper_CreateConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SingletonHelper_CreateConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new SingletonHelper_CreateConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
