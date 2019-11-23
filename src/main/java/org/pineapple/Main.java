/*
This class must be higher in hierarchy than all Controller classes!
 */
package org.pineapple;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.pineapple.api.LibraryController;
import org.pineapple.core.JavaFXPlayer;
import org.pineapple.core.JukeBox;
import org.pineapple.core.MediaLibrary;
import org.pineapple.gui.LibraryModel;
import org.pineapple.gui.LibraryView;
import org.pineapple.gui.QueueModel;
import org.pineapple.gui.QueueView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main extends Application
{
    private ConfigurableApplicationContext context;

    /*
    This lets Spring wire this object in different places around the application (e.g REST controllers)
     */
    @Bean
    public JukeBox jukeBox(){
        return new JukeBox(new MediaLibrary("/dev/null"),
                           new JavaFXPlayer());
    }

    /*
    Starts all Spring stuff (REST)
     */
    @Override
    public void init() throws Exception{
        // saving context so we can kill spring when application is closed
        this.context = SpringApplication.run(Main.class);
    }

    /*
    Starts all JavaFX stuff
     */
    public static void main(String[] args){
        launch();
    }

    /*
    Starts all JavaFX stuff
     */
    @Override
    public void start(Stage primaryStage)
    throws Exception
    {
        Scene scene1, scene2;
        Button changeSceneButtonQueue = new Button("Library");
        Button changeSceneButtonLibrary = new Button("Queue");

        LibraryController libraryController = new LibraryController();
        LibraryModel libraryModel = new LibraryModel(libraryController);
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

    @Override
    public void stop()
    throws Exception
    {
        // End spring boot when JavaFX is closed
        if(this.context != null){
            this.context.close();
        }
        super.stop();
    }
}
