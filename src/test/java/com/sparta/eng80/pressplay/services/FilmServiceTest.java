package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(filmService);
    }

    @Test
    public void testFindByName(){
        Iterable<FilmEntity> test = filmService.findByTitle("MARRIED GO");
        Assertions.assertEquals(test.iterator().next().getFilmId(), 559);
    }

    @Test
    public void testFindByCategory(){
        Iterable<FilmEntity> test = filmService.findByCategory("BILL OTHERS");
        Assertions.assertEquals(test.iterator().next().getFilmId(), 6);
    }

    @Test
    public void testFindByActor(){
        Iterable<FilmEntity> test = filmService.findActorByName("SILVERADO GOLDFINGER");
        Assertions.assertEquals(test.iterator().next().getFilmId(), 21);
    }

    @Test
    public void testFindByLanguage(){
        Iterable<FilmEntity> test = filmService.findByLanguage("English");
        Assertions.assertEquals(test.iterator().next().getFilmId(), 1);
    }
}
