/*
This class must be higher in hierarchy than all Controller classes!
 */
package org.pineapple;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.pineapple.gui.GUIModel;
import org.pineapple.gui.GUIView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        JukeBox.getInstance(); // just to instantiate the jukebox
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
        Scene scene1;

        GUIModel guiModel = new GUIModel();
        GUIView guiView = new GUIView(guiModel);

        scene1 = new Scene(guiView.getScene(), 800, 600);


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
