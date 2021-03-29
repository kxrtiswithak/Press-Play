package com.sparta.eng80.pressplay.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InventoryServiceTest {

    @Autowired
    InventoryService inventoryService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(inventoryService);
    }


    @ParameterizedTest
    @CsvSource({
            "10", "100", "35", "60"
    })
    public void getNumberInStockByFilmIdTest(int id){
        System.out.println(inventoryService.getNumberInStockByFilmId(id));
    }
}
