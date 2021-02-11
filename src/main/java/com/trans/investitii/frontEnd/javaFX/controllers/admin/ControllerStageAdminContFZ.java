package com.trans.investitii.frontEnd.javaFX.controllers.admin;

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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.*;
import java.util.List;

public class ControllerStageAdminContFZ implements Initializable {

    @FXML
    public Button goToStage1Intro;
    public Button adminFZ;
    public Button adminContracte;
    public Button adminCtInvest;
    public Button adminCtFz;
    public Button adminNrProiect;
    public Button adminRespProiect;
    public Button back;
    public Button back0S;
    public Button exportButton;
    public TextArea ItemList;
    public Button addButton;
    public TextField addCtFZ;
    public Button adminDeviz;
    public Button adminOrg;
    public Text added;

    public String pathFileCtFz = "C:\\Investitii\\resurse\\ctFz";
//    public String pathFileCtFz = "/resources/adminZone/ctFz.txt";
//    public String pathFileCtFz = ("/main/resources/adminZone/ctFz");

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
            Scanner s = new Scanner(new File( pathFileCtFz )).useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) {
                    ItemList.appendText(s.nextInt() + " "+"\n");
                } else {
                    ItemList.appendText(s.next() + " "+"\n");
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }


    }

    public void click(){
        this.adminCtFz.setDisable( true );
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

    public void exportAdminList ( ActionEvent event ) throws IOException {
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

    public void addNewFz ( ActionEvent event ) throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader( pathFileCtFz ));
        String fileLine;
        String addString = addCtFZ.getCharacters().toString();
        String inactiveString = "INACTIV-".concat( addString );
        String addCtFzString = addCtFZ.getCharacters().toString();

            if (addCtFzString.isEmpty()) {
                Alert fail = new Alert( Alert.AlertType.INFORMATION );
                fail.setHeaderText( "Atentie!" );
                fail.setContentText( "Nu poti introduce campuri goale!" );
                fail.showAndWait();
            }

            else  {
                try {
                         while((fileLine=bReader.readLine()) != null) {
                             if (fileLine.equals( addCtFzString )) {
                                 Alert fail = new Alert( Alert.AlertType.INFORMATION );
                                 fail.setHeaderText( "Atentie!" );
                                 fail.setContentText( "Elementul " + addCtFzString + " exista in baza de date" );
                                 fail.showAndWait();
                                 addCtFZ.clear();
                                 break;
                             }


                             if (fileLine.equalsIgnoreCase( inactiveString )) {
                                 Alert fail = new Alert( Alert.AlertType.INFORMATION );
                                 fail.setHeaderText( "Atentie!" );
                                 fail.setContentText( "Elementul " + addCtFzString + " este WHILE inactiv in baza de date" );
                                 fail.showAndWait();
                                 addCtFZ.clear();
                                 break;
                             }
                         }

                    if (fileLine == null || (!(fileLine.equalsIgnoreCase(addCtFzString))
                            && !(inactiveString.equalsIgnoreCase( fileLine )))) {
                        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtFz, true ) );
                            writer.append( addCtFzString+ "\n" );
                            writer.close();
                            ItemList.appendText( addCtFzString + "\n" ); // ad data in TextArea from text field
                            addCtFZ.clear();
                            this.added.setText( "Ati adaugat cu succes" );
                            this.added.setText("INACTIV-".concat( addCtFzString));

                            sortFile();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }



        public static void sortFile() throws IOException    {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\ctFz");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
              while ((line = bufferedReader.readLine()) != null) {
                 lines.add(line);
              }
                bufferedReader.close();
                Collections.sort(lines, Collator.getInstance());

              FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\ctFz");
              for(String str: lines) {
                 writer.write(str + "\r\n");
              }
             writer.close();
        }


    }
