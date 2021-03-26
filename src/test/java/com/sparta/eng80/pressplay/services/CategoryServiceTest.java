package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(categoryService);
    }

    @ParameterizedTest
    @CsvSource({
            "Action", "Comedy"
    })
    public void findCategoryTest(String category){
        CategoryEntity categoryEntity = categoryService.findCategory(category);
        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(categoryEntity.getName(), category);
    }

    @ParameterizedTest
    @CsvSource({
            "CategoryTest1", "CategoryTest2"
    })
    public void addAndDeleteNewCategoryTest(String category){
        categoryService.addCategory(category);
        CategoryEntity categoryEntity = categoryService.findCategory(category);
        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(categoryEntity.getName(), category);

        categoryService.removeCategory(category);
        Assertions.assertNull(categoryService.findCategory(category));
    }
    

}
