/*
This class must be higher in hierarchy than all Controller classes!
 */
package org.pineapple;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import org.pineapple.core.JavaFXPlayer;
import org.pineapple.core.JukeBox;
import org.pineapple.core.Media;
import org.pineapple.core.MediaLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main extends Application
{
    private ConfigurableApplicationContext context;

    /**
     * Starts all Spring stuff (REST)
     * @throws Exception
     */
    @Override
    public void init()
    throws Exception
    {
        // saving context so we can kill spring when application is closed
        this.context = SpringApplication.run(Main.class);
    }

    /**
     *     Starts all JavaFX stuff
     * @param args
     */
    public static void main(String[] args)
    {
        launch();
    }

    /**
     * Starts all JavaFX stuff
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage)
    throws Exception
    {
        Scene scene1, scene2;
        Button changeSceneButtonQueue = new Button("Library");
        Button changeSceneButtonLibrary = new Button("Queue");


        LibraryModel libraryModel = new LibraryModel(jukeBox());
        LibraryView libraryView = new LibraryView(libraryModel, changeSceneButtonLibrary);

        QueueModel queueModel = new QueueModel();
        QueueView queueView = new QueueView(queueModel, changeSceneButtonQueue);


        scene2 = new Scene(queueView.getScene(), 800, 600);
        scene1 = new Scene(libraryView.getScene(), 800, 600);

        //Changing between different windows should be done atleast in main, because both scenes are known here
        changeSceneButtonQueue.setOnAction(e -> primaryStage.setScene(scene1));
        changeSceneButtonLibrary.setOnAction(e -> primaryStage.setScene(scene2));

        primaryStage.setScene(scene1);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Kills Spring once JavaFX GUI has been closed.
     * @throws Exception
     */
    @Override
    public void stop()
    throws Exception
    {
        // End spring boot when JavaFX is closed
        if(this.context != null)
        {
            this.context.close();
        }
        super.stop();
    }
}
