package com.example.hogwarts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        HelloApplication.stage = stage;
        HelloApplication.loadFXML("hello-view.fxml");
        stage.show();
    }



    public static FXMLLoader loadFXML(String fxml){

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = null;
        try {
            Parent root = loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        return loader;
    }



    public static void main(String[] args) {
        launch();
    }
}