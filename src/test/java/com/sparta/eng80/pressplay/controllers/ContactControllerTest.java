package com.sparta.eng80.pressplay.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
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

    @Test
    void checkReturn() {
        Assertions.assertTrue(this.testTemplate.getForObject("http://localhost:" + port + "/contact", String.class).contains("Get in touch!"));
    }
}
