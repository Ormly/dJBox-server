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
    public void logIn(String userName, String password)
    {
        try
        {
            jukeBox.doAuthentication(userName, password);
            changeScene();
        }
        catch (AuthenticationFailedException aex)
        {
            aex.printStackTrace();
        }
    }

    public void changeScene()
    {
        sceneController.changelogIntoMain();
    }

}
