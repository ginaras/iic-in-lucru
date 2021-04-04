package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage4PIF implements Initializable {
    public ComboBox comboBoxButtonOrg;
    public ComboBox comboBoxButtonProj;
    public TableView <Investitii> tabelFacturiPIF;
    public TableColumn <Investitii, String> furnizorColumn;
    public TableColumn <Investitii, String> nrFacturaColumn;
    public TableColumn <Investitii, String> contContabilColumn;
    public TableColumn <Investitii, String> valoareColumn;
    public TableColumn  <Investitii, String> nrCrtColumn;


    public Label totalProiect;
    public Label totalPIF;
    public Label labelNrCrt;
    public Label labelNrCrt1;
    public Label labelNrCrt2;
    public Label labelNrCrt3;
    public Label labelNrCrt4;
    public Label labelNrCrt5;
    public Label labelNrCrt6;
    public Label labelNrCrt7;
    public Label labelNrCrt8;
    public Label labelNrCrt9;
    public Label labelNrCrt10;
    public Label labelNrCrt11;
    public Label labelNrCrt12;
    public Label labelNrCrt13;
    public Label labelNrCrt14;
    public Label labelNrCrt15;
    public Label labelNrCrt16;
    public Label labelNrCrt17;
    public Label labelNrCrt18;
    public Label labelNrCrt19;
    public Label labelNrCrt20;
    public Label labelNrCrt21;
    public Label labelNrCrt22;
    public Label labelNrCrt23;
    public Label labelNrCrt24;
    public Label labelNrCrt25;
    public Label labelNrCrt26;
    public Label labelNrCrt27;
    public Label labelNrCrt28;
    public Label labelNrCrt29;

    public Label labelNrCrtReturn;

    public TableView <Investitii> tabelFinalPif;
    public TableColumn <Investitii, String>  columnFurnizorPif;
    public TableColumn <Investitii, String> columnFacturaPif;
    public TableColumn <Investitii, String> columnValoarePif;
    public TableColumn <Investitii, String> columnNrCrtPif;

    public Button buttonTotalPif;
    public Button buttonReturFactura;

    public Button butonStage0;
    public Button butonStage1Intro;
    public Button butonStage2Rapoarte;
    public Button butonStage3RapoarteInv;
    public TextField textFieldValoarePIF;
    public TextField textFieldNrPVR;
    public Button buttonPIF;
    public Button buttonValidare;

    public ObservableList<Investitii> tabelFacturiDePIF;
    public ObservableList<Investitii> tabelFinalDePif2;
    public Button buttonReset;
    public DatePicker dataPIF;


    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();

    public CtrlStage4PIF () throws SQLException {
    }

    public void goOnStage0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goOnStage1Intro ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void backOnStage2Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnStage3RapoarteInv ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void comboBoxActOrg ( ActionEvent actionEvent ) throws SQLException {
        if (comboBoxButtonOrg!=null){
            comboBoxButtonProj.setDisable( false );

        String selectProj = "SELECT nrProiect FROM invTbl WHERE org ='"+comboBoxButtonOrg.getValue()+"'";
        List<String> myListProj =new ArrayList<>();

        Statement stm = connection.createStatement();
        ResultSet rsProjOrg = stm.executeQuery( selectProj );

        try{
            while (rsProjOrg.next()) {
                myListProj.add( rsProjOrg.getString( "nrProiect"));
            }
            List<String> noDuplicatesListProj = myListProj.stream().distinct().collect( Collectors.toList());
             comboBoxButtonProj.setItems( FXCollections.observableList( noDuplicatesListProj ) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }
    }

    public void comboBoxActProj ( ActionEvent actionEvent ) throws SQLException {

        String totalProiectSql = "SELECT ROUND(SUM(valoare),2) as 'totalProiect' FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"'";
        String selectProj = "SELECT * FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"'";

        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        nrFacturaColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareColumn.setCellValueFactory( new PropertyValueFactory<>( "valoare" ) );
        contContabilColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        nrCrtColumn.setCellValueFactory( new PropertyValueFactory<>( "nrCrt" ) );

        tabelFacturiDePIF = FXCollections.observableArrayList();
        ResultSet rsProj =stm.executeQuery( selectProj  );
        try{
            while (rsProj.next()){
                tabelFacturiDePIF.add( new Investitii(
                        rsProj.getString( "furnizor" ),
                        rsProj.getString( "nrFactura" ),
                        rsProj.getString( "contInv" ),
                        rsProj.getString( "valoare" ),
                        rsProj.getInt( "nrCrt" )
                        ) );
            }
            tabelFacturiPIF.setItems( tabelFacturiDePIF );

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        ResultSet rsProjTotal= stm.executeQuery( totalProiectSql );
        double totalProiectValue=0;
        try {
            while (rsProjTotal.next()){
                totalProiectValue=rsProjTotal.getDouble( "totalProiect" );
            }
            totalProiect.setText( String.valueOf( totalProiectValue ) );
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        tabelFacturiPIF.setEditable( true );

        String selectOrg = "SELECT org FROM invTbl";
        List<String> myListOrg =new ArrayList<>();

        ResultSet rsOrg = null;
        try {
            rsOrg = stm.executeQuery( selectOrg );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            while (rsOrg.next()) {
                myListOrg.add( rsOrg.getString( "org"));
            }
            List<String> noDuplicatesList = myListOrg.stream().distinct().collect(Collectors.toList());
                comboBoxButtonOrg.setItems(FXCollections.observableList( noDuplicatesList ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        comboBoxButtonProj.setDisable( true );
        buttonReturFactura.setDisable( true );
        buttonReturFactura.disableProperty().bind( Bindings.isEmpty(tabelFinalPif.getSelectionModel().getSelectedItems()));  //disable daca nu e sel o linie de tabel
        buttonPIF.setDisable( true );

        tabelFinalPif.setPlaceholder(new Label("Nici o factura de PIF-UIT"));
        tabelFacturiPIF.setPlaceholder(new Label("Nici o factura de PIF-UIT"));
    }

    public void clickedOnTableFacturiDePif ( MouseEvent event ) throws SQLException {
        tabelFinalPif.setEditable( true );
        columnValoarePif.setEditable( true );
        comboBoxButtonOrg.setDisable( true );
        comboBoxButtonProj.setDisable( true );

        Investitii selectValoare = tabelFacturiPIF.getSelectionModel().getSelectedItem();

        if (labelNrCrt.getText().equals( "" ) && selectValoare != null){
            labelNrCrt.setText( selectValoare.getNrCrt().toString() );
        }
        else if (!labelNrCrt.getText().equals( "" ) && labelNrCrt1.getText().equals( "" )&& selectValoare != null){
            labelNrCrt1.setText( selectValoare.getNrCrt().toString() );
        }
        else if (!labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && labelNrCrt2.getText().equals( "" ) && selectValoare != null){
            labelNrCrt2.setText( selectValoare.getNrCrt().toString() );
        }
        else if (!labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && labelNrCrt3.getText().equals( "" ) && selectValoare != null){
            labelNrCrt3.setText( selectValoare.getNrCrt().toString() );
        }
        else if (!labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && labelNrCrt4.getText().equals( "" )&& selectValoare != null){
            labelNrCrt4.setText( selectValoare.getNrCrt().toString() );
        }
        else if (!labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && labelNrCrt5.getText().equals( "" ) && selectValoare != null){
            labelNrCrt5.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && labelNrCrt6.getText().equals( "" ) ){
            labelNrCrt6.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && labelNrCrt7.getText().equals( "" ) ){
            labelNrCrt7.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && labelNrCrt8.getText().equals( "" ) ){
            labelNrCrt8.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" ) && labelNrCrt9.getText().equals( "" )){
            labelNrCrt9.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" ) && !labelNrCrt9.getText().equals( "" )&& labelNrCrt10.getText().equals( "" )){
            labelNrCrt10.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && labelNrCrt11.getText().equals( "" ) ){
            labelNrCrt11.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && labelNrCrt12.getText().equals( "" ) ){
            labelNrCrt12.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && labelNrCrt13.getText().equals( "" )){
            labelNrCrt13.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && labelNrCrt14.getText().equals( "" )){
            labelNrCrt14.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && labelNrCrt15.getText().equals( "" )){
            labelNrCrt15.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && labelNrCrt16.getText().equals( "" )){
            labelNrCrt16.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && labelNrCrt17.getText().equals( "" )){
            labelNrCrt17.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && labelNrCrt18.getText().equals( "" )){
            labelNrCrt18.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && labelNrCrt19.getText().equals( "" )){
            labelNrCrt19.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && labelNrCrt20.getText().equals( "" )){
            labelNrCrt20.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && labelNrCrt21.getText().equals( "" )){
            labelNrCrt21.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && labelNrCrt22.getText().equals( "" )){
            labelNrCrt22.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && labelNrCrt23.getText().equals( "" )){
            labelNrCrt23.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && labelNrCrt24.getText().equals( "" )){
            labelNrCrt24.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && labelNrCrt25.getText().equals( "" )){
            labelNrCrt25.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && !labelNrCrt25.getText().equals( "" ) && labelNrCrt26.getText().equals( "" )){
            labelNrCrt26.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && !labelNrCrt25.getText().equals( "" ) && !labelNrCrt26.getText().equals( "" )
                && labelNrCrt27.getText().equals( "" )){
            labelNrCrt27.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && !labelNrCrt25.getText().equals( "" ) && !labelNrCrt26.getText().equals( "" )
                && !labelNrCrt27.getText().equals( "" ) && labelNrCrt28.getText().equals( "" )){
            labelNrCrt28.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && !labelNrCrt25.getText().equals( "" ) && !labelNrCrt26.getText().equals( "" )
                && !labelNrCrt27.getText().equals( "" ) && !labelNrCrt28.getText().equals( "" ) && labelNrCrt29.getText().equals( "" )){
            labelNrCrt29.setText( selectValoare.getNrCrt().toString() );
        }else if (selectValoare != null && !labelNrCrt.getText().equals( "" ) && !labelNrCrt1.getText().equals( "" ) && !labelNrCrt2.getText().equals( "" ) && !labelNrCrt3.getText().equals( "" ) && !labelNrCrt4.getText().equals( "" ) && !labelNrCrt5.getText().equals( "" )
                && !labelNrCrt6.getText().equals( "" ) && !labelNrCrt7.getText().equals( "" ) && !labelNrCrt8.getText().equals( "" )&& !labelNrCrt9.getText().equals( "" ) && !labelNrCrt10.getText().equals( "" ) && !labelNrCrt11.getText().equals( "" ) && !labelNrCrt12.getText().equals( "" )
                && !labelNrCrt13.getText().equals( "" ) && !labelNrCrt14.getText().equals( "" ) && !labelNrCrt15.getText().equals( "" ) && !labelNrCrt16.getText().equals( "" ) && !labelNrCrt17.getText().equals( "" ) && !labelNrCrt18.getText().equals( "" ) && !labelNrCrt19.getText().equals( "" )
                && !labelNrCrt20.getText().equals( "" ) && !labelNrCrt21.getText().equals( "" ) && !labelNrCrt22.getText().equals( "" ) && !labelNrCrt23.getText().equals( "" ) && !labelNrCrt24.getText().equals( "" ) && !labelNrCrt25.getText().equals( "" ) && !labelNrCrt26.getText().equals( "" )
                && !labelNrCrt27.getText().equals( "" ) && !labelNrCrt28.getText().equals( "" ) && !labelNrCrt29.getText().equals( "" )){

            Alert confirm = new Alert( Alert.AlertType.INFORMATION );
            confirm.setHeaderText( "Ați ajuns la limita de 30 de facturi selectate!" );
            confirm.setContentText( "Apargeti PIFul in mai multe");
            confirm.show();


        }

        ObservableList<Investitii> selectedCels, selectedRows, allInvoice;
        allInvoice = tabelFacturiPIF.getItems();
        selectedRows = tabelFacturiPIF.getSelectionModel().getSelectedItems();

        int selectNrCrt1 =tabelFacturiPIF.getSelectionModel().getSelectedItem().getNrCrt();

//        for (Investitii factura : selectedRows) {
//            allInvoice.remove( factura );
//        }
//
//        String selectForPIF="SELECT * FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+selectNrCrt1+"'";
//        String totalSelectForPIF = "SELECT ROUND(SUM(valoare),2) as 'totalPif' FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+selectedCels+"'";

        String selectForPIF = "SELECT * FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+labelNrCrt.getText()+"' or nrcrt='"+labelNrCrt1.getText()+"'or nrcrt='"+labelNrCrt2.getText()+"'or nrcrt='"+labelNrCrt3.getText()+"'or nrcrt='"+labelNrCrt4.getText()+"'or nrcrt='"+labelNrCrt5.getText()+"'or nrcrt='"+labelNrCrt6.getText()+
                "'or nrcrt='"+labelNrCrt7.getText()+"'or nrcrt='"+labelNrCrt8.getText()+"'or nrcrt='"+labelNrCrt9.getText()+"'or nrcrt='"+labelNrCrt10.getText()+"'or nrcrt='"+labelNrCrt11.getText()+"'or nrcrt='"+labelNrCrt12.getText()+"'or nrcrt='"+labelNrCrt13.getText()+"'or nrcrt='"+labelNrCrt14.getText()+"'or nrcrt='"+labelNrCrt15.getText()+"'or nrcrt='"+labelNrCrt16.getText()+"'or nrcrt='"+labelNrCrt17.getText()+
                "'or nrcrt='"+labelNrCrt18.getText()+"'or nrcrt='"+labelNrCrt19.getText()+"'or nrcrt='"+labelNrCrt20.getText()+"'or nrcrt='"+labelNrCrt21.getText()+"'or nrcrt='"+labelNrCrt22.getText()+"'or nrcrt='"+labelNrCrt23.getText()+"'or nrcrt='"+labelNrCrt24.getText()+"'or nrcrt='"+labelNrCrt25.getText()+"'or nrcrt='"+labelNrCrt26.getText()+"'or nrcrt='"+labelNrCrt27.getText()+"'or nrcrt='"+labelNrCrt28.getText()+"'or nrcrt='"+labelNrCrt29.getText()+"'";

        String totalSelectForPIF = "SELECT ROUND(SUM(valoare),2) as 'totalPif' FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+labelNrCrt.getText()+"' or nrcrt='"+labelNrCrt1.getText()+"'or nrcrt='"+labelNrCrt2.getText()+"'or nrcrt='"+labelNrCrt3.getText()+"'or nrcrt='"+labelNrCrt.getText()+"'or nrcrt='"+labelNrCrt5.getText()+"' or nrcrt='"+labelNrCrt6.getText()+
                "'or nrcrt='"+labelNrCrt7.getText()+"'or nrcrt='"+labelNrCrt8.getText()+"'or nrcrt='"+labelNrCrt9.getText()+"'or nrcrt='"+labelNrCrt10.getText()+"'or nrcrt='"+labelNrCrt11.getText()+"'or nrcrt='"+labelNrCrt12.getText()+"'or nrcrt='"+labelNrCrt13.getText()+"'or nrcrt='"+labelNrCrt14.getText()+"'or nrcrt='"+labelNrCrt15.getText()+"'or nrcrt='"+labelNrCrt16.getText()+"'or nrcrt='"+labelNrCrt17.getText()+
                "'or nrcrt='"+labelNrCrt18.getText()+"'or nrcrt='"+labelNrCrt19.getText()+"'or nrcrt='"+labelNrCrt20.getText()+"'or nrcrt='"+labelNrCrt21.getText()+"'or nrcrt='"+labelNrCrt22.getText()+"'or nrcrt='"+labelNrCrt23.getText()+"'or nrcrt='"+labelNrCrt24.getText()+"'or nrcrt='"+labelNrCrt25.getText()+"'or nrcrt='"+labelNrCrt26.getText()+"'or nrcrt='"+labelNrCrt27.getText()+"'or nrcrt='"+labelNrCrt28.getText()+"'or nrcrt='"+labelNrCrt29.getText()+"' ";

        columnFurnizorPif.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        columnFacturaPif.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        columnValoarePif.setCellValueFactory( new PropertyValueFactory<>( "valoare" ) );
        columnNrCrtPif.setCellValueFactory( new PropertyValueFactory<>( "nrCrt" ) );

        tabelFinalDePif2 = FXCollections.observableArrayList();
        ResultSet rsTabelPif = stm.executeQuery(selectForPIF);

        try {
            while (rsTabelPif.next()){
                tabelFinalDePif2.add( new Investitii(
                        rsTabelPif.getString( "furnizor" ),
                        rsTabelPif.getString( "nrFactura" ),
                        rsTabelPif.getString( "valoare" ),
                        rsTabelPif.getInt( "nrCrt" )
                ) );
            }
            tabelFinalPif.setItems( tabelFinalDePif2 );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rsProjTotal= stm.executeQuery( totalSelectForPIF );
        double totalPifValue=0;
        try {
            while (rsProjTotal.next()){
                totalPifValue=rsProjTotal.getDouble( "totalPif" );
            }
            totalPIF.setText( String.valueOf( totalPifValue ) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Investitii factura : selectedRows) {
            allInvoice.remove( factura );
        }
    }


    public void returFactura ( ActionEvent event ) {
            ObservableList<Investitii> selectedRows, allInvoice;
            allInvoice = tabelFinalPif.getItems();
            selectedRows = tabelFinalPif.getSelectionModel().getSelectedItems();

        Investitii selectNrCrt = tabelFinalPif.getSelectionModel().getSelectedItem();
        labelNrCrtReturn.setText( selectNrCrt.getNrCrt().toString() ) ;

        for (Investitii factura : selectedRows) {
            allInvoice.remove( factura );
            }

        if (!labelNrCrt.getText().equals( "" ) && labelNrCrt.getText().equals( labelNrCrtReturn.getText())) {
            labelNrCrt.setText( "" );
            labelNrCrtReturn.setText( "" );
            return;}
        if(!labelNrCrt1.getText().equals( "" ) && labelNrCrt1.getText().equals( labelNrCrtReturn.getText())){
            labelNrCrt1.setText( "" );
            labelNrCrtReturn.setText( "" );
            return; }
        if(!labelNrCrt2.getText().equals( "" ) && labelNrCrt2.getText().equals( labelNrCrtReturn.getText())){
            labelNrCrtReturn.setText( "" );
            labelNrCrt2.setText( "" );
            return;}
        if(!labelNrCrt3.getText().equals( "" ) && labelNrCrt3.getText().equals( labelNrCrtReturn.getText())){
            labelNrCrt3.setText( "" );
            labelNrCrtReturn.setText( "" );
            return;}
        if(!labelNrCrt4.getText().equals( "" ) && labelNrCrt4.getText().equals(labelNrCrtReturn.getText())){
            labelNrCrt4.setText( "" );
            labelNrCrtReturn.setText( "" );
            return;}
        if(labelNrCrt5!=null && labelNrCrt5.getText().equals( labelNrCrtReturn.getText())){
            labelNrCrt5.setText( "" );
            labelNrCrtReturn.setText( "" );
            return;
        }
    }

    public void totalPif ( ActionEvent event ) throws SQLException {
        String totalSelectForPIF = "SELECT ROUND(SUM(valoare),2) as 'totalPif' FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+labelNrCrt.getText()+"' or nrcrt='"+labelNrCrt1.getText()+"'or nrcrt='"+labelNrCrt2.getText()+"'or nrcrt='"+labelNrCrt3.getText()+"'or nrcrt='"+labelNrCrt.getText()+"'or nrcrt='"+labelNrCrt5.getText()+"'";

        ResultSet rsProjTotal= stm.executeQuery( totalSelectForPIF );
        double totalPifValue=0;
        try {
            while (rsProjTotal.next()){
                totalPifValue=rsProjTotal.getDouble( "totalPif" );
            }
            totalPIF.setText( String.valueOf( totalPifValue ) );
            String hy = totalPIF.getText();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void reset ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4Pif.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }
    public void clickedValueTabelFinalPif ( MouseEvent event ){

        Investitii selectValoare = tabelFinalPif.getSelectionModel().getSelectedItem();

       if (selectValoare!=null){
//        textFieldValoarePIF.setText( selectValoare.getValoare() ) ;
        labelNrCrtReturn.setText( selectValoare.getNrCrt().toString() ) ;
       }
    }


    public void validarePIF ( ActionEvent event ) throws SQLException {
        String totalSelectForPIF = "SELECT ROUND(SUM(valoare),2) as 'totalPif' FROM invTBL WHERE org='"+comboBoxButtonOrg.getValue()+"' AND nrProiect='"+comboBoxButtonProj.getValue()+"' AND nrCrt='"+labelNrCrt.getText()+"' or nrcrt='"+labelNrCrt1.getText()+"'or nrcrt='"+labelNrCrt2.getText()+"'or nrcrt='"+labelNrCrt3.getText()+"'or nrcrt='"+labelNrCrt.getText()+"'or nrcrt='"+labelNrCrt5.getText()+"'";

        ResultSet rsProjTotal= stm.executeQuery( totalSelectForPIF );
        double totalPifValue=0;
        try {
            while (rsProjTotal.next()){
                totalPifValue=rsProjTotal.getDouble( "totalPif" );
            }
            totalPIF.setText( String.valueOf( totalPifValue ) );
            double total = Double.parseDouble( totalPIF.getText());


                if (total==0 ){
                    Alert alert = new Alert( Alert.AlertType.INFORMATION );
                    alert.setHeaderText( "Nici o factura selctata!" );
                    alert.show();
                    return;
                }
                if (textFieldNrPVR.getText()==null){
                    Alert alert = new Alert( Alert.AlertType.INFORMATION );
                    alert.setHeaderText( "Completeaza numarul PIF" );
                    alert.show();
                    return;
                }
                if (dataPIF.getValue()==null){
                    Alert alert = new Alert( Alert.AlertType.INFORMATION );
                    alert.setHeaderText( "Completeaza data PIF" );
                    alert.show();
                    return;
                }


            Alert alert0 = new Alert( Alert.AlertType.CONFIRMATION, "Confirmati PIF in valoare de "+total+" pe proiectul "+ comboBoxButtonProj.getValue()+"?", ButtonType.YES, ButtonType.NO);
                alert0.setHeaderText( " " );
                alert0.setTitle( "Validati facturile aferente PIF" );
                Button noButton = (Button) alert0.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );
                Button yesButton = (Button) alert0.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );
                alert0.showAndWait();

            if (alert0.getResult() == ButtonType.YES) {
                buttonPIF.setDisable( false );
            }if (alert0.getResult() == ButtonType.NO){

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void finalizarePIF ( ActionEvent event ) throws IOException {

        String valoare = "UPDATE invTBL SET valoare= '0' WHERE nrCrt = '" + labelNrCrt.getText() + "' OR nrCrt = '" + labelNrCrt1.getText() + "' OR nrCrt = '" + labelNrCrt2.getText() + "' OR nrCrt = '" + labelNrCrt3.getText() + "' OR nrCrt = '" + labelNrCrt4.getText() + "' OR nrCrt = '" + labelNrCrt5.getText() + "' " ;
        String nrPif= "UPDATE invTBL SET nrPIF='" + textFieldNrPVR.getText() + "' WHERE nrCrt = '" + labelNrCrt.getText() + "' OR nrCrt = '" + labelNrCrt1.getText() + "' OR nrCrt = '" + labelNrCrt2.getText() + "' OR nrCrt = '" + labelNrCrt3.getText() + "' OR nrCrt = '" + labelNrCrt4.getText() + "' OR nrCrt = '" + labelNrCrt5.getText() + "' " ;
        String dataPIIF="UPDATE invTBL SET dataPIF='" + dataPIF.getValue() + "' WHERE nrCrt = '" + labelNrCrt.getText() + "' OR nrCrt = '" + labelNrCrt1.getText() + "' OR nrCrt = '" + labelNrCrt2.getText() + "' OR nrCrt = '" + labelNrCrt3.getText() + "' OR nrCrt = '" + labelNrCrt4.getText() + "' OR nrCrt = '" + labelNrCrt5.getText() + "' " ;
        try (Statement stm = connection.createStatement() ){
            int updateValoare =stm.executeUpdate( valoare );
            int updateNrPif =stm.executeUpdate( nrPif );
            int updateDataPIF = stm.executeUpdate(dataPIIF);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reset( event );

        Alert confirm = new Alert( Alert.AlertType.INFORMATION );
        confirm.setHeaderText( "Feliciari! PIF Realizat" );
        confirm.setContentText( "la proiectul: " + comboBoxButtonProj.getValue() + " in valoare de: " + totalPIF.getText());
        confirm.show();

        }
}
