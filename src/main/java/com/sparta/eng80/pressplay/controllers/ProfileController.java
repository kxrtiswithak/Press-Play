package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.RentalEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Controller
public class ProfileController {

    private final CustomerService customerService;
    private final StaffService staffService;
    private final RentalService rentalService;
    private final ActorService actorService;
    private final FilmService filmService;

    @Autowired
    public ProfileController(CustomerService customerService, RentalService rentalService, StaffService staffService, ActorService actorService, FilmService filmService) {
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.staffService = staffService;
        this.actorService = actorService;
        this.filmService = filmService;
    }

    @GetMapping("/profile")
    public String mainProfile(ModelMap model) {
        model = Filters.getFilters(model, filmService, actorService);

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

     @GetMapping("/profile/edit")
     public String edit(Model model) {
        model.addAttribute("customer", getCurrentCustomer());
        return "fragments/edit";
     }

     @PutMapping("profile/edit")
     public String edit(@ModelAttribute("customer") CustomerEntity customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "fragments/edit";
        }
        customerService.save(customer);
        return "fragments/profile";
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
