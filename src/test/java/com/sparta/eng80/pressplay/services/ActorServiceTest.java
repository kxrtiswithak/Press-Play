package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

    @ParameterizedTest
    @DisplayName("Check that find all includes correct actors")
    @CsvSource({"1, PENELOPE", "10, CHRISTIAN", "100, SPENCER"})
    void findAllIncludesActors(int actorId, String actorName) {
        Iterable<ActorEntity> allActors = actorService.findAll();
        for (ActorEntity actor:allActors) {
            if(actor.getActorId() == actorId) {
                Assertions.assertEquals(actorName, actor.getFirstName());
            }
        }
    }

    @ParameterizedTest
    @DisplayName("Check that findAll does not return fake actors")
    @CsvSource({"1000", "201", "333"})
    void findAllActorsNotFound(int actorId) {
        Iterable<ActorEntity> allActors = actorService.findAll();
        for (ActorEntity actor:allActors) {
            Assertions.assertNotEquals(actorId, actor.getActorId());
        }
    }

    @ParameterizedTest
    @DisplayName("Check that findById returns correct actor")
    @CsvSource({"2, NICK", "20, LUCILLE", "200, THORA"})
    void findActorById(int id, String actorName) {
        ActorEntity actor = actorService.findById(id).get();
        Assertions.assertEquals(actorName, actor.getFirstName());
    }

    @ParameterizedTest
    @DisplayName("Check that findById returns empty for invalid actorId")
    @CsvSource({"201", "202", "2003"})
    void findActorByIdNotFound(int id) {
        Optional<ActorEntity> actor = actorService.findById(id);
        Assertions.assertTrue(actor.isEmpty());
    }

    @Test
    @DisplayName("Check getAllActorsAlphabetically returns actors in alphabetical order")
    void getActorsAlphabetically() {
        Iterable<ActorEntity> actors = actorService.getAllActorsAlphabetically();
        char previousLastNameFirstLetter = 'A';
        char previousFirstNameFirstLetter = 'A';
        for (ActorEntity actor:actors) {
            char actorFirstNameFirstLetter = actor.getFirstName().toUpperCase().charAt(0);
            char actorLastNameFirstLetter = actor.getFirstName().toUpperCase().charAt(0);
            Assertions.assertTrue(previousFirstNameFirstLetter <= actorFirstNameFirstLetter);
            if (previousFirstNameFirstLetter == actorFirstNameFirstLetter) {
                Assertions.assertTrue(previousLastNameFirstLetter <= actorLastNameFirstLetter);
            } else {
                previousLastNameFirstLetter = 'A';
            }
            previousFirstNameFirstLetter = actorFirstNameFirstLetter;
        }
    }

    @Test
    @Disabled("disabled until delete method is implemented")
    @DisplayName("check actor is saved")
    public void saveWorks(){
        ActorEntity actor = new ActorEntity();
        actor.setActorId(201);
        actor.setFirstName("Will");
        actor.setLastName("Temple");
        actor.setFilms(actorService.findById(100).get().getFilms());
        int createdActorId = actorService.save(actor);
        Assertions.assertTrue(actorService.findById(createdActorId).isPresent());

    }
}
