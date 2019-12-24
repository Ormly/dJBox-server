package org.pineapple.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LogInView
{
    private BorderPane scene;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button logInButton;
    private LogInModel model;

    public LogInView(LogInModel model)
    {
        this.model = model;
        configurePane();
        configureListeners();
    }

    public void configurePane()
    {
        scene = new BorderPane();
        usernameField = new TextField();
        passwordField = new PasswordField();
        logInButton = new Button("Log In");
        VBox center = new VBox(usernameField, passwordField, logInButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);
        center.setPadding(new Insets(10));
        scene.setCenter(center);
    }

    public void configureListeners()
    {
        logInButton.setOnAction(e -> model.logIn(usernameField.getText(), passwordField.getText()));
    }

    public BorderPane getScene() {
        return scene;
    }
}
