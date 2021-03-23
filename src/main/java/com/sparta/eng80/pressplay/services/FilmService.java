package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.repositories.ActorRepository;
import com.sparta.eng80.pressplay.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FilmService implements FilmInterface {

    private FilmRepository filmRepository;
    private ActorRepository actorRepository;

    public FilmService (FilmRepository filmRepository, ActorRepository actorRepository){
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public Iterable<FilmEntity> findByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    @Override
    public Iterable<FilmEntity> findByCategory() {
        return null;
    }

    @Override
    public Iterable<FilmEntity> findByActor(int actorID) {
        return filmRepository.findByActor(actorID);
    }

    public Iterable<ActorEntity> findActorByName(String actor) {
        actor = "%" + actor + "%";
        return actorRepository.findByName(actor);
    }

    public Iterable<ActorEntity> findActorByName(String firstName, String lastName) {
        firstName = "%" + firstName + "%";
        lastName = "%" + lastName + "%";
        return actorRepository.findByName(firstName, lastName);
    }

    @Override
    public Iterable<FilmEntity> findByLanguage() {
        return null;
    }

    @Override
    public Optional<FilmEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<FilmEntity> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public void save(FilmEntity filmEntity) {

    }
}
