package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.ActorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository  extends CrudRepository<ActorEntity, Integer> {


}
