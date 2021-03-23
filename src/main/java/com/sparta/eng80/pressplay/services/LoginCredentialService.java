package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
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

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        Optional<CustomerEntity> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isEmpty()) {
            Optional<StaffEntity> staffOptional = staffRepository.findByEmail(email);
            if (staffOptional.isEmpty()) {
                throw new UsernameNotFoundException(email);
            }
            StaffEntity staff = staffOptional.get();
            grantedAuthoritySet.add(new SimpleGrantedAuthority(staff.getRole()));
            return new User(staff.getEmail(), staff.getPassword(), grantedAuthoritySet);
        }
        CustomerEntity customer = customerOptional.get();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPassword(), grantedAuthoritySet);
    }
}
