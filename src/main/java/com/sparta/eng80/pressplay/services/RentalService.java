package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.*;
import com.sparta.eng80.pressplay.repositories.*;
import com.sparta.eng80.pressplay.services.interfaces.RentalInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements RentalInterface {

    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    private final StaffRepository staffRepository;

    public RentalService(RentalRepository rentalRepository, FilmRepository filmRepository, CustomerRepository customerRepository, InventoryRepository inventoryRepository, StaffRepository staffRepository) {
        this.rentalRepository = rentalRepository;
        this.filmRepository = filmRepository;
        this.customerRepository = customerRepository;
        this.inventoryRepository = inventoryRepository;
        this.staffRepository = staffRepository;
    }

    public boolean rentFilm(FilmEntity film, CustomerEntity customer, StaffEntity staff) {
        Optional<InventoryEntity> inventoryOptional = inventoryRepository.getSingleInventoryWithFilmIdThatIsCurrentlyNotRented(film.getFilmId());
        if (inventoryOptional.isPresent()) {
            LocalDate now = LocalDate.now();
            InventoryEntity inventoryEntity = inventoryOptional.get();
            inventoryEntity.setLastUpdate(Date.valueOf(now));
            inventoryEntity.setRented(true);
            RentalEntity rentalEntity = new RentalEntity();
            rentalEntity.setInventory(inventoryEntity);
            rentalEntity.setCustomer(customer);
            rentalEntity.setStaff(staff);
            rentalEntity.setRentalDate(Date.valueOf(now));
            rentalEntity.setReturnDate(Date.valueOf(now.plusDays(film.getRentalDuration())));
            inventoryRepository.save(inventoryEntity);
            save(rentalEntity);
            return true;
        }
        return false;
    }

    public Iterable<RentalEntity> getCurrentRentals(int customerId) {
        return rentalRepository.findCurrentRentalsCustomerID(customerId);
    }

    @Override
    public Iterable<RentalEntity> findByCustomerId(int id) {
        return rentalRepository.findRentalEntitiesByCustomer_CustomerIdEqualsOrderByRentalDateDesc(id);
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
        rentalEntity.setLastUpdate(Date.valueOf(LocalDate.now()));
        return rentalRepository.save(rentalEntity).getRentalId();
    }
}
