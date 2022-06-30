package story01jdbctests;

import dbconnection.DBConnection;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestClass {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;


    @BeforeEach
    public void setConnection () {
        try {
            connection = new DBConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void test () throws SQLException {
        String query = "SELECT * FROM customers WHERE customer_name = 'Nick Clements'";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Assert.assertEquals(1,resultSet.getInt("customer_id"));
        }
    }
    @AfterEach
    public void closeConnection() throws SQLException {
        statement.close();
        resultSet.close();
        connection.close();
    }
    }
