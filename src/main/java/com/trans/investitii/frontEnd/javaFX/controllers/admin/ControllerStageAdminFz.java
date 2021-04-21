package com.trans.investitii.frontEnd.javaFX.controllers.admin;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class ControllerStageAdminFz implements Initializable {

    @FXML
    public Button goToStage1Intro;
    public Button adminFZ;
    public Button adminContracte;
    public Button adminCtInvest;
    public Button adminCtFz;
    public Button adminNrProiect;
    public Button adminRespProiect;
    public Button back0S;
    public Button addButton;
    public TextArea ItemList;
    public Button exportButton;
    public Button adminDeviz;
    public Button adminOrg;
    public Text added;
    public String pathAdmFz = "C:\\Investitii\\resurse\\Fz";
    public TextField addFz;
    public TextField addCUI;
    public TextField addNrContract;
    public TextField addValoareContract;
    public DatePicker addDataContract;
    public TextField addAdresa;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    public ControllerStageAdminFz () throws SQLException {
    }

    public void goToStage1Intro( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

   public void click(){
    this.adminFZ.setDisable( true );
    }
    public void goOnAdminFz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminFz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnadminContracte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContracte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminCtInvest ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminCtFz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContFz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminNrProiect ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminNrProiect.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminRespProiect ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminRespProiect.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void goOnAdminDeviz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminDeviz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goOnAdminOrg ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminOrg.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void buttonBack ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public  void exportFzAdminList () throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("C:\\Investitii\\rapoarte\\export.txt") );
        bufferedWriter.append( ItemList.getText() );
        bufferedWriter.close();

        Desktop desktop= null;
        try {
            desktop.getDesktop().open( new File( "C:\\Investitii\\rapoarte\\export.txt" ) ); ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {
        try {
            sortFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner s = new Scanner(new File(pathAdmFz)).useDelimiter("\\s+");
            while (s.hasNext()) {
                    ItemList.appendText(s.next() + " "+"\n"); // else read the next token
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewFz ( ActionEvent event ) throws IOException {
        String fileLine;
        String addFzString = addFz.getCharacters().toString();
        String inactiveFzString = "INACTIV-".concat( addFzString );
        BufferedReader bReader = new BufferedReader( new FileReader( pathAdmFz ) );

        if (addFzString.isEmpty()) {
            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Nu poti introduce campuri goale!" );
            fail.showAndWait();
        }
        else  {
            try {
                while((fileLine=bReader.readLine()) != null) {
                    if (fileLine.equalsIgnoreCase( addFzString )) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul " + addFzString + " exista in baza de date" );
                        fail.showAndWait();
                        addFz.clear();
                        break;
                    }

                    if (fileLine.equalsIgnoreCase( inactiveFzString )){
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul "+addFzString+" este inactiv in baza de date" );
                        fail.showAndWait();
                        addFz.clear();
                        break;
                    }

                }

                if (fileLine == null || (!(fileLine.equalsIgnoreCase(addFzString))
                        && !(inactiveFzString.equalsIgnoreCase( fileLine )))) {
                    BufferedWriter writer = new BufferedWriter( new FileWriter( pathAdmFz, true ) );
                    writer.append( addFzString+ "\n" );
                    writer.close();
                    ItemList.appendText( addFzString + "\n" ); // ad data in TextArea from text field

                    //  10.03.2021
                    String addFzDataToBugetcontract = "INSERT INTO bugetcontract (furnizor, CUIfurnizor, adresa,nrContract, dataContract, valInitiala) VALUES(?,?,?,?,?,?)";
                    try (PreparedStatement stmPrep= connection.prepareStatement(addFzDataToBugetcontract)) {
                        Investitii addFzDataToBugetcontractInvestitii = new Investitii(
                                addFz.getCharacters(),
                                addCUI.getText(),
                                addAdresa.getCharacters(),
                                addNrContract.getCharacters(),
                                addDataContract.getValue(),
                                addValoareContract.getCharacters()
                        );
                        String varRectif = "0";
                        stmPrep.executeUpdate("INSERT INTO bugetCONTRACT (furnizor, CUIfurnizor, adresa, nrContract, dataContract, valInitiala, valRectificare, valFinala) VALUES ('" + addFz.getCharacters() + "','" + addCUI.getCharacters() + "','" + addAdresa.getCharacters() + "','" + addNrContract.getCharacters() + "','" + addDataContract.getValue() + "','" + addValoareContract.getCharacters() + "','"+varRectif+"','" + addValoareContract.getCharacters() + "')");

                    }

                    addFz.clear();
                    addCUI.clear();
                    addAdresa.clear();
                    addNrContract.clear();
                    addDataContract.setValue( null);
                    addValoareContract.clear();

                    addFz.clear();
                    addCUI.clear();
                    addAdresa.clear();
                    addNrContract.clear();
                    addDataContract.setValue(LocalDate.now());
                    addValoareContract.clear();

                    this.added.setText( "Ati adaugat cu succes" );
                    sortFile();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }




    public static void sortFile() throws IOException
    {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\fz");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        Collections.sort(lines, Collator.getInstance());

        FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\fz");
        for(String str: lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }
}
