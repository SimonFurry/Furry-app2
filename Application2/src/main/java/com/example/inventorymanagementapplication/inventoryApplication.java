package com.example.inventorymanagementapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class inventoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(inventoryApplication.class.getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller)loader.getController();
        controller.init(stage);


        FXMLLoader fxmlLoader = new FXMLLoader(inventoryApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}