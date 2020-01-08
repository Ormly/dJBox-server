package org.pineapple.api;

import org.pineapple.core.JukeBox;
import org.pineapple.core.SongResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController
{
    /**
     * Returns a json formatted list of songs currently available in the library.
     * @return
     */
    @GetMapping("/player/current")
    public SongResponse songList(@RequestHeader("token") String token)
    {
        // throws an exception if token is invalid
        JukeBox.getInstance().validateToke(token);

        return new SongResponse(JukeBox.getInstance().getCurrentlyPlayingSong());
    }

    /**
     * Returns a json formatted list of songs currently available in the library.
     * @return
     */
    @GetMapping("/player/elapsed")
    public double getElapsed(@RequestHeader("token") String token)
    {
        // throws an exception if token is invalid
        JukeBox.getInstance().validateToke(token);

        return JukeBox.getInstance().getCurrentPlayerElapsed();
    }

}
