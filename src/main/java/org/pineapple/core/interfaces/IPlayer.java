package org.pineapple.core.interfaces;

public interface IPlayer
{
    /**
     * Play the media file in the given relative path.
     * @param pathToFile
     */
    public void play(String pathToFile);

    /**
     * Pauses the currently playing song.
     */
    public void pause();

    /**
     * Un-pauses the currently playing song.
     */
    public void unPause();

    /**
     * Returns true is there is currently a song playing, or flase otherwise.
     * @return boolean
     */
    public boolean isPlaying();

    /**
     * Returns true is current player is stopped.
     * @return boolean
     */
    public boolean isStopped();

    /**
     * Returns true is current player is paused.
     * @return boolean
     */
    public boolean isPaused();

    /**
     * Event handler for when the currently playing song ends.
     */
    public void setOnSongEnd(Runnable callback);

    /**
     * Stops the current player.
     */
    public void stop();

    /**
     * Returns the elapsed time of the current song.
     * @return double
     */
    public double getElapsed();
}
