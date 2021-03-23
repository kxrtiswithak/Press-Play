package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.repositories.StaffRepository;
import com.sparta.eng80.pressplay.security.PasswordEncryptor;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService implements AccountInterface<StaffEntity> {

    private final StaffRepository staffRepository;
    private final PasswordEncryptor passwordEncryptor;

    public StaffService(StaffRepository staffRepository, PasswordEncryptor passwordEncryptor) {
        this.staffRepository = staffRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public Optional<StaffEntity> findByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public boolean isAdmin(Integer id) {
        return findById(id).isPresent();
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
    public int save(StaffEntity staffEntity) {
        staffEntity.setPassword(passwordEncryptor.encode(staffEntity.getPassword()));
        return staffRepository.save(staffEntity).getStaffId();
    }

}
