package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
}
