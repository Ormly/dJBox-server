package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.MediaQueue;
import org.pineapple.core.interfaces.IPlayer;
import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

import java.util.List;

public class JukeBox
{
    private IMediaLibrary libraryProvider;
    private IPlayer player;
    private MediaQueue playlist;
    private IAuthenticationManager authenticationManager;

    public JukeBox(IMediaLibrary library, IPlayer player){
        this.libraryProvider = library;
        this.player = player;
        this.authenticationManager = new AuthenticationManager(new UserDAO());
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
    }

    public String doAuthentication(String userName, String password){
        return authenticationManager.authenticate(userName,password);
    }
}
