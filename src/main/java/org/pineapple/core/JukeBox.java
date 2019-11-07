package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.IMediaQueue;
import org.pineapple.core.interfaces.IPlayer;

import java.util.List;

public class JukeBox
{
    private IMediaLibrary libraryProvider;
    private IPlayer player;
    private IMediaQueue playlist;

    public JukeBox(IMediaLibrary library, IPlayer player){
        this.libraryProvider = library;
        this.player = player;
    }

    public List<Media> getAllSongs(){
        return this.libraryProvider.getAllSongs();
    }
}
