package db_connection;

import helpers.PropertiesHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

    private static DB_Connection instance;
    private Connection connection;
    private PropertiesHelper propertiesHelper;

    public Connection getConnection () throws SQLException {
        try {
            propertiesHelper = new PropertiesHelper();
            connection = DriverManager.getConnection(
                    propertiesHelper.getUrl(),
                    propertiesHelper.getUser(),
                    propertiesHelper.getPassword()
            );
        } catch (IOException e) {
            System.out.println("Database Connection Creation Failed");
        }
        return connection;
    }

    public static DB_Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DB_Connection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DB_Connection();
        }
        return instance;
    }
}
