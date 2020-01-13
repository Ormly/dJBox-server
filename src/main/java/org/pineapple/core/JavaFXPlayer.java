/*
This is an implementation of media player using JavaFX
 */
package org.pineapple.core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.pineapple.core.interfaces.IPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the interface IPlayer using the JavaFX Media library.
 */
public class JavaFXPlayer implements IPlayer
{
    private List<Runnable> onSongEndCallbacks = new ArrayList<>();
    private Runnable onSongEndCallback;
    private MediaPlayer player;

    /**
     * Registers an event handler for when a song is finished.
     */
    public JavaFXPlayer(){
        this.onSongEndCallback = () -> onSongEnd();
    }

    /**
     * Creates a new MediaPlayer object and sets it as the current player.
     * @param pathToFile
     */
    private void createPlayerForSong(String pathToFile)
    {
        this.player = new MediaPlayer(new Media(new File(pathToFile).toURI().toString()));
        this.player.setOnEndOfMedia(this.onSongEndCallback);
    }

    /**
     * Gets called when song end and invokes all callbacks currently registered with JavaFXPlayer.
     */
    private void onSongEnd()
    {
        for(Runnable r: this.onSongEndCallbacks){
            r.run();
        }
    }

    /**
     * Plays the media file with given path.
     * @param pathToFile
     */
    @Override
    public void play(String pathToFile)
    {
        this.createPlayerForSong(pathToFile);
        this.player.play();
    }

    /**
     * Pauses the song if the player is currently playing.
     */
    @Override
    public void pause()
    {
        if(this.isPlaying())
            this.player.pause();
    }

    /**
     * Un-pauses the media if it is currently paused.
     */
    public void unPause()
    {
        if(this.isPaused())
            this.player.play();
    }

    /*
     * Checks if the song is currently playing
     * @return boolean
     */
    @Override
    public boolean isPlaying()
    {
        if(this.player != null)
            return this.player.getStatus() == MediaPlayer.Status.PLAYING;

        return false;
    }

    /**
     * Checks if the song is currently not playing
     * @return boolean
     */
    @Override
    public boolean isStopped()
    {
        return !this.isPlaying();
    }

    /**
     * Checks if the player is paused
     * @return boolean
     */
    @Override
    public boolean isPaused()
    {
        if(this.player != null)
            return this.player.getStatus() == MediaPlayer.Status.PAUSED;

        return false;
    }

    /**
     * Event handler when song ends
     * @param callback
     */
    @Override
    public void setOnSongEnd(Runnable callback)
    {
        this.onSongEndCallbacks.add(callback);
    }

    /*
     * Stops the player
     */
    @Override
    public void stop()
    {
        if(this.player != null)
            this.player.stop();

        this.player = null;
    }

    /**
     * Returns the elapsed time of the currently playing song.
     * @return double elapsedTime
     */
    public double getElapsed()
    {
        return player.getCurrentTime().toSeconds();
    }
}
