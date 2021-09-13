package com.samkim.catchUp.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.samkim.catchUp.models.User;
import com.samkim.catchUp.repositories.UserRepository;



@Component
public class UserValidator {

	@Autowired
	private UserRepository uRepo;
	
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
	
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if(!user.getPassword().equals(user.getConfirmPassword())) {
        	errors.rejectValue("password", "Match", "Passwords do not match!");
        }
        
        if(this.uRepo.existsByEmail(user.getEmail())) {
        	errors.rejectValue("email", "Unique", "Email has been used");
        }
        
        if(user.getFirstName().equals("Sam")) {
        	errors.rejectValue("firstName", "noSamsAllowed", "Sorry, we're not accepting this name at this time");
        }
    }
}
