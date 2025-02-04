package org.pineapple.api;

import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;
import org.pineapple.core.SongResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QueueController
{
    /**
     * Returns a json formatted list of the songs currently in queue.
     *
     * @return
     */
    @RequestMapping("/queue")
    public List<SongResponse> queue(@RequestHeader("token") String token)
    {
        // throws an exception if token invalid
        JukeBox.getInstance().validateToke(token);

        // List<Song> => List<SongResponse>
        List<Song> songs = JukeBox.getInstance().getSongsFromQueue();
        List<SongResponse> responses = new ArrayList<>();
        songs.forEach((song) -> responses.add(new SongResponse(song)));

        return responses;
    }

    /**
     * Attempts to add song with songID to queue
     * @param songID
     * @param token
     */
    @GetMapping("queue/add/{songID}")
    public void addToQueue(@PathVariable int songID, @RequestHeader("token") String token)
    {
        // throws an exception if the token is invalid
        JukeBox.getInstance().validateToke(token);

        // throws an exception if song is not in library
        JukeBox.getInstance().addSongToQueue(songID);
    }
}
