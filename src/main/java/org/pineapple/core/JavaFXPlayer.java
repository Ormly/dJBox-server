/*
This is an implementation of media player using JavaFX
 */
package org.pineapple.core;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.pineapple.core.interfaces.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface IPlayer using the JavaFX Media library.
 */
public class JavaFXPlayer implements IPlayer
{
    private List<Runnable> onSongEndCallbacks = new ArrayList<>();
    private MediaPlayer player;

    public JavaFXPlayer(){

    }

//    private void createPlayerForSong(String pathToFile)
//    {
//        this.player = new MediaPlayer(new Media())
//    }

    @Override
    public void play(String pathToFile)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public boolean isPlaying()
    {
        return false;
    }

    @Override
    public boolean isStopped()
    {
        return false;
    }

    @Override
    public void setOnSongEnd(Runnable callback)
    {
        this.onSongEndCallbacks.add(callback);
    }
}
