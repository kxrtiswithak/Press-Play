package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.repositories.CustomerRepository;
import com.sparta.eng80.pressplay.security.PasswordEncryptor;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService implements AccountInterface<CustomerEntity> {

    private final CustomerRepository customerRepository;
    private final PasswordEncryptor passwordEncryptor;

    public CustomerService(CustomerRepository customerRepository, PasswordEncryptor passwordEncryptor) {
        this.customerRepository = customerRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

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
    public int save(CustomerEntity customerEntity) {
        customerEntity.setPassword(passwordEncryptor.encode(customerEntity.getPassword()));
        customerEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        return customerRepository.save(customerEntity).getCustomerId();
    }
}
