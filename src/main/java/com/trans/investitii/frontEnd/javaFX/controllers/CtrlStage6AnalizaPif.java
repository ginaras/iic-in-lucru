package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage6AnalizaPif<dataInceput> implements Initializable {
    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonOrg;

    public Button buttonBackSt0;
    public Button butonStage1Intro;
    public Button butonStage2Rapoarte;
    public Button butonStage3Rapoarte;
    public Button goToStage4Pif;
    public Button buttonStage5Solduri;
    public Button resetButton;
    public Button buttonFilter;

    public DatePicker dataInceput;
    public DatePicker dataSfarsit;

    public TableView <Investitii>tableViewPifProiecte;
    public TableColumn <Investitii, String> collOrg;
    public TableColumn <Investitii, String> collProj;
    public TableColumn <Investitii, String> collDenProj;
    public TableColumn <Investitii, String> collNrPif;
    public TableColumn <Investitii, String> collDataPif;
    public TableColumn <Investitii, String> collValPif;
    public TableColumn <Investitii, String> collResp;

    public ObservableList <Investitii> tablePifProiecte;
    public Label totalPifInPerioada;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();

    ResultSet rs1 = stm.executeQuery( "SELECT org, invTBL.nrProiect, denProiect, nrPIF, dataPIF, respProiect, Round(SUM(invTBL.valInitiala), 2) as valInitiala FROM invTBL, bugetProj WHERE invTBL.nrPIF!='null' AND bugetProj.nrProiect=invTBL.nrProiect  GROUP BY nrPIF " );

    private URL location;
    private ResourceBundle resources;

    public CtrlStage6AnalizaPif() throws SQLException {
    }
    /*
    * am tabelul tableViewPifProiecte - fizic in stage
    * Fac o Observable list :
    *               ObservableList <Investitii> tablePifProiecte
    * Populez ObservableList ....
     */


    @Override
    public void initialize  (URL location, ResourceBundle resources) {
        if (dataInceput.getValue()==null || dataSfarsit==null){
            dataSfarsit.setValue( LocalDate.now());
            dataInceput.setValue(LocalDate.ofEpochDay(01/01/2021));
        }

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        comboBoxButtonProj.setDisable(true);
        try {
            comboBoxButtonOrg.setItems(FXCollections.observableArrayList(Files.readAllLines(Paths.get("C:/Investitii/resurse/org"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.location = location;
        this.resources = resources;

        collOrg.setCellValueFactory(new PropertyValueFactory<>("org"));
        collProj.setCellValueFactory(new PropertyValueFactory<>("nrProiect"));
        collDenProj.setCellValueFactory(new PropertyValueFactory<>("denProiect"));
        collNrPif.setCellValueFactory(new PropertyValueFactory<>("nrPIF"));
        collDataPif.setCellValueFactory(new PropertyValueFactory<>("dataPIF"));
        collValPif.setCellValueFactory(new PropertyValueFactory<>("valInitiala"));
        collResp.setCellValueFactory(new PropertyValueFactory<>("respProiect"));

        tablePifProiecte=  FXCollections.observableArrayList();
        double totalPIF=0;
        try {
            while (rs1.next() ){//&& rsOrg.next()
                tablePifProiecte.addAll(new Investitii(
                    rs1.getString("org"),
                    rs1.getString("nrProiect"),
                    rs1.getString("denProiect"),
                    rs1.getString("nrPIF"),
                    rs1.getDate("dataPIF"),
                    rs1.getString("valInitiala"),
                    rs1.getString("respProiect")  ));

                totalPIF += rs1.getDouble("valInitiala");
            }
            tableViewPifProiecte.setItems(tablePifProiecte);
            totalPifInPerioada.setText(df.format(totalPIF));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void comboBoxActOrg ( ActionEvent actionEvent ) throws SQLException {
        comboBoxButtonProj.setDisable(false);
        List <String> listProject = new ArrayList<>();
        ResultSet rsPopulareComboBoxProj = stm.executeQuery("SELECT nrProiect FROM invTBL WHERE nrPif != 'null'");
            try {

                while(rsPopulareComboBoxProj.next()){
                    listProject.add(rsPopulareComboBoxProj.getString("nrProiect"));
                }
                List <String> noDoubleListProject = listProject.stream().distinct().collect(Collectors.toList());
                comboBoxButtonProj.setItems(FXCollections.observableList(noDoubleListProject));

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void comboBoxActProj ( ActionEvent actionEvent ) throws SQLException {

    }


    public void apliFiter ( ActionEvent actionEvent ) throws SQLException {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("ro", "RO"));
        nf.setMaximumFractionDigits(2);
        DecimalFormat df = (DecimalFormat) nf;
        if (dataInceput.getValue() != null && dataSfarsit.getValue() != null && comboBoxButtonProj.getValue() == null && comboBoxButtonOrg.getValue() != null) {
            tablePifProiecte = FXCollections.observableArrayList();
            ResultSet rsProj = stm.executeQuery("SELECT org, invTBL.nrProiect, denProiect, nrPIF, dataPIF, respProiect, Round(SUM(invTBL.valInitiala), 2) as valInitiala FROM invTBL, bugetProj WHERE invTBL.nrPIF!='null' AND bugetProj.nrProiect=invTBL.nrProiect AND dataPIF >=  '" + dataInceput.getValue() + "' AND dataPIF <= '" + dataSfarsit.getValue() + "' GROUP BY nrPIF ");
            double totalPIFproj = 0;

            try {
                while (rsProj.next()) {
                    tablePifProiecte.addAll(new Investitii(
                            rsProj.getString("org"),
                            rsProj.getString("nrProiect"),
                            rsProj.getString("denProiect"),
                            rsProj.getString("nrPIF"),
                            rsProj.getDate("dataPIF"),
                            rsProj.getString("valInitiala"),
                            rsProj.getString("respProiect")));

                    totalPIFproj += rsProj.getDouble("valInitiala");
                }
                tableViewPifProiecte.setItems(tablePifProiecte);
                totalPifInPerioada.setText(df.format(totalPIFproj));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            tablePifProiecte = FXCollections.observableArrayList();
            ResultSet rsProj = stm.executeQuery("SELECT org, invTBL.nrProiect, denProiect, nrPIF, dataPIF, respProiect, Round(SUM(invTBL.valInitiala), 2) as valInitiala FROM invTBL, bugetProj WHERE invTBL.nrPIF!='null' AND bugetProj.nrProiect=invTBL.nrProiect AND invtbl.nrProiect='" + comboBoxButtonProj.getValue() + "' AND dataPIF >=  '" + dataInceput.getValue() + "' AND dataPIF <= '" + dataSfarsit.getValue() + "' GROUP BY nrPIF ");
            double totalPIFproj = 0;

            try {
                while (rsProj.next()) {
                    tablePifProiecte.addAll(new Investitii(
                            rsProj.getString("org"),
                            rsProj.getString("nrProiect"),
                            rsProj.getString("denProiect"),
                            rsProj.getString("nrPIF"),
                            rsProj.getDate("dataPIF"),
                            rsProj.getString("valInitiala"),
                            rsProj.getString("respProiect")));

                    totalPIFproj += rsProj.getDouble("valInitiala");
                }
                tableViewPifProiecte.setItems(tablePifProiecte);
                totalPifInPerioada.setText(df.format(totalPIFproj));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void resetAct ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPIF.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();

    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goOnStage1Intro ( ActionEvent actionEvent ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goOnStage2Rapoarte ( ActionEvent actionEvent ) throws IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnStage3Rapoarte ( ActionEvent actionEvent ) throws IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage4Pif ( ActionEvent actionEvent ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4Pif.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void goToStage5Solduri ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

}
