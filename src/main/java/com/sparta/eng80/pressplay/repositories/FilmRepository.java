package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<FilmEntity, Integer> {
}
