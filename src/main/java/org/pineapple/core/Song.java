package org.pineapple.core;

public class Song
{
    private int id;
    private String name;
    private String artist;
    private String album;
    private int year;

    public Song(int id, String name, String artist, String album, int year)
    {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public int getYear()
    {
        return year;
    }
}
