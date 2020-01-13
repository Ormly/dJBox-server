package org.pineapple.core.states;

/**
 * Represents a disabled state of the jukeBox states.
 */
public class DisabledState extends JukeBoxState
{
    /**
     * Generates a new disabled state.
     */
    public DisabledState()
    {
        jb.clearCurrentlyPlaying();
    }

    /**
     * Re-enables the jukeBox and sets it to the enabled, aka. waiting state.
     */
    @Override
    public void enable()
    {
        // transition to Stopped state
        jb.setState(new WaitingState());
    }
}
