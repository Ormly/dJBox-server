package org.pineapple.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController
{
    private Stage stage;

    private Scene mainGUI;
    private Scene logInGUI;

    public SceneController(Stage stage)
    {
        this.stage = stage;
    }

    public void changelogIntoMain()
    {
        stage.setScene(mainGUI);
    }

    public void changeMainToLogIn()
    {
        stage.setScene(logInGUI);
    }

    /**
     * Attaches the required scened to the controller (has to be called when all the Views are constructed)
     * @param mainGUI
     * @param logInGUI
     */
    public void setScenes(Scene mainGUI, Scene logInGUI)
    {
        this.mainGUI = mainGUI;
        this.logInGUI = logInGUI;
    }

    public Scene getMainGUI() {
        return mainGUI;
    }

    public Scene getLogInGUI() {
        return logInGUI;
    }
}
