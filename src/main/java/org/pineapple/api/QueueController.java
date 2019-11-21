package org.pineapple.api;

import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueueController
{
    @Autowired
    private JukeBox jb;

    /**
     * Returns a json formatted list of the songs currently in queue.
     * @return
     */
    @RequestMapping("/queue")
    public List<Song> queue()
    {
        return jb.getSongsFromQueue();
    }

    @GetMapping("queue/add/{songID}")
    public void addToQueue(@PathVariable int songID, @RequestHeader("token") String token){
        // TODO: verify token
        System.out.println("token: " + token);
        System.out.println("adding song with id: " +  String.valueOf(songID));
        this.jb.addSongToQueue(songID);
    }
}
