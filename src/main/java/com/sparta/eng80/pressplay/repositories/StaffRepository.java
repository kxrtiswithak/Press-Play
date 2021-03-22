package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {
}
