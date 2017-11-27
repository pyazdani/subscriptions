package com.project.belt.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.belt.models.User;



@Component
public class UserValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_RE=  "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

	public UserValidator(){
		pattern=Pattern.compile(EMAIL_RE);
	}
	

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
	public boolean validateEmail(String email) {
		matcher =pattern.matcher(email);
		return matcher.matches();
}    
    

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }       
    }	
}