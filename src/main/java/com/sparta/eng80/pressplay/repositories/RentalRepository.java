package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.RentalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Integer> {

    Iterable<RentalEntity> findRentalEntitiesByCustomer_CustomerId(int id);
}
