package org.pineapple.core;

public class CurrentSong
{
    private Song song;
    private double duration;
    private double elapsedTime;

    public CurrentSong(Song song, double duration)
    {
        this.song = song;
        this.duration = duration;
    }

    public void setElapsedTime(double elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }

    public int getID()
    {
        return song.getId();
    }

    public String getTitle()
    {
        return song.getTitle();
    }

    public String getArtist()
    {
        return song.getArtist();
    }

    public String getAlbum()
    {
        return song.getAlbum();
    }

    public int getYear()
    {
        return song.getYear();
    }

    public String getGenre()
    {
        return song.getGenre();
    }

    public double getDuration()
    {
        return duration;
    }

    public double getElapsedTime()
    {
        return elapsedTime;
    }

    public Song song()
    {
        return song;
    }
}
