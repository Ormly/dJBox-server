package org.pineapple.core.states;

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
