package com.FelipeMarques.salesController.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueAlreadyUsedException extends RuntimeException {

    public ValueAlreadyUsedException(String attribute, String value) {
        super(String.format("The %s %s is already in use", attribute, value));
    }
}
