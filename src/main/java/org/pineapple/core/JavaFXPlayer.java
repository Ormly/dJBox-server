/*
This is an implementation of media player using JavaFX
 */
package org.pineapple.core;

import org.pineapple.core.interfaces.IPlayer;

/**
 * This class implements the interface IPlayer using the JavaFX Media library.
 */
public class JavaFXPlayer implements IPlayer
{
    @Override
    public void play(String pathToFile)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public boolean isPlaying()
    {
        return false;
    }

    @Override
    public boolean isStopped()
    {
        return false;
    }
}
