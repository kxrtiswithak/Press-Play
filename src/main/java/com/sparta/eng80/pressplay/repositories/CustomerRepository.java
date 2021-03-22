package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
}
