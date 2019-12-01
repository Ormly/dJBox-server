package org.pineapple.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;

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
        queue = FXCollections.observableArrayList(jukeBox.getSongsFromQueue());
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
}
