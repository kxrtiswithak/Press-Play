package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerServiceTest {

    // @Autowired
    // private CustomerService customerService;

    @ParameterizedTest(name = "check {0} exists")
    @CsvSource({
            "MARY.SMITH@sakilacustomer.org",
            "PATRICIA.JOHNSON@sakilacustomer.org",
            "LINDA.WILLIAMS@sakilacustomer.org",
            "BARBARA.JONES@sakilacustomer.org",
            "ELIZABETH.BROWN@sakilacustomer.org"
    })
    public void testFindByEmailExists(String email){
        Optional<CustomerEntity> test = null;
        // test = customerService.findByEmail(email);
        Assertions.assertTrue(test.isPresent());
    }

    @ParameterizedTest(name = "check {0} does NOT exist")
    @CsvSource({
            "MARY.SMITH@gmail.org",
            "PATRICIA.JOHNSON@gmail.org",
            "LINDA.WILLIAMS@gmail.org",
            "BARBARA.JONES@gmail.org",
            "ELIZABETH.BROWN@gmail.org"
    })
    public void testFindByEmailNotExists(String email){
        Optional<CustomerEntity> test = null;
        // test = customerService.findByEmail(email);
        Assertions.assertTrue(test.isEmpty());
    }

    @ParameterizedTest(name = "check {0} matches")
    @CsvSource({
            "MARY.SMITH@sakilacustomer.org",
            "PATRICIA.JOHNSON@sakilacustomer.org",
            "LINDA.WILLIAMS@sakilacustomer.org",
            "BARBARA.JONES@sakilacustomer.org",
            "ELIZABETH.BROWN@sakilacustomer.org"
    })
    public void testFindByEmailMatches(String email){
        Optional<CustomerEntity> test = null;
        // test = customerService.findByEmail(email);
        Assertions.assertEquals(test.get().getEmail(), email);
    }

}
