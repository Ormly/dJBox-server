/*
This class must be higher in hierarchy than all Controller classes!
 */
package org.pineapple;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application
{
    private ConfigurableApplicationContext context;
    /*
    Starts all Spring stuff (REST)
     */
    @Override
    public void init() throws Exception{
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
        if(this.context != null){
            this.context.close();
        }
        super.stop();
    }
}
