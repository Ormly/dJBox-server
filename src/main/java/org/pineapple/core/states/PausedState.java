package org.pineapple.core.states;

/**
 * Represents a paused state of the jukeBox states. Is not implemented.
 */
public class PausedState extends JukeBoxState
{
    PausedState() { }

    /**
     * Sets the player to disabled state
     */
    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }
}
