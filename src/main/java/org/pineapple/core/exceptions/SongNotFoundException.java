package org.pineapple.core.exceptions;

public class SongNotFoundException extends RuntimeException
{
    /**
     * This exception can be used to indicate the requested song does not exist in the database.
     */
    public SongNotFoundException(int songID){
        super("Song with ID: " + String.valueOf(songID) + " has not been found in the library.");
    }
}
