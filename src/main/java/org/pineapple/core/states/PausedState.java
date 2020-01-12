package org.pineapple.core.states;

import org.pineapple.core.JukeBox;

import java.util.NoSuchElementException;

public class PausedState extends JukeBoxState
{
    public PausedState()
    {
        jb.clearCurrentlyPlaying();
    }

    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }
}
