package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.RentalEntity;
import com.sparta.eng80.pressplay.services.CustomerService;
import com.sparta.eng80.pressplay.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Controller
public class ProfileController {

    private final CustomerService customerService;
    private final RentalService rentalService;

    @Autowired
    public ProfileController(CustomerService customerService, RentalService rentalService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
    }

    @GetMapping("/profile")
    public String mainProfile(Model model) {
        model.addAttribute("customer", getCurrentCustomer());
        return "profile";
    }

    @GetMapping("/profile/rental-history")
    public String rentalHistory(Model model) {
        CustomerEntity customer = getCurrentCustomer();
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
        return "rental-history";
     }

     @GetMapping("/profile/edit")
     public String edit(Model model) {
        model.addAttribute("customer", getCurrentCustomer());
        return "edit";
     }

     @PutMapping("profile/edit")
     public String edit(@ModelAttribute("customer") CustomerEntity customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        customerService.save(customer);
        return "profile";
     }

     private CustomerEntity getCurrentCustomer() {
         UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
         Optional<CustomerEntity> customer = customerService.findByEmail(user.getUsername());
         return customer.orElse(null);
     }
}
