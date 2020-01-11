package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaFileMetadata;

/**
 * This class represents a song. Duh.
 */
public class Song implements IMediaFileMetadata
{
    private int id;
    private String title;
    private String artist;
    private String album;
    private int year;
    private String genre;
    private String pathToFile;
    private double durationSec;
    private String coverArtURL;

    private final String[] genres =
            {
                    "Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz",
                    "Metal", "New Age", "Oldies", "Other", "Pop", "Rhythm and Blues", "Rap", "Reggae", "Rock",
                    "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno",
                    "Ambient", "Trip-Hop", "Vocal", "Jazz & Funk", "Fusion", "Trance", "Classical", "Instrumental",
                    "Acid", "House", "Game", "Sound clip", "Gospel", "Noise", "Alternative Rock", "Bass", "Soul",
                    "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic",
                    "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock",
                    "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle music", "Native US",
                    "Cabaret", "New Wave", "Psychedelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk",
                    "Acid Jazz", "Polka", "Retro", "Musical", "Rock n Roll", "Hard Rock"
            };

    public Song(int id, String title, String artist, String album, int year, String genre, String pathToFile, double durationSec, String coverArtURL)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.pathToFile = pathToFile;
        this.durationSec = durationSec;
        this.coverArtURL = coverArtURL;
    }

    //We assume every new song is not constructed with an ID value
    public Song(String title, String artist, String album, int year, String genre, String pathToFile, double durationSec, String coverArtURL)
    {
        this.id = 0;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = getActualGenre(genre);
        this.pathToFile = pathToFile;
        this.durationSec = durationSec;
        this.coverArtURL = coverArtURL;
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

    public double getDuration() { return this.durationSec; }

//    public void setDuration(double duration) { this.durationSec = duration; }

    public String getCoverArtURL() { return coverArtURL; }

    /**
     * Compare two Song objects by comparing artist and album names
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        Song s = (Song) obj;
        return this.artist.equals(s.artist) && this.album.equals(s.album) && this.title.equals(s.title);
    }

    /**
     * Return the actual genre that is represented by the ID3v1 genre tag
     * @param pos
     * @return String
     * @throws NumberFormatException
     */
    public String getActualGenre(String pos)
    throws NumberFormatException
    {
        try
        {
            int i = Integer.valueOf(pos);
            if(i < 0 || i > genres.length-1)
                return "None";

            return genres[i];
        } catch(NumberFormatException ex)
        {
            System.out.println("The song already has a string genre: " + pos);
            return pos;
        }
    }
}
