package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CategoryEntity;
import com.sparta.eng80.pressplay.services.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private FilmService filmService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    void loadContext() {
        Assertions.assertNotNull(categoryController);
    }

    @ParameterizedTest
    @CsvSource({"Action", "Drama", "Games"})
    void checkReturn(String expectedCategory) {
        System.out.println(expectedCategory);
        Assertions.assertTrue(this.testTemplate.getForObject("http://localhost:" + port + "/categories", String.class).contains(expectedCategory));
    }

    public List<String> getCategories() {
        List<String> categoryList = new ArrayList<>();
        Iterable<CategoryEntity> categories = filmService.findAllGenres();
        for (CategoryEntity category:categories) {
            categoryList.add(category.getName());
        }
        return categoryList;
    }
}
