package org.pineapple.db;
/*
This class implements access to database containing Song information
 */

import org.pineapple.core.Song;
import org.pineapple.db.interfaces.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDAO implements DAO<Song>
{
    private Connection connection;

    private void openConnection()
    {
        try
        {
            if(this.connection == null || this.connection.isClosed())
                this.connection = DBConnection.getConnection();
        } catch(SQLException e)
        {

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

        }
    }

    @Override
    public Optional<Song> get(long id)
    {
        this.openConnection();

        Song m = null;

        try
        {
            Statement s = this.connection.createStatement();
            ResultSet result = s.executeQuery(
                    "SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location\n" +
                    "FROM song s, artist art, album alb\n" +
                    "WHERE s.artist_id = art.artist_id\n" +
                    "AND alb.album_id = s.album_id\n" +
                    "AND s.song_id=" + (int) id + ";");

            if(result.next())
            {
                m = new Song(result.getInt("s.song_id"),
                             result.getString("s.title"),
                             result.getString("art.name"),
                             result.getString("alb.name"),
                             result.getInt("s.year"),
                             result.getString("s.genre"),
                             result.getString("s.location"));
            }
        } catch(SQLException e)
        {

        } finally
        {
            this.closeConnection();
        }

        return Optional.ofNullable(m);
    }

    @Override
    public List<Song> getAll()
    {
        this.openConnection();
        List<Song> list = new ArrayList<>();

        try
        {
            Statement s = this.connection.createStatement();
            ResultSet result = s.executeQuery(
                    "SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location\n" +
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
                                  result.getString("s.location"));
                list.add(m);
            }
        } catch(SQLException e)
        {

        } finally
        {
            this.closeConnection();
        }
        return list;    // implement get all songs from database
    }

    @Override
    public void save(Song song)
    {
        // save song to database
    }

    @Override
    public void update(Song song, String[] params)
    {
        // update song in database
    }

    @Override
    public void delete(Song song)
    {
        // delete song from database
    }
}
