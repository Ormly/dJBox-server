package org.pineapple.db;
/*
This class implements access to database containing Song information
 */

import org.pineapple.core.Song;
import org.pineapple.db.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A MySQl implementation for the Song table of the DAO interface. Used to perform queries on the Songs table.
 */
public class SongDAO implements DAO<Song>
{
    private Connection connection;

    private void openConnection()
    {
        try
        {
            if(this.connection == null || this.connection.isClosed())
                this.connection = DBConnection.getConnection(DBConnection.Database.MEDIA);
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void closeConnection()
    {
        try
        {
            if(this.connection != null)
                this.connection.close();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the song that corresponds to the id passed.
     * Returns null if data could not be extracted.
     * @param id
     * @return Song
     */
    @Override
    public Optional<Song> get(long id)
    {
        this.openConnection();

        Song m = null;

        try
        {
            //changed to a prepared statement since we're passing a parameter
            PreparedStatement p = this.connection.prepareStatement(
                    "SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location, s.duration, alb.imageurl\n" +
                    "FROM song s, artist art, album alb\n" +
                    "WHERE s.artist_id = art.artist_id\n" +
                    "AND alb.album_id = s.album_id\n" +
                    "AND s.song_id=?;");

            p.setInt(1, (int) id);
            ResultSet result = p.executeQuery();

            if(result.next())
            {
                m = new Song(result.getInt("s.song_id"),
                             result.getString("s.title"),
                             result.getString("art.name"),
                             result.getString("alb.name"),
                             result.getInt("s.year"),
                             result.getString("s.genre"),
                             result.getString("s.location"),
                             result.getDouble("s.duration"),
                             result.getString("alb.imageurl"));
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            this.closeConnection();
        }

        return Optional.ofNullable(m);
    }

    /**
     * Extracts all song entries in the DB.
     * Returns empty list if data could not be extracted.
     */
    @Override
    public List<Song> getAll()
    {
        this.openConnection();
        List<Song> list = new ArrayList<>();

        try
        {
            Statement s = this.connection.createStatement();
            ResultSet result = s.executeQuery(
                    "SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location, s.duration, alb.imageurl\n" +
                    "FROM song s, artist art, album alb\n" +
                    "WHERE s.artist_id = art.artist_id\n" +
                    "AND alb.album_id = s.album_id;");

            while(result.next())
            {
                Song m = new Song(result.getInt("s.song_id"),
                                  result.getString("s.title"),
                                  result.getString("art.name"),
                                  result.getString("alb.name"),
                                  result.getInt("s.year"),
                                  result.getString("s.genre"),
                                  result.getString("s.location"),
                                  result.getDouble("s.duration"),
                                  result.getString("alb.imageurl"));
                list.add(m);
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            this.closeConnection();
        }
        return list;
    }

    /**
     * Saves a song into the DB, if no artist or album entries exist yet,
     * it generates those too.
     * @param song
     */
    @Override
    public void save(Song song)
    {
        // save song to database
        this.openConnection();

        try
        {
            PreparedStatement ps;

            PreparedStatement artID = this.connection.prepareStatement(
                    "SELECT 1 FROM artist WHERE name=?");
            PreparedStatement albID = this.connection.prepareStatement(
                    "SELECT 1 FROM album WHERE name=?");

            artID.setString(1, song.getArtist());
            albID.setString(1, song.getAlbum());

            // If the artist isn't already in the table insert it
            if(!artID.executeQuery().next())
            {
                ps = this.connection.prepareStatement(
                  "INSERT INTO artist (name) VALUES (?);"
                );
                ps.setString(1,song.getArtist());
                ps.executeUpdate();
                System.out.println("Artist inserted");
            }

            // if the album isn't already in the table insert it
            if(!albID.executeQuery().next())
            {
                ps = this.connection.prepareStatement(
                        "INSERT INTO album (name,imageurl) VALUES (?,?);"
                );
                ps.setString(1,song.getAlbum());
                ps.setString(2,song.getCoverArtURL());
                ps.executeUpdate();
                System.out.println("Album inserted");
            }

            // Insert song

             ps = this.connection.prepareStatement(
                    "INSERT INTO song (title, artist_id, album_id, genre, year, location, duration)" +
                            "VALUES (" +
                            "?," +
                            "(SELECT artist_id FROM artist WHERE name=?)," +
                            "(SELECT album_id FROM album WHERE name=?)," +
                            "?,?,?,?)");

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getAlbum());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getYear());
            ps.setString(6, song.getPathToFile());
            ps.setDouble(7,song.getDuration());

            ps.executeUpdate();

            this.closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Updates a song entry in the DB.
     * @param song
     */
    @Override
    public void update(Song song)
    {
        this.openConnection();
        try
        {
            PreparedStatement ps1 = this.connection.prepareStatement(
                    "UPDATE song SET title=?, genre=?, year=?, location=?, duration=?" +
                    "WHERE song_id=?;");

            ps1.setString(1, song.getTitle());
            ps1.setString(2, song.getGenre());
            ps1.setInt(3, song.getYear());
            ps1.setString(4, song.getPathToFile());
            ps1.setInt(5, song.getId());
            ps1.setDouble(6, song.getDuration());

            ps1.executeUpdate();

            this.closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a song in the DB.
     * @param song
     */
    @Override
    public void delete(Song song)
    {
        // delete song from database
        this.openConnection();

        try
        {
            PreparedStatement ps = this.connection.prepareStatement(
                    "DELETE FROM song WHERE song_id=?;");

            ps.setInt(1, song.getId());

            ps.executeUpdate();

            this.closeConnection();

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
