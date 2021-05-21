package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import main.java.com.trans.investitii.backEnd.DBase.Investitii;
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
import java.util.*;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ControllerStageAdminNrProiect implements Initializable {

    @FXML
    public Button goToStage1Intro;
    public Button adminFZ;
    public Button adminContracte;
    public Button adminCtInvest;
    public Button adminCtFz;
    public Button adminNrProiect;
    public Button adminRespProiect;
    public Button back0S;
    public Button exportButton;
    public TextArea ItemList;
    public Button addButton;
    public TextField addProj;
    public Button adminDeviz;
    public Button adminOrg;
    public Text added;
    public String pathFileNrProiect = "C:\\Investitii\\resurse\\newproj";
    public TextField addDenumireProj;
    public TextField addValProj;
    public DatePicker addDataAprobariiProj;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    public ControllerStageAdminNrProiect () throws SQLException {
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
            Scanner s = new Scanner(new File("C:\\Investitii\\resurse\\newproj")).useDelimiter("\\s+");
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


    }
    public void click(){
        this.adminNrProiect.setDisable( true );
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

    public void exportNrProjAdminList ( ActionEvent event ) throws IOException {
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

    public void addNewNrProj ( ActionEvent event ) throws FileNotFoundException {
        BufferedReader bReader = new BufferedReader(new FileReader( pathFileNrProiect ));
        String fileLine;
        String addString = addProj.getCharacters().toString();
        String inactiveString = "Arhivat-".concat( addString );

        try {
            double s = Double.parseDouble( addValProj.getText() );
            String f = addValProj.getText();

            if (f.matches( "\\d+(\\.\\d\\d)" )) {

            }

        } catch (NumberFormatException e) {
            Alert alert1 = new Alert( Alert.AlertType.INFORMATION );
            alert1.setHeaderText( "Adauga VALOAREA ca numar! Atentie la virgula!" );
            alert1.showAndWait();
            return;
        }

        if (addString.isEmpty() || addDenumireProj.getText().isEmpty()
                || (addDataAprobariiProj.getValue() ==null) || addValProj.getText().isEmpty() ) {

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Nu poti lasa campuri necompletate!" );
            fail.showAndWait();
        }

        else {
            try {
                while((fileLine=bReader.readLine()) != null){
                    if(fileLine.equals(addString)) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul "+addString+" exista in baza de date" );
                        fail.showAndWait();
                        addProj.clear();
                        return;
                    }
                    if (fileLine.equalsIgnoreCase( inactiveString )) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul " + addString + " este Arhivat in baza de date" );
                        fail.showAndWait();
                        addProj.clear();
                        return;
                    }

                if (fileLine == null || (!(fileLine.equalsIgnoreCase(addString))
                        && !(inactiveString.equalsIgnoreCase( fileLine )))) {
                    BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileNrProiect, true ) );
                    writer.append( addString + "\n" );
                    writer.close();
                    ItemList.appendText( addString + "\n" ); // ad data in TextArea from text field
                    addProj.clear();
                    this.added.setText( "Ati adaugat cu succes" );
                    sortFile();

                    String addProiectToDB = "INSERT INTO bugetPROJ nrProiect";
                    try (PreparedStatement statement = connection.prepareStatement( addProiectToDB );) {
                        double val = parseDouble( addValProj.getText() );
                        val = Math.round( val * 100 );
                        val = val / 100;
                        statement.executeUpdate( "INSERT INTO bugetPROJ (nrProiect, denProiect, startProiect, valInitiala, valRectificare, valFinala ) VALUES('" + addString + "','" + addDenumireProj.getCharacters().toString() + "','" + addDataAprobariiProj.getValue() + "','" + addValProj.getText() + "','0','" + val + "')" );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    addDenumireProj.clear();
                    addValProj.clear();
                    addDataAprobariiProj.getEditor().clear();
                    return;
                }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void sortFile() throws IOException
    {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\newproj");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        Collections.sort(lines, Collator.getInstance());

        FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\newproj");
        for(String str: lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }
}
