package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.repositories.CustomerRepository;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements AccountInterface<CustomerEntity> {

    private CustomerRepository customerRepository;

    @Override
    public Optional<CustomerEntity> findByEmail(String username) {
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
