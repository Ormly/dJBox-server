package org.pineapple.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.pineapple.core.Song;

import java.awt.image.RescaleOp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDAOTest
{
    static private SongDAO s;
    static private Connection c;
    static List<Song> cleanUp = new ArrayList<>();

    @BeforeAll
    static void beforeAll()
    {
        s = new SongDAO();
        c = DBConnection.getConnection(DBConnection.Database.MEDIA);

    }

    @Test
    void saveNewSong()
    throws SQLException
    {
        s.save(new Song("jb_test_title", "jb_test_artist", "jb_test_album", 2019, "yes", "here/to/there"));

        Statement s1 = c.createStatement();
        Statement s2 = c.createStatement();
        Statement s3 = c.createStatement();

        ResultSet rs1 = s1.executeQuery("SELECT EXISTS(SELECT 1 FROM artist WHERE name='jb_test_artist')");

        ResultSet rs2 = s2.executeQuery("SELECT EXISTS(SELECT 1 FROM album WHERE name='jb_test_album')");

        ResultSet rs3 = s3.executeQuery("SELECT title FROM song WHERE title='jb_test_title'");


        Assertions.assertTrue(rs1.next());
        Assertions.assertTrue(rs2.next());
        Assertions.assertTrue(rs3.next());

        //this.cleanUp.addAll(this.findSongsWithName("jb_test_title"));
    }

    @Test
    void getSongGenre()
    throws SQLException
    {
        Song song = s.get(12).get();

        System.out.println(song.getGenre());
    }

    /*@Test
    void duplicateSongs()
    {

    }

    private List<Song> findSongsWithName(String title){
        List<Song> result = new ArrayList<>();

        List<Song> allSongs = s.getAll();
        for(Song x: allSongs){
            if(x.getTitle().equals(title)){
                result.add(x);
            }
        }

        return result;
    }

    @Test
    void removeSongFromPersistenceTest(){
        Song songy = new Song("jb_test_delete_title",
                              "jb_test_artist",
                              "jb_test_album",
                              2019,
                              "yes",
                              "here/to/there");

        s.save(songy);

        cleanUp.addAll(findSongsWithName(songy.getTitle()));

        List<Song> songs = s.getAll();

        Song toDelete = null;

        // find the song we just saved
        for(Song x: songs)
        {
            if (x.getTitle().equals(songy.getTitle())
                && x.getArtist().equals(songy.getArtist()))
            {
                toDelete = x;
            }
        }

        if(toDelete != null)
            s.delete(toDelete);

        // make sure nothing comes back
        Assertions.assertFalse(s.get(toDelete.getId()).isPresent());

    }

    @AfterAll
    static void cleanUp() throws SQLException{
        Statement cleanUpStatement = c.createStatement();
        for(Song s: cleanUp){
            cleanUpStatement.executeUpdate("DELETE FROM song WHERE song_id="+s.getId()+";");
        }

        c.close();

    }

     */
}
