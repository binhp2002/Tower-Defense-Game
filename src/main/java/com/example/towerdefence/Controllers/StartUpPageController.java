package com.example.towerdefence.Controllers;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;


public class StartUpPageController {

    private Scene nextScene;

    public Scene getNextScene() {
        return this.nextScene;
    }

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    @FXML
    public void handlePlayButtonAction(ActionEvent ae) {
        Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(this.nextScene);
        stage.setTitle("Game Configuration");
    }

}
