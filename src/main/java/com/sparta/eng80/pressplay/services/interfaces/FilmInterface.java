package com.sparta.eng80.pressplay.services.interfaces;

import com.sparta.eng80.pressplay.entities.FilmEntity;

import java.util.Optional;

public interface FilmInterface extends ServiceInterface<FilmEntity>{

    //find by name, category, actor, language, inventory
    Iterable<FilmEntity> findByTitle(String filmName);
    Iterable<FilmEntity> findByCategory(int categoryId);
    Iterable<FilmEntity> findByCategory(String category);
    Optional<FilmEntity> findActorById(int actorID);
    Iterable<FilmEntity> findActorByName(String actor);
    Iterable<FilmEntity> findActorByName(String firstName, String lastName);
    Iterable<FilmEntity> findByLanguage(String language);

}
