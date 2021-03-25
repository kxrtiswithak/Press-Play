package com.sparta.eng80.pressplay.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class LoginCredentialsServiceTest {

    @Autowired
    LoginCredentialService loginCredentialService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(loginCredentialService);
    }

    @ParameterizedTest
    @CsvSource({
            "LINDA.WILLIAMS@sakilacustomer.org","BARBARA.JONES@sakilacustomer.org", "SANDRA.MARTIN@sakilacustomer.org"
    })
    public void loadByUsernameTest(String email){
        UserDetails user = loginCredentialService.loadUserByUsername(email);
        Assertions.assertNotNull(user); }
}
