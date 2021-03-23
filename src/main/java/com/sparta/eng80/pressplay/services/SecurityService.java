package com.sparta.eng80.pressplay.services;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SecurityService {

    private final AuthenticationManager authenticationManager;

    private final AccountInterface<CustomerEntity> customerService;

    private final AccountInterface<StaffEntity> staffService;

    public SecurityService(AuthenticationManager authenticationManager, AccountInterface<CustomerEntity> customerService, AccountInterface<StaffEntity> staffService) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.staffService = staffService;
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public void autoLogin(String email, String password) {
        Optional<CustomerEntity> customerOptional = customerService.findByUsername(email);

        if (!customerOptional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        CustomerEntity customer = customerOptional.get();

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails userDetails = new User(customer.getEmail(), customer.getPassword(), grantedAuthoritySet);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
