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

    public JukeBox(IMediaLibrary library, IPlayer player)
    {
        this.libraryProvider = library;
        this.player = player;
        this.authenticationManager = new AuthenticationManager(new UserDAO());
        this.playlist = new SongQueue();
    }

    /**
     * Returns a list of Song objects that are currently available in the library.
     * @return
     */
    public List<Song> getAllSongs()
    {
        return this.libraryProvider.getAllSongs();
    }

    /**
     * Returns a list of Song objects currently available in the queue
     * @return
     */
    public List<Song> getSongsFromQueue() { return this.playlist.getMedia(); }

    /**
     * Adds the song with id from the media library to the song queue.
     * Returns false if no song with the given id was found in the library.
     * @param id
     * @return
     */
    public boolean addSongToQueue(int id)
    {
        Song s = this.libraryProvider.getSong(id);
        if(s != null)
            this.playlist.pushMedia(s);

        return s != null;
    }

    /**
     * Exposes user authentication to upper layer.
     * @param userName
     * @param password
     * @return
     */
    public String doAuthentication(String userName, String password)
    {
        return authenticationManager.authenticate(userName, password);
    }
}
