package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.repositories.StaffRepository;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService implements AccountInterface<StaffEntity> {

    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @Override
    public Optional<StaffEntity> findByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public boolean isAdmin(Integer id) {
        return false;
    }

    @Override
    public Optional<StaffEntity> findById(Integer id) {
        return staffRepository.findById(id);
    }

    @Override
    public Iterable<StaffEntity> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public int save(StaffEntity staff) {
        return staffRepository.save(staff).getStaffId();
    }

}
