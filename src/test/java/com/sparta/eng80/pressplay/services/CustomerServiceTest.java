package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(customerService);
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email exists")
    @CsvSource({
            "MARY.SMITH@sakilacustomer.org",
            "PATRICIA.JOHNSON@sakilacustomer.org",
            "LINDA.WILLIAMS@sakilacustomer.org",
            "BARBARA.JONES@sakilacustomer.org",
            "ELIZABETH.BROWN@sakilacustomer.org"
    })
    public void emailFound(String email){
        Optional<CustomerEntity> test = customerService.findByEmail(email);
        Assertions.assertTrue(test.isPresent());
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email does NOT exist")
    @CsvSource({
            "MARY.SMITH@gmail.org",
            "PATRICIA.JOHNSON@gmail.org",
            "LINDA.WILLIAMS@gmail.org",
            "BARBARA.JONES@gmail.org",
            "ELIZABETH.BROWN@gmail.org"
    })
    public void emailNotFound(String email){
        Optional<CustomerEntity> test = customerService.findByEmail(email);
        Assertions.assertTrue(test.isEmpty());
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email matches")
    @CsvSource({
            "MARY.SMITH@sakilacustomer.org",
            "PATRICIA.JOHNSON@sakilacustomer.org",
            "LINDA.WILLIAMS@sakilacustomer.org",
            "BARBARA.JONES@sakilacustomer.org",
            "ELIZABETH.BROWN@sakilacustomer.org"
    })
    public void emailMatch(String email){
        Optional<CustomerEntity> test = customerService.findByEmail(email);
        Assertions.assertEquals(test.get().getEmail(), email);
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check account is not admin")
    @CsvSource({"11", "12", "13", "14", "15"})
    public void notAdmin(int id){
        Assertions.assertFalse(customerService.isAdmin(id));
    }


    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id is found")
    @CsvSource({"11", "12", "13", "14", "15"})
    public void idFound(int id){
        Assertions.assertTrue(customerService.findById(id).isPresent());
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id is NOT found")
    @CsvSource({"1111", "1112", "1113", "1114", "1115"})
    public void idNotFound(int id){
        Assertions.assertTrue(customerService.findById(id).isEmpty());
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id matches")
    @CsvSource({"11", "12", "13", "14", "15"})
    public void idMatch(int id){
        Optional<CustomerEntity> test = customerService.findById(id);
        Assertions.assertEquals(test.get().getCustomerId(), id);
    }

    @Test
    @Disabled("disabled until delete method is implemented")
    @DisplayName("check customer is saved")
    public void saveWorks(){
        CustomerEntity customer = new CustomerEntity();
        customer.setStore(customerService.findById(77).get().getStore());
        customer.setFirstName("RICARDO");
        customer.setLastName("GURNIOLA");
        customer.setEmail("RICARDO.GURNIOLA@spartaglobal.com");
        customer.setAddress(customerService.findById(77).get().getAddress());
        customer.setCreateDate(customerService.findById(77).get().getCreateDate());
        customer.setLastUpdate(customerService.findById(77).get().getLastUpdate());

        int createdUserId = customerService.save(customer);

        Assertions.assertTrue(customerService.findById(createdUserId).isPresent());

    }

}
