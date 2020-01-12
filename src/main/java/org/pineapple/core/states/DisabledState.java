package org.pineapple.core.states;

public class DisabledState extends JukeBoxState
{
    public DisabledState()
    {
        jb.clearCurrentlyPlaying();
    }

    @Override
    public void enable()
    {
        // transition to Stopped state
        jb.setState(new WaitingState());
    }
}
