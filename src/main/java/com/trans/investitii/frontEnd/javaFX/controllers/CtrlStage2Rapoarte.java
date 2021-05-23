package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import main.java.com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage2Rapoarte implements Initializable {

    @FXML    public Button buttonBackSt0;
    @FXML    public Button buttonSt1Intro;
    @FXML    public TableView<Investitii> tableViewTotal;
    @FXML    public TableColumn<Investitii, String> furnizorColumn;
    @FXML    public TableColumn<Investitii, String> valoareFactSoldColumn;
    @FXML    public TableColumn<Investitii, String> valoareFactInitColumn;
    @FXML    public TableColumn<Investitii, String> dataCtbColumn;
    @FXML    public TableColumn<Investitii, String> respProjColumn;
    @FXML    public TableColumn<Investitii, String> contractColumn;
    @FXML    public TableColumn<Investitii, String> contInvColumn;
    @FXML    public TableColumn<Investitii, String> contFzColumn;
    @FXML    public TableColumn<Investitii, String> orgColumn;

    public TableColumn<Investitii, String> nrProjColumn;

    public ObservableList<Investitii> tabelFacturi;
    public TableColumn<Object, Object> nrFactColumn;
    public ComboBox <String> comboBoxAlegeProj;
    public ComboBox <String> comboBoxAlegeOrg;

    public Button selectButton;
    public Button refreshButton;
    public DatePicker secondDate;
    public DatePicker firstDate;
    public Button ExportButton;
    public Button RapoarteButton;
    public Button ExportSelectieButton;
    public Button buttonStage3Sumar;
    public Button buttonStage5Solduri;
    public Button goToStage4Pif;
    public Button buttonStage6AnalizaPif;
    public Label valoareaTotalaSold;
    public Label valoareaTotalaInitiala;
    public ComboBox comboBoxAlegeProjNume;


    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();
    ResultSet rs1 = stm.executeQuery( "SELECT * FROM invTBL WHERE valoare <>'0' " );
    private URL location;
    private ResourceBundle resources;


    public CtrlStage2Rapoarte () throws SQLException {
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        this.location = location;
        this.resources = resources;
        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        nrFactColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareFactSoldColumn.setCellValueFactory( new PropertyValueFactory<>( "valoare" ) );
        valoareFactInitColumn.setCellValueFactory( new PropertyValueFactory<>( "valInitiala" ) );
        dataCtbColumn.setCellValueFactory( new PropertyValueFactory<>( "dataContabilizarii" ) );
        respProjColumn.setCellValueFactory( new PropertyValueFactory<>( "respProiect" ) );
        contractColumn.setCellValueFactory( new PropertyValueFactory<>( "contract" ) );
        contInvColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        contFzColumn.setCellValueFactory( new PropertyValueFactory<>( "contFz" ) );
        nrProjColumn.setCellValueFactory( new PropertyValueFactory<>( "nrProiect" ) );
        orgColumn.setCellValueFactory( new PropertyValueFactory<>( "org" ) );

        tabelFacturi = FXCollections.observableArrayList();
        try {
            while (rs1.next()) {
                tabelFacturi.addAll( new Investitii (
                        rs1.getString( "furnizor" ),
                        rs1.getString( "nrFactura" ),
                        rs1.getString( "valoare" ),
                        rs1.getString( "valInitiala" ),
                        rs1.getString( "dataContabilizarii" ),
                        rs1.getString( "respProiect" ),
                        rs1.getString( "contract" ),
                        rs1.getString( "contInv" ),
                        rs1.getString( "contFz" ),
                        rs1.getString( "nrProiect" ),
                        rs1.getString( "org" )) );
            }
            valoareaTotalaInitiala.setTextAlignment( TextAlignment.RIGHT );

            tableViewTotal.setItems( tabelFacturi );
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet total = stm.executeQuery( "SELECT ROUND(SUM(valoare),2) AS valoare FROM invTBL  " );
            while (total.next()) {
                double valoarea = total.getDouble( "valoare" );
                double valTotala = ++valoarea;
                valoareaTotalaSold.setText(df.format( valTotala ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            ResultSet rsTotalValInit = stm.executeQuery("SELECT ROUND(SUM(valInitiala),2) AS valInitiala FROM invTBL WHERE valoare<>'0'  ");
            while (rsTotalValInit.next()){
                double valInit = rsTotalValInit.getDouble("valInitiala");
                valoareaTotalaInitiala.setText(df.format(valInit+1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            comboBoxAlegeProj.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" )) ) ) );
            comboBoxAlegeOrg.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/org" )) ) ) );

        } catch (IOException e) {
            e.printStackTrace();
        }
        tableViewTotal.setPlaceholder(new Label("Nici o factura in combinatia selectata! Reseteaza selectia si ia-o de la capat "));


        java.util.List<String> myListNumeProj =new ArrayList<>();
        try {
           ResultSet rsProj = stm.executeQuery( "SELECT denProiect FROM bugetProj" );

           while (rsProj.next()) {
               myListNumeProj.add( rsProj.getString( "denProiect"));
            }
            java.util.List<String> noDuplicatesList = myListNumeProj.stream().distinct().collect(Collectors.toList());
                comboBoxAlegeProjNume.setItems(FXCollections.observableList( noDuplicatesList ) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void selectButtton () throws SQLException {

    String query = "SELECT * FROM invTBL WHERE ";
    String sumValSold = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE ";
    String sumValInitiala = "SELECT round(SUM(valInitiala), 2) as 'totalValInitiala' FROM invTBL WHERE ";

    Object valueProj = comboBoxAlegeProj.getValue();
    Object valueOrg = comboBoxAlegeOrg.getValue();
    Object valueFDdate = firstDate.getValue();
    Object valueSDate =secondDate.getValue();

        if( valueProj ==null && valueOrg==null && valueFDdate==null && valueSDate==null)    {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Da-mi un criteriu sa-ti pot arata ceva" );
            alert.showAndWait();
            return;
        }

        if(valueProj !=null && valueOrg==null && valueFDdate==null && valueSDate==null )    {
                query += "nrProiect='"+valueProj+"'";
                sumValSold += "nrProiect='"+valueProj+"'";
                sumValInitiala += "nrProiect='"+valueProj+"'";
         }

        if(valueOrg !=null && valueFDdate==null && valueSDate==null) {
                if (valueProj == null) {
                    query += " org ='"+valueOrg+"' ";
                    sumValSold += " org='"+valueOrg+"' ";
                } else {
                    query  += "nrProiect='"+valueProj+"' AND org='"+valueOrg+ "' ";
                    sumValSold += "nrProiect='"+valueProj+"' AND org='"+valueOrg+ "' ";
                    sumValInitiala += "nrProiect='"+valueProj+"' AND org='"+valueOrg+ "' ";
                }
         }

        if(valueFDdate !=null && valueSDate!=null) {
                if (valueProj != null && valueOrg != null) {
                    query += "nrProiect='" + valueProj+ "' AND org='" + valueOrg+ "' AND dataContabilizarii BETWEEN '" +valueFDdate+ "' AND '" +valueSDate+ "' ";
                    sumValSold += "nrProiect='" + valueProj+ "' AND org='" + valueOrg+ "' AND dataContabilizarii BETWEEN '" +valueFDdate+ "' AND '" +valueSDate+ "' ";
                    sumValInitiala += "nrProiect='" + valueProj+ "' AND org='" + valueOrg+ "' AND dataContabilizarii BETWEEN '" +valueFDdate+ "' AND '" +valueSDate+ "' ";
                }
                if (valueProj == null && valueOrg !=null){
                    query += "org='" + valueOrg + "' AND dataContabilizarii BETWEEN '" +valueFDdate + "' AND '" +valueSDate +"' ";
                    sumValSold += "org='" + valueOrg+ "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate+ "' ";
                    sumValInitiala += "org='" + valueOrg+ "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate+ "' ";

                } if (valueProj != null && valueOrg ==null){
                    query += "nrProiect='" + valueProj+ "' AND dataContabilizarii BETWEEN '" +valueFDdate + "' AND '" +valueSDate +"' ";
                    sumValSold += "nrProiect='" + valueProj+ "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate+ "' ";
                    sumValInitiala += "nrProiect='" + valueProj+ "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate+ "' ";

                }
                if (valueProj ==null && valueOrg ==null){
                    query += "dataContabilizarii BETWEEN '" + valueFDdate + "' AND '"+ valueSDate+ "' ";
                    sumValSold += "dataContabilizarii BETWEEN '" +valueFDdate.toString() + "' AND '" +valueSDate+ "' ";
                    sumValInitiala += "dataContabilizarii BETWEEN '" +valueFDdate.toString() + "' AND '" +valueSDate+ "' ";
                }
         }
        if ((valueFDdate ==null && valueSDate!=null) || (valueFDdate !=null && valueSDate==null)){
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Trebuie completate ambele campuri de date!" );
            alert.showAndWait();
            return;
        }


    tabelFacturi =FXCollections.observableArrayList();
    ResultSet rsProj = stm.executeQuery( query );
     try {
            while (rsProj.next()) {
                tabelFacturi.addAll( new Investitii(
                        rsProj.getString( "furnizor" ),
                        rsProj.getString( "nrFactura" ),
                        rsProj.getString( "valoare" ),
                        rsProj.getString( "valInitiala"),
                        rsProj.getString( "dataContabilizarii" ),
                        rsProj.getString( "respProiect" ),
                        rsProj.getString( "contract" ),
                        rsProj.getString( "contInv" ),
                        rsProj.getString( "contFz" ),
                        rsProj.getString( "nrProiect" ),
                        rsProj.getString( "org" )) );
            }
            tableViewTotal.setItems( tabelFacturi );
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
// face totalul
        Statement stm2=connection.createStatement();
        ResultSet rsProj2 = stm2.executeQuery( sumValSold );
        double totalProiect=0.00;
        try {
            while (rsProj2.next()) {
                totalProiect = rsProj2.getDouble( "totalProiect" );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        double tp =  totalProiect * 100 / 100;
        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        valoareaTotalaSold.setText( df.format( tp  ));

        ResultSet rsProj3= stm.executeQuery(sumValInitiala);
        double totalValInitiala = 0;
        try {
            while(rsProj3.next()){
                totalValInitiala=rsProj3.getDouble("totalValInitiala");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        valoareaTotalaInitiala.setText(df.format(totalValInitiala));

    }

    public void refresh ( ActionEvent event ) throws  IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void ExportXlsButton ( ActionEvent actionEvent ) {
            try {
                Connection connection = DriverManager.getConnection(Investitii.URL, Investitii.USER, Investitii.PASSWORD );
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery( "SELECT * FROM invTBL " );

//Print - Crearea si prima linie a fisierilui de raport
                LocalDateTime date0 = LocalDateTime.now();
                DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
                String replaceNume2 = date0.format( date2 );

                BufferedWriter writer0 = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv", false ) );
                writer0.append( "nrCrt, furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, descriereFactura, nr_PIF, Data_PIF, valoare_Initiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect" );
                writer0.close();
//Parcurgerea BD si extragerea datelor iterate through the java resultset
                while (rs.next()) {
                    Integer nrCrtPrint = rs.getInt( "nrCrt" );
                    String furnizorPrint = rs.getString( "furnizor" );
                    String nrFacturaPrint = rs.getString( "nrFactura" );
                    Date dataFacturiiPrint = rs.getDate( "dataFacturii" );
                    Date dataContabilizariiPrint = rs.getDate( "dataContabilizarii" );
                    String valoarePrint = rs.getString( "valoare" );
                    String descrierePrint = rs.getString("descriereFactura");
                    String nrPIFPrint = rs.getString( "nrPIF" );
                    Date dataPIFPrint = rs.getDate( "dataPIF" );
                    String valoareInitialaPrint = rs.getString( "valInitiala" );
                    String tvaPrint = rs.getString( "tva" );
                    String valTotPrint = rs.getString( "valTot" );
                    String contractPrint = rs.getString( "contract" );
                    String contInvPrint = rs.getString( "contInv" );
                    String contFzPrint = rs.getString( "contFz" );
                    String nrProiectPrint = rs.getString( "nrProiect" );
                    String devizPrint = rs.getString( "deviz" );
                    String orgPrint = rs.getString( "org" );
                    String respProiectPrint = rs.getString( "respProiect" );

//print - adaugarea datelor in fisier
                    String datele =  nrCrtPrint + "," + furnizorPrint + "," + nrFacturaPrint + "," +dataFacturiiPrint+","+ dataContabilizariiPrint+","+ valoarePrint +","+descrierePrint+ ","+nrPIFPrint+","+dataPIFPrint+","+valoareInitialaPrint + ","+tvaPrint + ","  +valTotPrint + "," +contractPrint + "," +contInvPrint + "," +contFzPrint
                            + "," +nrProiectPrint  + "," +devizPrint + "," +orgPrint  + "," +respProiectPrint;
                    BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv", true ) );
                    writer.append( " \n" );
                    writer.append( datele );
                    writer.close();
                }
                Desktop desktop = null;
                desktop.getDesktop().open( new File( "c:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv" ) );

            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
    }

    public void RapoarteOpenButton ( ActionEvent actionEvent ) {
        Desktop desktop = null;
        try {
            desktop.getDesktop().open( new File( "c:\\Investitii\\rapoarte" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ExportSelectieXlsButton ( ActionEvent actionEvent ) throws IOException, SQLException {

        String query = "SELECT * FROM invTBL WHERE ";

        Object valueProj = comboBoxAlegeProj.getValue();
        Object valueOrg = comboBoxAlegeOrg.getValue();
        Object valueFDdate = firstDate.getValue();
        Object valueSDate =secondDate.getValue();

        if(valueProj ==null && valueOrg==null && valueFDdate==null && valueSDate==null)    {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Da-mi un criteriu sa-ti pot arata ceva" );
            alert.showAndWait();
            return;
        }

        if(valueProj !=null && valueOrg==null && valueFDdate==null && valueSDate==null)    {
            query += "nrProiect='"+valueProj.toString()+"'";
        }

        if(valueOrg !=null && valueFDdate==null && valueSDate==null) {
            if (valueProj == null) {
                query += " org='"+valueOrg.toString()+"' ";
            } else {
                query  += "nrProiect='"+valueProj.toString()+"' AND org='"+valueOrg.toString()+ "' ";
            }
        }

        if(valueFDdate !=null && valueSDate!=null) {
            if (valueProj != null && valueOrg != null) {
                query += "nrProiect='" + valueProj.toString() + "' AND org='" + valueOrg.toString() + "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate.toString()+ "' ";
            }
            if (valueProj == null && valueOrg !=null){
                query += "furnizor='" + valueOrg.toString() + "' AND dataContabilizarii BETWEEN '" +valueFDdate.toString()+ "' AND '" +valueSDate.toString()+"' ";
            }
            if (valueProj ==null && valueOrg ==null){
                query += "dataContabilizarii BETWEEN '" + valueFDdate.toString() + "' AND '"+ valueSDate.toString()+ "' ";
            }
        }
        if ((valueFDdate ==null && valueSDate!=null) || (valueFDdate !=null && valueSDate==null)){
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Trebuie completate ambele campuri de date!" );
            alert.showAndWait();
            return;
        }
        try {
            LocalDateTime date0 = LocalDateTime.now();
        DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
        String replaceNume2 = date0.format( date2 );

        BufferedWriter writer0 = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv", false ) );
        writer0.append( "nrCrt, furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare_ramasa, descriereFactura, nrPIF, Data_PIF, valoare_Initiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect" );
        writer0.close();

            ResultSet rsSelectXLS = stm.executeQuery( query );
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
            BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv", true ) );
            writer.append( " \n" );
            writer.append( datele );
            writer.close();
        }
            Desktop desktop1 = null;
            desktop1.getDesktop().open( new File( "c:\\Investitii\\rapoarte\\"+replaceNume2+" - detaliu_investitii.csv" ) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comboBoxAlegeProj ( ActionEvent event ) throws SQLException {
        String selectOrg = "SELECT org FROM invTbl WHERE nrProiect ='"+ comboBoxAlegeProj.getValue()+"' ";
        Object valueOrg = comboBoxAlegeOrg.getValue();
//        comboBoxAlegeProjNume.setValue(null);

        java.util.List<String> myListOrg =new ArrayList<>();

        Statement stm=connection.createStatement();

        ResultSet rsOrg = stm.executeQuery( selectOrg );
        try {
            while (rsOrg.next()) {
                myListOrg.add( rsOrg.getString( "org"));
            }
            java.util.List<String> noDuplicatesList = myListOrg.stream().distinct().collect(Collectors.toList());
            if(valueOrg==null){
                comboBoxAlegeOrg.setItems(FXCollections.observableList( noDuplicatesList ) );}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        ResultSet rsProj = stm.executeQuery( "SELECT denProiect FROM bugetProj WHERE nrProiect ='"+ comboBoxAlegeProj.getValue()+"' " );
        try {
            String proj=null;
            while (rsProj.next()) {
                proj = rsProj.getString( "denProiect");
            }
            if(comboBoxAlegeProjNume.getValue()==null){
                comboBoxAlegeProjNume.setValue(proj);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            comboBoxAlegeProjNume.setDisable(true);
            comboBoxAlegeProj.setDisable(true);

    }
    public void comboBoxAlegeProjNume(ActionEvent actionEvent) throws SQLException {
        ResultSet rsProj = stm.executeQuery( "SELECT nrProiect FROM bugetProj WHERE denProiect ='"+comboBoxAlegeProjNume.getValue()+"'" );
        try {
            String nrProj=null;
            while (rsProj.next()) {
                nrProj = rsProj.getString("nrProiect");
            }
//            comboBoxAlegeProj.setValue(null);
            if(comboBoxAlegeProj.getValue()==null){
                comboBoxAlegeProj.setValue(nrProj);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboBoxAlegeProj.setDisable(true);
        comboBoxAlegeProjNume.setDisable(true);

    }


    public void comboBoxAlegeOrg ( ActionEvent event ) throws SQLException {
        String selectProj = "SELECT nrProiect FROM invTbl WHERE org ='"+ comboBoxAlegeOrg.getValue()+"' ";
        Object valueProj = comboBoxAlegeProj.getValue();

        java.util.List<String> myListProj =new ArrayList<>();

        Statement stm=connection.createStatement();

        ResultSet rsProj = stm.executeQuery( selectProj );
        try {
            while (rsProj.next()) {
                myListProj.add( rsProj.getString( "nrProiect"));
            }
            java.util.List<String> noDuplicatesList = myListProj.stream().distinct().collect( Collectors.toList());
            if(valueProj==null){
                comboBoxAlegeProj.setItems(FXCollections.observableList( noDuplicatesList ) );}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage1Intro ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage3Sumar ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage4Pif ( ActionEvent actionEvent ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4PIF.fxml" ) );
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

    public void goToStage6AnalizaPif ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPif.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
}


