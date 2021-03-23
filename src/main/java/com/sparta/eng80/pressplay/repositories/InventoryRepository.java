package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {

    @Query(nativeQuery = true, value = "select count(*) from inventory i " +
            "where i.film_id = ?1")
    int getInventoryCountByFilmId(int filmId);
}
