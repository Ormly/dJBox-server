package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.db.MediaDAO;

import java.util.List;

public class MediaLibrary implements IMediaLibrary
{
    private DAO<Media> persistence;
    private String pathToMediaDir;

    public MediaLibrary(String pathToMediaDir){
        this.persistence = new MediaDAO();
        this.pathToMediaDir = pathToMediaDir; // can be used to initialize database with media directory content
    }

    public List<Media> getAllSongs(){
        return this.persistence.getAll();
    }
}
