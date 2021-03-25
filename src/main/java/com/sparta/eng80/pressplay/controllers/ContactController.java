package com.sparta.eng80.pressplay.controllers;

import com.sparta.eng80.pressplay.entities.AddressEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    private final StaffService staffService;

    @Autowired
    public ContactController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/contact")
    public String showContactDetails(ModelMap modelMap){
        StaffEntity staffMember = staffService.findAll().iterator().next();
        modelMap.addAttribute("staff", staffMember);
        return "fragments/contact";
    }
}
