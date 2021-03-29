package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.LanguageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
}
