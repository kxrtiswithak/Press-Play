package com.sparta.eng80.pressplay.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @Autowired
    private ContactController contactController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    void loadContext() {
        Assertions.assertNotNull(contactController);
    }

    @ParameterizedTest
    @CsvSource({"Get in touch", "Contact us if you have any issues, details can be found below"})
    void checkReturn(String returnedText) {
        Assertions.assertTrue(this.testTemplate.getForObject("http://localhost:" + port + "/contact", String.class).contains(returnedText));
    }
}
