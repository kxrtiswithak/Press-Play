package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.VisitorEntity;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitorService implements AccountInterface<VisitorEntity> {

    @Override
    public Optional<VisitorEntity> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean isAdmin(Integer id) {
        return false;
    }

    @Override
    public ArrayList<String> getDetails(VisitorEntity visitorEntity) {
        return null;
    }

    @Override
    public Optional<VisitorEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<VisitorEntity> findAll() {
        return null;
    }

    @Override
    public int save(VisitorEntity visitorEntity) {
        return 0;
    }
}
