package com.FelipeMarques.salesController.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final String ENTITY;
    private final String FIELD;
    private final String FIELD_VALUE;

    public EntityNotFoundException(String entity, String field, String fieldValue) {
        super(String.format("%s not found with %s = %s", entity, field, fieldValue));
        ENTITY = entity;
        FIELD = field;
        FIELD_VALUE = fieldValue;
    }
}
