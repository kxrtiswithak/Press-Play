package com.sparta.eng80.pressplay.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    @DisplayName("check valid logins are authenticated")
    @CsvSource({"Mike.Hillyer@sakilastaff.com, password",
            "MARY.SMITH@sakilacustomer.org, password"})
    void validUserLogin(String username, String password) {
        securityService.autoLogin(username, password);
        Assertions.assertTrue(securityService.isAuthenticated());
    }

    @ParameterizedTest
    @DisplayName("check invalid password throws BadCredentialException")
    @CsvSource({"Mike.Hillyer@sakilastaff.com, fake",
            "MARY.SMITH@sakilacustomer.org, invalid"})
    void invalidPasswordLogin(String username, String password) {
        Assertions.assertThrows(BadCredentialsException.class, () -> {
            securityService.autoLogin(username, password);
        });
    }

    @ParameterizedTest
    @DisplayName("check invalid username throws UsernameNotFoundException")
    @CsvSource({"Mike.Hillyer@gmail.com, password",
            "MARY.SMITH@gmail.com, password"})
    void invalidUsernameLogin(String username, String password) {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            securityService.autoLogin(username, password);
        });
    }
}
