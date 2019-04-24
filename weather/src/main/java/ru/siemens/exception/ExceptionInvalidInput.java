package ru.siemens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT)
public class ExceptionInvalidInput extends RuntimeException {

}
