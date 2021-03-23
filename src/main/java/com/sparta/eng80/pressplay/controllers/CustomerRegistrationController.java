package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.security.CustomerRegistrationValidator;
import com.sparta.eng80.pressplay.services.AccountInterface;
import com.sparta.eng80.pressplay.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerRegistrationController {

    private final AccountInterface<CustomerEntity> customerService;

    private final SecurityService securityService;

    private final CustomerRegistrationValidator customerRegistrationValidator;

    @Autowired
    public CustomerRegistrationController(AccountInterface<CustomerEntity> customerService, SecurityService securityService, CustomerRegistrationValidator customerRegistrationValidator) {
        this.customerService = customerService;
        this.securityService = securityService;
        this.customerRegistrationValidator = customerRegistrationValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("customerForm", new CustomerEntity());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("customerForm") CustomerEntity customer, BindingResult bindingResult) {
        customerRegistrationValidator.validate(customer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registrtaion";
        }

        customerService.save(customer);
        securityService.autoLogin(customer.getEmail(), customer.getPassword());
        return "redirect:/welcome";
    }
}
