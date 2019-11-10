package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.db.SongDAO;

import java.util.List;
import java.util.Optional;

public class MediaLibrary implements IMediaLibrary
{
    private DAO<Song> persistence;
    private String pathToMediaDir;

    public MediaLibrary(String pathToMediaDir){
        this.persistence = new SongDAO();
        this.pathToMediaDir = pathToMediaDir; // can be used to initialize database with media directory content
    }

    public List<Song> getAllSongs(){
        return this.persistence.getAll();
    }

    @Override
    public Song getSong(int id)
    {
        Optional<Song> s = this.persistence.get((int)id);
        if(s.isPresent())
            return s.get();
        else
            return null;
    }
}
