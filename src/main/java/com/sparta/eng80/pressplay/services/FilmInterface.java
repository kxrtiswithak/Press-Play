package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;

import java.util.Optional;

public interface FilmInterface extends ServiceInterface<FilmEntity>{

    //find by name, category, actor, language, inventory
    Iterable<FilmEntity> findByName(String filmName);
    Iterable<FilmEntity> findByCategory(String category);
    Optional<FilmEntity> findByActorId(int actorID);
    Iterable<ActorEntity> findByActorName(String actor);
    Iterable<ActorEntity> findByActorName(String firstName, String lastName);
    Iterable<FilmEntity> findByLanguage(String language));

}
