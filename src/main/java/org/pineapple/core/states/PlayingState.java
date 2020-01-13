package org.pineapple.core.states;

import org.pineapple.core.Song;

import java.util.NoSuchElementException;

/**
 * Represents a playing state of the jukeBox states.
 */
public class PlayingState extends JukeBoxState
{
    /**
     * Sets the jukeBox to playing.
     */
    public PlayingState()
    {
        this.play();
    }

    /**
     * Sets the player to disabled state.
     */
    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }

    /**
     * Handles the event of a song finished playing.
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
     * Plays the next song from the queue if it is not empty. If the queue is empty, a new waiting state is generated.
     * @throws NoSuchElementException
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
