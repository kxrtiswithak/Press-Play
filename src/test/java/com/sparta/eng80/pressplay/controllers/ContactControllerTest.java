package com.sparta.eng80.pressplay.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactControllerTest {

    @Autowired
    private ContactController contactController;
    
    @Test
    void loadContext() {
        Assertions.assertNotNull(contactController);
    }
}
