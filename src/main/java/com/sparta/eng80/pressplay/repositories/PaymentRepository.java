package com.sparta.eng80.pressplay.repositories;

import com.sparta.eng80.pressplay.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
}
