package org.pineapple.api.advices;

import org.pineapple.core.exceptions.RegistrationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RegistrationFailedAdvice
{
    @ResponseBody
    @ExceptionHandler(RegistrationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String registrationFailedHandler(RegistrationFailedException ex) { return ex.getMessage(); }
}
