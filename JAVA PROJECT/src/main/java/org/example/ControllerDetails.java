package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDetails {
    @FXML
    Button backButton = new Button();

    @FXML
    Label idLabel = new Label();
    @FXML
    Label nameLabel = new Label();
    @FXML
    Label priceLabel = new Label();
    @FXML
    Label directorLabel = new Label();
    @FXML
    Label publisherLabel = new Label();
    @FXML
    Label genreLabel = new Label();

    private Stage stage;
    private Parent root;
    private Scene scene;

    public void updateDetails(Movie selected) {
        //System.out.println(selected);
        idLabel.setText("ID: " + selected.id);
        nameLabel.setText("Name: " + selected.name);
        priceLabel.setText("Price: " + selected.price);
        publisherLabel.setText("Publisher: " + selected.publisher);
        directorLabel.setText("Director: " + selected.director);
        genreLabel.setText("Genre: " + selected.genre);
    }

    public void switchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
