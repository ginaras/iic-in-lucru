package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static main.java.com.trans.investitii.backEnd.DBase.Investitii.*;

public class Stage7ModificariFacturi implements Initializable {


    public Button buttonStage6AnalizaPif;
    public Button buttonStage5Solduri;
    public Button goToStage4Pif;
    public Button butonStage3Rapoarte;
    public Button butonStage2Rapoarte;
    public Button butonStage1Intro;
    public Button buttonBackSt0;
    public Button butonModificariFact;
    public Button butonImpartireFact;

    public ComboBox comboBoxFzModificari;
    public ComboBox comboBoxNrFactModificari;
    public ComboBox comboBoxFzImpartire;
    public ComboBox comboBoxNrFactImpartire;
    public Label valFactModificare;
    public ComboBox comboBoxContInvestitiiModificare;
    public ComboBox comboBoxNrProjModificare;
    public ComboBox comboBoxDevizModificare;
    public ComboBox comboBoxOrgModificare;
    public DatePicker dataFactModificare;
    public TextField descriereModificare;
    public ComboBox comboBoxRespProjModificare;

    public Label dataFactImpartire;
    public Label valFactImpartire;
    public Label labelContInvImpartire;
    public Label labelProjImpartire;
    public Label labelDevizImpartire;
    public Label labelOrgImpartire;
    public Label labelDescriereImpartire;
    public Label labelFzImpartire;
    public Label dataFactImpartire1;
    public Label labelNrFactImpartire;
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

    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    Statement statement = conn.createStatement();

    public Stage7ModificariFacturi() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxNrFactModificari.setDisable(true);
        comboBoxNrFactImpartire.setDisable(true);
        butonModificariFact.setDisable(true);
        butonImpartireFact.setDisable(true);

        List<String> myListFz = null;
        List<String> myListCtInvest = null;
        List<String> myListProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListCtInvest = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctInv") ));
            myListDeviz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/deviz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListRespProj = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/respproj") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));
//            myListContract = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ));
//            myListCtFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctFz") ));


        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> furnizoriActivi = myListFz.stream()
                .filter( furnizor -> !furnizor.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxFzModificari.setItems( FXCollections.observableArrayList(furnizoriActivi));
        comboBoxFzImpartire.setItems( FXCollections.observableArrayList(furnizoriActivi));


        List<String> ctInvestActive = myListCtInvest.stream()
                .filter( ctInvest -> !ctInvest.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxContInvestitiiModificare.setItems( FXCollections.observableArrayList(ctInvestActive));
        comboBoxContInvestitiiImpartire1.setItems( FXCollections.observableArrayList(ctInvestActive));
        comboBoxContInvestitiiImpartire2.setItems( FXCollections.observableArrayList(ctInvestActive));


        List<String> devizeActive = myListDeviz.stream()
                .filter( devize -> !devize.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxDevizModificare.setItems( FXCollections.observableArrayList(devizeActive));
        comboBoxDevizImpartire1.setItems( FXCollections.observableArrayList(devizeActive));
        comboBoxDevizImpartire2.setItems( FXCollections.observableArrayList(devizeActive));

        List<String> orgActive = myListOrg.stream()
                .filter( org -> !org.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxOrgModificare.setItems( FXCollections.observableArrayList(orgActive));
        comboBoxOrgImpartire1.setItems( FXCollections.observableArrayList(orgActive));
        comboBoxOrgImpartire2.setItems( FXCollections.observableArrayList(orgActive));

        List<String> responsabiliActivi = myListRespProj.stream()
                .filter( resp -> !resp.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxRespProjModificare.setItems( FXCollections.observableArrayList(responsabiliActivi));

        List<String> proiecteActive = myListProj.stream()
                .filter( proiect -> !proiect.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxNrProjModificare.setItems( FXCollections.observableArrayList(proiecteActive));
        comboBoxProjImpartire1.setItems( FXCollections.observableArrayList(proiecteActive));
        comboBoxProjImpartire2.setItems( FXCollections.observableArrayList(proiecteActive));



    }

    public void goToStage6AnalizaPif(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPif.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage5Solduri(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage4Pif(ActionEvent actionEvent) throws IOException {  Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4PIF.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void goOnStage3Rapoarte(ActionEvent actionEvent) throws IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnStage2Rapoarte(ActionEvent actionEvent) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnStage1Intro(ActionEvent actionEvent) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnSt0(ActionEvent actionEvent) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void butonModificariFactAct(ActionEvent actionEvent) {
    }

    public void butonImpartireFact(ActionEvent actionEvent) {
    }

    public void comboBoxFzModificariAct(ActionEvent actionEvent) throws SQLException {
        comboBoxNrFactModificari.setDisable(false);

        String findFacturi= "SELECT * FROM invtbl WHERE  furnizor = '"+comboBoxFzModificari.getValue()+"'";
        ResultSet rsFz = statement.executeQuery(findFacturi);
        List<String> listaFacturi = new ArrayList<>();

        while (rsFz.next()){
            listaFacturi.add(rsFz.getString("nrFactura"));
        }
        comboBoxNrFactModificari.setItems(FXCollections.observableList(listaFacturi));
    }

    public void comboBoxNrFactModificariAct(ActionEvent actionEvent) throws SQLException {
        String findRest= "SELECT * FROM invtbl WHERE  furnizor = '"+comboBoxFzModificari.getValue()+"' and nrFactura = '"+comboBoxNrFactModificari.getValue()+"'";
        ResultSet rsNrFact = statement.executeQuery(findRest);
        String valoare=null;
        LocalDate data = null;
        String contInv = null;
        String proj = null;
        String deviz = null;
        String org = null;
        String responsabil = null;
        String descriere = null;

        while (rsNrFact.next()){
            valoare =rsNrFact.getString("Valoare");
            data = (LocalDate) rsNrFact.getObject("dataFacturii");
            contInv = rsNrFact.getString("contInv");
            proj = rsNrFact.getString("nrProiect");
            deviz = rsNrFact.getString("deviz");
            org = rsNrFact.getString("org");
            responsabil = rsNrFact.getString("respProiect");
            descriere = rsNrFact.getString("descriereFactura");
        }
            valFactModificare.setText(valoare);
            dataFactModificare.setValue(data);
            comboBoxContInvestitiiModificare.setValue(contInv);
            comboBoxNrProjModificare.setValue(proj);
            comboBoxDevizModificare.setValue(deviz);
            comboBoxOrgModificare.setValue(org);
            comboBoxRespProjModificare.setValue(responsabil);
            descriereModificare.setText(descriere);

    }

    public void comboBoxFzImpartireAct(ActionEvent actionEvent) {
        comboBoxNrFactImpartire.setDisable(false);
        labelFzImpartire.setText(comboBoxFzImpartire.getValue().toString());
    }

    public void comboBoxNrFactImpartireAct(ActionEvent actionEvent) {
    }
}
