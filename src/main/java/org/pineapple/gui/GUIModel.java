package org.pineapple.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * LibraryModel contains all the functionality that is somehow connected to the outside world
 * All the calls to the jukebox should be done using this model
 */
public class GUIModel
{
    /**
     * List, which contains data from the database of the library
     */
    private ObservableList<Song> libray;
    private ObservableList<Song> queue;

    /**
     * Actual JukeBox, through which we connect the design to the logic
     */
    //private JukeBox jukeBox;
    private JukeBox jukeBox = JukeBox.getInstance();

    /**
     * Constructor for the model
     */
    public GUIModel()
    {

        this.libray = FXCollections.observableArrayList(jukeBox.getAllSongs());
        this.queue = FXCollections.observableArrayList(jukeBox.getSongsFromQueue());
        this.jukeBox.setOnQueueChanged(() -> this.updateQueue());
    }

    /**
     * Call to update the library with actual songs
     */
    public void updateLibrary()
    {
        libray = FXCollections.observableArrayList(jukeBox.getAllSongs());
    }

    public void updateQueue()
    {
        System.out.println("updateQueue called!");
        queue.clear();
        queue.addAll(jukeBox.getSongsFromQueue());
//        queue = FXCollections.observableArrayList(jukeBox.getSongsFromQueue());
    }

    /**
     * Simple getter for the List of Media
     * @return List of Media
     */
    public ObservableList<Song> getLibray()
    {
        return libray;
    }

    public ObservableList<Song> getQueue() {
        return queue;
    }

    /**
     * Opens the file chooser, where a song (.mp3) will be selected, added to the local directory and to the database
     * @param stage needs to be passed to use the file chooser
     * @param fileChooser Chooses the which will be added to the library
     */
    public void chooseFile(Stage stage, FileChooser fileChooser)
    {
        File selectedFile = fileChooser.showOpenDialog(stage);
        try
        {
            //Copy the selected song to the directory where all the songs are stored
            Files.copy(selectedFile.toPath(), Paths.get("media\\" + selectedFile.getName()));

            //add the song to the db
            jukeBox.addSong("media\\" + selectedFile.getName());

            //Update the library table with new contents
            libray.clear();
            libray.addAll(jukeBox.getAllSongs());

        }
        catch (IOException ex)
        {
            //ex.printStackTrace();
        }
        catch (NullPointerException ex)
        {
            System.out.println("No file chosen");
        }
    }

    /**
     * Selected song from the library is deleted from the db and directory
     * @param songToRemove specifies which song to delete
     */
    public void removeSong(Song songToRemove)
    {
        try
        {
            //delete the song locally
            Files.deleteIfExists(Paths.get(songToRemove.getPathToFile()));

            //delete the song from the database
            jukeBox.deleteSong(songToRemove);

            //update the library table
            libray.clear();
            libray.addAll(jukeBox.getAllSongs());

        } catch (NullPointerException | IOException e)
        {
            System.out.println("No song selected");
        }

    }
}
