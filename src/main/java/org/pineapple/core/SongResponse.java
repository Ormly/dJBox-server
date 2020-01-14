package org.pineapple.core;

/**
 * This is a wrapper for the Song object, used to control which properties are exposed.
 */
public class SongResponse
{
    private Song song;

    /**
     * Generates a new song response
     * @param s
     */
    public SongResponse(Song s)
    {
        this.song = s;
    }

    /**
     * Returns the title of the song.
     * @return String title
     */
    public String getTitle()
    {
        return this.song.getTitle();
    }

    /**
     * Returns the genre of the song.
     * @return String genre
     */
    public String getGenre()
    {
        return this.song.getGenre();
    }

    /**
     * Returns the id of the song.
     * @return int id
     */
    public int getId()
    {
        return this.song.getId();
    }

    /**
     * Returns the artist of the song.
     * @return String artist
     */
    public String getArtist()
    {
        return this.song.getArtist();
    }

    /**
     * Returns the album of the song.
     * @return String album
     */
    public String getAlbum()
    {
        return this.song.getAlbum();
    }

    /**
     * Returns the year the song was created.
     * @return int year
     */
    public int getYear()
    {
        return this.song.getYear();
    }

    /**
     * Returns the duration of the song.
     * @return double duration
     */
    public double getDuration() { return this.song.getDuration(); }

    /**
     * Returns the cover art URL of the song.
     * @return String coverArtURL
     */
    public String getCoverArtURL() { return this.song.getCoverArtURL(); }

}

