package com.sparta.eng80.pressplay.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    @Order(Integer.MIN_VALUE)
    void contextLoads() {
        Assertions.assertNotNull(securityService);
    }

    @ParameterizedTest
    @DisplayName("check staff login works")
    @CsvSource({"Mike.Hillyer@sakilastaff.com, password",
            "MARY.SMITH@sakilacustomer.org, password"})
    void validUserLogin(String username, String password) {
        securityService.autoLogin(username, password);
        Assertions.assertTrue(securityService.isAuthenticated());
    }
}
