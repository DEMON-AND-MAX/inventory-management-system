package org.example;
import java.io.*;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static boolean bIsAdmin;

    public static void main(String[] args) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file.txt", false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("input.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputDevice inputDevice = new InputDevice(System.in);
        OutputDevice outputDevice = new OutputDevice(System.out);
        App App = new App(inputDevice, outputDevice);

        if(Objects.equals(args[0], "admin")) bIsAdmin = true;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}