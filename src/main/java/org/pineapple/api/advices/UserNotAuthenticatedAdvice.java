package org.pineapple.api.advices;

import org.pineapple.core.exceptions.UserNotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotAuthenticatedAdvice
{
    @ResponseBody
    @ExceptionHandler(UserNotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String userNotAuthenticatedHandler(UserNotAuthenticatedException ex) {return ex.getMessage();}
}
