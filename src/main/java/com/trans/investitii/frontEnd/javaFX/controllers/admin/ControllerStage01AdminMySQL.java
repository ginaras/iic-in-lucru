package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static main.java.com.trans.investitii.backEnd.DBase.Investitii.*;

public class ControllerStage01AdminMySQL implements Initializable {


    public Button backupFacturiXLS;
    public Button backupOrgXLS;
    public Button backupProjXLS;
    public Button backupContracteXLS;

    public Button createBackupMySQLButton;

    public Button confirmLoadBackupButton;
    public Button mySQLBackupFacturaButton;
    public Button mySQLBackupOrgButton;
    public Button mySQLBacKupProjButton;
    public Button mySQLBacKupContractButton;

    public Button back;

    Connection connectionInv = DriverManager.getConnection( URL, USER,PASSWORD );
    Statement statementINV=connectionInv.createStatement();

    LocalDateTime date0 = LocalDateTime.now();
    DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
    String replaceNume2 = date0.format( date2 );

    public ControllerStage01AdminMySQL() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mySQLBacKupContractButton.setDisable(true);
        mySQLBackupFacturaButton.setDisable(true);
        mySQLBackupOrgButton.setDisable(true);
        mySQLBacKupProjButton.setDisable(true);

    }



    public void backAct(ActionEvent event) throws IOException, SQLException {
        statementINV.execute( USE_DATABASE );
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        Stage window = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void backupFacturiXLSButtonAct(ActionEvent actionEvent) throws IOException, SQLException {
        String query = "SELECT * FROM invTBL";

        try {
        BufferedWriter writer0 = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\"+replaceNume2+" - Total facturi.csv", false ) );
        writer0.append( "nrCrt, furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare_ramasa, descriereFactura, nrPIF, Data_PIF, valoare_Initiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect" );
        writer0.close();

        ResultSet rsSelectXLS = statementINV.executeQuery( query );
        while (rsSelectXLS.next()) {
            Integer nrCrtPrint = rsSelectXLS.getInt( "nrCrt" );
            String furnizorPrint = rsSelectXLS.getString( "furnizor" );
            String nrFacturaPrint = rsSelectXLS.getString( "nrFactura" );
            Date dataFacturiiPrint = rsSelectXLS.getDate( "dataFacturii" );
            Date dataContabilizariiPrint = rsSelectXLS.getDate( "dataContabilizarii" );
            String valoarePrint = rsSelectXLS.getString( "valoare" );
            String descrierePrint = rsSelectXLS.getString("descriereFactura");
            String nrPIFPrint = rsSelectXLS.getString( "nrPIF" );
            Date dataPIFPrint = rsSelectXLS.getDate( "dataPIF" );
            String valoareInitPrint = rsSelectXLS.getString( "valInitiala" );
            String tvaPrint = rsSelectXLS.getString( "tva" );
            String valTotPrint = rsSelectXLS.getString( "valTot" );
            String contractPrint = rsSelectXLS.getString( "contract" );
            String contInvPrint = rsSelectXLS.getString( "contInv" );
            String contFzPrint = rsSelectXLS.getString( "contFz" );
            String nrProiectPrint = rsSelectXLS.getString( "nrProiect" );
            String devizPrint = rsSelectXLS.getString( "deviz" );
            String orgPrint = rsSelectXLS.getString( "org" );
            String respProiectPrint = rsSelectXLS.getString( "respProiect" );


            String datele = nrCrtPrint + "," +furnizorPrint + "," +nrFacturaPrint + "," + dataFacturiiPrint + "," + dataContabilizariiPrint + "," +valoarePrint +","+descrierePrint+","+nrPIFPrint+","+dataPIFPrint+ "," +valoareInitPrint + "," +tvaPrint + "," +valTotPrint + "," +contractPrint + "," +contInvPrint + "," +contFzPrint
                    + "," +nrProiectPrint + "," +devizPrint + "," +orgPrint + "," +respProiectPrint;
            BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\"+replaceNume2+" - Total facturi.csv", true ) );
            writer.append( " \n" );
            writer.append( datele );
            writer.close();
        }
            Alert alert =new Alert( Alert.AlertType.INFORMATION, "A fost creat backup CSV pentru facturi" );
            alert.showAndWait();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        System.out.println(replaceNume2 + "  S-a creat backup CSV pentru facturi (invTBL)");
    }

    public void backupOrgXLSButtonAct(ActionEvent actionEvent) throws IOException, SQLException {
        try {
            BufferedWriter writerOrg = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detaliu organizatii.csv", false ) );
            writerOrg.append( "Numar curent, Organizatie, Denumire organizatie, Anul bugetar, Valoare initiala, Valoare rectificata, Valoare finala" );
            writerOrg.close();

            ResultSet rsXLSOrg = statementINV.executeQuery( "SELECT * FROM bugetOrg" );
            while (rsXLSOrg.next()){
                Integer nrCrtPrint = rsXLSOrg.getInt( "nrCrt" );
                String orgPrint = rsXLSOrg.getString( "org" );
                String denOrgPrint = rsXLSOrg.getNString("denumireOrg");
                String anulBugetarPrint = rsXLSOrg.getString("anulBugetar");
                String valoareInitialaPrint =rsXLSOrg.getString( "valInitiala" );
                String valoareRectificataPrint =rsXLSOrg.getString( "valRectificata" );
                String valFinalaPrint = rsXLSOrg.getString( "valFinala" );

                BufferedWriter writerOrg1 = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detaliu organizatii.csv", true ) );
                writerOrg1.append( "\n" );
                writerOrg1.append( +nrCrtPrint+","+orgPrint+","+denOrgPrint+","+anulBugetarPrint+","+valoareInitialaPrint+","+valoareRectificataPrint+","+valFinalaPrint );
                writerOrg1.close();
            }
            Alert alert =new Alert( Alert.AlertType.INFORMATION, "A fost creat backup CSV pentru Organizatii" );
            alert.showAndWait();
            System.out.println(replaceNume2 + "  A fost creat un bakup CSV pentru baza de date - bugetORG");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void backupProjXLSButtonAct(ActionEvent actionEvent) {
        try {
            BufferedWriter writerProj = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detalii proiecte.csv", false ) );
            writerProj.append( "Numar curent, NR. Proiect, Denumire proiect, Data de incepere a proiectului, Valoare initiala, Valoare rectificata, Valoare finala" );
            writerProj.close();

            ResultSet rsXLSProj = statementINV.executeQuery( "SELECT * FROM bugetProj" );
            while (rsXLSProj.next()){
                Integer nrCrtPrint = rsXLSProj.getInt( "nrCrt" );
                String projPrint = rsXLSProj.getString( "nrProiect" );
                String denprojPrint = rsXLSProj.getNString("denProiect");
                String startProiectPrint = rsXLSProj.getString("startProiect");
                String valoareInitialaPrint =rsXLSProj.getString( "valInitiala" );
                String valoareRectificataPrint =rsXLSProj.getString( "valRectificare" );
                String valFinalaPrint = rsXLSProj.getString( "valFinala" );

                BufferedWriter writerProj1 = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detalii proiecte.csv", true ) );
                writerProj1.append( "\n" );
                writerProj1.append( +nrCrtPrint+","+projPrint+","+denprojPrint+","+startProiectPrint+","+valoareInitialaPrint+","+valoareRectificataPrint+","+valFinalaPrint );
                writerProj.close();
            }
            Alert alert =new Alert( Alert.AlertType.INFORMATION, "A fost creat backup CSV pentru PROIECTE" );
            alert.showAndWait();
            System.out.println(replaceNume2 + "  A fost creat un bakup CSV pentru baza de date - bugetPROJ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void backupContracteXLSButtonAct(ActionEvent actionEvent) {
        try {
            BufferedWriter writerProj = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detalii contracte.csv", false ) );
            writerProj.append( "Numar curent, NR. Proiect, Denumire proiect, Data de incepere a proiectului, Valoare initiala, Valoare rectificata, Valoare finala" );
            writerProj.close();

            ResultSet rsXLSProj = statementINV.executeQuery( "SELECT * FROM bugetContract" );
            while (rsXLSProj.next()){
                Integer nrCrtPrint = rsXLSProj.getInt( "nrCrt" );
                String fzPrint = rsXLSProj.getString( "furnizor" );
                String CUIPrint = rsXLSProj.getNString("CUIfurnizor");
                String adresaPrint = rsXLSProj.getString("adresa");
                String nrContractPrint = rsXLSProj.getString("nrContract");
                String dataContractPrint = rsXLSProj.getString("dataContract");
                String valoareInitialaPrint =rsXLSProj.getString( "valInitiala" );
                String valoareRectificataPrint =rsXLSProj.getString( "valRectificare" );
                String valFinalaPrint = rsXLSProj.getString( "valFinala" );

                BufferedWriter writerProj1 = new BufferedWriter( new FileWriter( "C:\\Investitii\\backup\\" + replaceNume2 + " - Detalii contracte.csv", true ) );
                writerProj1.append( "\n" );
                writerProj1.append( +nrCrtPrint+","+fzPrint+","+CUIPrint+","+adresaPrint+","+nrContractPrint+","+dataContractPrint+","+valoareInitialaPrint+","+valoareRectificataPrint+","+valFinalaPrint );
                writerProj.close();
            }
            Alert alert =new Alert( Alert.AlertType.INFORMATION, "A fost creat backup CSV pentru CONTRACTE" );
            alert.showAndWait();
            System.out.println(replaceNume2 + "  A fost creat un bakup CSV pentru baza de date - bugetContract");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void confirmLoadBackupButtonAct(ActionEvent actionEvent) throws SQLException {
        try (Connection connectionInvBackup = DriverManager.getConnection( URLbackup, USER, PASSWORD )){
            Statement statementInvBackup =connectionInvBackup.createStatement();
            if (connectionInvBackup!=null) {


                Alert alert0 = new Alert( Alert.AlertType.CONFIRMATION, "Activati butoanele de de backup? Atentie! La apasarea butoanelor de mai jos vor fi inlocuite bazele date existente cu cele salvate anterior ", ButtonType.YES, ButtonType.NO );
                alert0.setHeaderText( " " );
                Button noButton = (Button) alert0.getDialogPane().lookupButton( ButtonType.NO );
                noButton.setDefaultButton( true );
                Button yesButton = (Button) alert0.getDialogPane().lookupButton( ButtonType.YES );
                yesButton.setDefaultButton( false );
                alert0.showAndWait();

                if (alert0.getResult() == ButtonType.YES) {
                    mySQLBacKupContractButton.setDisable( false );
                    mySQLBackupFacturaButton.setDisable( false );
                    mySQLBackupOrgButton.setDisable( false );
                    mySQLBacKupProjButton.setDisable( false );

                }
                if (alert0.getResult() == ButtonType.NO) {

                }
            }
    } catch (SQLException throwables) {
//        throwables.printStackTrace();
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Nu exista inca o baza de date de backup!" );
            alert.showAndWait();
    }
    }

        public void mySQLBackupFacturiButtonAct(ActionEvent actionEvent) throws SQLException {

        String takeTBL_from_invTBL_Backup = "INSERT INTO invDB.invTBL SELECT * FROM invDBbackup.invTBL";
        String dropInvTBL ="DROP TABLE invDB.invTBL";

        statementINV.execute( dropInvTBL );
        statementINV.execute( USE_DATABASE );
        statementINV.execute( CREATE_TABLE );
        statementINV.execute( takeTBL_from_invTBL_Backup );

            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Au fost preluate datele FACTURILOR de la ultimul backup" );
            alert.showAndWait();

        System.out.println( replaceNume2 + "   Au fost preluate datele din Backup facturi - invTBL");
        }

    public void mySQLBackupOrgButtonAct(ActionEvent actionEvent) throws SQLException {
        String takeTBL_from_BugetOrg_Backup = "INSERT INTO invDB.bugetOrg SELECT * from invDBbackup.bugetOrg";
        String dropBugetOrg = "DROP TABLE invDB.bugetOrg ";

        statementINV.execute( dropBugetOrg );
        statementINV.execute( USE_DATABASE );
        statementINV.execute( CREATE_TABLE_BUGET_ORG );
        statementINV.execute(takeTBL_from_BugetOrg_Backup );

        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setHeaderText( "Au fost preluate datele ORGANIZATIILOR de la ultimul backup" );
        alert.showAndWait();

        System.out.println( replaceNume2 + "  Au fost preluate datele din Backup bugetOrg");
    }

    public void mySQLBackupProjButton(ActionEvent actionEvent) throws SQLException {
        String dropBugetProj = "DROP TABLE invDB.bugetProj";
        String takeTBL_from_bugetProj_backup ="INSERT INTO invDB.bugetProj SELECT * FROM invDBbackup.bugetProj";

        statementINV.execute( dropBugetProj );
        statementINV.execute( USE_DATABASE );
        statementINV.execute( CREATE_TABLE_BUGET_PROJ );
        statementINV.execute( takeTBL_from_bugetProj_backup );

        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setHeaderText( "Au fost preluate datele PROIECTELOR de la ultimul backup" );
        alert.showAndWait();

        System.out.println(replaceNume2 + "  Au fost preluate datele din Backup bugetProject");
    }

    public void mySQLBackupContractButtonAct(ActionEvent actionEvent) throws SQLException {
        String  dropBugetContracte = "DROP TABLE invDB.bugetContract";
        String takeTBL_from_BugetContract_backup = "INSERT INTO invDB.bugetContract SELECT * FROM invDBbackup.bugetContract";

        statementINV.execute( dropBugetContracte );
        statementINV.execute( USE_DATABASE );
        statementINV.execute( CREATE_TABLE_BUGET_CONTRACT );
        statementINV.execute( takeTBL_from_BugetContract_backup );

        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setHeaderText( "Au fost preluate datele CONTRACTELOR de la ultimul backup" );
        alert.showAndWait();

        System.out.println(replaceNume2 + "  Au fost preluate datele din Backup bugetContract");

    }

    public void createBackupMySQLButtonAct(ActionEvent actionEvent) throws SQLException {

        String createDBBugetContract_Backup="INSERT INTO invDBbackup.bugetContract SELECT * from invDB.bugetContract";
        String createDBBugetOrg_Backup = "INSERT INTO invDBbackup.bugetOrg SELECT * from invDB.bugetOrg";
        String createDBBugetProj_Backup= "INSERT INTO invDBbackup.bugetProj SELECT * from invDB.bugetProj";
        String createDBInvTBL_Backup= "INSERT INTO invDBbackup.invTBL SELECT * from invDB.invTBL";

        try {
            statementINV.executeUpdate( DROP_DATABASE_backup );
            statementINV.executeUpdate( CREATE_DATABASE_backup );
            statementINV.executeUpdate( USE_DATABASE_backup );
            statementINV.executeUpdate( CREATE_TABLE );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_ORG );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_PROJ );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_CONTRACT );

            statementINV.execute( createDBInvTBL_Backup );
            statementINV.execute( createDBBugetOrg_Backup );
            statementINV.execute( createDBBugetProj_Backup );
            statementINV.execute( createDBBugetContract_Backup );

            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "S-au creat bazele de date pt Backup" );
            alert.showAndWait();

            System.out.println( replaceNume2+"  S-au creat bazele de date pt Backup in MySQL");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
