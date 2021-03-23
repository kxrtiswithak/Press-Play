package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.FilmCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmCategoryRepository extends CrudRepository<FilmCategoryEntity, Integer> {
}
