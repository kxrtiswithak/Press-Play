package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.repositories.AddressRepository;
import com.sparta.eng80.pressplay.repositories.CustomerRepository;
import com.sparta.eng80.pressplay.security.PasswordEncryptor;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService implements AccountInterface<CustomerEntity> {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncryptor passwordEncryptor;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, PasswordEncryptor passwordEncryptor) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public Optional<CustomerEntity> findByEmail(String username) {
        return customerRepository.findCustomerEntityByEmailEquals(username);
    }

    @Override
    public boolean isAdmin(Integer id) {
        return false;
    }

    @Override
    public ArrayList<String> getDetails(CustomerEntity customerEntity) {
        ArrayList<String> customerDetails = new ArrayList<>();
        customerDetails.add(String.valueOf(customerEntity.getCustomerId()));
        customerDetails.add(customerEntity.getFirstName());
        customerDetails.add(customerEntity.getLastName());
        customerDetails.add(customerEntity.getEmail());
        customerDetails.add(customerEntity.getAddress().getAddress());
        return customerDetails;
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
        Date now = Date.valueOf(LocalDate.now());
        customerEntity.setCreateDate(now);
        customerEntity.setLastUpdate(now);
        return customerRepository.save(customerEntity).getCustomerId();
    }
}
