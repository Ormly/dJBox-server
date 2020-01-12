package org.pineapple.core.states;

public class PausedState extends JukeBoxState
{
    public PausedState()
    {
        this.pause();
    }

    /**
     * Pauses the player
     */
    @Override
    public void pause()
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
}
