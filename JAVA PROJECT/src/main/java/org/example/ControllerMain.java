package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {

    @FXML
    Button detailsButton = new Button();
    @FXML
    Button addButton = new Button();
    @FXML
    Button deleteButton = new Button();
    @FXML
    Button addDummyButton = new Button();

    @FXML
    TableView<Product> tableView = new TableView<>();
    @FXML
    TableColumn<Product, Integer> idColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, String> nameColumn = new TableColumn<>();
    @FXML
    TableColumn<Product, Integer> priceColumn = new TableColumn<>();

    @FXML
    Label noSelectionLabel = new Label();

    private Stage stage;
    private Parent root;
    private Scene scene;

    Movie selected = new Movie();
    boolean bIsSelected = false;

    public void delete(ActionEvent event) {
        try {
            if(bIsSelected) {
                TableData.deleteFromDatabase(selected);
                selected = new Movie();
                bIsSelected = false;
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            noSelectionLabel.setOpacity(1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.bIsAdmin) {
            addButton.setDisable(false);
            addButton.setOpacity(1);
            deleteButton.setDisable(false);
            deleteButton.setOpacity(1);
            addDummyButton.setDisable(false);
            addDummyButton.setOpacity(1);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableData.loadDatabase();
        tableView.setItems(TableData.tableData);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selected = (Movie)newValue;
                //System.out.println(selected.name);
                bIsSelected = true;
                noSelectionLabel.setOpacity(0);
            }
        });
    }

    public void addDummy(ActionEvent event) {
        TableData.addToDatabase(new Movie(-1, "dummy", 10, "director", "publisher", "genre"));
    }

    public void switchToDetails(ActionEvent event) throws IOException {
        try {
            if(bIsSelected) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Details.fxml"));
                root = loader.load();

                ControllerDetails controllerDetails = loader.getController();
                controllerDetails.updateDetails(selected);

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            noSelectionLabel.setOpacity(1);
        }
    }

    public void switchToAdd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Add.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
