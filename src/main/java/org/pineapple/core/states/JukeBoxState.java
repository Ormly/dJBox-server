package org.pineapple.core.states;

import org.pineapple.core.JukeBox;

/**
 * Represents a viable state of the jukeBox.
 */
public abstract class JukeBoxState
{
    protected static JukeBox jb;

    /**
     * Initializes the jukeBox.
     * @param j
     */
    public static void setJukeBox(JukeBox j)
    {
        jb = j;
    }

    /**
     * Override this method to implement an Enable transition
     */
    public void enable()
    {
        // override to do something
    }

    /**
     * Override this method to implement a Disable transition
     */
    public void disable()
    {
        // override to do something
    }

    /**
     * Override this method to implement a Queue changed transition
     */
    public void handleQueueChanged()
    {
        // override to do something
    }

    /**
     * Override this method to implement a Song finished transition
     */
    public void handleSongFinished()
    {
        // override to do something
    }

    /**
     * Override this method to implement a pause transition
     */
    public void pause()
    {
        // override to do something
    }

    /**
     * Override this method to implement an unpause transition
     */
    public void unPause()
    {
        // override to do something
    }
}
