package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.text.Collator;
import java.util.*;
import java.util.List;

public class ControllerStageAdminContInv implements Initializable {

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
    public TextField addCtInv;
    public Button adminDeviz;
    public Button adminOrg;
    public Text added;
    public String pathFileCtInv = "C:\\Investitii\\resurse\\ctInv";


    public void goToStage1Intro ( ActionEvent event ) throws IOException {
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
            Scanner s = new Scanner( new File( "C:\\Investitii\\resurse\\ctInv" ) ).useDelimiter( "\\s+" );
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    ItemList.appendText( s.nextInt() + " " + "\n" ); // display the found integer
                } else {
                    ItemList.appendText( s.next() + " " + "\n" ); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println( ex );
        }
        addButton.setDisable(true);

    }

    public void click () {
        this.adminCtInvest.setDisable( true );
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

    public void exportCtInvAdminList ( ActionEvent event ) throws IOException {
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


    public void addNewCtInv ( ActionEvent event ) throws FileNotFoundException {
        BufferedReader bReader = new BufferedReader(new FileReader( pathFileCtInv ));
        String fileLine;
        String addString = addCtInv.getCharacters().toString();
        String inactiveString = "INACTIV-".concat( addString );
        String addCtInvString = addCtInv.getCharacters().toString();

        if (addCtInvString.isEmpty()) {
            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Nu poti introduce campuri goale!" );
            fail.showAndWait();
        }
        else {
            try {
                while((fileLine=bReader.readLine()) != null){
                    if(fileLine.equals(addCtInvString)) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul " + addCtInvString + " exista in baza de date" );
                        fail.showAndWait();
                        addCtInv.clear();
                        break;
                    }
                        if (fileLine.equalsIgnoreCase( inactiveString )) {
                            Alert fail2 = new Alert( Alert.AlertType.INFORMATION );
                            fail2.setHeaderText( "Atentie!" );
                            fail2.setContentText( "Elementul " + addCtInvString + " este inactiv in baza de date" );
                            fail2.showAndWait();
                            addCtInv.clear();
                            break;
                        }
                    }

                if (fileLine == null || (!(fileLine.equalsIgnoreCase(addCtInvString))
                        && !(inactiveString.equalsIgnoreCase( fileLine ))))
            {
                    BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtInv, true ) );
                    writer.append( addCtInvString+ "\n" );
                    writer.close();
                    ItemList.appendText( addCtInvString + "\n" ); // ad data in TextArea from text field
                    addCtInv.clear();
                    this.added.setText( "Ati adaugat cu succes" );
                    sortFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void sortFile() throws IOException
    {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\ctInv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        Collections.sort(lines, Collator.getInstance());

        FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\ctInv");
        for(String str: lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }

}