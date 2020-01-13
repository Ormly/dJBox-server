package org.pineapple.core.states;

import org.pineapple.core.JukeBox;

/**
 * Represents the enabled or waiting state. Otherwise known as the StoppedState
 */
public class WaitingState extends JukeBoxState
{
    public WaitingState() {    }

    /**
     * Sets the player to disabled state.
     */
    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }

    /**
     * Event handler in case of a change in the queue.
     */
    @Override
    public void handleQueueChanged()
    {
        this.playIfQueueNotEmpty();
    }

    /**
     * If the queue is not empty, transition to a playing state.
     */
    private void playIfQueueNotEmpty()
    {
        // if queue is not empty transition immediately to playing state
        if(!jb.getSongsFromQueue().isEmpty())
            jb.setState(new PlayingState());
    }
}
