package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.entities.LanguageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilmServiceTest {

    @Autowired
    private FilmService filmService;

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "MARRIED GO",
            "ALTER VICTORY",
            "MINE TITANS",
            "TOOTSIE PILOT",
            "ZORRO ARK"
    })
    public void nameIsFound(String title){
        Iterable<FilmEntity> test = filmService.findByTitle(title);
        Assertions.assertFalse(test.iterator().next().getTitle().isEmpty());
    }

    @Test
    public void nameIsNotFound(){
        Iterable<FilmEntity> test = filmService.findByTitle("dghfshdgfshgdfjhsd");
        Assertions.assertTrue(test.iterator().next().getTitle().isEmpty());
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "MARRIED GO",
            "ALTER VICTORY",
            "MINE TITANS",
            "TOOTSIE PILOT",
            "ZORRO ARK"
    })
    public void nameMatch(String title){
        Iterable<FilmEntity> test = filmService.findByTitle(title);
        Assertions.assertEquals(test.iterator().next().getTitle(), title);
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "Action",
            "Children",
            "Foreign",
            "New",
            "Travel"
    })
    public void categoryIsFound(String category){
        Iterable<FilmEntity> test = filmService.findByCategory(category);
        Assertions.assertFalse(test.iterator().next().getCategories().isEmpty());
    }

    @Test
    public void categoryIsNotFound(){
        Iterable<FilmEntity> test = filmService.findByCategory("dghfshdgfshgdfjhsd");
        Assertions.assertTrue(test.iterator().next().getCategories().isEmpty());
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "Action",
            "Children",
            "Foreign",
            "New",
            "Travel"
    })
    public void categoryMatch(String category){
        Iterable<FilmEntity> test = filmService.findByCategory(category);
        Assertions.assertEquals(test.iterator().next().getCategories(), category);
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "SILVERADO",
            "SISSY",
            "AUDREY",
            "ELVIS",
            "VAL"
    })
    public void actorIsFound(String name){
        Iterable<FilmEntity> test = filmService.findActorByName(name);
        Assertions.assertTrue(test.iterator().next().getActors().contains(name));
    }

    @Test
    public void actorIsNotFound(){
        Iterable<FilmEntity> test = filmService.findActorByName("dghfshdgfshgdfjhsd");
        Assertions.assertFalse(test.iterator().next().getActors().contains("dghfshdgfshgdfjhsd"));
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "English",
            "Italian",
            "Mandarin",
            "German",
            "French"
    })
    public void languageIsFound(LanguageEntity language){
        Iterable<FilmEntity> test = filmService.findByLanguage(language.getName());
        Assertions.assertTrue(test.iterator().next().getLanguage().getLanguageId() > 0);
    }

    @Test
    public void languageIsNotFound(){
        Iterable<FilmEntity> test = filmService.findByLanguage("dghfshdgfshgdfjhsd");
        Assertions.assertTrue(test.iterator().next().getLanguage().getLanguageId() <= 0);
    }

    @ParameterizedTest(name = "check {0}")
    @CsvSource({
            "English",
            "Italian",
            "Mandarin",
            "German",
            "French"
    })
    public void languageMatch(LanguageEntity language){
        Iterable<FilmEntity> test = filmService.findByLanguage(language.getName());
        Assertions.assertEquals(test.iterator().next().getLanguage().getLanguageId(), language.getLanguageId());
    }
}
