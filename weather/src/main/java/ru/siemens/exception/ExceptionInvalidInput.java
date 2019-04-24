package ru.siemens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExceptionInvalidInput extends RuntimeException {
    public ExceptionInvalidInput(String message) {
        super(message);
    }
}
