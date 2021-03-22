package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.InventoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {
}
