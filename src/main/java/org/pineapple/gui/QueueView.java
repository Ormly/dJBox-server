package org.pineapple.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class QueueView
{
    private GridPane scene;
    private QueueModel model;
    private Button changeToLibrary;
    private TextField searchField;

    public QueueView(QueueModel model, Button changeToLibrary)
    {
        this.model = model;
        this.changeToLibrary = changeToLibrary;
        configurePane();
    }

    public GridPane getScene()
    {
        return scene;
    }

    public void configurePane()
    {

        searchField = new TextField("search");
        scene = new GridPane();
        for (int i = 0; i < 20; i++)
        {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / 20);
            scene.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < 20; i++)
        {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / 20);
            scene.getRowConstraints().add(rowConst);
        }
        scene.setHgap(8);
        scene.setVgap(8);
        scene.setPadding(new Insets(7));
        //addSongButton.setOnAction(e -> System.out.println("Text from button"));
        scene.add(searchField, 0, 0, 8, 1);
        scene.add(changeToLibrary, 18, 0, 2, 1);
    }
}
