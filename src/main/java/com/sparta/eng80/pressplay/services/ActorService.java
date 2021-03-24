package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import com.sparta.eng80.pressplay.repositories.ActorRepository;
import com.sparta.eng80.pressplay.services.interfaces.ActorInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService implements ActorInterface {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Iterable<ActorEntity> getAllActorsAlphabetically() {
        return actorRepository.getAllActorsAlphabetically();
    }

    @Override
    public Optional<ActorEntity> findById(Integer id) {
        return actorRepository.findById(id);
    }

    @Override
    public Iterable<ActorEntity> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public int save(ActorEntity actorEntity) {
        return actorRepository.save(actorEntity).getActorId();
    }
}
