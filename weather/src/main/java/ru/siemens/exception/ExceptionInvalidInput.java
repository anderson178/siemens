package ru.siemens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 */

/**
 * Exception class for invalid input data
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExceptionInvalidInput extends RuntimeException {
    public ExceptionInvalidInput(String message) {
        super(message);
    }
}
