package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.FilmEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<FilmEntity, Integer> {

    @Query(value = "select * from film where title = ?", nativeQuery = true)
    Iterable<FilmEntity> findByTitle(String title);

    @Query(value = "select * from film f" +
            "inner join film_actor a on a.film_id = f.film_id where actor_id = ?", nativeQuery = true)
    Iterable<FilmEntity> findByActor(int actorID);


}
