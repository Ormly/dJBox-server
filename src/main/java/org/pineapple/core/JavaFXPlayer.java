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

/**
 * This class implements the interface IPlayer using the JavaFX Media library.
 */
public class JavaFXPlayer implements IPlayer
{
    private List<Runnable> onSongEndCallbacks = new ArrayList<>();
    private Runnable onSongEndCallback;
    private MediaPlayer player;

    public JavaFXPlayer(){
        this.onSongEndCallback = () -> onSongEng();
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
     * Gets called when song end and invokes all callbacks currently registered with JavaFXPlayer
     */
    private void onSongEng()
    {
        for(Runnable r: this.onSongEndCallbacks){
            r.run();
        }
    }

    /**
     * Plays the media file with given path
     * @param pathToFile
     */
    @Override
    public void play(String pathToFile)
    {
        this.createPlayerForSong(pathToFile);
        this.player.play();
    }

    @Override
    public void pause()
    {
        if(this.isPlaying())
            this.player.pause();
    }

    public void unPause()
    {
        if(this.isPaused())
            this.player.play();
    }

    @Override
    public boolean isPlaying()
    {
        if(this.player != null)
            return this.player.getStatus() == MediaPlayer.Status.PLAYING;

        return false;
    }

    @Override
    public boolean isStopped()
    {
        return !this.isPlaying();
    }

    @Override
    public boolean isPaused()
    {
        if(this.player != null)
            return this.player.getStatus() == MediaPlayer.Status.PAUSED;

        return false;
    }

    @Override
    public void setOnSongEnd(Runnable callback)
    {
        this.onSongEndCallbacks.add(callback);
    }

    @Override
    public void stop()
    {
        if(this.player != null)
            this.player.stop();

        this.player = null;
    }
}
