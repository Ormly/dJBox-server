package org.pineapple.core;

/**
 * This is a wrapper for the Song object, used to control which properties are exposed.
 */
public class SongResponse
{
    private Song song;

    public SongResponse(Song s)
    {
        this.song = s;
    }

    public String getTitle()
    {
        return this.song.getTitle();
    }

    public String getGenre()
    {
        return this.song.getGenre();
    }

    public int getId()
    {
        return this.song.getId();
    }

    public String getArtist()
    {
        return this.song.getArtist();
    }

    public String getAlbum()
    {
        return this.song.getAlbum();
    }

    public int getYear()
    {
        return this.song.getYear();
    }

}

