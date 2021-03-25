package com.sparta.eng80.pressplay.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActorServiceTest {

    @Autowired
    private ActorService actorService;

    @Test
    @Order(Integer.MIN_VALUE)
    void contextLoads() {
        Assertions.assertNotNull(actorService);
    }

    @Test
    @DisplayName("Check that find all returns data")
    void findAllActorsNotNull() {
        Assertions.assertNotNull(actorService.findAll());
    }
}
