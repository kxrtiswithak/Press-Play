package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer> {
}
