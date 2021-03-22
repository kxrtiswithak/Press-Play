package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.FilmEntity;

public interface FilmInterface extends ServiceInterface<FilmEntity>{

    //find by name, category, actor, language, inventory
    Iterable<FilmEntity> findByName();
    Iterable<FilmEntity> findByCategory();
    Iterable<FilmEntity> findByActor();
    Iterable<FilmEntity> findByLanguage();

}
