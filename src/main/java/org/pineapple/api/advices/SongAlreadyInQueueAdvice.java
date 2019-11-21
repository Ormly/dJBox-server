package org.pineapple.api.advices;

import org.pineapple.core.exceptions.SongAlreadyInQueueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SongAlreadyInQueueAdvice
{
    @ResponseBody
    @ExceptionHandler(SongAlreadyInQueueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String songNotInQueueHandler(SongAlreadyInQueueException ex) {
        return ex.getMessage();
    }

}
