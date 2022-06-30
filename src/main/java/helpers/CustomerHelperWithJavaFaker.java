package helpers;

import com.github.javafaker.Faker;
import pojos.CustomerPojoWithLombock;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerHelperWithJavaFaker {

    public static CustomerPojoWithLombock createOneCustomerWithJavaFaker() {
        Faker faker = new Faker();
        CustomerPojoWithLombock newCustomer = CustomerPojoWithLombock.builder()
                .customerId(faker.number().numberBetween(10,100))
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

    public static List<CustomerPojoWithLombock> createListOfCustomersWithJavaFaker() {
        Faker faker = new Faker();
        ArrayList<CustomerPojoWithLombock> newCustomersList = new ArrayList<>();

        for (int i=0; i < 10; i++) {
            newCustomersList.add(CustomerPojoWithLombock.builder()
                    .customerId(faker.number().numberBetween(10,100))
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
