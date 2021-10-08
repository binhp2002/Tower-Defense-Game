package com.example.towerdefence.StartUpPage;
import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;


public class StartUpPageController {

    Scene nextScene;

    public void setNextScene(Scene scene) {
        this.nextScene = scene;
    }

    @FXML
    public void handlePlayButtonAction(ActionEvent ae) {
        Stage stage = (Stage)((Node) ae.getSource()).getScene().getWindow();
        stage.setScene(this.nextScene);
    }

}
