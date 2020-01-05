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

    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }

    @Override
    public void handleQueueChanged()
    {
        this.playIfQueueNotEmpty();
    }

    private void playIfQueueNotEmpty()
    {
        // if queue is not empty transition immediately to playing state
        if(!jb.getSongsFromQueue().isEmpty())
            jb.setState(new PlayingState());
    }
}
