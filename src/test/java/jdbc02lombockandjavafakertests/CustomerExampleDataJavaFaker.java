package jdbc02lombockandjavafakertests;

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
    }

    @Test
    public void verifyCreatingListOfCustomersWithJavaFaker() throws Exception {
        List <CustomerPojoWithLombock> listOfCustomers = CustomerHelperWithJavaFaker.createListOfCustomersWithJavaFaker();
       listOfCustomers.forEach(user -> {
           Assert.assertNotNull(user.getCustomerEmail());
           Assert.assertNotNull(user.getCustomerName());
           Assert.assertNotNull(user.getCustomerPhone());
       });
    }

}
