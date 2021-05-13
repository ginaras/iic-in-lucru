package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;


@SuppressWarnings("ALL")
public class ControllerStageBAdminDelete implements Initializable {


    public ComboBox comboBoxFz;
    public ComboBox comboBoxDeviz;
    public ComboBox comboBoxOrg;
    public ComboBox comboBoxContr;
    public ComboBox comboBoxCtInv;
    public ComboBox comboBoxCtFz;
    public ComboBox comboBoxNrProj;
    public ComboBox comboBoxRespProj;
    public Button back0S;
    public Button fzDeleteBut;
    public Button fzReActivBut;
    public Button back0S1;
    public Button respProjDeleteBut;
    public Button respProjReActivBut;
    public Button devizDeleteBut;
    public Button devizReActivBut;
    public Button orgDeleteBut;
    public Button orgReActivBut;
    public Button contractDeleteBut;
    public Button contractReActivBut;
    public Button ctInvDeleteBut;
    public Button ctInvReActivBut;
    public Button nrProjReActivBut;
    public Button nrProjDeleteBut;
    public Button ctFzReActivBut;
    public Button ctFzDeleteBut;

    public String pathFileFz = "C:\\Investitii\\resurse\\fz";
    public String pathFileFzTmp = "C:\\Investitii\\resurse\\fzTmp";
    public String pathFileContract = "C:\\Investitii\\resurse\\contract";
    public String pathFileContractTmp = "C:\\Investitii\\resurse\\contractTmp";

    public String pathFileCtInv = "C:\\Investitii\\resurse\\ctInv";
    public String pathFileCtInvTmp = "C:\\Investitii\\resurse\\ctInvTmp";

    public String pathFileCtFz = "C:\\Investitii\\resurse\\ctFz";
    public String pathFileCtFzTmp = "C:\\Investitii\\resurse\\ctFzTmp";

    public String pathFileNrProj = "C:\\Investitii\\resurse\\newproj";
    public String pathFileNrProjTmp = "C:\\Investitii\\resurse\\newprojTmp";

    public String pathFileRespProj = "C:\\Investitii\\resurse\\respproj";
    public String pathFileRespProjTmp = "C:\\Investitii\\resurse\\respprojTmp";

    public String pathFileDeviz = "C:\\Investitii\\resurse\\deviz";
    public String pathFileDevizTmp = "C:\\Investitii\\resurse\\devizTmp";

    public String pathFileOrg = "C:\\Investitii\\resurse\\org";
    public String pathFileOrgTmp = "C:\\Investitii\\resurse\\orgTmp";

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        
        fzDeleteBut.setDisable( true );
        fzReActivBut.setDisable( true );
        respProjDeleteBut.setDisable( true );
        respProjReActivBut.setDisable( true );
         devizDeleteBut.setDisable( true );
         devizReActivBut.setDisable( true );
         orgDeleteBut.setDisable( true );
         orgReActivBut.setDisable( true );
         contractDeleteBut.setDisable( true );
         contractReActivBut.setDisable( true );
         ctInvDeleteBut.setDisable( true );
         ctInvReActivBut.setDisable( true );
         nrProjReActivBut.setDisable( true );
         nrProjDeleteBut.setDisable( true );
         ctFzReActivBut.setDisable( true );
         ctFzDeleteBut.setDisable( true );


        List<String> myListFz = null;
        // List<String> myListCUI = null;
        List<String> myListContract = null;
        List<String> myListCtInvest = null;
        List<String> myListCtFz = null;
        List<String> myListNrProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines( (Paths.get( pathFileFz )) );
            myListContract = Files.readAllLines( ( Paths.get(pathFileContract) ));
            myListCtInvest = Files.readAllLines( ( Paths.get(pathFileCtInv) ));
            myListCtFz = Files.readAllLines( ( Paths.get(pathFileCtFz) ));
            myListDeviz = Files.readAllLines( ( Paths.get(pathFileDeviz) ));
            myListOrg = Files.readAllLines( ( Paths.get(pathFileOrg) ));
            myListRespProj = Files.readAllLines( ( Paths.get(pathFileRespProj) ));
            myListNrProj = Files.readAllLines( (Paths.get( pathFileNrProj ) ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxFz.setItems( FXCollections.observableArrayList( myListFz ) );
        comboBoxContr.setItems( FXCollections.observableArrayList(myListContract));
        comboBoxCtInv.setItems( FXCollections.observableArrayList(myListCtInvest));
        comboBoxCtFz.setItems( FXCollections.observableArrayList(myListCtFz));
        comboBoxDeviz.setItems( FXCollections.observableArrayList(myListDeviz));
        comboBoxOrg.setItems( FXCollections.observableArrayList(myListOrg));
        comboBoxRespProj.setItems( FXCollections.observableArrayList(myListRespProj));
        comboBoxNrProj.setItems( FXCollections.observableArrayList(myListNrProj));

}

    public void deactBtnFz() {
                fzDeleteBut.setDisable( false );
                fzReActivBut.setDisable( false );
    }
    public void deactBtnContract() {
                contractDeleteBut.setDisable( false );
                contractReActivBut.setDisable( false );
    }
    public void deactBtnCtInv() {
        ctInvDeleteBut.setDisable( false );
        ctInvReActivBut.setDisable( false );
    }
    public void deactBtnCtFz() {
        ctFzDeleteBut.setDisable( false );
        ctFzReActivBut.setDisable( false );
    }
    public void deactBtnNrProj() {
        nrProjDeleteBut.setDisable( false );
        nrProjReActivBut.setDisable( false );
    }
    public void deactBtnRespProg() {
        respProjDeleteBut.setDisable( false );
        respProjReActivBut.setDisable( false );
    }
    public void deactBtnDeviz() {
        devizDeleteBut.setDisable( false );
        devizReActivBut.setDisable( false );
    }
    public void deactBtnOrg() {
        orgDeleteBut.setDisable( false );
        orgReActivBut.setDisable( false );
    }

    public void buttonBack ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void fzDeleteBut ( ActionEvent event ) throws IOException {

        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileFz, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileFz ) );
        String withINACTIV ="Arhivat-"+comboBoxFz.getValue().toString()+"\n";
        if (comboBoxFz.getValue().toString().contains( "Arhivat" )){
            fzDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxFz.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileFzTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileFz ) );
            String lineToRemove= comboBoxFz.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileFz ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileFzTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxFz.setDisable( true );
            fzDeleteBut.setDisable( true );
            fzReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxFz.getValue()+" a fost inactivat" );
            fail.showAndWait();}
    }

    public void fzReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileFz, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileFz ) );
        if (comboBoxFz.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxFz.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxFz.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileFzTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileFz ) );
            String lineToRemove = comboBoxFz.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileFz ) );
            BufferedReader br2 = new BufferedReader( new FileReader( pathFileFzTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxFz.setDisable( true );
            fzDeleteBut.setDisable( true );
            fzReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxFz.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            fzReActivBut.setDisable( true );
        }
    }

    public void ctFzDeleteBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtFz, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileCtFz ) );
        String withINACTIV ="Arhivat-"+comboBoxCtFz.getValue().toString()+"\n";
        if (comboBoxCtFz.getValue().toString().contains( "Arhivat" )){
            ctFzDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxCtFz.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileCtFzTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileCtFz ) );
            String lineToRemove= comboBoxCtFz.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileCtFz ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileCtFzTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxCtFz.setDisable( true );
            ctFzDeleteBut.setDisable( true );
            ctFzReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxCtFz.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void ctFzReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtFz, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileCtFz ) );
        if (comboBoxCtFz.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxCtFz.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxCtFz.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileCtFzTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileCtFz ) );
            String lineToRemove = comboBoxCtFz.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileCtFz ) );
            BufferedReader br2 = new BufferedReader( new FileReader(pathFileCtFzTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "ctfz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxCtFz.setDisable( true );
            ctFzDeleteBut.setDisable( true );
            ctFzReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxCtFz.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            ctFzReActivBut.setDisable( true );
        }
    }

    public void contractDeleteBut ( ActionEvent event ) throws IOException
    { BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileContract, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileContract ) );
        String withINACTIV ="Arhivat-"+comboBoxContr.getValue().toString()+"\n";
        if (comboBoxContr.getValue().toString().contains( "Arhivat" )){
            contractDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxContr.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileContractTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileContract ) );
            String lineToRemove= comboBoxContr.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileContract ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileContractTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxContr.setDisable( true );
            contractDeleteBut.setDisable( true );
            contractReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxContr.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void contractReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileContract, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileContract ) );
        if (comboBoxContr.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxContr.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxContr.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileContractTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileContract ) );
            String lineToRemove = comboBoxContr.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileContract ) );
            BufferedReader br2 = new BufferedReader( new FileReader( pathFileContractTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxContr.setDisable( true );
            contractDeleteBut.setDisable( true );
            contractReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxContr.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            contractReActivBut.setDisable( true );
        }
    }

    public void ctInvDeleteBut ( ActionEvent event ) throws IOException {
         BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtInv, true ) );
            BufferedReader br=new BufferedReader( new FileReader( pathFileCtInv ) );
            String withINACTIV ="Arhivat-"+comboBoxCtInv.getValue().toString()+"\n";
            if (comboBoxCtInv.getValue().toString().contains( "Arhivat" )){
                ctInvDeleteBut.setDisable( true );
            }else {
                String currentLine0 = comboBoxCtInv.getValue().toString();
                while (currentLine0==br.readLine() );
                writer.write( withINACTIV );
                writer.close();
                br.close();

                BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileCtInvTmp ) );
                BufferedReader br1=new BufferedReader( new FileReader( pathFileCtInv ) );
                String lineToRemove= comboBoxCtInv.getValue().toString();
                String currentLine;
                while((currentLine = br1.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.equals(lineToRemove)) continue;
                    writer1.write(currentLine + System.getProperty("line.separator"));
                    System.out.println("tmp: "+currentLine);
                }
                writer1.close();
                br1.close();

                BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileCtInv ));
                BufferedReader br2=new BufferedReader( new FileReader( pathFileCtInvTmp ) );
                String currentLine2;
                while((currentLine2=br2.readLine()) != null) {
                    writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                    System.out.println( "fz: " + currentLine2 );
                }
                br2.close();
                writer2.close();

                comboBoxCtInv.setDisable( true );
                ctInvDeleteBut.setDisable( true );
                ctInvReActivBut.setDisable( true );

                Alert fail = new Alert( Alert.AlertType.INFORMATION );
                fail.setHeaderText( "Atentie!" );
                fail.setContentText( "Elementul "+comboBoxCtInv.getValue()+" a fost inactivat" );
                fail.showAndWait();
            }

        }

    public void ctInvReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileCtInv, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileCtInv ) );
        if (comboBoxCtInv.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxCtInv.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxCtInv.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileCtInvTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileCtInv ) );
            String lineToRemove = comboBoxCtInv.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileCtInv ) );
            BufferedReader br2 = new BufferedReader( new FileReader( pathFileCtInvTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "fz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxCtInv.setDisable( true );
            ctInvDeleteBut.setDisable( true );
            ctInvReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxCtInv.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            ctInvReActivBut.setDisable( true );
        }
    }

     public void nrProjDeleteBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileNrProj, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileNrProj ) );
        String withINACTIV ="Arhivat-"+comboBoxNrProj.getValue().toString()+"\n";
        if (comboBoxNrProj.getValue().toString().contains( "Arhivat" )){
            nrProjDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxNrProj.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileNrProjTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileNrProj ) );
            String lineToRemove= comboBoxNrProj.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileNrProj ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileNrProjTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "proj: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxNrProj.setDisable( true );
            nrProjDeleteBut.setDisable( true );
            nrProjReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxNrProj.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void nrProjReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileNrProj, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileNrProj ) );
        if (comboBoxNrProj.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxNrProj.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxNrProj.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileNrProjTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileNrProj ) );
            String lineToRemove = comboBoxNrProj.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileNrProj ) );
            BufferedReader br2 = new BufferedReader( new FileReader( pathFileNrProjTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "nrProj: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxNrProj.setDisable( true );
            nrProjDeleteBut.setDisable( true );
            nrProjReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxNrProj.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            nrProjReActivBut.setDisable( true );
        }
    }

    public void respProjDeleteBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileRespProj, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileRespProj ) );
        String withINACTIV ="Arhivat-"+comboBoxRespProj.getValue().toString()+"\n";
        if (comboBoxRespProj.getValue().toString().contains( "Arhivat" )){
            respProjDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxRespProj.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileRespProjTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileRespProj ) );
            String lineToRemove= comboBoxRespProj.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileRespProj ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileRespProjTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "RespPproj: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxRespProj.setDisable( true );
            respProjDeleteBut.setDisable( true );
            respProjReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxRespProj.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void respProjReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileRespProj, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileRespProj ) );
        if (comboBoxRespProj.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxRespProj.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxRespProj.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileRespProjTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileRespProj ) );
            String lineToRemove = comboBoxRespProj.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileRespProj ) );
            BufferedReader br2 = new BufferedReader( new FileReader( pathFileRespProjTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "nrProj: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxRespProj.setDisable( true );
            respProjDeleteBut.setDisable( true );
            respProjReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxRespProj.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            respProjReActivBut.setDisable( true );
        }
    }

    public void devizDeleteBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileDeviz, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileDeviz ) );
        String withINACTIV ="Arhivat-"+comboBoxDeviz.getValue().toString()+"\n";
        if (comboBoxDeviz.getValue().toString().contains( "Arhivat" )){
            devizDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxDeviz.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileDevizTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileDeviz ) );
            String lineToRemove= comboBoxDeviz.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileDeviz ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileDevizTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "RespPproj: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxDeviz.setDisable( true );
            devizDeleteBut.setDisable( true );
            devizReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxDeviz.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void devizReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileDeviz, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileDeviz ) );
        if (comboBoxDeviz.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxDeviz.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxDeviz.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileDevizTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileDeviz ) );
            String lineToRemove = comboBoxDeviz.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileDeviz ) );
            BufferedReader br2 = new BufferedReader( new FileReader(pathFileDevizTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "Deviz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxDeviz.setDisable( true );
            devizDeleteBut.setDisable( true );
            devizReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxDeviz.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            devizReActivBut.setDisable( true );
        }
    }

    public void orgDeleteBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileOrg, true ) );
        BufferedReader br=new BufferedReader( new FileReader( pathFileOrg ) );
        String withINACTIV ="Arhivat-"+comboBoxOrg.getValue().toString()+"\n";
        if (comboBoxOrg.getValue().toString().contains( "Arhivat" )){
            orgDeleteBut.setDisable( true );
        }else {
            String currentLine0 = comboBoxOrg.getValue().toString();
            while (currentLine0==br.readLine() );
            writer.write( withINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileOrgTmp ) );
            BufferedReader br1=new BufferedReader( new FileReader( pathFileOrg ) );
            String lineToRemove= comboBoxOrg.getValue().toString();
            String currentLine;
            while((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer1.write(currentLine + System.getProperty("line.separator"));
                System.out.println("tmp: "+currentLine);
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileOrg ));
            BufferedReader br2=new BufferedReader( new FileReader( pathFileOrgTmp ) );
            String currentLine2;
            while((currentLine2=br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "org: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxOrg.setDisable( true );
            orgDeleteBut.setDisable( true );
            orgReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul "+comboBoxOrg.getValue()+" a fost inactivat" );
            fail.showAndWait();
        }
    }

    public void orgReActivBut ( ActionEvent event ) throws IOException {
        BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileOrg, true ) );
        BufferedReader br = new BufferedReader( new FileReader( pathFileOrg ) );
        if (comboBoxOrg.getValue().toString().contains( "Arhivat" ) ){
            String withoutINACTIV = comboBoxOrg.getValue().toString().replace( "Arhivat-", "" ) + "\n";
            String currentLine0 = comboBoxOrg.getValue().toString();
            while (currentLine0 == br.readLine()) ;
            writer.write( withoutINACTIV );
            writer.close();
            br.close();

            BufferedWriter writer1 = new BufferedWriter( new FileWriter( pathFileOrgTmp ) );
            BufferedReader br1 = new BufferedReader( new FileReader( pathFileOrg ) );
            String lineToRemove = comboBoxOrg.getValue().toString();
            String currentLine;
            while ((currentLine = br1.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals( lineToRemove )) continue;
                writer1.write( currentLine + System.getProperty( "line.separator" ) );
                System.out.println( "tmp: " + currentLine );
            }
            writer1.close();
            br1.close();

            BufferedWriter writer2 = new BufferedWriter( new FileWriter( pathFileOrg ) );
            BufferedReader br2 = new BufferedReader( new FileReader(pathFileOrgTmp ) );
            String currentLine2;
            while ((currentLine2 = br2.readLine()) != null) {
                writer2.write( currentLine2 + System.getProperty( "line.separator" ) );
                System.out.println( "Deviz: " + currentLine2 );
            }
            br2.close();
            writer2.close();

            comboBoxOrg.setDisable( true );
            orgDeleteBut.setDisable( true );
            orgReActivBut.setDisable( true );

            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Elementul " + comboBoxOrg.getValue() + " a fost Activat" );
            fail.showAndWait();
        } else {
            orgReActivBut.setDisable( true );
        }
    }
}
