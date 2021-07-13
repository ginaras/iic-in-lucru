package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.toList;
import static main.java.com.trans.investitii.backEnd.DBase.Investitii.*;

public class CtrlStage7ModificariFacturi implements Initializable {


    public Button buttonStage6AnalizaPif;
    public Button buttonStage5Solduri;
    public Button goToStage4Pif;
    public Button butonStage3Rapoarte;
    public Button butonStage2Rapoarte;
    public Button butonStage1Intro;
    public Button buttonBackSt0;
    public Button butonImpartireFact;


    public ComboBox comboBoxFzImpartire;
    public ComboBox comboBoxNrFactImpartire;
    public Label labelDataFactImpartire;
    public Label labelValFactImpartire;
    public Label labelContInvImpartire;
    public Label labelProjImpartire;
    public Label labelDevizImpartire;
    public Label labelOrgImpartire;
    public Label labelDescriereImpartire;
    public Label labelFzImpartire;
    public Label labelNrFactImpartire;
    public Label labelNrFactImpartire2;

    public ComboBox comboBoxContInvestitiiImpartire1;
    public ComboBox comboBoxProjImpartire1;
    public ComboBox comboBoxDevizImpartire1;
    public ComboBox comboBoxOrgImpartire1;
    public TextField textFieldDescriereImpartire1;
    public ComboBox comboBoxContInvestitiiImpartire2;
    public ComboBox comboBoxProjImpartire2;
    public ComboBox comboBoxDevizImpartire2;
    public ComboBox comboBoxOrgImpartire2;
    public TextField textFieldDescriereImpartire2;
    public TextField textFieldValImpartita1;
    public TextField textFieldValImpartita2;
    public Label labelRespImpartire;
    public ComboBox comboBoxRespImpartire1;
    public ComboBox comboBoxRespImpartire2;
    public Label labelDataCtbtImpartire;
    public DatePicker textFieldDataCtbImpartire;
    public Button butonValidareImpartireFact1;
    public Button butonVizualizareModificari;

    public DatePicker textFieldDataBegin;
    public DatePicker textFieldDataEnd;

    public Label labelNrFactImpartire1;
    public Label labelDataFactImpartire1;
    public Label labelDataCtbtImpartire1;
    public Label labelValFactImpartire1;
    public Label labelContInvImpartire1;
    public Label labelProjImpartire1;
    public Label labelDevizImpartire1;
    public Label labelOrgImpartire1;
    public Label labelRespImpartire1;
    public Label labelDenProjImpartire;
    public Label labelDenProjImpartire1;
    public Label labelDenProjImpartire2;

    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    Statement statement = conn.createStatement();

    LocalDateTime date0 = LocalDateTime.now();
    DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
    String replaceNume2 = date0.format( date2 );

    public CtrlStage7ModificariFacturi () throws SQLException {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxNrFactImpartire.setDisable(true);
        butonImpartireFact.setDisable(true);
        textFieldValImpartita2.setDisable(true);
        butonValidareImpartireFact1.setDisable( true );

        List<String> myListFz = null;
        List<String> myListCtInvest = null;
        List<String> myListProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines(( Paths.get("C:/Investitii/resurse/fz") ));
            myListCtInvest = Files.readAllLines(( Paths.get("C:/Investitii/resurse/ctInv") ));
            myListDeviz = Files.readAllLines(( Paths.get("C:/Investitii/resurse/deviz") ));
            myListOrg = Files.readAllLines(( Paths.get("C:/Investitii/resurse/org") ));
            myListRespProj = Files.readAllLines(( Paths.get("C:/Investitii/resurse/respproj") ));
            myListProj = Files.readAllLines(( Paths.get("C:/Investitii/resurse/newproj") ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> furnizoriActivi = myListFz.stream()
                .filter(furnizor -> !furnizor.contains("INACTIV-"))
                .collect(toList());
        comboBoxFzImpartire.setItems(FXCollections.observableArrayList(furnizoriActivi));


        List<String> ctInvestActive = myListCtInvest.stream()
                .filter(ctInvest -> !ctInvest.contains("INACTIV-"))
                .collect(toList());
        comboBoxContInvestitiiImpartire1.setItems(FXCollections.observableArrayList(ctInvestActive));
        comboBoxContInvestitiiImpartire2.setItems(FXCollections.observableArrayList(ctInvestActive));


        List<String> devizeActive = myListDeviz.stream()
                .filter(devize -> !devize.contains("INACTIV-"))
                .collect(toList());
        comboBoxDevizImpartire1.setItems(FXCollections.observableArrayList(devizeActive));
        comboBoxDevizImpartire2.setItems(FXCollections.observableArrayList(devizeActive));

        List<String> orgActive = myListOrg.stream()
                .filter(org -> !org.contains("INACTIV-"))
                .collect(toList());
        comboBoxOrgImpartire1.setItems(FXCollections.observableArrayList(orgActive));
        comboBoxOrgImpartire2.setItems(FXCollections.observableArrayList(orgActive));

        List<String> responsabiliActivi = myListRespProj.stream()
                .filter(resp -> !resp.contains("INACTIV-"))
                .collect(toList());
        comboBoxRespImpartire1.setItems(FXCollections.observableArrayList(responsabiliActivi));
        comboBoxRespImpartire2.setItems(FXCollections.observableArrayList(responsabiliActivi));

        List<String> proiecteActive = myListProj.stream()
                .filter(proiect -> !proiect.contains("INACTIV-"))
                .collect(toList());
        comboBoxProjImpartire1.setItems(FXCollections.observableArrayList(proiecteActive));
        comboBoxProjImpartire2.setItems(FXCollections.observableArrayList(proiecteActive));
    }

    public void goToStage6AnalizaPif(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/Stage6AnalizaPif.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        Stage window = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void goToStage5Solduri(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/Stage5Solduri.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        Stage window = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void goToStage4Pif(ActionEvent actionEvent) throws IOException {
        Parent stage1Intro = FXMLLoader.load(getClass().getResource("/fxml/Stage4PIF.fxml"));
        Scene tableViewScene = new Scene(stage1Intro);
        Stage windowStage1Intro = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        windowStage1Intro.setScene(tableViewScene);
        windowStage1Intro.show();

    }

    public void goOnStage3Rapoarte(ActionEvent actionEvent) throws IOException {
        Parent stage3Intro = FXMLLoader.load(getClass().getResource("/fxml/Stage3RapoarteInv.fxml"));
        Scene tableViewScene = new Scene(stage3Intro);
        Stage windowStage1Intro = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        windowStage1Intro.setScene(tableViewScene);
        windowStage1Intro.show();
    }

    public void goOnStage2Rapoarte(ActionEvent actionEvent) throws IOException {
        Parent stage1Intro = FXMLLoader.load(getClass().getResource("/fxml/Stage2Rapoarte.fxml"));
        Scene tableViewScene = new Scene(stage1Intro);
        Stage windowStage1Intro = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        windowStage1Intro.setScene(tableViewScene);
        windowStage1Intro.show();
    }

    public void goOnStage1Intro(ActionEvent actionEvent) throws IOException {
        Parent stage1Intro = FXMLLoader.load(getClass().getResource("/fxml/Stage1Intro.fxml"));
        Scene tableViewScene = new Scene(stage1Intro);
        Stage windowStage1Intro = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        windowStage1Intro.setScene(tableViewScene);
        windowStage1Intro.show();
    }

    public void goOnSt0(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        //This line gets the stage inforation
        Stage window = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void comboBoxFzImpartireAct(ActionEvent actionEvent) throws SQLException {
        comboBoxNrFactImpartire.setDisable(false);
        labelFzImpartire.setText(comboBoxFzImpartire.getValue().toString());

        String findFacturi4Impartire = "SELECT * FROM invtbl WHERE  furnizor = '" + comboBoxFzImpartire.getValue() + "'";
        ResultSet rsFz = statement.executeQuery(findFacturi4Impartire);
        List<String> listaFacturi4Impartire = new ArrayList<>();

        while (rsFz.next()) {
            listaFacturi4Impartire.add(rsFz.getString("nrFactura"));
        }
        comboBoxNrFactImpartire.setItems(FXCollections.observableList(listaFacturi4Impartire));
    }

    public void comboBoxNrFactImpartireAct(ActionEvent actionEvent) throws SQLException {
        butonValidareImpartireFact1.setDisable( false );
        String findRest4Impartire = "SELECT * FROM invtbl WHERE  furnizor = '" + comboBoxFzImpartire.getValue() + "' and nrFactura = '" + comboBoxNrFactImpartire.getValue() + "'";
        ResultSet rsNrFact = statement.executeQuery(findRest4Impartire);
        String nrFact = null;
        String valoare = null;
        String data = null;
        String dataContabilizarii = null;
        String contInv = null;
        String proj = null;
        String deviz = null;
        String org = null;
        String responsabil = null;
        String descriereImpartire = null;

        while (rsNrFact.next()) {
            nrFact=rsNrFact.getString( "nrFactura" );
            valoare = rsNrFact.getString("Valoare");
            data = rsNrFact.getString("dataFacturii");
            dataContabilizarii=rsNrFact.getString( "dataContabilizarii" );
            contInv = rsNrFact.getString("contInv");
            proj = rsNrFact.getString("nrProiect");
            deviz = rsNrFact.getString("deviz");
            org = rsNrFact.getString("org");
            responsabil = rsNrFact.getString("respProiect");
            descriereImpartire = rsNrFact.getString("descriereFactura");
        }
        labelValFactImpartire.setText(valoare);
        labelNrFactImpartire.setText(comboBoxNrFactImpartire.getValue().toString() + ".1FI");
        labelNrFactImpartire2.setText(comboBoxNrFactImpartire.getValue().toString() + ".2FI");
        labelDataFactImpartire.setText(( data ));
        labelDataCtbtImpartire.setText( dataContabilizarii );
        labelContInvImpartire.setText(contInv);
        labelProjImpartire.setText(proj);
        labelDevizImpartire.setText(deviz);
        labelOrgImpartire.setText(proj);
        labelRespImpartire.setText(responsabil);
        labelOrgImpartire.setText(org);
        labelDescriereImpartire.setText(descriereImpartire);

        labelNrFactImpartire1.setText( "-"+ nrFact);
        labelDataFactImpartire1.setText( data );
        labelValFactImpartire1.setText( "-"+valoare );
        labelContInvImpartire1.setText( contInv );
        labelProjImpartire1.setText( proj );
        labelDevizImpartire1.setText( deviz );
        labelOrgImpartire1.setText( org );
        labelRespImpartire1.setText( responsabil );

        comboBoxContInvestitiiImpartire1.setValue(contInv);
        comboBoxContInvestitiiImpartire2.setValue(contInv);

        comboBoxProjImpartire1.setValue(proj);
        comboBoxProjImpartire2.setValue(proj);

        comboBoxDevizImpartire1.setValue(deviz);
        comboBoxDevizImpartire2.setValue(deviz);

        comboBoxOrgImpartire1.setValue(org);
        comboBoxOrgImpartire2.setValue(org);

        comboBoxRespImpartire1.setValue(responsabil);
        comboBoxRespImpartire2.setValue(responsabil);

        textFieldDescriereImpartire1.setText(descriereImpartire);
        textFieldDescriereImpartire2.setText(descriereImpartire);

        String denProj="SELECT denProiect FROM bugetProj WHERE nrProiect='"+labelProjImpartire.getText()+"' ";
        ResultSet rsDenProj= statement.executeQuery(denProj);

        while (rsDenProj.next()){
            labelDenProjImpartire.setText(rsDenProj.getString( "denProiect" ));
        }
    }

    public void butonValidareImpartireFact(ActionEvent actionEvent) throws SQLException {
        if ( textFieldValImpartita1.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Introduceti valorile");
            alert.showAndWait();
            return;
        }
        if ( textFieldValImpartita2.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nu ai schimbat nimic!");
            alert.showAndWait();
            return;
//            textFieldValImpartita1act2();
        }
        double val  = Double.parseDouble(labelValFactImpartire.getText());
        double val1 = Double.parseDouble(textFieldValImpartita1.getText());
        double val2 = Double.parseDouble(textFieldValImpartita2.getText());
        double val3 = val1+val2;

        if (val1 + val2 != val) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Suma noilor valori introduse nu sunt egale cu valoarea de impartit "+val1+"+"+val2 +"="+val3+"nu e="+val);
            alert.showAndWait();

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Hint: Dupa ce ai completat valoarea : "+textFieldValImpartita1.getText()+" apasa ENTER");
            alert2.showAndWait();
            return;
        }

        if (val == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Nu ai ce imparti. Valoarea facturii este 0");
            alert1.showAndWait();
            return;
        }
        if (textFieldDataCtbImpartire.getValue() == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Alege data la care faci modificarea");
            alert1.showAndWait();
            return;
        } else {
            butonImpartireFact.setDisable(false);
        }
    }
    public void textFieldValImpartita1act( ActionEvent actionEvent  ) throws SQLException {
        String selectToChange = "SELECT * FROM invTBL WHERE furnizor = '" + comboBoxFzImpartire.getValue() + "'AND nrFactura='" + comboBoxNrFactImpartire.getValue() + "'";
        ResultSet rs5 = statement.executeQuery(selectToChange);
        String val = "0";

        while (rs5.next()){
            val = rs5.getString("valoare");
        }
        double val2 = Double.parseDouble(val)-Double.parseDouble(textFieldValImpartita1.getText());

        textFieldValImpartita2.setText(String.valueOf(val2));
    }
    public void textFieldValImpartita1act2( ) throws SQLException {
        if (!textFieldValImpartita1.getCharacters().toString().isEmpty()){
        String selectToChange = "SELECT valoare FROM invTBL WHERE furnizor = '" + comboBoxFzImpartire.getValue() + "'AND nrFactura='" + comboBoxNrFactImpartire.getValue() + "'";
        ResultSet rs5 = statement.executeQuery(selectToChange);
        String val = "0";

        while (rs5.next()){
            val = rs5.getString("valoare");
        }
        double val2 = Double.parseDouble(val)-Double.parseDouble(textFieldValImpartita1.getText());

        textFieldValImpartita2.setText(String.valueOf(val2));
        }
        else{

        }
    }

    public void butonImpartireFact(ActionEvent actionEvent) throws SQLException {

        String selectToChange = "SELECT * FROM invTBL WHERE furnizor = '" + comboBoxFzImpartire.getValue() + "'AND nrFactura='" + comboBoxNrFactImpartire.getValue() + "'";
        String createLine = "INSERT INTO invTBL  (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereaFacturii)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ResultSet rs4 = statement.executeQuery(selectToChange);
        String myNrCrt= null;
        String myDataFacturii = null;
        String myValoare="0";
        String myTVA="0";
        String myValoareTotala="0";
        Object myContract=null;
        Object myContInv=null;
        Object myContFz=null;
        Object mynrProiect=null;
        Object myDeviz=null;
        Object myOrg=null;
        Object myRespProiect=null;
        String myDescriereaFacturii=null;

        while (rs4.next()) {
            myNrCrt =rs4.getString("nrCrt");
            myDataFacturii = rs4.getString("dataFacturii");
            myValoare = rs4.getString("valoare");
            myTVA = rs4.getString("TVA");
            myValoareTotala = rs4.getString("valTot");
            myContract = rs4.getObject("contract");
            myContInv = rs4.getObject("contInv");
            myContFz = rs4.getObject("contFz");
            mynrProiect = rs4.getObject("nrProiect");
            myDeviz = rs4.getObject("deviz");
            myOrg = rs4.getObject("org");
            myRespProiect = rs4.getObject("respProiect");
            myDescriereaFacturii = rs4.getString("descriereFactura");
        }
        double TVA1 = 0;
        double TVA2 = 0;
        double valoareTotala1 = 0;
        double valoareTotala2 = 0;
                TVA1 = ( Double.parseDouble(textFieldValImpartita1.getText()) * Double.parseDouble(myTVA) ) / Double.parseDouble(myValoare);
                valoareTotala1 = Double.parseDouble(textFieldValImpartita1.getText()) + TVA1;
                TVA2 = Double.parseDouble(myTVA) - TVA1;
                valoareTotala2 = Double.parseDouble(myValoareTotala) - valoareTotala1;

            try (PreparedStatement statement = conn.prepareStatement(createLine) ) {

            String anulareFacturaInitiala = "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('" + comboBoxFzImpartire.getValue() + "','" +"-"+ comboBoxNrFactImpartire.getValue() + "','" + myDataFacturii + " ',' " + textFieldDataCtbImpartire.getValue() + "','" +"-"+ myValoare + " ','" +"-"+ myValoare + " ',' " +"-"+ myTVA + " ' , '" +"-"+ myValoareTotala + " ',' " +
                    myContract + "','" + myContInv + "','" + myContFz + "','" + mynrProiect + "','" + myDeviz + "','" + myOrg + "','" + myRespProiect + "','" + myDescriereaFacturii + "')";
            statement.execute(anulareFacturaInitiala);

            String adaugareFactura1 = "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('" + comboBoxFzImpartire.getValue() + "','" + labelNrFactImpartire.getText() + "','" + myDataFacturii + " ',' " + textFieldDataCtbImpartire.getValue() + "','" + textFieldValImpartita1.getText() + " ','" + textFieldValImpartita1.getText() + " ',' " + TVA1 + " ' , '" + valoareTotala1 + " ',' " +
                    myContract + "','" + comboBoxContInvestitiiImpartire1.getValue() + "','" + myContFz + "','" + comboBoxProjImpartire1.getValue() + "','" + comboBoxDevizImpartire1.getValue() + "','" + comboBoxOrgImpartire1.getValue() + "','" + comboBoxRespImpartire1.getValue() + "','" + textFieldDescriereImpartire1.getText() + "')";
             statement.execute(adaugareFactura1);

             if(Double.parseDouble(textFieldValImpartita2.getText()) >= 0.001){
            String adaugareFactura2 = "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('" + comboBoxFzImpartire.getValue() + "','" + labelNrFactImpartire2.getText() + "','" + myDataFacturii + " ',' " + textFieldDataCtbImpartire.getValue() + "','" + textFieldValImpartita2.getText() + " ','" + textFieldValImpartita2.getText() + " ',' " + TVA2 + " ' , '" + valoareTotala2 + " ',' " +
                    myContract + "','" + comboBoxContInvestitiiImpartire2.getValue() + "','" + myContFz + "','" + comboBoxProjImpartire2.getValue() + "','" + comboBoxDevizImpartire2.getValue() + "','" + comboBoxOrgImpartire2.getValue() + "','" + comboBoxRespImpartire2.getValue() + "','" + textFieldDescriereImpartire2.getText() + "')";
             statement.execute(adaugareFactura2);
            }
                System.out.println(replaceNume2+"  impartire nrCRT: "+myNrCrt+" factura "+comboBoxNrFactImpartire.getValue() +" in valoare de: "+labelValFactImpartire.getText()+" lei, a furnizorului "+labelFzImpartire.getText()+"  in facturile: "+labelNrFactImpartire.getText()+" "+textFieldValImpartita1.getText() +" lei si "+labelNrFactImpartire2.getText()+" "+textFieldValImpartita2.getText()+" lei");
            }
            Alert alert = new Alert(Alert.AlertType.WARNING,"Modificari realizate");
            alert.showAndWait();

//        comboBoxFzImpartire.setValue(null);
//        comboBoxNrFactImpartire.setValue( null );
        labelFzImpartire.setText( null );
        labelValFactImpartire.setText(null);
        labelNrFactImpartire.setText(null);
        labelDataCtbtImpartire.setText( null );
        labelNrFactImpartire2.setText(null);
        labelDataFactImpartire.setText(( null ));
        labelContInvImpartire.setText(null);
        labelProjImpartire.setText(null);
        labelDevizImpartire.setText(null);
        labelOrgImpartire.setText(null);
        labelRespImpartire.setText(null);
        labelOrgImpartire.setText(null);
        labelDescriereImpartire.setText(null);


        labelNrFactImpartire1.setText( null );
        labelDenProjImpartire.setText( null );
        labelDataFactImpartire1.setText( null );
        labelValFactImpartire1.setText( null );
        labelContInvImpartire1.setText( null );
        labelProjImpartire1.setText( null );
        labelDevizImpartire1.setText( null );
        labelOrgImpartire1.setText( null );
        labelRespImpartire1.setText( null );

        comboBoxContInvestitiiImpartire1.setValue(null);
        comboBoxContInvestitiiImpartire2.setValue(null);

        comboBoxProjImpartire1.setValue(null);
        comboBoxProjImpartire2.setValue(null);

        comboBoxDevizImpartire1.setValue(null);
        comboBoxDevizImpartire2.setValue(null);

        comboBoxOrgImpartire1.setValue(null);
        comboBoxOrgImpartire2.setValue(null);

        comboBoxRespImpartire1.setValue(null);
        comboBoxRespImpartire2.setValue(null);

        textFieldDescriereImpartire1.clear();
        textFieldDescriereImpartire2.clear();

        textFieldDataCtbImpartire.setValue(null);
        textFieldValImpartita1.clear();
        textFieldValImpartita2.clear();

        labelDenProjImpartire1.setText( "-" );
        labelDenProjImpartire2.setText( "-" );

        butonImpartireFact.setDisable(true);
        butonValidareImpartireFact1.setDisable( true );
        }


    public void butonVizualizareModificariAct(ActionEvent actionEvent) throws SQLException, IOException {
        String fzNrfact="SELECT furnizor, nrFactura FROM invTBL ";
        if ( textFieldDataEnd.getValue()==null)
            textFieldDataEnd.setValue( LocalDate.now() );
        if (textFieldDataBegin.getValue()==null)
            textFieldDataBegin.setValue( LocalDate.ofEpochDay( 1 ) );

        try {
            String modificari = "0";
            List listaFacturiNegative= new ArrayList();
            List listafacturi = new ArrayList();

            ResultSet rsFzNrFactura = statement.executeQuery(fzNrfact);

            BufferedWriter writeModificari =new BufferedWriter(new FileWriter("C:\\Investitii\\rapoarte\\"+replaceNume2+" - modificari.csv", false));
            writeModificari.append("Nr Crt, Furnizor, Numar factura, Data facturii, Data contabilizarii, Valoare curenta, Valoare initiala, TVA, Valoare totala, Contract, Cont investitie, Cont furnizor, Nr. proiect, Deviz, Organizatie, Responsabil Proiect, Descriere factura"+"\n");
            writeModificari.close();

            while (rsFzNrFactura.next()) {
                String nrfactNegativa = "-" + rsFzNrFactura.getString("nrFactura");
                String nrFactura = rsFzNrFactura.getString("nrFactura");
                listaFacturiNegative.add(nrfactNegativa);
                listafacturi.add(nrFactura);
            }
              List<String> comune = (List<String>) listaFacturiNegative.stream().filter(listafacturi::contains).collect(toList());

                String nrCrt = "0";
                String furnizor = "0";
                String nrfactura = "0";
                String dataFacturii = "0";
                String dataCTB = "0";
                String valoare = "0";
                String valoareInitiala = "0";
                String TVA = "0";
                String valoareTotala = "0";
                Object contract = "0";
                Object contInv = "0";
                Object contFz = "0";
                Object proiect = "0";
                Object deviz = "0";
                Object org = "0";
                Object respProiect = "0";
                String descriereaFacturii = "0";

                for (String x :comune) {
                    String y = x.replace("-" ,"");
                    String w = y.concat(".1FI");
                    String z = y.concat(".2FI");
                    modificari = "SELECT* FROM invtbl WHERE (nrFactura = '" + x + "' OR nrFactura='"+y+"'OR nrFactura='"+w+"'OR nrFactura='"+z+"') AND (dataContabilizarii BETWEEN '"+textFieldDataBegin.getValue()+"' AND'"+textFieldDataEnd.getValue()+"')";
                    System.out.println(x);
                    System.out.println(y);

                    ResultSet rsSelModificari = statement.executeQuery(modificari);

                    while (rsSelModificari.next()) {
                        nrCrt = rsSelModificari.getString("nrCrt");
                        furnizor = rsSelModificari.getString("furnizor");
                        nrfactura = rsSelModificari.getString("nrFactura");
                        dataFacturii = rsSelModificari.getString("dataFacturii");
                        dataCTB = rsSelModificari.getString("dataContabilizarii");
                        valoare = rsSelModificari.getString("valoare");
                        TVA = rsSelModificari.getString("TVA");
                        valoareTotala = rsSelModificari.getString("valTot");
                        contract = rsSelModificari.getObject("contract");
                        contInv = rsSelModificari.getObject("contInv");
                        contFz = rsSelModificari.getObject("contFz");
                        proiect = rsSelModificari.getObject("nrProiect");
                        deviz = rsSelModificari.getObject("deviz");
                        org = rsSelModificari.getObject("org");
                        respProiect = rsSelModificari.getObject("respProiect");
                        descriereaFacturii = rsSelModificari.getString("descriereFactura");

                        BufferedWriter writeModificari1 = new BufferedWriter(new FileWriter("C:\\Investitii\\rapoarte\\" + replaceNume2 + " - modificari.csv", true));
                        writeModificari1.append(nrCrt + "," + furnizor + ","+nrfactura+"," + dataFacturii + ","+dataCTB+","+ valoare+","+valoareInitiala+","+TVA+","+valoareTotala+","+contract+","+contInv+","+contFz+","+proiect+","+deviz+","+org+","+respProiect+","+descriereaFacturii);
                        writeModificari1.append("\n");
                        writeModificari1.close();
                    }
                }
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Desktop desktop1 = null;
            desktop1.getDesktop().open( new File ("C:\\Investitii\\rapoarte\\"+replaceNume2+" - modificari.csv"));


    }

    public void comboBoxProjImpartire1Act ( ActionEvent actionEvent ) throws SQLException {
        String denProj="SELECT denProiect FROM bugetProj WHERE nrProiect='"+comboBoxProjImpartire1.getValue()+"' ";
        ResultSet rsDenProj= statement.executeQuery(denProj);

        while (rsDenProj.next()){
            labelDenProjImpartire1.setText(rsDenProj.getString( "denProiect" ));
        }
    }

    public void comboBoxProjImpartire2Act ( ActionEvent actionEvent ) throws SQLException {
        String denProj="SELECT denProiect FROM bugetProj WHERE nrProiect='"+comboBoxProjImpartire2.getValue()+"' ";
        ResultSet rsDenProj= statement.executeQuery(denProj);

        while (rsDenProj.next()){
            labelDenProjImpartire2.setText(rsDenProj.getString( "denProiect" ));
        }
    }
}

