package org.pineapple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JukeBox jukeBox;

    /**
     * Constructor for the model
     * @param jukeBox connects a JukeBox object to the Model
     */
    public LibraryModel(JukeBox jukeBox)
    {
        this.jukeBox = jukeBox;
        this.data = FXCollections.observableArrayList(jukeBox.getAllSongs());;

    }

    /**
     * Call to update the library with actual songs
     */
    public void updateLibrary()
    {
        data = FXCollections.observableArrayList(jukeBox.getAllSongs());
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
