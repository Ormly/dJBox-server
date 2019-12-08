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

    public void chooseFile(Stage stage, FileChooser fileChooser)
    {
        File selectedFile = fileChooser.showOpenDialog(stage);
        try
        {
            Files.copy(selectedFile.toPath(), Paths.get("C:\\Users\\Public\\Music\\" + selectedFile.getName()));
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

    public void removeSong(Song songToRemove)
    {
        try
        {
            System.out.println(songToRemove.getTitle());
        } catch (NullPointerException e)
        {
            System.out.println("No song selected");
        }

    }
}
