package org.pineapple.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The LogInView is responsible for creating and filling the actual scene that will be displayed when logging in.
 * It also binds even handlers for to buttons.
 */
public class LogInView
{
    private BorderPane scene;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button logInButton;
    private LogInModel model;

    /**
     * Constructor for the LogInView.
     * @param model
     */
    public LogInView(LogInModel model)
    {
        this.model = model;
        configurePane();
        configureListeners();
    }

    /**
     * Configures the pane, by specifying all the nodes and borders.
     */
    public void configurePane()
    {
        scene = new BorderPane();
        usernameField = new TextField();
        passwordField = new PasswordField();
        logInButton = new Button("Log In");
        usernameField.setMaxWidth(400);
        passwordField.setMaxWidth(400);
        VBox center = new VBox(new Label("Username"), usernameField, new Label("Password"), passwordField, logInButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);
        center.setPadding(new Insets(10));

        scene.setCenter(center);
    }

    /**
     * Attaches event handlers and listeners to the button.
     */
    public void configureListeners()
    {
        logInButton.setOnAction(e -> model.logIn(usernameField.getText(), passwordField.getText()));
    }

    /**
     * Returns the scene.
     * @return BorderPane scene
     */
    public BorderPane getScene() {
        return scene;
    }
}
