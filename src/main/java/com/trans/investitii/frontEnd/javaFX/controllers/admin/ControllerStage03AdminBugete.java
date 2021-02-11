package com.trans.investitii.frontEnd.javaFX.controllers.admin;


import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerStage03AdminBugete implements Initializable {


    public ComboBox comboOrg;
    public ComboBox comboOrgType;
    public ComboBox comboBProj;
    public ComboBox comboBProjType;
    public ComboBox comboBContract;
    public ComboBox comboBContractType;
    public Button buttonAplicaBugetOrg;
    public Button buttonAplicaBugetProj;
    public Button buttonAplicaBugetContract;
    public Button butonStage0;
    public CheckBox checkOrg;
    public CheckBox checkProj;
    public CheckBox checkContract;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();

    public ControllerStage03AdminBugete () throws SQLException {
    }

    public void comboOrgAction ( ActionEvent event ) {
    }

    public void comboOrgTipeAction ( ActionEvent event ) {
    }

    public void comboBProjAction ( ActionEvent event ) {
    }

    public void comboBProjTypeAction ( ActionEvent event ) {
    }

    public void comboBContractAction ( ActionEvent event ) {
    }

    public void comboBContractTypeAction ( ActionEvent event ) {
    }

    public void buttonAplicaBugetOrgAction ( ActionEvent event ) {
    }

    public void buttonAplicaBugetProjAction ( ActionEvent event ) {
    }

    public void buttonAplicaBugetContractAction ( ActionEvent event ) {
    }

    public void goOnStage0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void checkOrgAction ( ActionEvent event ) {
        if (checkOrg.isSelected()){
            comboOrgType.setDisable( false );
            buttonAplicaBugetOrg.setDisable( false );
        }
        if (!checkOrg.isSelected()){
            comboOrgType.setDisable( true );
            buttonAplicaBugetOrg.setDisable( true );
        }
    }

    public void checkProjAction ( ActionEvent event ) {
        if (checkProj.isSelected()){
            comboBProjType.setDisable( false );
            buttonAplicaBugetProj.setDisable( false );
        }else{
            comboBProjType.setDisable( true );
            buttonAplicaBugetProj.setDisable( true );
        }
    }

    public void checkContractAction ( ActionEvent event ) {
        if (checkContract.isSelected()){
            comboBContractType.setDisable( false );
            buttonAplicaBugetContract.setDisable( false );
        }else{
            comboBContractType.setDisable( true );
            buttonAplicaBugetContract.setDisable( true );
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        List<String> myListContract = null;
        List<String> myListProj = null;
        List<String> myListOrg = null;

        try {
            myListContract = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/contract" )) );
            myListOrg = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/org" )) );
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" )) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> contracteActive = myListContract.stream()
                .filter( contract -> !contract.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBContract.setItems( FXCollections.observableArrayList(contracteActive));

        List<String> orgActive = myListOrg.stream()
                .filter( org -> !org.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboOrg.setItems( FXCollections.observableArrayList(orgActive));

        List<String> projActiv = myListProj.stream()
                .filter( proj -> !proj.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBProj.setItems( FXCollections.observableArrayList(projActiv));

        comboOrgType.getItems().addAll( "Buget Initial", "Rectificare" );
        comboBProjType.getItems().addAll( "Buget Initial", "Rectificare" );
        comboBContractType.getItems().addAll( "Buget Initial", "Rectificare" );

        comboBProjType.setDisable( true );
        comboOrgType.setDisable( true );
        comboBContractType.setDisable( true );
        buttonAplicaBugetContract.setDisable( true );
        buttonAplicaBugetOrg.setDisable( true );
        buttonAplicaBugetProj.setDisable( true );
    }
}
