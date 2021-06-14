package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.com.trans.investitii.backEnd.DBase.Investitii;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static main.java.com.trans.investitii.backEnd.DBase.Investitii.*;
//import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

public class Controller0Sample implements Initializable {


    @FXML
    public Button goToStage1Intro;
    public Button adminFZ;
    public Button adminContracte;
    public Button adminCtInvest;
    public Button adminCtFz;
    public Button adminNrProiect;
    public Button adminRespProiect;
    public Button adminDeviz;
    public Button adminOrg;
    public Button goToStageBDelete;
    public Button goToStage2Rapoarte;
    public Button buttonStage3Sumar;
    public Button goToStage4Pif;
    public Button goToStageNewUnit;
    public Button goToStage03AdminBuget;
    public Button buttonStage5Solduri;
    public Button buttonStage6AnalizaPif;
    public Button goToStage01AdminMySQL;
    public Button startButton;

    private void goToStage(){    }

    public void goToStage1Intro( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goToStage2Rapoarte ( ActionEvent event ) throws SQLException, IOException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goToStage3Sumar ( ActionEvent event ) throws SQLException, IOException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goToStage4Pif ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4PIF.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage03AdminBugetAction ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/admin/Stage03AdminBugete.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage5Solduri ( ActionEvent event ) throws SQLException, IOException {
        getConectionNew();
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage6AnalizaPif ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPif.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public Connection getConectionNew () throws SQLException, FileNotFoundException {
//        Connection connection =DriverManager.getConnection( Investitii.URL0, Investitii.USER, Investitii.PASSWORD );
//        Statement statement = connection.createStatement();
//        try {
//            statement.execute( CREATEUSERNAME );
//            statement.execute( "GRANT ALL PRIVILEGES ON * . * TO 'investitii'@'localhost'");
//        }
//        catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        Connection connectionInv = DriverManager.getConnection( URLinv, USERinv,PASSWORDinv );
            Statement statementINV=connectionInv.createStatement();
//        try {
//        statement.executeUpdate( CREATE_DATABASE );
//        statement.executeUpdate( USE_DATABASE );
//        statement.executeUpdate( CREATE_TABLE );
//        statement.executeUpdate( CREATE_TABLE_BUGET_ORG );
//        statement.executeUpdate( CREATE_TABLE_BUGET_PROJ );
//        statement.executeUpdate( CREATE_TABLE_BUGET_CONTRACT );
//
//        statementINV.executeUpdate( CREATE_DATABASE );
//        statementINV.executeUpdate( USE_DATABASE );
//        statementINV.executeUpdate( CREATE_TABLE );
//        statementINV.executeUpdate( CREATE_TABLE_BUGET_ORG );
//        statementINV.executeUpdate( CREATE_TABLE_BUGET_PROJ );
//        statementINV.executeUpdate( CREATE_TABLE_BUGET_CONTRACT );
//        } catch (SQLException throwables) {
//
//        }
        return null;
    }

    public void goOnAdminFz ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminFz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

      //  ExportItemsInList.exportFzList();
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

    public void goOnAdminNrProiect ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
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

    public void goOnAdminOrg ( ActionEvent event ) throws IOException, SQLException {
        getConectionNew();
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminOrg.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goToStageBDelete ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageBAdminDelete.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {
// create & print err in File
        File file = new File( "c:\\Investitii" );
        File file1 = new File( "c:\\Investitii\\resurse" );
        File file2= new File( "c:\\Investitii\\rapoarte" );
        File log = new File( "c:\\Investitii\\resurse\\log" );
        File mySQL= new File( "C:\\Investitii\\resurse\\log\\MySQL" );
        File mySQLerrSampl0= new File( "C:\\Investitii\\resurse\\log\\MySQLerrSampl0" );
        boolean fileExists = file.mkdir();
        boolean fileExists1 = file1.mkdir();
        boolean fileExists2 = file2.mkdir();
        boolean fileExists5 = log.mkdir();


        String pathSQL = "C:\\Investitii\\resurse\\log\\MySqL";
        String pathSQLerr0 = "C:\\Investitii\\resurse\\log\\MySQLerrSampl0";


        //!!!!!!!! Printare erori in fisier

//        PrintStream out = null;
//        try {
//            out = new PrintStream(new FileOutputStream(pathSQLerr0));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.setOut(out);
//        System.setErr(out);
//        LocalDateTime date0 = LocalDateTime.now();
//        DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
//        String dataErr = date0.format( date2 );
//        System.out.println("DAY OF ERROR IS:   "+ dataErr);

//        if(mySQL.exists()) {
//            try {
//                USER= Files.readAllLines( Paths.get( pathSQL ) ).get( 0 );
//                PASSWORD= Files.readAllLines( Paths.get( pathSQL ) ).get( 1 );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try (Connection connectionInvesttii = DriverManager.getConnection( URLinv,USERinv, PASSWORDinv )){
            Statement statementInv = connectionInvesttii.createStatement();
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            goToStage1Intro.setDisable(true);
//            adminFZ.setDisable(true);
//            adminContracte.setDisable(true);
//            adminCtInvest.setDisable(true);
//            adminCtFz.setDisable(true);
//            adminNrProiect.setDisable(true);
//            adminRespProiect.setDisable(true);
//            adminDeviz.setDisable(true);
//            adminOrg.setDisable(true);
//            goToStageBDelete.setDisable(true);
//            goToStage2Rapoarte.setDisable(true);
//            buttonStage3Sumar.setDisable(true);
//            goToStage4Pif.setDisable(true);
//            goToStage03AdminBuget.setDisable(true);
//            buttonStage5Solduri.setDisable(true);
//            buttonStage6AnalizaPif.setDisable(true);
        }
//        if (!mySQL.exists()){
//
//
//        }


        File fz= new File("c:\\Investitii\\resurse\\fz" );
        File contract= new File("c:\\Investitii\\resurse\\contract" );
        File ctfz= new File("c:\\Investitii\\resurse\\ctFz" );
        File ctInv= new File("c:\\Investitii\\resurse\\ctInv" );
        File cui= new File("c:\\Investitii\\resurse\\cui" );
        File deviz= new File("c:\\Investitii\\resurse\\deviz" );
        File newproj= new File("c:\\Investitii\\resurse\\newproj" );
        File org= new File("c:\\Investitii\\resurse\\org" );
        File respproj= new File("c:\\Investitii\\resurse\\respproj" );
        File ani= new File("c:\\Investitii\\resurse\\ani" );
        File export = new File("c:\\Investitii\\rapoarte\\export.txt" );


        if (!fz.exists()){
            try {
                fz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!contract.exists()){
            try {
                contract.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!ctfz.exists()){
            try {
                ctfz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!ctInv.exists()){
            try {
                ctInv.createNewFile();
                FileWriter writer = new FileWriter(ctInv);
                writer.append("231.01.01.01"+"\r\n"+"231.02.01.01"+"\r\n"+"231.02.02.01"+"\r\n"+"231.03.01.01"+"\r\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!cui.exists()){
            try {
                cui.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!deviz.exists()){
            try {
                deviz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!newproj.exists()){
            try {
                newproj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!org.exists()){
            try {
                org.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!respproj.exists()){
            try {
                respproj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!ani.exists()){
            try {
                ani.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToStage01AdminMySQLAction(ActionEvent event) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/Stage01AdminMySQL.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void startAction ( ActionEvent actionEvent ) throws SQLException, FileNotFoundException {
        Connection connection =DriverManager.getConnection( Investitii.URL0, Investitii.USER, Investitii.PASSWORD );
        Statement statement = connection.createStatement();
        try {
            statement.execute( CREATEUSERNAME );
            statement.execute( "GRANT ALL PRIVILEGES ON * . * TO 'investitii'@'localhost'");
            statement.execute( selectUser );
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Connection connectionInv = DriverManager.getConnection( URLinvsys, USERinv,PASSWORDinv );
        Statement statementINV=connectionInv.createStatement();
        try {
            statementINV.executeUpdate( CREATE_DATABASE );
            statementINV.executeUpdate( USE_DATABASE );
            statementINV.executeUpdate( CREATE_TABLE );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_ORG );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_PROJ );
            statementINV.executeUpdate( CREATE_TABLE_BUGET_CONTRACT );
        } catch (SQLException throwables) {

        }    }
}

