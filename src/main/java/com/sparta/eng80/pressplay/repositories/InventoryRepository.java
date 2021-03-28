package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {

    @Query(nativeQuery = true, value = "select count(*) from inventory i " +
            "where i.film_id = ?1 and i.is_rented = 0")
    int getInventoryCountByFilmId(int filmId);

    @Query(nativeQuery = true, value = "select * from inventory where film_id = ?1 and is_rented = 0 limit 1")
    Optional<InventoryEntity> getSingleInventoryWithFilmIdThatIsCurrentlyNotRented(int id);
}
