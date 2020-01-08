package org.pineapple.api.advices;

import org.pineapple.core.exceptions.NoSongCurrentlyPlayingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoSongCurrentlyPlayingAdvice
{
    @ResponseBody
    @ExceptionHandler(NoSongCurrentlyPlayingException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String noSongCurrentlyPlayingHandler(NoSongCurrentlyPlayingException ex) {
        return ex.getMessage();
    }
}
