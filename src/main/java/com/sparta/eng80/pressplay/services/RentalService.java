package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.*;
import com.sparta.eng80.pressplay.repositories.*;
import com.sparta.eng80.pressplay.services.interfaces.RentalInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    private void refreshInventoryStatus() {
        Iterable<RentalEntity> rentalBacklog = rentalRepository.findAll();

        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());

        for (RentalEntity rentalEntity : rentalBacklog) {
            if (rentalEntity.getInventory().getLastUpdate().before(rentalEntity.getLastUpdate())) {
                if (rentalEntity.getReturnDate().before(currentTime)) {
                    InventoryEntity inventoryEntity = rentalEntity.getInventory();
                    inventoryEntity.setLastUpdate(currentTime);
                    inventoryEntity.setRented(false);
                    rentalEntity.setInventory(inventoryEntity);
                } else if (rentalEntity.getReturnDate().after(currentTime)) {
                    InventoryEntity inventoryEntity = rentalEntity.getInventory();
                    inventoryEntity.setLastUpdate(currentTime);
                    inventoryEntity.setRented(true);
                    rentalEntity.setInventory(inventoryEntity);
                }
            }
        }
    }

    public boolean rentAFilm(FilmEntity filmEntity, CustomerEntity customerEntity, java.util.Date returnDate, StaffEntity staffEntity) {
        refreshInventoryStatus();

        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());

        if (returnDate.before(currentTime)) {
            return false;
        }

        //will return true, if film is in stock, and rentable.
        Iterable<InventoryEntity> inventoryEntities = inventoryRepository.findAll();
        for (InventoryEntity inventoryEntity : inventoryEntities) {
            // Inventory TABLE MUST BE UPDATED TO HAVE A BOOLEAN FOR IF A FILM
            // HAS BEEN RETURNED!
            if (inventoryEntity.getFilm() == filmEntity && !inventoryEntity.isRented()) {
                RentalEntity rentalEntity = new RentalEntity();
                rentalEntity.setRentalDate(new java.sql.Date(date.getTime()));
                rentalEntity.setInventory(inventoryEntity);
                rentalEntity.setCustomer(customerEntity);
                rentalEntity.setReturnDate(new java.sql.Date(returnDate.getTime()));
                rentalEntity.setStaff(staffEntity);
                rentalEntity.setLastUpdate(currentTime);
                break;
            }
        }
        return true;
    }

    public Iterable<RentalEntity> getCurrentlyRentedFilms(int customerId) {
        List<RentalEntity> currentRentals = new ArrayList<>();
        Iterable<RentalEntity> allRentals = rentalRepository.findRentedRentalEntityByCustomerID(customerId);
        for (RentalEntity rental : allRentals) {
            RentalEntity latestRental = rentalRepository.findLatestRentalEntityByInventoryID(rental.getInventory().getInventoryId());
            if (latestRental.getCustomer().getCustomerId() == customerId) {
                currentRentals.add(rental);
            }
        }
        return currentRentals;
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
