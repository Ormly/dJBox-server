package org.pineapple.core.exceptions;

public class NoSongCurrentlyPlayingException extends RuntimeException
{
    /**
     * This exception can be used to indicate no song is currently playing.
     */
    public NoSongCurrentlyPlayingException()
    {
        super("No song is currently playing!");
    }
}
