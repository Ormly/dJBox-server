package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.MediaQueue;
import org.pineapple.core.interfaces.IPlayer;

import java.util.List;

public class JukeBox
{
    private IMediaLibrary libraryProvider;
    private IPlayer player;
    private MediaQueue playlist;

    public JukeBox(IMediaLibrary library, IPlayer player){
        this.libraryProvider = library;
        this.player = player;
        this.playlist = new SongQueue();
    }

    public List<Song> getAllSongs(){
        return this.libraryProvider.getAllSongs();
    }

    public List<Song> getSongsFromQueue() { return this.playlist.getMedia(); }

    public boolean addSongToQueue(int id){
        Song s = this.libraryProvider.getSong(id);
        if(s != null)
            this.playlist.pushMedia(s);

        return s != null;

    };

}
