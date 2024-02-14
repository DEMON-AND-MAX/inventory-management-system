package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerAdd {
    @FXML
    Button backButton = new Button();
    @FXML
    Button saveButton = new Button();

    @FXML
    TextField nameTextField = new TextField();
    @FXML
    TextField priceTextField = new TextField();
    @FXML
    TextField directorTextField = new TextField();
    @FXML
    TextField publisherTextField = new TextField();
    @FXML
    TextField genreTextField = new TextField();

    @FXML
    Label wrongInputLabel = new Label();

    private Stage stage;
    private Parent root;
    private Scene scene;

    Movie newMovie = new Movie();
    boolean bSaved = false;

    public void save(ActionEvent event) {
        boolean bContinue = true;
        try {
            newMovie = new Movie(
                -1,
                nameTextField.getText(),
                Integer.parseInt(priceTextField.getText()),
                directorTextField.getText(),
                publisherTextField.getText(),
                genreTextField.getText()
            );
            if(Objects.equals(nameTextField.getText(), "")) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            bContinue = false;
            wrongInputLabel.setOpacity(1);
        }
        if(bContinue) {
            bSaved = true;
            try {
                switchToMain(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void switchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        root = loader.load();

        if(bSaved) {
            TableData.addToDatabase(newMovie);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}