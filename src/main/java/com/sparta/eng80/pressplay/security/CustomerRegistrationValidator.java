package com.sparta.eng80.pressplay.security;

import com.sparta.eng80.pressplay.entities.CustomerEntity;
import com.sparta.eng80.pressplay.entities.StaffEntity;
import com.sparta.eng80.pressplay.services.interfaces.AccountInterface;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerRegistrationValidator implements Validator {

    private final AccountInterface<CustomerEntity> customerService;
    private final AccountInterface<StaffEntity> staffService;

    public CustomerRegistrationValidator(AccountInterface<CustomerEntity> customerService, AccountInterface<StaffEntity> staffService) {
        this.customerService = customerService;
        this.staffService = staffService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerEntity customer = (CustomerEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (customer.getEmail().length() < 6 || customer.getEmail().length() > 64) {
            errors.rejectValue("email", "Size.customerFrom.email");
        }
        if (customerService.findByEmail(customer.getEmail()).isPresent()||
            staffService.findByEmail(customer.getEmail()).isPresent()) {
            errors.rejectValue("email", "Duplicate.customerFrom.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (customer.getPassword().length() < 8 || customer.getPassword().length() > 64) {
            errors.rejectValue("password", "Size.customerFrom.password");
        }

        if (!customer.getPasswordConfirmation().equals(customer.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Diff.customerFrom.passwordConfirmation");
        }
    }
}
