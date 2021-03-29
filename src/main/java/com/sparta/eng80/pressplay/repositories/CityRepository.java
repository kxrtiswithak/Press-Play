package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Integer> {

    Optional<CityEntity> findCityEntitiesByCityEqualsAndCountryCountryEquals(String city, String country);
}
