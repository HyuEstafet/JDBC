package daos;

import dbconnection.DBConnectionNew;
import pojos.CustomersPojo;
import dbconnection.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao implements DaoInterface<CustomersPojo> {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public void CustomerDao() throws SQLException, IOException {
        connection=DBConnection.getInstance().getConnection();
        System.out.println("Connection: " + connection);
    }

    @Override
    public void save(CustomersPojo newCustomer) throws SQLException {
        connection = new DBConnection().getConnection();
        try {
            System.out.println("Connection save(): " + connection);
            preparedStatement =connection.prepareStatement("INSERT INTO customers " +
                    "(customer_id,customer_name,customer_email,customer_phone,customer_age,gdpr_status," +
                    "customer_profile_status,date_profile_created,date_profile_deactivated,deactivation_reason," +
                    "customer_notes)" +
                    "VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,newCustomer.getCustomerId());
            preparedStatement.setString(2,newCustomer.getCustomerName());
            preparedStatement.setString(3,newCustomer.getCustomerEmail());
            preparedStatement.setString(4,newCustomer.getCustomerPhone());
            preparedStatement.setInt(5,newCustomer.getCustomerAge());
            preparedStatement.setBoolean(6,newCustomer.isGdprStatus());preparedStatement.setBoolean(7,newCustomer.isCustomerProfileStatus());
            preparedStatement.setDate(8,newCustomer.getDateProfileCreated());
            preparedStatement.setDate(9,newCustomer.getDateProfileDeactivated());
            preparedStatement.setString(10,newCustomer.getDeactivationReason());
            preparedStatement.setString(11,newCustomer.getCustomerNotes());
            System.out.println("Prepared statement: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            System.out.println("New customer is saved successfully:" + newCustomer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(int id, String email) throws SQLException {
        connection = new DBConnection().getConnection();
        try {
            preparedStatement=connection.prepareStatement("UPDATE customers SET customer_email = ? WHERE customer_id = ?");
            preparedStatement.setString(4,email);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        connection =  DBConnectionNew().getConnection();
        try {
            preparedStatement =connection.prepareStatement("DELETE FROM customers WHERE customer_id= ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Customer with "+id+ " is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        connection = new DBConnection().getConnection();
        try {
            preparedStatement=connection.prepareStatement("DELETE FROM customers");
            preparedStatement.executeUpdate();
            System.out.println("All records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CustomersPojo getRandomId() throws SQLException {
        connection = new DBConnection().getConnection();
        CustomersPojo randomCustomerId = null;
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM customers ORDER BY RANDOM() LIMIT 1");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomCustomerId = CustomersPojo.builder()
                        .customerId(resultSet.getInt(1))
                        .customerName(resultSet.getString(2))
                        .customerEmail(resultSet.getString(3))
                        .customerPhone(resultSet.getString(4))
                        .customerAge(resultSet.getInt(5))
                        .gdprStatus(resultSet.getBoolean(6))
                        .customerProfileStatus(resultSet.getBoolean(7))
                        .dateProfileCreated(resultSet.getDate(8))
                        .dateProfileDeactivated(resultSet.getDate(9))
                        .deactivationReason(resultSet.getString(10))
                        .customerNotes(resultSet.getString(11))
                        .build();
            }
            System.out.println("The selected random customer is: "+randomCustomerId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomCustomerId;
    }

    @Override
    public List<Integer> getRandomIds(int randomCount) throws SQLException {
        connection = new DBConnection().getConnection();
        List<Integer> randomCustomersIds = new ArrayList<>();
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM customers ORDER BY RANDOM() LIMIT ?");
            preparedStatement.setInt(1,randomCount);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                randomCustomersIds.add(resultSet.getInt("customer_id"));
            }
            System.out.println("The selected random customers are: " + randomCustomersIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return randomCustomersIds;
    }

    @Override
    public int getRecordsCount() throws SQLException {
        connection = new DBConnection().getConnection();
        int recordsCount = 0;
        try {
            preparedStatement=connection.prepareStatement("SELECT COUNT(customer_id) FROM customers");
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                recordsCount=resultSet.getInt("customer_id");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return recordsCount;
    }

    @Override
    public CustomersPojo getById(int id) throws SQLException {
        connection = new DBConnection().getConnection();
        CustomersPojo searchedCustomer = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id= ?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                searchedCustomer=CustomersPojo.builder()
                        .customerId(resultSet.getInt("customer_id"))
                        .customerName(resultSet.getString("customer_name"))
                        .customerEmail(resultSet.getString("customer_email"))
                        .customerPhone(resultSet.getString("customer_phone"))
                        .customerAge(resultSet.getInt("customer_age"))
                        .gdprStatus(resultSet.getBoolean("gdpr_status"))
                        .customerProfileStatus(resultSet.getBoolean("customer_profile_status"))
                        .dateProfileCreated(resultSet.getDate("date_profile_created"))
                        .dateProfileDeactivated(resultSet.getDate("date_profile_deactivated"))
                        .deactivationReason(resultSet.getString("deactivation_reason"))
                        .customerNotes(resultSet.getString("customer_notes")).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Selected user is with id: "+id);
        System.out.println(searchedCustomer);
        return searchedCustomer;
    }
}
