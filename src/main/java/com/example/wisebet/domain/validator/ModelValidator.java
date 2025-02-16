package com.example.wisebet.domain.validator;

import com.example.wisebet.domain.exception.InvalidDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Iterator;
import java.util.Set;

public class ModelValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T obj) throws InvalidDataException{
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if(!constraintViolations.isEmpty()){
            Iterator<ConstraintViolation<T>> it = constraintViolations.iterator();
            StringBuilder message = new StringBuilder("Invalid Data: ");
            while(it.hasNext()){
                message.append(it.next().getMessage());
                if(it.hasNext()){
                    message.append(" ");
                }
            }
            throw new InvalidDataException(message.toString());
        }
    }
}
