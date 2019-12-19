package org.pineapple.core;

import org.pineapple.core.exceptions.SongNotFoundException;
import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.MediaQueue;
import org.pineapple.core.interfaces.IPlayer;
import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

import java.util.List;

public class JukeBox
{
    private static JukeBox instance;

    private MediaLibrary libraryProvider;
    private IPlayer player;
    private MediaQueue playlist;
    private IAuthenticationManager authenticationManager;

    private JukeBox()
    {
        this.libraryProvider = new MediaLibrary("media");
        this.player = new JavaFXPlayer();
        this.authenticationManager = new AuthenticationManager();
        this.playlist = new SongQueue();
    }

    /**
     * Returns the one and only allowed instance of the JukeBox. Lazy instantiation.
     * @return
     */
    public static JukeBox getInstance(){
        if(instance == null){
            instance = new JukeBox();
        }

        return instance;
    }
    public void addSong(String path)
    {
        this.libraryProvider.addSongToLibrary(path);
    }

    public void deleteSong(Song song)
    {
        this.libraryProvider.deleteSongFromLibrary(song);
    }

    public void setOnQueueChanged(Runnable callback){
        this.playlist.setOnQueueChanged(callback);
    }

    /**
     * Returns a list of Song objects that are currently available in the library.
     *
     * @return
     */
    public List<Song> getAllSongs()
    {
        return this.libraryProvider.getAllMedia();
    }

    /**
     * Returns a list of Song objects currently available in the queue
     *
     * @return
     */
    public List<Song> getSongsFromQueue() { return this.playlist.getMedia(); }

    /**
     * Adds the song with id from the media library to the song queue.
     * A SongNotFoundException is thrown if song is not found
     * @param id
     * @return
     */
    public void addSongToQueue(int id) throws SongNotFoundException
    {
        Song s = this.libraryProvider.getMedia(id);

        this.playlist.pushMedia(s);
    }

    /**
     * Exposes user authentication to upper layer.
     *
     * @param userName
     * @param password
     * @return
     */
    public String doAuthentication(String userName, String password)
    {
        return authenticationManager.authenticate(userName, password);
    }

    /**
     * Register a user
     *
     * @param userEmail
     * @param password
     * @return
     */
    public void doRegistration(String userEmail, String password)
    {
        this.authenticationManager.createUser(userEmail, password);
    }

    /**
     * log out user with the give token
     * @param token
     */
    public void logOutToken(String token)
    {
        this.authenticationManager.logOut(token);
    }

    /**
     * check if token valid
     * @param token
     */
    public void validateToke(String token)
    {
        this.authenticationManager.validateToke(token);
    }


}
