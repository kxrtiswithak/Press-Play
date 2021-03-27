package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.*;
import com.sparta.eng80.pressplay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ProfileController {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final StaffService staffService;
    private final RentalService rentalService;
    private final SecurityService securityService;

    @Autowired
    public ProfileController(CustomerService customerService, AddressService addressService, RentalService rentalService, StaffService staffService, SecurityService securityService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.rentalService = rentalService;
        this.staffService = staffService;
        this.securityService = securityService;
    }

    @GetMapping("/profile")
    public String mainProfile(Model model) {
        StaffEntity staff = getCurrentStaff();
        if (staff != null) {
            model.addAttribute("user", staff);
        } else {
            CustomerEntity customer = getCurrentCustomer();
            model.addAttribute("user", customer);

            Iterable<RentalEntity> rentalHistory = rentalService.findByCustomerId(customer.getCustomerId());
            Iterable<RentalEntity> overdueRentals = rentalService.findOverdueRentalsByCustomerId(customer.getCustomerId());
            Iterable<RentalEntity> currentRentals = rentalService.getCurrentlyRentedFilms(customer.getCustomerId());

            rentalHistory.forEach(rental -> {
                overdueRentals.forEach(overdue -> {
                    if (rental.equals(overdue)) {
                        rental.setStatus(RentalEntity.OVERDUE);
                    }
                });

                if (rental.getStatus() != RentalEntity.OVERDUE) {
                    currentRentals.forEach(current -> {
                        if (rental.equals(current)) {
                            rental.setStatus(RentalEntity.CURRENT);
                        }
                    });
                }
            });
            model.addAttribute("rentalHistory", rentalHistory);
        }
        return "fragments/profile";
    }

     @PostMapping("/profile")
     public String edit(@ModelAttribute("customer") CustomerEntity customer) {
        String currentEmail = getCurrentCustomer().getEmail();
        if (securityService.authToken(currentEmail, customer.getPassword()).isAuthenticated())  {
            Optional<CustomerEntity> currentDetailsOptional = customerService.findByEmail(currentEmail);
            if (currentDetailsOptional.isPresent()) {
                CustomerEntity currentDetails = currentDetailsOptional.get();

                String inputFirstName = customer.getFirstName();
                if (inputFirstName != null) {
                    currentDetails.setFirstName(inputFirstName);
                }

                String inputLastName = customer.getLastName();
                if (inputLastName != null) {
                    currentDetails.setLastName(inputLastName);
                }

                String inputEmail = customer.getEmail();
                if (inputEmail != null) {
                    currentDetails.setEmail(inputEmail);
                }

                AddressEntity inputAddress = customer.getAddress();
                if (inputAddress != null) {
                    Optional<AddressEntity> currentAddressOptional = addressService.findFullAddressByCustomerId(currentDetails.getCustomerId());
                    AddressEntity currentAddress;
                    if (currentAddressOptional.isPresent()) {
                        currentAddress = currentAddressOptional.get();

                        CityEntity inputCity = inputAddress.getCity();
                        CountryEntity inputCountry = inputCity.getCountry();

                        Optional<CityEntity> cityOptional = addressService.findCityByCityAndCountry(inputCity.getCity(), inputCountry.getCountry());
                        CityEntity city;
                        if (cityOptional.isPresent()) {
                            city = cityOptional.get();
                        } else {
                            city = new CityEntity();
                            city.setCity(inputCity.getCity());
                            Optional<CountryEntity> countryOptional = addressService.findCountryByName(inputCountry.getCountry());
                            CountryEntity country;
                            if (countryOptional.isPresent()) {
                                country = countryOptional.get();
                            } else {
                                country = new CountryEntity();
                                country.setCountry(inputCountry.getCountry());
                                addressService.saveCountry(country);
                            }
                            city.setCountry(country);
                            addressService.saveCity(city);
                        }

                        currentAddress.setAddress(inputAddress.getAddress());
                        currentAddress.setDistrict(inputAddress.getDistrict());
                        currentAddress.setPostalCode(inputAddress.getPostalCode());
                        currentAddress.setPhone(inputAddress.getPhone());
                        currentAddress.setCity(city);
                        addressService.saveAddress(currentAddress);
                        currentDetails.setAddress(currentAddress);
                    }
                }
                String inputPassword = customer.getPassword();
                currentDetails.setPassword(inputPassword);
                customerService.save(currentDetails);
                SecurityContextHolder.getContext().setAuthentication(securityService.authToken(inputEmail, inputPassword));
            }
        }
        return "redirect:/profile";
     }

     private StaffEntity getCurrentStaff() {
         UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         Optional<StaffEntity> staff = staffService.findByEmail(user.getUsername());
         return staff.orElse(null);
     }

     private CustomerEntity getCurrentCustomer() {
         UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         Optional<CustomerEntity> customer = customerService.findByEmail(user.getUsername());
         return customer.orElse(null);
     }
}
