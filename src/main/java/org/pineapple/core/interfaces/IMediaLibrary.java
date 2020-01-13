package org.pineapple.core.interfaces;

import org.pineapple.core.Song;

import java.util.List;

public interface IMediaLibrary
{
    /**
     * Returns a List of Song objects representing all the songs currently available in the library.
     * @return List of songs
     */
    public List<Song> getAllMedia();

    /**
     * Returns a single song object that matches the id provided, or null if no such song has been found.
     * @param id
     * @return Song
     */
    public Song getMedia(int id);

    /**
     * Adds a song to the library.
     * @param path
     */
    public void addSongToLibrary(String path);

    /**
     * Deletes the passed song in the library.
     * @param songToDelete
     */
    public void deleteSongFromLibrary(Song songToDelete);
}
