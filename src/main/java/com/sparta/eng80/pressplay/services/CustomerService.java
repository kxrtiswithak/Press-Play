package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.repositories.CustomerRepository;

import java.util.Optional;

public class CustomerService implements CustomerInterface {

    private CustomerRepository customerRepository;

    @Override
    public Optional<CustomerEntity> findByUsername(String username) {
        return customerRepository.findByEmail(username);
    }

    @Override
    public boolean isAdmin(Integer id) {
        return false;
    }

    @Override
    public Optional<CustomerEntity> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Iterable<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }
}
