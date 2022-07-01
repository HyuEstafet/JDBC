package jdbc.tests;

import helpers.CustomerHelperWithJavaFaker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojos.CustomerPojoWithLombock;
import java.sql.SQLException;
import java.util.List;

public class CustomerExampleDataJavaFaker {

    @Test
    public void createOneCustomerWithJavaFaker() throws SQLException {
        CustomerPojoWithLombock newCustomer= CustomerHelperWithJavaFaker.createOneCustomerWithJavaFaker();
        Assert.assertNotNull(newCustomer.getCustomerName());
        Assert.assertNotNull(newCustomer.getCustomerEmail());
        Assert.assertNotNull(newCustomer.getCustomerPhone());
    }

    @Test
    public void verifyCreatingListOfCustomersWithJavaFaker() throws Exception {
        List <CustomerPojoWithLombock> listOfCustomers = CustomerHelperWithJavaFaker.createListOfCustomersWithJavaFaker();
       listOfCustomers.forEach(customer -> {
           Assert.assertNotNull(customer.getCustomerEmail());
           Assert.assertNotNull(customer.getCustomerName());
           Assert.assertNotNull(customer.getCustomerPhone());
       });
    }

}
