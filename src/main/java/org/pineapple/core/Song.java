package org.pineapple.core;

/**
 * This class represents a song. Duh.
 */
public class Song
{
    private int id;
    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String pathToFile;

    public Song(int id, String title, String artist, String album, int year, String genre, String pathToFile)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.pathToFile = pathToFile;
    }

    public Song(String title, String artist, String album, int year, String genre, String pathToFile)
    {
        this.id = 0;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.pathToFile = pathToFile;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getPathToFile()
    {
        return pathToFile;
    }

    public int getId()
    {
        return id;
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
