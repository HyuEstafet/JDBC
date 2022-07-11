package daos;

import dbconnection.DBConnection;
import pojos.CustomersPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao implements DaoInterface<CustomersPojo> {
    private Connection connection;
    private DBConnection dbConnection = new DBConnection();
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public void save(CustomersPojo newCustomer) throws SQLException {
        try {
            preparedStatement = dbConnection.getInstance().getConnection().prepareStatement("INSERT INTO customers " +
                    "(customer_name,customer_email,customer_phone,customer_age,gdpr_status," +
                    "customer_profile_status,date_profile_created,date_profile_deactivated,deactivation_reason," +
                    "customer_notes)" +
                    "VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,newCustomer.getCustomerName());
            preparedStatement.setString(2,newCustomer.getCustomerEmail());
            preparedStatement.setString(3,newCustomer.getCustomerPhone());
            preparedStatement.setInt(4,newCustomer.getCustomerAge());
            preparedStatement.setBoolean(5,newCustomer.isGdprStatus());
            preparedStatement.setBoolean(6,newCustomer.isCustomerProfileStatus());
            preparedStatement.setDate(7,newCustomer.getDateProfileCreated());
            preparedStatement.setDate(8,newCustomer.getDateProfileDeactivated());
            preparedStatement.setString(9,newCustomer.getDeactivationReason());
            preparedStatement.setString(10,newCustomer.getCustomerNotes());
            System.out.println("Prepared statement: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            System.out.println("New customer is saved successfully:" + newCustomer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(int id, String email) throws SQLException {
        try {
            preparedStatement=dbConnection.getInstance().getConnection().prepareStatement("UPDATE customers SET customer_email = ? WHERE customer_id = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            preparedStatement =dbConnection.getInstance().getConnection().prepareStatement("DELETE FROM customers WHERE customer_id= ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Customer with id:"+id+ " is deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        try {
            preparedStatement=dbConnection.getInstance().getConnection().prepareStatement("DELETE FROM customers");
            preparedStatement.executeUpdate();
            System.out.println("All records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CustomersPojo getRandomId() throws SQLException {
        CustomersPojo randomCustomerId = null;
        try {
            preparedStatement=dbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customers ORDER BY RANDOM() LIMIT 1");
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
    public List<Integer> getRandomIds(int randomCount) {
        List<Integer> randomCustomersIds = new ArrayList<>();
        try {
            preparedStatement=dbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customers ORDER BY RANDOM() LIMIT ?");
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
    public int getRecordsCount(){
        int recordsCount = 0;
        try {
            preparedStatement=dbConnection.getInstance().getConnection().prepareStatement("SELECT COUNT(customer_id) FROM customers");
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
    public CustomersPojo getById(int id){
        CustomersPojo searchedCustomer = null;
        try {
            preparedStatement = dbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customers WHERE customer_id= ?");
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
        System.out.println("Selected user is with id: " + id);
        System.out.println(searchedCustomer);
        return searchedCustomer;
    }

    @Override
    public List<CustomersPojo> getByIds(List<Integer> ids) throws SQLException {
        List<CustomersPojo> customers = new ArrayList<>();
        try {
            preparedStatement = dbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
            for (int i=0; i < ids.size(); i ++) {
                preparedStatement.setInt(1,ids.get(i));
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CustomersPojo customer = new CustomersPojo();
                    customer.setCustomerId(resultSet.getInt("customer_id"));
                    customer.setCustomerName(resultSet.getString("customer_name"));
                    customer.setCustomerEmail(resultSet.getString("customer_email"));
                    customer.setCustomerPhone(resultSet.getString("customer_phone"));
                    customer.setCustomerAge(resultSet.getInt("customer_age"));
                    customer.setGdprStatus(resultSet.getBoolean("gdpr_status"));
                    customer.setCustomerProfileStatus(resultSet.getBoolean("customer_profile_status"));
                    customer.setDateProfileCreated(resultSet.getDate("date_profile_created"));
                    customer.setDateProfileDeactivated(resultSet.getDate("date_profile_deactivated"));
                    customer.setDeactivationReason(resultSet.getString("deactivation_reason"));
                    customer.setCustomerNotes(resultSet.getString("customer_notes"));
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("List of customers: "+customers);
        return customers;
    }
}
