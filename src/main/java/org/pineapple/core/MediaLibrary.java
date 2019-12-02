package org.pineapple.core;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.db.SongDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the IMediaLibrary interface to provide Song objects to the Jukebox.
 */
public class MediaLibrary implements IMediaLibrary
{
    private static boolean isInstantiated = false;
    private DAO<Song> persistence;
    private String pathToMediaDir;


    public MediaLibrary(String pathToMediaDir)
    {
        // don't allow multiple instances of MediaLibrary
        if(MediaLibrary.isInstantiated)
            throw new UnsupportedOperationException("MediaLibrary can only be instantiated once!");

        this.persistence = new SongDAO();
        this.pathToMediaDir = pathToMediaDir; // can be used to initialize database with media directory content
        this.init();

        MediaLibrary.isInstantiated = true;
    }

    private void init(){

        List<String>  pathsToMediaFiles = new ArrayList<>();

        //find all mp3 files in media directory
        try{
            Files.find(Paths.get(pathToMediaDir),
                       Integer.MAX_VALUE,
                       (filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.toString().endsWith(".mp3"))
                    .forEach((x) -> pathsToMediaFiles.add(x.toString()));
        }
        catch(IOException ex){
            System.out.println("Error traversing media directory.");
            System.out.println(ex.getMessage());
        }

        // save all Song objects to persistence
        for(String path:pathsToMediaFiles){
            
            this.persistence.save(this.createSongFromPath(path));
        }
    }

    /**
     * Extracts the IDv1 tag from the MP3 file in path and returns a Song object representing it.
     * @param path
     * @return Song
     */
    private Song createSongFromPath(String path){
        MP3File music = null;

        try{
            music = new MP3File(new File(path).toURI().toString());
        }
        catch(Exception e){
            System.out.println("Error reading mp3 file");
            System.out.println(e.getMessage());
        }

        ID3v1 tag = music.getID3v1Tag();

        Song s = new Song(tag.getTitle(),
                          tag.getArtist(),
                          tag.getAlbum(),
                          Integer.valueOf(tag.getYear()),
                          tag.getSongGenre(),
                          path);

        return s;
    }

    private boolean isSongInLibrary(Song song){
        for(Song s: this.persistence.getAll()){
            if(s.equals(song))
                return true;
        }

        return false;
    }

    public List<Song> getAllMedia()
    {
        return this.persistence.getAll();
    }

    @Override
    public Song getMedia(int id)
    {
        Optional<Song> s = this.persistence.get((int) id);
        if(s.isPresent())
            return s.get();
        else
            return null;
    }
}
