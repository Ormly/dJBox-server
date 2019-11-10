package org.pineapple.api;

import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueueController
{
    @Autowired
    private JukeBox jb;

    @RequestMapping("/queue")
    public List<Song> queue(){
        return jb.getSongsFromQueue();
    }

}
