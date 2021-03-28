package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.entities.UserEntity;
import com.sparta.eng80.pressplay.repositories.CustomerRepository;
import com.sparta.eng80.pressplay.repositories.StaffRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginCredentialService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public LoginCredentialService(CustomerRepository customerRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    public UserEntity getCurrentUser(String email) {
        UserEntity user;
        Optional<CustomerEntity> customerOptional = customerRepository.findCustomerEntityByEmailEquals(email);
        if (customerOptional.isEmpty()) {
            Optional<StaffEntity> staffOptional = staffRepository.findStaffEntityByEmailEquals(email);
            if (staffOptional.isEmpty()) {
                throw new UsernameNotFoundException(email);
            }
            return staffOptional.get();
        }
        return customerOptional.get();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        UserEntity user = getCurrentUser(email);
        grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getEmail(), user.getPassword(), grantedAuthoritySet);
    }
}
