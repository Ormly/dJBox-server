package org.pineapple.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pineapple.core.JukeBox;
import org.pineapple.core.Song;

import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

/**
 * LibraryModel contains all the functionality that is somehow connected to the outside world
 * All the calls to the jukebox should be done using this model
 */
public class GUIModel
{
    /**
     * List, which contains data from the database of the library
     */
    private ObservableList<Song> library;
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

        this.library = FXCollections.observableArrayList(jukeBox.getAllSongs());
        this.queue = FXCollections.observableArrayList(jukeBox.getSongsFromQueue());
        this.jukeBox.setOnQueueChanged(() -> this.updateQueue());
    }

    /**
     * Call to update the library with actual songs
     */
    public void updateLibrary()
    {
        library = FXCollections.observableArrayList(jukeBox.getAllSongs());
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
    public ObservableList<Song> getLibrary()
    {
        return library;
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
            library.clear();
            library.addAll(jukeBox.getAllSongs());

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
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
            library.clear();
            library.addAll(jukeBox.getAllSongs());

        }
        catch (NullPointerException | IOException e)
        {
            //if no song selected, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please select a song to remove");

            alert.showAndWait();
        }

    }

    /**
     * Get all IPs that are available on the system and selects one that corresponds to the wireless device
     * @return returns the wireless ip, otherwise shows that there is no ip found
     * @throws SocketException
     */
    public String getIP() throws SocketException {


        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();

            if (ni.isLoopback() || !ni.isUp())
                continue;

            Enumeration<InetAddress> e2 = ni.getInetAddresses();

            while (e2.hasMoreElements()) {
                InetAddress address = e2.nextElement();

                if (address instanceof Inet6Address)
                    continue;

                String ip = address.getHostAddress();

                if (ni.getDisplayName().contains("Wi"))
                    return ip;
            }


        }
        return "No wireless network found";
    }
}
