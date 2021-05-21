package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import main.java.com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.Collator;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerStageAdminContracte implements Initializable {

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
    public TextField addContract;
    public Button adminDeviz;
    public Button adminOrg;
    public Text added;
    public String pathFileContract = "C:\\Investitii\\resurse\\contract";
    public ComboBox comboBoxAlegeFurnizor;
    public TextField addValInitiala;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    public ControllerStageAdminContracte () throws SQLException {
    }


    public void goToStage1Intro( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {
        try {
            sortFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Scanner s = new Scanner(new File( pathFileContract )).useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    ItemList.appendText(s.nextInt() + " "+"\n"); // display the found integer
                } else {
                    ItemList.appendText(s.next() + " "+"\n"); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        List<String> myListFz = null;
        try {
            try {
                myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String> furnizoriActivi = myListFz.stream()
                    .filter( furnizor -> !furnizor.contains( "Arhivat-" ) )
                    .collect( Collectors.toList() );
            comboBoxAlegeFurnizor.setItems( FXCollections.observableArrayList(furnizoriActivi));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void click(){
        this.adminContracte.setDisable( true );
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

    public void buttonBack ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
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

    public void addNewContract ( ActionEvent event ) throws FileNotFoundException {
        BufferedReader bReader = new BufferedReader(new FileReader( pathFileContract ));
        String fileLine;
        String addString = addContract.getCharacters().toString();
        String inactiveString = "Arhivat-".concat( addString );
        String addCtFzString = addContract.getCharacters().toString();

        if (comboBoxAlegeFurnizor.getSelectionModel().isEmpty()){
            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Alge furnizorul!" );
            fail.showAndWait();
            return;
        }
        if (addString.isEmpty() || addValInitiala.getText().isEmpty()) {
            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Nu poti introduce campuri goale!" );
            fail.showAndWait();
        }

        else {
            try {
                while ((fileLine = bReader.readLine()) != null) {
                    if (fileLine.toLowerCase().equals( addString.toLowerCase() )) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul " + addContract + " exista in baza de date" );
                        fail.showAndWait();
                        addContract.clear();
                        return;
                    }
                        if (fileLine.equalsIgnoreCase( inactiveString )) {
                            Alert fail2 = new Alert( Alert.AlertType.INFORMATION );
                            fail2.setHeaderText( "Atentie!" );
                            fail2.setContentText( "Elementul " + addCtFzString + " este Arhivat in baza de date"+"\n"+"Activeaza-l" );
                            fail2.showAndWait();
                            addContract.clear();
                            return;
                        }

                    if (fileLine != null && (!(fileLine.equalsIgnoreCase( addCtFzString ))
                            && !(inactiveString.equalsIgnoreCase( fileLine )))) {
                        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileContract, true ) );
                        writer.append( addString.toUpperCase() + "\n" );
                        writer.close();
                        ItemList.appendText( addString.toUpperCase() + "\n" ); // ad data in TextArea from text field

                        sortFile();

                        String addContractToDB = "INSERT INTO bugetCONTRACT nrContract";
                        try (PreparedStatement statement = connection.prepareStatement( addContractToDB );) {

                            statement.executeUpdate( "INSERT INTO bugetCONTRACT (nrContract, furnizor,valInitiala, valRectificare, valFinala ) VALUES('" + addString.toUpperCase() + "','" + comboBoxAlegeFurnizor.getValue() + "','" + addValInitiala.getText() + "','" + "0" + "','" + addValInitiala.getText() + "')" );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        addContract.clear();
                        addValInitiala.clear();
                        this.added.setText( "Ati adaugat cu succes" );
                        return;
                    }
                    }
            }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    public void exportContracteAdminList ( ActionEvent event ) throws IOException {
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
    public static void sortFile() throws IOException     {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\contract");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        Collections.sort(lines, Collator.getInstance());

        FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\contract");
        for(String str: lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }

    public void alegeFurnizorAction ( ActionEvent event ) {

    }

}
