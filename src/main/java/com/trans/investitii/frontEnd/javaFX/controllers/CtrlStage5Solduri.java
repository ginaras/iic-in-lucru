package com.trans.investitii.frontEnd.javaFX.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CtrlStage5Solduri {

    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonOrg;
    public Button buttonBackSt0;
    public Button butonStage1Intro;
    public Button resetButton;
    public Button selectButton;
    public Label setOrg;
    public Label setProj;
    public Label setFz;
    public Button butonStage2Rapoarte;
    public Button goToStage4Pif;
    public ComboBox comboBoxButtonOrgYear;
    public ComboBox comboBoxButtonFzContract;
    public Button butonStage3Rapoarte;
    public Label labelTotalOrg;
    public Label labelTotalProj;
    public Label labelTotalFz;
    public DatePicker dataSold;

    public void comboBoxActProj ( ActionEvent actionEvent ) {
    }

    public void comboBoxFzAct ( ActionEvent actionEvent ) {
    }

    public void comboBoxActOrg ( ActionEvent actionEvent ) {
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goOnStage1Intro ( ActionEvent actionEvent ) {
    }

    public void resetAct ( ActionEvent actionEvent ) {
    }

    public void apliFiter ( ActionEvent actionEvent ) {
    }

    public void goOnStage2Rapoarte ( ActionEvent actionEvent ) {
    }

    public void goToStage4Pif ( ActionEvent actionEvent ) {
    }

    public void comboBoxActOrgYear ( ActionEvent actionEvent ) {
    }

    public void comboBoxFzContractAct ( ActionEvent actionEvent ) {
    }

    public void goOnStage3Rapoarte ( ActionEvent actionEvent ) {
    }
}
