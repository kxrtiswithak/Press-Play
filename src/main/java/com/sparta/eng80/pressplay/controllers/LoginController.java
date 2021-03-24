package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.services.CustomerService;
import com.sparta.eng80.pressplay.services.SecurityService;
import com.sparta.eng80.pressplay.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final CustomerService customerService;
    private final StaffService staffService;
    private final SecurityService securityService;

    @Autowired
    public LoginController(CustomerService customerService, StaffService staffService, SecurityService securityService) {
        this.customerService = customerService;
        this.staffService = staffService;
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login() {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }
}
