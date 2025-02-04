package org.pineapple.core;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import javafx.scene.media.Media;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import org.pineapple.core.exceptions.SongNotFoundException;
import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.IMultimediaInfoProvider;
import org.pineapple.db.interfaces.DAO;
import org.pineapple.db.SongDAO;
import org.pineapple.core.HTTPAlbumInfo;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class implements the IMediaLibrary interface to provide Song objects to the Jukebox.
 */
public class MediaLibrary implements IMediaLibrary
{
    private static boolean isInstantiated = false;
    private DAO<Song> persistence;
    private String pathToMediaDir;
    private IMultimediaInfoProvider infoProvider;

    /**
     * Generates a new Media library.
     * @param pathToMediaDir
     * @throws UnsupportedOperationException
     */
    public MediaLibrary(String pathToMediaDir)
    {
        // don't allow multiple instances of MediaLibrary
        if(MediaLibrary.isInstantiated)
            throw new UnsupportedOperationException("MediaLibrary can only be instantiated once!");

        this.persistence = new SongDAO();
        this.pathToMediaDir = pathToMediaDir;
        this.infoProvider = new MP3MetadataProvider();

        this.init();

        MediaLibrary.isInstantiated = true;
    }

    /**
     * Initializes the library with the media files found in "pathToMediaDir".
     */
    private void init()
    {
        List<String> pathsToMediaFiles = this.findAllSongsInMediaDir();
        this.clearStaleSongsFromPersistence(pathsToMediaFiles);
        this.addAllSongsToLibrary(pathsToMediaFiles);
    }

    /**
     * Searches for all songs in the path provided when generated.
     * Checks all .mp3 files in the hierarchically lower directories and adds them to the list.
     * @return List of path strings
     * @throws IOException
     */
    private List<String> findAllSongsInMediaDir()
    {
        List<String> pathsToMediaFiles = new ArrayList<>();

        //find all mp3 files in media directory
        try
        {
            Files.find(Paths.get(pathToMediaDir),
                       Integer.MAX_VALUE,
                       (filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.toString().endsWith(".mp3"))
                    .forEach((x) -> pathsToMediaFiles.add(x.toString()));
        } catch(IOException ex)
        {
            System.out.println("Error traversing media directory.");
            System.out.println(ex.getMessage());
        }

        return pathsToMediaFiles;
    }

    /**
     * Clears out the stale entries in the DB which are not in the media library folder.
     * @param paths
     */
    private void clearStaleSongsFromPersistence(List<String> paths){
        List<Song> songsInPersistence = this.persistence.getAll();

        for(Song s: songsInPersistence){
            if(!paths.contains(s.getPathToFile())){
                this.persistence.delete(s);
            }
        }
    }

    /**
     * Extracts the IDv1 tag from the MP3 file in path and returns a Song object representing it.
     * Returns null if data could not be extracted.
     * @param path
     * @return Song
     */
    private Song createSongFromPath(String path)
    {
        Song s = null;
        try
        {
           s =  (Song) this.infoProvider.getMediaFileMetadata(new File(path));
        } catch(Exception e)
        {
            System.out.println("Error reading mp3 file" + path);
            System.out.println(e.getMessage());
        }

        return s;
    }

    /**
     * Can be used to add a media file with path to the library.
     * @param path
     */
    public void addSongToLibrary(String path)
    {
        // TODO: first copy the file over to media directory
        this.addAllSongsToLibrary(new ArrayList<>(List.of(path)));
    }

    /**
     * Deletes the passed song in the DB.
     * @param songToDelete
     */
    public void deleteSongFromLibrary(Song songToDelete)
    {
        persistence.delete(songToDelete);
    }

    /**
     * Can be used to add a list of media files to the media library.
     * @param paths = a list of media file paths
     */
    public void addAllSongsToLibrary(List<String> paths)
    {
        List<Song> currentLibrary = this.getAllMedia();

        for(String path : paths)
        {
            Song s = this.createSongFromPath(path);
            System.out.println("checking if: " + s.getTitle() + " is in library");
            if(!currentLibrary.contains(s))
            {
                System.out.println("it's not! adding.");
                this.persistence.save(s);
            }
        }
    }

    /**
     * Gets a list of all songs in currently in the library.
     * @return List<Song>
     */
    public List<Song> getAllMedia()
    {
        return this.persistence.getAll();
    }

    /**
     * Gets a song with id from the media library.
     * @param id
     * @return Song
     * @throws SongNotFoundException
     */
    @Override
    public Song getMedia(int id) throws SongNotFoundException
    {
        Optional<Song> s = this.persistence.get(id);
        if(s.isPresent())
            return s.get();
        else
            throw new SongNotFoundException(id);
    }
}
