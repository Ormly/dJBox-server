package org.pineapple.api;

import org.pineapple.core.MediaLibrary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LibraryController
{
    @RequestMapping("/library")
    public HashMap<Integer,String> songList(){
        return MediaLibrary.getSongList();
    }
}
