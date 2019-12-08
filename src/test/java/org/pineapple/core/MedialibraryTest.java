package org.pineapple.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.pineapple.db.DBConnection;
import org.pineapple.db.SongDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedialibraryTest
{
    static private Connection c;
    static MediaLibrary lib = new MediaLibrary("/dev/null");
    static SongDAO dao = new SongDAO();
    static List<Song> cleanUpList = new ArrayList<>();

    private List<Song> findSongsWithName(String title){
        List<Song> result = new ArrayList<>();

        List<Song> allSongs = dao.getAll();
        for(Song x: allSongs){
            if(x.getTitle().equals(title)){
                result.add(x);
            }
        }

        return result;
    }

    @BeforeAll
    static void buildUp()
    {
        c = DBConnection.getConnection(DBConnection.Database.MEDIA);
    }

    @Test
    void oneTimeInstantiationTest()
    {
        Assertions.assertThrows(UnsupportedOperationException.class,
                                () -> new MediaLibrary("doesnt/matter"));
    }

    @Test
    void getSongsInLibraryTest()
    {
        Song newSong1 = new Song("dj_test_title1",
                                "dj_test_artist",
                                "dj_test_album",
                                1990,
                                "Country",
                                "/dev/null");

        Song newSong2 = new Song("dj_test_title2",
                                "dj_test_artist",
                                "dj_test_album",
                                1990,
                                "Country",
                                "/dev/null");

        dao.save(newSong1);
        dao.save(newSong2);

        // so we can clean them up later
        cleanUpList.add(findSongsWithName("dj_test_title1").get(0));
        cleanUpList.add(findSongsWithName("dj_test_title2").get(0));

        // all songs that are present in DB
        List<Song> songs = dao.getAll();

        List<Song> library = lib.getAllMedia();

        Assertions.assertEquals(songs,library);
    }

    @Test
    void getSongWithID()
    {
        Song newSong = new Song("dj_test_title",
                                "dj_test_artist",
                                "dj_test_album",
                                1990,
                                "Country",
                                "/dev/null");

        Song hopefullyNewSong = null;

        dao.save(newSong);

        List<Song> songs = lib.getAllMedia();

        for(Song s:songs)
        {
            if(s.getTitle().equals("dj_test_title"))
                hopefullyNewSong = s;
        }

        cleanUpList.add(hopefullyNewSong);

        Assertions.assertEquals(lib.getMedia(hopefullyNewSong.getId()).getTitle(),
                                newSong.getTitle());
    }

    @AfterAll
    static void tearDown() throws SQLException
    {
        Statement cleanUpStatement = c.createStatement();
        for(Song s: cleanUpList){
            cleanUpStatement.executeUpdate("DELETE FROM song WHERE song_id="+s.getId()+";");
        }

        c.close();
    }
}
