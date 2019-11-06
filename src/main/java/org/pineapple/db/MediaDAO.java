package org.pineapple.db;
/*
This class implements access to database containing Media information
 */
import org.pineapple.core.Media;
import org.pineapple.db.interfaces.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MediaDAO implements DAO<Media>
{
    private Connection connection;

    private void openConnection(){
        try{
            if(this.connection == null || this.connection.isClosed())
                this.connection = DBConnection.getConnection();
        }
        catch(SQLException e){

        }
    }

    private void closeConnection(){
        try{
            if(this.connection != null)
                this.connection.close();
        }
        catch(SQLException e){

        }
    }

    @Override
    public Optional<Media> get(long id)
    {
        this.openConnection();

        Media m = null;

        try{
            Statement s = this.connection.createStatement();
            ResultSet result = s.executeQuery("SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location\n" +
                                              "FROM song s, artist art, album alb\n" +
                                              "WHERE s.artist_id = art.artist_id\n" +
                                              "AND alb.album_id = s.album_id\n" +
                                              "AND s.song_id=" + (int)id +";");

            if(result.next()){
                m = new Media(result.getInt("s.song_id"),
                                result.getString("s.title"),
                                result.getString("art.name"),
                                result.getString("alb.name"),
                                result.getInt("s.year"),
                                result.getString("s.genre"),
                                result.getString("s.location"));
            }
        }
        catch(SQLException e){

        }
        finally
        {
            this.closeConnection();
        }

        return Optional.ofNullable(m);
    }

    @Override
    public List<Media> getAll()
    {
        this.openConnection();
        List<Media> list = new ArrayList<>();

        try{
            Statement s = this.connection.createStatement();
            ResultSet result = s.executeQuery("SELECT s.song_id, s.title, art.name, alb.name, s.year, s.genre, s.location\n" +
                                              "FROM song s, artist art, album alb\n" +
                                              "WHERE s.artist_id = art.artist_id\n" +
                                              "AND alb.album_id = s.album_id;");

            while(result.next()){
                Media m = new Media(result.getInt("s.song_id"),
                                    result.getString("s.title"),
                                    result.getString("art.name"),
                                    result.getString("alb.name"),
                                    result.getInt("s.year"),
                                    result.getString("s.genre"),
                                    result.getString("s.location"));
                list.add(m);
            }
        }
        catch(SQLException e){

        }
        finally
        {
            this.closeConnection();
        }
        return list;    // implement get all songs from database
    }

    @Override
    public void save(Media media)
    {
        // save media to database
    }

    @Override
    public void update(Media media, String[] params)
    {
        // update media in database
    }

    @Override
    public void delete(Media media)
    {
        // delete media from database
    }
}
