package org.pineapple.core;

import org.pineapple.core.exceptions.NoSongCurrentlyPlayingException;
import org.pineapple.core.exceptions.SongNotFoundException;
import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.MediaQueue;
import org.pineapple.core.interfaces.IPlayer;
import org.pineapple.core.states.DisabledState;
import org.pineapple.core.states.JukeBoxState;
import org.pineapple.core.states.WaitingState;
import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

import java.util.List;
import java.util.NoSuchElementException;

public class JukeBox
{
    private static JukeBox instance;

    private IMediaLibrary libraryProvider;
    private IPlayer player;
    private MediaQueue playlist;
    private IAuthenticationManager authenticationManager;
    private JukeBoxState state;
    private Song currentlyPlaying;

    private JukeBox()
    {
        this.libraryProvider = new MediaLibrary("media");
        this.player = new JavaFXPlayer();
        this.authenticationManager = new AuthenticationManager();
        this.playlist = new SongQueue();

        // bad things happen without this
        JukeBoxState.setJukeBox(this);

        // initial state
        this.state = new WaitingState();

        // register to events
        this.setOnQueueChanged(() -> this.onQueueChanged());
        this.player.setOnSongEnd(() -> this.onSongFinished());
    }

    /**
     * lets the current state know when the queue has been changed
     */
    private void onQueueChanged()
    {
        this.state.handleQueueChanged();
    }

    /**
     * lets the current state know when the current song has finished
     */
    private void onSongFinished()
    {
        this.state.handleSongFinished();
    }

    /**
     * Returns the one and only allowed instance of the JukeBox. Lazy instantiation.
     * @return
     */
    public static JukeBox getInstance(){
        if(instance == null)
            instance = new JukeBox();

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

    /**
     * Sets the current state of the JukeBox
     * @param newState an implementation of the JukeBoxState class
     */
    public void setState(JukeBoxState newState)
    {
        System.out.println(this.state.toString() + "->" + newState.toString());
        this.state = newState;
    }

    /**
     * Gets the current state of the JukeBox
     * @return an implementation of JukeBoxState
     */
    public JukeBoxState getState()
    {
        return this.state;
    }

    /**
     * Registers a callback method with the onQueueChanged event
     * @param callback the function to be invoked in the event of queue change.
     */
    public void setOnQueueChanged(Runnable callback){
        this.playlist.setOnQueueChanged(callback);
    }

    /**
     * Sets the currently playing song to null
     */
    public void clearCurrentlyPlaying()
    {
        this.currentlyPlaying = null;
    }

    public void playSong(Song song)
    {
        this.player.play(song.getPathToFile());
        this.currentlyPlaying = song;
    }

    /**
     *  Pops the song at top of the queue and returns a reference to it.
     *  Throws a NoSuchElementException if the queue is empty
     * @return a reference to the song that was at the top of the queue or null if the
     */
    public Song popNextSong() throws NoSuchElementException
    {
        return (Song)this.playlist.popNextMedia();
    }

    /**
     * Call to make JukeBox available for clients
     */
    public void enableJukeBox()
    {
        this.state.enable();
    }

    /**
     * Call to make JukeBox unavailable to clients
     */
    public void disableJukeBox()
    {
        this.state.disable();
    }

    /**
     * Pauses current song
     */
    public void pauseCurrentSong()
    {
        if(this.player != null)
            this.player.pause();
    }

    /**
     * Un-pauses currently playing song
     */
    public void unpauseCurrentSong()
    {
        if(this.player != null)
            this.player.unPause();
    }

    /**
     * Returns true if a song is currently playing
     * @return false if no song is playing
     */
    public boolean isPlaying()
    {
        return this.player.isPlaying();
    }

    /**
     * Returns true if a song is currently paused
     * @return false if no song is paused
     */
    public boolean isPaused()
    {
        return this.player.isPaused();
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

    /**
     * Fetch the currently playing song object.
     * @return
     */
    public Song getCurrentlyPlayingSong()
    {
        if(this.currentlyPlaying == null)
            throw new NoSongCurrentlyPlayingException();
        return currentlyPlaying;
    }

    public double getCurrentPlayerElapsed()
    {
        if(this.currentlyPlaying == null)
            throw new NoSongCurrentlyPlayingException();
        return player.getElapsed();
    }
}
