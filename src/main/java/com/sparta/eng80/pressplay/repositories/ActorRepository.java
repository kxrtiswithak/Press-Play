package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository  extends CrudRepository<ActorEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from actor where first_name like ?0 or last_name like ?0")
    Iterable<ActorEntity> findByName(String name);

    @Query(nativeQuery = true, value = "select * from actor where first_name like ?1 and last_name like ?2")
    Iterable<ActorEntity> findByName(String firstName, String lastName);
}
