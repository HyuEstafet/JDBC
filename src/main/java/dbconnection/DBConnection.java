package dbconnection;

import helpers.PropertiesHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private static Connection connection;
    private PropertiesHelper propertiesHelper;

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection () throws SQLException {
        if(connection==null){
            createConnection();
        } else if (connection.isClosed()){
            createConnection();
        }
        return connection;
    }

    private void createConnection() throws SQLException {
        try {
            propertiesHelper = PropertiesHelper.getInstance();
            connection = DriverManager.getConnection(
                    propertiesHelper.getUrl(),
                    propertiesHelper.getUser(),
                    propertiesHelper.getPassword()
            );
        } catch (IOException e) {
            System.out.println("Database Connection Creation Failed");
        }
    }
}
