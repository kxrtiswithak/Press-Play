package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.FilmEntity;
import com.sparta.eng80.pressplay.entities.RentalEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
public class RentalServiceTest {

    @Autowired
    RentalService rentalService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads() { Assertions.assertNotNull(rentalService);}

    //Rent a film
    @Test
    @Disabled("Until rents can be deleted")
    public void rentAFilmTest(FilmEntity filmEntity, CustomerEntity customerEntity, java.util.Date returnDate, StaffEntity staffEntity){
        Assertions.assertTrue(rentalService.rentAFilm(filmEntity, customerEntity, returnDate, staffEntity));
    }

    //Get currently rented films
    @ParameterizedTest
    @CsvSource({
            "1", "431", "282", "368", "119"
    })
    public void getCurrentlyRentedFilmsTest(int id){
        Iterable<RentalEntity> test = rentalService.getCurrentlyRentedFilms(id);
        Assertions.assertTrue(test.iterator().hasNext());
    }

    //Find by customer id - PASSED
    @ParameterizedTest
    @CsvSource({
            "130", "459", "408", "333", "222"
    })
    public void customerIsFound(int id){
        Iterable<RentalEntity> test = rentalService.findByCustomerId(id);
        Assertions.assertTrue(test.iterator().hasNext());
    }

    @Test
    public void customerIsNotFound(){
        Iterable<RentalEntity> test = rentalService.findByCustomerId(140139824);
        Assertions.assertFalse(test.iterator().hasNext());
    }

    //Find overdue Rentals by customer Id - FAILED
    @ParameterizedTest
    @CsvSource({
            "10", "459", "408", "333", "222"
    })
    public void overdueRentalsByCustomerIsFound(int id){
        Iterable<RentalEntity> test = rentalService.findOverdueRentalsByCustomerId(id);
        Assertions.assertTrue(test.iterator().hasNext());
    }

    @Test
    public void overdueRentalsByCustomerIsNotFound(){
        Iterable<RentalEntity> test = rentalService.findOverdueRentalsByCustomerId(13312);
        Assertions.assertFalse(test.iterator().hasNext());
    }

    //Find all overdue rentals - FAILED
    @Test
    public void findAllOverdueRentalsTest(){
        Iterable<RentalEntity> test = rentalService.findAllOverdueRentals();
        Assertions.assertTrue(test.iterator().hasNext());
    }

    //find by id - PASSED
    @ParameterizedTest
    @CsvSource({
            "130", "459", "408", "333", "222"
    })
    public void findByIdIsFound(int id){
        Optional<RentalEntity> test = rentalService.findById(id);
        Assertions.assertTrue(test.isPresent());
    }

    @Test
    public void findByIdIsNotFound(){
        Optional<RentalEntity> test = rentalService.findById(17000);
        Assertions.assertFalse(test.isPresent());
    }

    //find all - PASSED
    @Test
    public void findAllTest(){
        Iterable<RentalEntity> test = rentalService.findAll();
        Assertions.assertTrue(test.iterator().hasNext());
    }

    //save - DISABLED
    @Test
    @Disabled("disabled until delete method is implemented")
    public void saveWorks(){
        RentalEntity rental = new RentalEntity();
        rental.setRentalDate(rentalService.findById(100).get().getRentalDate());
        rental.setReturnDate(rentalService.findById(100).get().getReturnDate());
        rental.setCustomer(rentalService.findById(100).get().getCustomer());
        rental.setInventory(rentalService.findById(100).get().getInventory());
        rental.setStaff(rentalService.findById(100).get().getStaff());
        rental.setLastUpdate(rentalService.findById(100).get().getLastUpdate());

        int createRentalId = rentalService.save(rental);

        Assertions.assertTrue(rentalService.findById(createRentalId).isPresent());
    }

}



