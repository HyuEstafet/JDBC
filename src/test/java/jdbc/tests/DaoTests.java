package jdbc.tests;

import daos.CustomersDao;
import helpers.CustomersHelper;
import pojos.CustomersPojo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class DaoTests {
    public static void main(String[] args) throws SQLException, IOException {
        CustomersDao customersDao = new CustomersDao();

        //to save a record
        CustomersPojo oneCustomerWithJavaFaker = CustomersHelper.createOneCustomerWithJavaFaker();
        customersDao.save(oneCustomerWithJavaFaker);
//
//        //to update a record
//        customersDao.update(1,"newwwemailnick@email.com");
//
//        //to delete a record
//        customersDao.delete(1);
//
//        //to delete all records
//        customersDao.deleteAll();
//
//        //to get a random customer id
//        customersDao.getRandomId();
//
//        //to get a few random customer ids
//        customersDao.getRandomIds(10);
//
//        //to get the count of all the records in the table
//        customersDao.getRecordsCount();
//
//        //to extract a single object from the database by id
//        customersDao.getById(1);

    }
}
