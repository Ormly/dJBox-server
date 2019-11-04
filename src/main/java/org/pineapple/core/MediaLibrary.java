package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.db.SongDAO;

import java.util.List;

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
}
