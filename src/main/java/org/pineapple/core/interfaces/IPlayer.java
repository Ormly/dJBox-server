package org.pineapple.core.interfaces;

public interface IPlayer
{
    public void play(String pathToFile);
    public void pause();
    public boolean isPlaying();
    public boolean isStopped();
}
