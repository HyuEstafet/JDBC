package jdbc.tests;

import daos.CustomersDao;
import helpers.CustomersHelper;
import pojos.CustomersPojo;

import java.io.IOException;
import java.sql.SQLException;


public class DaoTests {
    public static void main(String[] args) throws SQLException, IOException {
        CustomersDao customersDao = new CustomersDao();

//        //to save a record
//        CustomersPojo oneCustomerWithJavaFaker = CustomersHelper.createOneCustomerWithJavaFaker();
//        customersDao.save(oneCustomerWithJavaFaker);
//
//        //to update a record
//        customersDao.update(1,"updatedemailnick@email.com");
//
//        //to delete a record
//        customersDao.delete(16);
//
//        //to delete all records
//        customersDao.deleteAll();

        //to get a random customer id
        customersDao.getRandomId();

        //to get


    }
}
