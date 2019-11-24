package org.pineapple.core.exceptions;

public class SongNotFoundException extends RuntimeException
{
    public SongNotFoundException(int songID){
        super("Song with ID: " + String.valueOf(songID) + " has not been found in the library.");
    }
}
