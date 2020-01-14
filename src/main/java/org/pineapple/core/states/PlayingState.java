package org.pineapple.core.states;

import org.pineapple.core.Song;

import java.util.NoSuchElementException;

public class PlayingState extends JukeBoxState
{
    public PlayingState()
    {
        this.play();
    }

    /**
     * Sets the player to disabled state
     */
    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }

    /**
     * If song is finished, play the next if it's in the queue
     */
    @Override
    public void handleSongFinished()
    {
        this.play();
    }

    /**
     * Sets the player to paused state
     */
    @Override
    public void pause()
    {
        jb.setState(new PausedState());
    }

    /**
     * Plays the next song in the queue if it exists, else goes to initial waiting state
     */
    private void play()
    {
        try{
            jb.playSong(jb.popNextSong());
        }
        catch(NoSuchElementException e)
        {
            jb.setState(new WaitingState());
        }

    }
}
