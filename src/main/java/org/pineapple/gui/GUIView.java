package org.pineapple.gui;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.pineapple.core.Song;


/**
 * The LibraryView is responsible for creating and filling the actual scene that will be displayed in the windows
 * It also binds even handlers for to buttons
 */
public class GUIView
{
    private GridPane scene;
    private TableView libraryTable;
    private TableView queueTable;
    private GUIModel model;
    private Image image;

    private TableColumn songCol;
    private TableColumn artistCol;
    private TableColumn albumCol;

    private Label songName = new Label("");
    private Label artistName = new Label("");
    private Label albumName = new Label("");

    private Button addSongButton = new Button("Add Song");

    private TextField searchField;

    /**
     * Constructor for the LibraryView
     * @param model attaches a model to get required functionality
     */
    public GUIView(GUIModel model)
    {
        this.model = model;
        configurePane();
    }

    /**
     *
     * @return the scene
     */
    public GridPane getScene()
    {
        return scene;
    }

    /**
     * Configures the pane, by specifying all the nodes and borders
     */
    public void configurePane()
    {
        setUpTable();
        configureListeners();
        searchField = new TextField("search");
        image = new Image("file:album_cover.jpg", 150, 150, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);


        scene = new GridPane();

        for (int i = 0; i < 20; i++)
        {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 20);
            scene.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < 20; i++)
        {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / 20);
            scene.getRowConstraints().add(rowConst);
        }


        scene.setHgap(8);
        scene.setVgap(8);
        scene.setPadding(new Insets(7));

        scene.add(searchField, 0, 0, 9, 1);
        scene.add(libraryTable, 0, 1, 9, 17);
        scene.add(queueTable, 14, 1, 19, 17);
        scene.add(addSongButton, 10, 16, 3, 1);
    }

    /**
     * Sets up the table with the songs from the database
     * Executed once, when the panes is being set up
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
        libraryTable.setItems(model.getLibray());
        libraryTable.getColumns().addAll(songCol, artistCol, albumCol);

        queueTable = new TableView();
        TableColumn songColQueue = new TableColumn("Song");
        songCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("title"));
        TableColumn artistColQueue = new TableColumn("Artist");
        artistCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("artist"));
        TableColumn albumColQueue = new TableColumn("Album");
        albumCol.setCellValueFactory(
                new PropertyValueFactory<Song, String>("album"));
        queueTable.setItems(model.getQueue());
        queueTable.getColumns().addAll(songColQueue, artistColQueue, albumColQueue);


    }

    /**
     * Attaches eventhandlers and listeners to buttons and the table
     */
    public void configureListeners()
    {
        addSongButton.setOnAction(e -> System.out.println("Text from button"));

        libraryTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                try
                {
                    int index = libraryTable.getSelectionModel().getSelectedIndex();
                    Song object = (Song) libraryTable.getItems().get(index);

                    songName.setText(object.getTitle());
                    artistName.setText(object.getArtist());
                    albumName.setText(object.getAlbum());
                } catch (IndexOutOfBoundsException e)
                {

                }

            }
        });
    }
}
