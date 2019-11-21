package org.pineapple.core.exceptions;

public class SongAlreadyInQueueException extends RuntimeException
{
    /**
     * This exception can be used to indicate the requested song has already been added to the queue.
     */
    public SongAlreadyInQueueException(int songId)
    {
        super("Song with ID: " + String.valueOf(songId) + "is already in the queue and cannot be added again.");
    }

}
