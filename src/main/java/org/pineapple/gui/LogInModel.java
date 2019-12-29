package org.pineapple.gui;

import org.pineapple.core.JukeBox;
import org.pineapple.core.exceptions.AuthenticationFailedException;

public class LogInModel
{

    private SceneController sceneController;
    private JukeBox jukeBox = JukeBox.getInstance();

    public LogInModel(SceneController sceneController)
    {
        this.sceneController = sceneController;
    }

    /**
     * Logs the user in and changes the scene to the main UI
     * @param userName
     * @param password
     */
    public void logIn(String userName, String password)
    {
        try
        {
            jukeBox.doAuthentication(userName, password);
            sceneController.changelogIntoMain();
        }
        catch (AuthenticationFailedException aex)
        {
            aex.printStackTrace();
        }
    }
}
