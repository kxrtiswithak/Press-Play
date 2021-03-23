package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.FilmEntity;

public interface FilmInterface extends ServiceInterface<FilmEntity>{

    //find by name, category, actor, language, inventory
    Iterable<FilmEntity> findByTitle(String title);
    Iterable<FilmEntity> findByCategory();
    Iterable<FilmEntity> findByActor(int actorID);
    Iterable<FilmEntity> findByLanguage();

}
