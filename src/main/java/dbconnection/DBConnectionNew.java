package dbconnection;

import helpers.PropertiesHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionNew {

    private static Connection connection;
    private static PropertiesHelper propertiesHelper;

    public static Connection getConnection () throws SQLException {
        if(connection==null){
            createConnection();
        } else if (connection.isClosed()){
            createConnection();
        }
        return connection;
    }

    private static Connection createConnection() throws SQLException {
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
        return connection;
    }

}
