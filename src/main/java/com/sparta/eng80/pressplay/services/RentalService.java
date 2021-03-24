package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.RentalEntity;
import com.sparta.eng80.pressplay.repositories.RentalRepository;
import com.sparta.eng80.pressplay.services.interfaces.RentalInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RentalService implements RentalInterface {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Iterable<RentalEntity> findByCustomerId(int id) {
        return rentalRepository.findRentalEntitiesByCustomer_CustomerId(id);
    }

    @Override
    public Iterable<RentalEntity> findOverdueRentalsByCustomerId(int id) {
        return rentalRepository.findMostRecentRentalsForCustomerGroupedByInventoryIdWhereReturnDateIsBefore(id, Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public Iterable<RentalEntity> findAllOverdueRentals() {
        return rentalRepository.findMostRecentRentalsGroupedByInventoryIdWhereReturnDateIsBefore(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public Optional<RentalEntity> findById(Integer id) {
        return rentalRepository.findById(id);
    }

    @Override
    public Iterable<RentalEntity> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public int save(RentalEntity rentalEntity) {
        return rentalRepository.save(rentalEntity).getRentalId();
    }
}
