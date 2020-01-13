package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaFileMetadata;

/**
 * This class represents a song.
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

    //genres as encoded in the IDv1 tags
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

    /**
     * Generates a new Song object. Used only when creating a new Song object from a song already contained in the DB.
     *
     * @param id
     * @param title
     * @param artist
     * @param album
     * @param year
     * @param genre
     * @param pathToFile
     * @param durationSec
     * @param coverArtURL
     */
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

    /**
     * Generates a new Song object. Used when we are creating a completely new Song object.
     *
     * @param title
     * @param artist
     * @param album
     * @param year
     * @param genre
     * @param pathToFile
     * @param durationSec
     * @param coverArtURL
     */
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

    /**
     * Returns the title of the song.
     * @return String title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Returns the genre of the song.
     * @return String genre
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     * Returns the location of the song.
     * @return String pathToFile
     */
    public String getPathToFile()
    {
        return pathToFile;
    }

    /**
     * Returns the id of the song.
     * @return int id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the artist of the song.
     * @return String artist
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Returns the album of the song.
     * @return String album
     */
    public String getAlbum()
    {
        return album;
    }

    /**
     * Returns the year the song was created.
     * @return int year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the duration of the song.
     * @return double duration
     */
    public double getDuration() { return this.durationSec; }

    /**
     * Returns the cover art URL of the song.
     * @return String coverArtURL
     */
    public String getCoverArtURL() { return coverArtURL; }

    /**
     * Compare two Song objects by comparing artist and album names. Return true if
     * they are equal.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj)
    {
        Song s = (Song) obj;
        return this.artist.equals(s.artist) && this.album.equals(s.album) && this.title.equals(s.title);
    }

    /**
     * Return the actual genre that is represented by the ID3v1 genre tag.
     * @param pos
     * @return String actualGenre
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
