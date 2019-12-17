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

    public void unPause();

    /**
     * Returns true is there is currently a song playing, or flase otherwise.
     * @return
     */
    public boolean isPlaying();

    /**
     * Returns true is current player is stopped.
     * @return
     */
    public boolean isStopped();

    public boolean isPaused();

    public void setOnSongEnd(Runnable callback);

    public void stop();
}
