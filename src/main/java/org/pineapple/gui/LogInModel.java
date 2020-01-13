package org.pineapple.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.pineapple.core.JukeBox;
import org.pineapple.core.exceptions.AuthenticationFailedException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * LogInModel contains all the functionality needed for an admin user to log in.
 */
public class LogInModel
{
    private SceneController sceneController;
    private JukeBox jukeBox = JukeBox.getInstance();

    /**
     * Constructor for the model.
     */
    public LogInModel(SceneController sceneController)
    {
        this.sceneController = sceneController;
    }

    /**
     * Logs the user in and changes the scene to the main UI.
     * @param userName
     * @param password
     */
    public void logIn(String userName, String password)
    {
        try
        {
            jukeBox.logInAdmin(userName, password);
            sceneController.changelogIntoMain();
        }
        catch (AuthenticationFailedException aex)
        {
            showException(aex);
        }
    }
    /**
     * Generates a new alert and displays the error passed for better error understanding.
     * @param ex
     */
    public void showException(Exception ex)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception Dialog");
        alert.setContentText("An exception has occured");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
