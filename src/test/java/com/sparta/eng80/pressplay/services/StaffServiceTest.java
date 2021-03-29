package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    @Order(Integer.MIN_VALUE)
    void contextLoads(){
        Assertions.assertNotNull(staffService);
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email exists")
    @CsvSource({
            "Mike.Hillyer@sakilastaff.com",
            "Jon.Stephens@sakilastaff.com"
    })
    public void emailFound(String email){
        Assertions.assertTrue(staffService.findByEmail(email).isPresent());
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email does NOT exist")
    @CsvSource({
            "Mike.Hillyer@gmail.com",
            "Jon.Stephens@gmail.com"
    })
    public void emailNotFound(String email){
        Assertions.assertTrue(staffService.findByEmail(email).isEmpty());
    }

    @ParameterizedTest(name = "check {0}")
    @DisplayName("check email matches")
    @CsvSource({
            "Mike.Hillyer@sakilastaff.com",
            "Jon.Stephens@sakilastaff.com"
    })
    public void emailMatch(String email){
        Assertions.assertEquals(staffService.findByEmail(email).get().getEmail(), email);
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check account is admin")
    @CsvSource({"1", "2"})
    public void isAdmin(int id){
        Assertions.assertTrue(staffService.isAdmin(id));
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check account is NOT admin")
    @CsvSource({"1111", "1112", "1113", "1114", "1115"})
    public void notAdmin(int id){
        Assertions.assertFalse(staffService.isAdmin(id));
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id is found")
    @CsvSource({"1", "2"})
    public void idFound(int id){
        Assertions.assertTrue(staffService.findById(id).isPresent());
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id is NOT found")
    @CsvSource({"1111", "1112", "1113", "1114", "1115"})
    public void idNotFound(int id){
        Assertions.assertTrue(staffService.findById(id).isEmpty());
    }

    @ParameterizedTest(name = "check id {0}")
    @DisplayName("check id matches")
    @CsvSource({"1", "2"})
    public void idMatch(int id){
        Assertions.assertEquals(staffService.findById(id).get().getStaffId(), id);
    }

    @Test
    @Disabled("disabled until delete method is implemented")
    @DisplayName("check customer is saved")
    public void saveWorks(){
        StaffEntity staff = new StaffEntity();
        staff.setStore(staffService.findById(1).get().getStore());
        staff.setFirstName("Steve");
        staff.setLastName("O");
        staff.setEmail("Steve.O@spartaglobalstaff.com");
        staff.setUsername("steve-o");
        staff.setPassword("password");
        staff.setAddress(staffService.findById(1).get().getAddress());
        staff.setLastUpdate(staffService.findById(1).get().getLastUpdate());

        int createdUserId = staffService.save(staff);

        Assertions.assertTrue(staffService.findById(createdUserId).isPresent());

    }
}
