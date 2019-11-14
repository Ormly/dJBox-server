package org.pineapple.core.interfaces;

import org.pineapple.core.Song;

import java.util.List;

public interface IMediaLibrary
{
    /**
     * Returns a List of Song objects representing all the songs currently available in the library.
     * @return
     */
    public List<Song> getAllSongs();

    /**
     * Returns a single song object that matches the id provided, or null if no such song has been found.
     * @param id
     * @return
     */
    public Song getSong(int id);
}
