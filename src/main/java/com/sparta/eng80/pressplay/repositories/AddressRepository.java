package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}
