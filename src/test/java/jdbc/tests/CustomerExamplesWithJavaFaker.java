package jdbc.tests;

import helpers.CustomersHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojos.CustomersPojo;
import java.sql.SQLException;
import java.util.List;

public class CustomerExamplesWithJavaFaker {

    @Test
    public void createOneCustomerWithJavaFaker() throws SQLException {
        CustomersPojo newCustomer= CustomersHelper.createOneCustomerWithJavaFaker();
        Assert.assertNotNull(newCustomer.getCustomerName());
        Assert.assertNotNull(newCustomer.getCustomerEmail());
        Assert.assertNotNull(newCustomer.getCustomerPhone());
    }

    @Test
    public void verifyCreatingListOfCustomersWithJavaFaker() throws Exception {
        List <CustomersPojo> listOfCustomers = CustomersHelper.createListOfCustomersWithJavaFaker();
       listOfCustomers.forEach(eachCustomer -> {
           Assert.assertNotNull(eachCustomer.getCustomerEmail());
           Assert.assertNotNull(eachCustomer.getCustomerName());
           Assert.assertNotNull(eachCustomer.getCustomerPhone());
       });
    }

}
