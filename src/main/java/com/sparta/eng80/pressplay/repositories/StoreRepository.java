package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.StoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
}
