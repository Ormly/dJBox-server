package org.pineapple.api;

import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController
{
    /**
     * Returns a json formatted list of songs currently available in the library.
     * @return
     */
    @RequestMapping("/library")
    public List<Song> songList(@RequestHeader("token") String token)
    {
        // throws an exception if token is invalid
        JukeBox.getInstance().validateToke(token);
        return JukeBox.getInstance().getAllSongs();
    }
}
