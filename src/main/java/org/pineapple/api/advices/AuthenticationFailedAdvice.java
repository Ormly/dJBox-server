package org.pineapple.api.advices;

import org.pineapple.core.AuthenticationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthenticationFailedAdvice
{
    @ResponseBody
    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String authenticationFailedHandler(AuthenticationFailedException ex) {
        return ex.getMessage();
    }
}
