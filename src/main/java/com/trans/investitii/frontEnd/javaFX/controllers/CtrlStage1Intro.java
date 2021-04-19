package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class CtrlStage1Intro implements Initializable {

    @FXML
    public Label furnizor;
    @FXML public Label nrFactura;
    @FXML public Label Data;
    public Label valoare;
    public Label proiect;
    public Label deviz;
    public Label utt;
    public Label Data1;
    public Label respProj;

    @FXML  public ComboBox comBoboxFz;
    public TextField fieldNrFact;
    public TextField fieldValFact;
    public DatePicker fieldDataFactura;
    public DatePicker fieldDataGL;
    public ComboBox comboBoxContract;
    public ComboBox comboBoxCtInv;
    public ComboBox cBCtFz;
    public ComboBox cBProjNr;
    public ComboBox comboBoxDeviz;
    public ComboBox comboBoxOrg;
    public ComboBox comboBoxRespProj;
    public TableColumn furnizorColumn;
    public TableColumn facturaColumn;
    public TableColumn valoareColumn;
    public TableColumn contInvColumn;
    public TableColumn respProjColumn;
    public TableColumn nrProjColumn;
    public TableColumn dataContabilizarii;
    public Button validFacturaButton;
    public Button ButtonSt2Rapoarte;
    public Button butonStage3Rapoarte;
    public Button goToStage4Pif;
    public TextField fieldDescriere;
    public CheckBox checkTVA;
    public Button buttonStage5Solduri;
    public Button buttonStage6AnalizaPif;

    @FXML
    TableView <Investitii> tableView;


    public Button buttonBackSt0;
    public Button addFacturaButtonId;

    public CtrlStage1Intro () throws SQLException {
    }



    public ObservableList<Investitii> getInvestitii (){
        ObservableList <Investitii> investitii = FXCollections.observableArrayList() ;
        return investitii;
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
    public void goToStage2Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }
    public ObservableList <Investitii> invest;
    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    ResultSet rs1 =statement.executeQuery( "SELECT * FROM invTBL");// WHERE dataContablizarii > LOCALDATE " );


    public void addFacturaButton ( ActionEvent event ) throws IOException {
//        validareCampuri();
        addFactToSQL( connection );
        clearData();
        addFacturaButtonId.setDisable( true );

    }
    public void addFactToSQL (Connection connection){
        String addSql = "INSERT INTO invTBL  (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereaFacturii)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(addSql) ){

            Investitii newInvestitii = new Investitii(
                    comBoboxFz.getValue(),
                    fieldNrFact.getText().toUpperCase(),
                    fieldValFact.getText(),
                    fieldDataFactura.getValue(),
                    fieldDataGL.getValue(),
                    comboBoxContract.getValue(),
                    comboBoxCtInv.getValue(),
                    cBCtFz.getValue(),
                    comboBoxRespProj.getValue(),
                    comboBoxDeviz.getValue(),
                    comboBoxOrg.getValue(),
                    cBProjNr.getValue(),
                    fieldDescriere.getText());

           if(!fieldValFact.getText().isEmpty() || !(fieldDataGL.getValue() ==null) || !(fieldDataFactura.getValue()==null))
            {
                if (checkTVA.isSelected()){
                       double val= parseDouble( fieldValFact.getText());
                            val = Math.round( val*100);
                            val = val/100;
                       double tva0 = val * 0;
                       tva0 = Math.round( tva0 * 100 );
                       double tva = tva0 / 100;
                       double valTot = val + tva;
                       valTot = Math.round( valTot * 100 );
                       valTot = valTot / 100;

                       statement.executeUpdate( "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('"+comBoboxFz.getValue()+"','" +fieldNrFact.getText().toUpperCase()+ "','" +fieldDataFactura.getValue()+ " ',' " +fieldDataGL.getValue() + "','" +val+ " ','" +val+ " ',' " +tva+ " ' , '" +valTot+ " ',' " +
                               comboBoxContract.getValue()+ "','" +comboBoxCtInv.getValue()+ "','" +cBCtFz.getValue()+ "','" +cBProjNr.getValue()+ "','" +comboBoxDeviz.getValue() +"','"+comboBoxOrg.getValue()+"','"+comboBoxRespProj.getValue()+"','"+fieldDescriere.getText()+"')" );

                       Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                       confirm.setHeaderText( "Factura a fost adaugata" );
                       confirm.setContentText( "TVA :  " + tva + "   Valoare Totala  : " + valTot );
                       confirm.show();

                       tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
                }
                if (!checkTVA.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva0 = val * 0.19;
                    tva0 = Math.round( tva0 * 100 );
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round( valTot * 100 );
                    valTot = valTot / 100;

                    statement.executeUpdate( "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('"+comBoboxFz.getValue()+"','" +fieldNrFact.getText().toUpperCase()+ "','" +fieldDataFactura.getValue()+ " ',' " +fieldDataGL.getValue() + "','" +val+ " ','" +val+ " ',' " +tva+ " ' , '" +valTot+ " ',' " +
                            comboBoxContract.getValue()+ "','" +comboBoxCtInv.getValue()+ "','" +cBCtFz.getValue()+ "','" +cBProjNr.getValue()+ "','" +comboBoxDeviz.getValue() +"','"+comboBoxOrg.getValue()+"','"+comboBoxRespProj.getValue()+"','"+fieldDescriere.getText()+"')" );
//
                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost adaugata" );
                    confirm.setContentText( "TVA:  " + tva + "    Valoare Totala:   " + valTot );
                    confirm.show();

                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
                }




            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        addFacturaButtonId.setDisable( true );

        List<String> myListFz = null;
        List<String> myListContract = null;
        List<String> myListCtInvest = null;
        List<String> myListCtFz = null;
        List<String> myListProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListContract = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ));
            myListCtInvest = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctInv") ));
            myListCtFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctFz") ));
            myListDeviz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/deviz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListRespProj = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/respproj") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> furnizoriActivi = myListFz.stream()
                .filter( furnizor -> !furnizor.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comBoboxFz.setItems( FXCollections.observableArrayList(furnizoriActivi));

        List<String> contracteActive = myListContract.stream()
                .filter( contract -> !contract.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxContract.setItems( FXCollections.observableArrayList(contracteActive));

        List<String> ctInvestActive = myListCtInvest.stream()
                .filter( ctInvest -> !ctInvest.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxCtInv.setItems( FXCollections.observableArrayList(ctInvestActive));

        List<String> ctFzActivi = myListCtFz.stream()
                .filter( ctFz -> !ctFz.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        cBCtFz.setItems( FXCollections.observableArrayList(ctFzActivi));

        List<String> devizeActive = myListDeviz.stream()
                .filter( devize -> !devize.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxDeviz.setItems( FXCollections.observableArrayList(devizeActive));

        List<String> orgActive = myListOrg.stream()
                .filter( org -> !org.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxOrg.setItems( FXCollections.observableArrayList(orgActive));

        List<String> responsabiliActivi = myListRespProj.stream()
                .filter( resp -> !resp.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxRespProj.setItems( FXCollections.observableArrayList(responsabiliActivi));

        List<String> proiecteActive = myListProj.stream()
                .filter( proiect -> !proiect.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        cBProjNr.setItems( FXCollections.observableArrayList(proiecteActive));
//pt tabel in scena
        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        facturaColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareColumn.setCellValueFactory(  new PropertyValueFactory<>( "valoare" ) );
        contInvColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        nrProjColumn.setCellValueFactory( new PropertyValueFactory<>( "nrProiect" ) );
        respProjColumn.setCellValueFactory( new PropertyValueFactory<>( "respProiect" ) );
        dataContabilizarii.setCellValueFactory( new PropertyValueFactory<>( "dataContabilizarii" ) );
        tableView.setItems(getInvestitii()  );

        invest = FXCollections.observableArrayList();
        try {
            int factura=0;
            while (rs1.next()) {
                if(rs1.isLast()){invest.addAll( new Investitii(
                        rs1.getObject( "furnizor" ),
                        rs1.getString( "nrFactura" ),
                        rs1.getString( "valoare" ),
                        rs1.getObject( "contInv" ),
                        rs1.getObject( "nrProiect" ),
                        rs1.getObject( "respProiect" ),
                        rs1.getString("dataContabilizarii")
                ));}}
            tableView.setItems( invest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void clearData() {
        comBoboxFz.getSelectionModel().select( null);
//        fieldNrFact.setText( null );
        fieldNrFact.clear();
        fieldValFact.clear();
        fieldDataFactura.getEditor().clear();
        fieldDataGL.getEditor().clear();
        comboBoxRespProj.getSelectionModel().select( null);
        comboBoxContract.getSelectionModel().select( null);
        comboBoxCtInv.getSelectionModel().select( null);
        cBCtFz.getSelectionModel().select( null);
        cBProjNr.getSelectionModel().select( null);
        comboBoxDeviz.getSelectionModel().select( null);
        comboBoxOrg.getSelectionModel().select( null);
        fieldDescriere.clear();
    }

    public void validareCampuri() throws InterruptedException {
        if (comBoboxFz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege Furnizorul" );
            alert.showAndWait();
            return;
        }

        if (fieldNrFact.getText().isEmpty() || fieldNrFact.getText()==null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga numarul de factura!" );
            alert.showAndWait();
            return;
        }
        if (fieldValFact.getText().isEmpty()) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga VALOAREA facturii fara TVA!" );
            alert.showAndWait();
            return;
        }

        try {
            double s = Double.parseDouble( fieldValFact.getText() );
            String f = fieldValFact.getText();

            if (f.matches( "\\d+(\\.\\d\\d)" )) {

            }

        } catch (NumberFormatException e) {
            Alert alert1 = new Alert( Alert.AlertType.INFORMATION );
            alert1.setHeaderText( "Adauga VALOAREA ca numar! Atentie la virgula!" );
            alert1.showAndWait();
            return;
        }

        if (fieldDataFactura.getValue() == null || fieldDataGL.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga data!" );
            alert.showAndWait();
            return;
        }
        if (comboBoxContract.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de contract" );
            alert.showAndWait();
            return;
        }
        if (comboBoxCtInv.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege contul de investitii" );
            alert.showAndWait();
            return;
        }
        if (cBCtFz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege contul de furnizori" );
            alert.showAndWait();
            return;
        }
        if (cBProjNr.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de proiect" );
            alert.showAndWait();
            return;
        }
        if (comboBoxRespProj.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege responsabilul de proiect" );
            alert.showAndWait();
            return;
        }
        if (comboBoxDeviz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de deviz" );
            alert.showAndWait();
            return;
        }
        if (comboBoxOrg.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege organizatia" );
            alert.showAndWait();
            return;
        }
        if (fieldDescriere.getText().isEmpty()) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Completeaza obiectul facturii" );
            alert.showAndWait();
            return;
        }

        if (   comBoboxFz.getValue() != null && !fieldNrFact.getText().isEmpty() && !fieldValFact.getText().isEmpty()
                && fieldDataFactura.getValue() != null && fieldDataGL.getValue() != null
                && !(comboBoxContract.getValue() == null) && !(comboBoxCtInv.getValue() == null) && !(cBCtFz.getValue() == null)
                && !(cBProjNr.getValue() == null) && !(comboBoxDeviz.getValue() == null)
                && !(comboBoxOrg.getValue() == null) && !(comboBoxRespProj.getValue() == null)
                && !fieldDescriere.getText().isEmpty())
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Toate campurile au fost completate!" );
            alert.showAndWait();
            addFacturaButtonId.setDisable( false );
        }


        if (checkTVA.isSelected()){
            double val= parseDouble( fieldValFact.getText());
            val = Math.round( val*100);
            val = val/100;
            double tva0 = val * 0;
            tva0 = Math.round( tva0 * 100 );
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round( valTot * 100 );
            valTot = valTot / 100;

            Alert confirm = new Alert( Alert.AlertType.INFORMATION );
            confirm.setHeaderText( "Factura nu are TVA" );
            confirm.setContentText( "TVA   :" + tva + "     Valoare Totala:   " + valTot );
            confirm.show();
        }
        if (!checkTVA.isSelected()) {
            double val = parseDouble( fieldValFact.getText() );
            val = Math.round( val * 100 );
            val = val / 100;
            double tva0 = val * 0.19;
            tva0 = Math.round( tva0 * 100 );
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round( valTot * 100 );
            valTot = valTot / 100;

            Alert confirm = new Alert( Alert.AlertType.INFORMATION );
            confirm.setHeaderText( "Factura are TVA" );
            confirm.setContentText( "TVA :  " + tva + "    Valoare Totala : " + valTot );
            confirm.show();
        }
        }

    public void goOnStage3Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage4Pif ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4Pif.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void goToStage5Solduri ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage6AnalizaPif ( ActionEvent actionEvent ) {
    }
}
