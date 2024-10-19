package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // This method is called when the JavaFX application starts
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the UI layout
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);

        // Add a CSS stylesheet to the scene for UI styling
        scene.getStylesheets().add("styleSheet.css");
        stage.setScene(scene);
        stage.setTitle("Calculator");

        // Display the application window
        stage.show();
    }
}

