package org.pineapple.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.pineapple.api.LibraryController;
import org.pineapple.core.JukeBox;
import org.pineapple.core.Media;

/**
 * LibraryModel contains all the functionality that is somehow connected to the outside world
 * All the calls to the jukebox should be done using this model
 */
public class LibraryModel
{
    /**
     * List, which contains data from the database of the library
     */
    private ObservableList<Media> data;

    /**
     * Actual JukeBox, through which we connect the design to the logic
     */
    //private JukeBox jukeBox;
    private LibraryController controller;

    /**
     * Constructor for the model
     * @param controller connects a JukeBox object to the Model
     */
    public LibraryModel(LibraryController controller)
    {
        this.controller = controller;
        this.data = FXCollections.observableArrayList(controller.songList());;

    }

    /**
     * Call to update the library with actual songs
     */
    public void updateLibrary()
    {
        data = FXCollections.observableArrayList(controller.songList());
    }

    /**
     * Simple getter for the List of Media
     * @return List of Media
     */
    public ObservableList<Media> getData()
    {
        return data;
    }
}
