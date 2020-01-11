package org.pineapple.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.pineapple.core.Song;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.*;



/**
 * The LibraryView is responsible for creating and filling the actual scene that will be displayed in the windows
 * It also binds even handlers for to buttons
 */
public class GUIView
{
    private BorderPane scene;
    private TableView libraryTable;
    private TableView queueTable;
    private GUIModel model;
    private Image image;
    private Stage stage;

    private FileChooser fileChooser;

    private Label songName = new Label("");
    private Label artistName = new Label("");
    private Label albumName = new Label("");

    private Button addSongButton = new Button("Add Song");
    private Button logOutButton = new Button("Log Out");
    private Button settingsButton = new Button("Settings");
    private Button removeSongButton = new Button("Remove Song");
    private Song selectedSong;

    private TextField searchField;

    /**
     * Constructor for the LibraryView
     * @param model attaches a model to get required functionality
     * @param stage required by the file chooser
     */
    public GUIView(GUIModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        configurePane();

    }

    /**
     *
     * @return the scene
     */
    public BorderPane getScene()
    {
        return scene;
    }

    /**
     * Configures the pane, by specifying all the nodes and borders
     */
    public void configurePane() {
        setUpTable();
        configureListeners();
        Label libraryLabel = new Label("Available Songs:");
        Label queueLabel = new Label("Songs in Queue:");
        String ipAddressString = "";

        try
        {
           ipAddressString = "IP Address: " + model.getIP();
        } catch (SocketException ex)
        {
            model.showException(ex);
        }

        Label ipAddress = new Label(ipAddressString);
        libraryLabel.setFont(new Font("Arial", 17));
        queueLabel.setFont(new Font("Arial", 17));

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 file", "*.mp3"));
        searchField = new TextField("search");
        image = new Image("https://lastfm.freetls.fastly.net/i/u/770x0/54010ae7c4fa4c96a1e1872a051d9ecc.jpg", 200, 200, false, false);
        ImageView imageView = new ImageView(image);

        songName.setFont(new Font("Arial", 20));
        albumName.setFont(new Font("Arial", 14));
        artistName.setFont(new Font("Arial", 17));

        songName.setWrapText(true);
        albumName.setWrapText(true);
        artistName.setWrapText(true);

        scene = new BorderPane();
        VBox leftSide = new VBox(searchField, libraryLabel, libraryTable, removeSongButton);
        leftSide.setSpacing(20);
        leftSide.setPadding(new Insets(10));
        leftSide.setMinWidth(400);

        VBox center = new VBox(imageView, songName, artistName, albumName, addSongButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);
        center.setPadding(new Insets(10));
        center.setMinWidth(400);

        HBox buttons = new HBox(settingsButton, logOutButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        VBox rightSide = new VBox(buttons, queueLabel, queueTable, ipAddress);
        rightSide.setSpacing(20);
        rightSide.setPadding(new Insets(10));
        rightSide.setMinWidth(400);

        scene.setRight(rightSide);
        scene.setLeft(leftSide);
        scene.setCenter(center);


      }

    /**
     * Sets up the table with the songs from the database
     * Executed once, when the pane is being set up
     */
    public void setUpTable()
    {
        libraryTable = new TableView();
        TableColumn songCol = new TableColumn("Song");
        songCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("title"));
        TableColumn artistCol = new TableColumn("Artist");
        artistCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("artist"));
        TableColumn albumCol = new TableColumn("Album");
        albumCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("album"));
        libraryTable.setItems(model.getLibrary());
        libraryTable.getColumns().addAll(songCol, artistCol, albumCol);

        queueTable = new TableView();
        TableColumn songColQueue = new TableColumn("Song");
        songColQueue.setCellValueFactory(
                new PropertyValueFactory<Song, String>("title"));
        TableColumn artistColQueue = new TableColumn("Artist");
        artistColQueue.setCellValueFactory(
                new PropertyValueFactory<Song, String>("artist"));
        TableColumn albumColQueue = new TableColumn("Album");
        albumColQueue.setCellValueFactory(
                new PropertyValueFactory<Song, String>("album"));
        queueTable.setItems(model.getQueue());
        queueTable.getColumns().addAll(songColQueue, artistColQueue, albumColQueue);


    }


    /**
     * Attaches event handlers and listeners to buttons and the table
     */
    public void configureListeners()
    {
        addSongButton.setOnAction(e -> model.chooseFile(stage, fileChooser));
        removeSongButton.setOnAction(e -> model.removeSong(selectedSong));
        logOutButton.setOnAction(e -> model.logOut());

        libraryTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                try
                {
                    int index = libraryTable.getSelectionModel().getSelectedIndex();
                    selectedSong = (Song) libraryTable.getItems().get(index);

                    songName.setText(selectedSong.getTitle());
                    artistName.setText(selectedSong.getArtist());
                    albumName.setText(selectedSong.getAlbum());
                } catch (IndexOutOfBoundsException e)
                {
                    //Occurs only when an empty field is clicked in the table
                }

            }
        });
    }


}
