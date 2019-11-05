package org.pineapple.db;
/*
This class implements access to database containing Song information
 */
import org.pineapple.core.Song;
import org.pineapple.db.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongDAO implements DAO<Song>
{
    @Override
    public Optional<Song> get(long id)
    {
        return Optional.empty();
    }

    @Override
    public List<Song> getAll()
    {
        List<Song> list = new ArrayList<>();
        list.add(new Song(1,"For Whom the Bell Tolls", "Metallica", "Ride the Lightning",1984));
        list.add(new Song(2,"Cry Me A River", "Justin Timberlake", "Justified",2003));
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
