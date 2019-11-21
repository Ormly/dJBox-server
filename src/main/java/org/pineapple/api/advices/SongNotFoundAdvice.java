package org.pineapple.api.advices;

import org.pineapple.core.exceptions.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class SongNotFoundAdvice
{
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String songNotFoundHandler(SongNotFoundException ex) {return ex.getMessage();}
}
