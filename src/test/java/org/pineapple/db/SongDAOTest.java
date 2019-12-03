package org.pineapple.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.pineapple.core.Song;

import java.awt.image.RescaleOp;
import java.sql.*;

public class SongDAOTest
{
    static private SongDAO s;
    static private Connection c;

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
        s.save(new Song("title", "artist", "album", 2019, "yes", "here/to/there"));

        Statement s1 = c.createStatement();
        Statement s2 = c.createStatement();
        Statement s3 = c.createStatement();

        ResultSet rs1 = s1.executeQuery("SELECT EXISTS(SELECT 1 FROM artist WHERE name='artist')");

        ResultSet rs2 = s2.executeQuery("SELECT EXISTS(SELECT 1 FROM album WHERE name='album')");

        ResultSet rs3 = s3.executeQuery("SELECT title FROM song WHERE title='title'");


        Assertions.assertTrue(rs1.next());
        Assertions.assertTrue(rs2.next());
        Assertions.assertTrue(rs3.next());
    }

    @Test
    void duplicateSongs()
    {

    }
}
