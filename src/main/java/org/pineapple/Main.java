/*
This class must be higher in hierarchy than all Controller classes!
 */
package org.pineapple;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.pineapple.core.JavaFXPlayer;
import org.pineapple.core.JukeBox;
import org.pineapple.core.MediaLibrary;
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
    public JukeBox jukeBox()
    {
        return new JukeBox(new MediaLibrary("/dev/null"),
                           new JavaFXPlayer());
    }

    /*
    Starts all Spring stuff (REST)
     */
    @Override
    public void init()
    throws Exception
    {
        // saving context so we can kill spring when application is closed
        this.context = SpringApplication.run(Main.class);
    }

    /*
    Starts all JavaFX stuff
     */
    public static void main(String[] args)
    {
        launch();
    }

    /*
    Starts all JavaFX stuff
     */
    @Override
    public void start(Stage primaryStage)
    throws Exception
    {
        AnchorPane rootNode = new AnchorPane();

        primaryStage.setScene(new Scene(rootNode, 800, 600));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

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
