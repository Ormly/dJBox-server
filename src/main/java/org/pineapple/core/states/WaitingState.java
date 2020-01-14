package org.pineapple.core.states;

import org.pineapple.core.JukeBox;

/**
 * Otherwise known as the StoppedState
 */
public class WaitingState extends JukeBoxState
{
    public WaitingState()
    {
        
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
     * Even handler if queue is changed
     */
    @Override
    public void handleQueueChanged()
    {
        this.playIfQueueNotEmpty();
    }

    /**
     * Plays the next song in the queue if it's not empty
     */
    private void playIfQueueNotEmpty()
    {
        // if queue is not empty transition immediately to playing state
        if(!jb.getSongsFromQueue().isEmpty())
            jb.setState(new PlayingState());
    }
}
