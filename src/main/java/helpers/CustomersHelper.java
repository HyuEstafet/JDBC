package helpers;

import com.github.javafaker.Faker;
import pojos.CustomersPojo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomersHelper {

    public static CustomersPojo createOneCustomerWithJavaFaker() {
        Faker faker = new Faker();
        CustomersPojo newCustomer = CustomersPojo.builder()
//                .customerId(faker.number().numberBetween(10,100))--> we don't need this because the id is autoincremented (serial)
                .customerName(faker.name().fullName())
                .customerEmail(faker.internet().emailAddress())
                .customerPhone(faker.phoneNumber().cellPhone())
                .customerAge(faker.number().numberBetween(18,99))
                .gdprStatus(faker.random().nextBoolean())
                .customerProfileStatus(faker.random().nextBoolean())
                .dateProfileCreated(Date.valueOf(LocalDate.now()))
                .dateProfileDeactivated(Date.valueOf(LocalDate.now()))
                .deactivationReason("Deactivated due to inactivity")
                .customerNotes("No notes").build();
        System.out.println("New customer: " + newCustomer);
        return newCustomer;
    }

    public static List<CustomersPojo> createListOfCustomersWithJavaFaker() {
        Faker faker = new Faker();
        ArrayList<CustomersPojo> newCustomersList = new ArrayList<>();

        for (int i=0; i < 10; i++) {
            newCustomersList.add(CustomersPojo.builder()
//                    .customerId(faker.number().numberBetween(10,100)) --> we don't need this because the id is autoincremented (serial)
                    .customerName(faker.name().fullName())
                    .customerEmail(faker.internet().emailAddress())
                    .customerPhone(faker.phoneNumber().cellPhone())
                    .customerAge(faker.number().numberBetween(18,99))
                    .gdprStatus(faker.random().nextBoolean())
                    .customerProfileStatus(faker.random().nextBoolean())
                    .dateProfileCreated(Date.valueOf(LocalDate.now()))
                    .dateProfileDeactivated(Date.valueOf(LocalDate.now()))
                    .deactivationReason("Deactivated due to inactivity")
                    .customerNotes("No notes").build());
        }
        System.out.println("List of new customers: " + newCustomersList);
        return newCustomersList;
    }
}
