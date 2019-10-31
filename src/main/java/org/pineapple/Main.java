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

@SpringBootApplication
public class Main extends Application
{
    /*
    Starts all Spring stuff (REST)
     */
    @Override
    public void init() throws Exception{
        SpringApplication.run(Main.class);
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
}
