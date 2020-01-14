package org.pineapple.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class controls the scene.
 */
public class SceneController
{
    private Stage stage;
    private Scene mainGUI;
    private Scene logInGUI;

    /**
     * Constructor to set up the stage.
     * @param stage
     */
    public SceneController(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * Once the admin is authenticated and logged in, it changes the scene form the LogIn scene to the Main UI.
     */
    public void changelogIntoMain()
    {
        stage.setScene(mainGUI);
    }

    /**
     * Once the admin logs out, it changes the scene form the Main UI to the LogIn scene.
     */
    public void changeMainToLogIn()
    {
        stage.setScene(logInGUI);
    }

    /**
     * Attaches the required scened to the controller (has to be called when all the Views are constructed).
     * @param mainGUI
     * @param logInGUI
     */
    public void setScenes(Scene mainGUI, Scene logInGUI)
    {
        this.mainGUI = mainGUI;
        this.logInGUI = logInGUI;
    }

    /**
     * Returns the Main UI scene.
     * @return Scenee main UI
     */
    public Scene getMainGUI() {
        return mainGUI;
    }

    /**
     * Returns the LogIn scene.
     * @return Scene logIn scene
     */
    public Scene getLogInGUI() {
        return logInGUI;
    }
}
