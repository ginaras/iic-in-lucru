package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage3RapoarteInv implements Initializable {


    public Label labelProjEstimatInit;
    public Label labelAditionalProj;
    public Label labelTotalEstimatProj;
    public Label labelTotalRealizatProj;
    public Label labelDiferentaProj;

    public Label labelEstimatInitFz;
    public Label labelAditionalFz;
    public Label labelTotalEstimatFz;
    public Label labelTotalRealizatFz;
//    public Label labelDiferentaFz;
    public Label labelDiferentafz;


    public Label labelEstimatInitOrg;
    public Label labelAditionalOrg;
    public Label labetTotalEstimatOrg;
    public Label labelTotalRealizatOrg;
    public Label labelDiferentaOrg;

    public Label labelCMProj;
    public Label labelEchipamenteProj;
    public Label labelMagazieProj;
    public Label labelAlteInvProj;

    public Label labelCMFz;
    public Label labelEchipamenteFz;
    public Label labelMagazieFz;
    public Label labelAlteInvFz;

    public Label labelCMOrg;
    public Label labelEchipamenteOrg;
    public Label labelMagazieOrg;
    public Label labelAlteInvOrg;

    public Label setOrg;
    public Label setProj;
    public Label setFz;

    public DatePicker dataFinala;
    public Button buttonBackSt0;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonOrg;
    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonOrgYear;
    public Button resetButton;
    public Button selectButton;
    public Button butonStage2Rapoarte;
    public Button goToStage4Pif;
    public Button butonStage1Intro;
    public ComboBox comboBoxButtonFzContract;



    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();


    public CtrlStage3RapoarteInv () throws SQLException {
    }


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        comboBoxButtonFzContract.setDisable( true );
        List<String> myListFz = null;
        List<String> myListProj = null;
        List<String> myListOrg = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));


        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxButtonFz.setItems( FXCollections.observableArrayList(myListFz));
        comboBoxButtonOrg.setItems( FXCollections.observableArrayList(myListOrg));
        comboBoxButtonProj.setItems( FXCollections.observableArrayList(myListProj));
        comboBoxButtonOrgYear.getItems().addAll("2020","2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030" );

    }


    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void resetAct ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void apliFiter ( ActionEvent actionEvent ) throws SQLException {
        String query1 = "SELECT round(SUM(valInitiala), 2) as 'totalProiect' FROM invTBL WHERE  ";
        String query2 = "SELECT round(SUM(valInitiala), 2) as 'totalProiect' FROM invTBL WHERE ";
        String query3 = "SELECT round(SUM(valInitiala), 2) as 'totalProiect' FROM invTBL WHERE ";

        Object valueProj = comboBoxButtonProj.getValue();
        Object valueFz = comboBoxButtonFz.getValue();
        Object valueCtr= comboBoxButtonFzContract.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        if(valueProj ==null && valueFz==null && valueOrg==null)    {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Da-mi un criteriu sa-ti pot arata ceva" );
            alert.showAndWait();
            return;
        }
//
//
//
//        if(valueProj !=null) {
//            if (valueOrg == null && valueFz==null) {
//                query2 += "nrProiect='" + valueProj.toString() + "'";
//
//                Statement stm2 = connection.createStatement();
//                ResultSet rsProj1 = stm2.executeQuery( query2 );
//                double totalProiect = 0.00;
//                try {
//                    while (rsProj1.next()) {
//                        totalProiect = rsProj1.getDouble( "totalProiect" );
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                labelTotalRealizatProj.setText( String.valueOf( totalProiect ) );
//            }
//            if (valueOrg == null && valueFz!=null) {
//                query1 += "nrProiect='" + valueProj.toString() + "' AND furnizor='"+valueFz.toString()+"' ";
//                query2 += "nrProiect='" + valueProj.toString() + "'";
//
//                Statement stm2 = connection.createStatement();
//
//                ResultSet rsProj1 = stm2.executeQuery( query2 );
//                double totalProiect1 = 0.00;
//                try {
//                    while (rsProj1.next()) {
//                        totalProiect1 = rsProj1.getDouble( "totalProiect" );
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                labelTotalRealizatProj.setText( String.valueOf( totalProiect1 ) );
//            }
//
//            if (valueOrg != null && valueFz==null) {
//                query1 += "org = '"+valueOrg.toString()+"' AND nrProiect='"+valueProj.toString()+"' ";
//                query2 += "org='" + valueOrg.toString() + "'";
//
//                Statement stm2 = connection.createStatement();
//              ResultSet rsOrgProj1 = stm2.executeQuery( query1 );
//                double totalProiect = 0.00;
//                try {
//                    while (rsOrgProj1.next()) {
//                        totalProiect = rsOrgProj1.getDouble( "totalProiect" );
//                    }
//                    labelTotalRealizatProj.setText( String.valueOf( totalProiect ) );
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//            }
//            if (valueOrg != null && valueFz!=null) {
//                query1 += "org = '"+valueOrg.toString()+"'";
//                query2 += "nrProiect='" + valueProj.toString() + "' AND org='" +valueOrg.toString()+"'";
//                query3 += "furnizor='" + valueFz.toString() + "'AND nrProiect='" +valueProj.toString()+"' AND org = '"+valueOrg.toString()+"'" ;
//
//                Statement stm2 = connection.createStatement();
//                ResultSet rsProj1 = stm2.executeQuery( query2 );
//                double totalProiect = 0.00;
//                try {
//                    while (rsProj1.next()) {
//                        totalProiect = rsProj1.getDouble( "totalProiect" );
//                    }
//                    labelTotalRealizatProj.setText( String.valueOf( totalProiect ) );
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                ResultSet rsFz=stm.executeQuery( query3 );
//                try {
//                    double totalProiect3=0.00;
//                    while ((rsFz.next())){
//                        totalProiect3=rsFz.getDouble( "totalProiect" );
//                    }
//                    labelTotalRealizatFz.setText( String.valueOf( totalProiect3 ) );
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//
//        }
//
//        if(valueFz !=null && valueOrg!=null && valueProj==null) {
//            query1 += "org = '"+valueOrg.toString()+"'";
//            query2 += "furnizor='" + valueFz.toString() + "' AND org='" +valueOrg.toString()+"'";
//            Statement stm2= connection.createStatement();
//
//            ResultSet rsFzOrg = stm2.executeQuery( query2 );
//            double totalProiect2 = 0;
//            try {
//                while (rsFzOrg.next()){
//                    totalProiect2=rsFzOrg.getDouble( "totalProiect" );
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            labelTotalRealizatFz.setText( String.valueOf( totalProiect2 ) );
//        }
//
//
//        if(valueFz !=null && valueOrg==null && valueProj==null) {
//            if (comboBoxButtonFzContract.getValue() == "Total") {
//                query2 += "furnizor='" + valueFz.toString() + "'";
//                Statement stm2 = connection.createStatement();
//                ResultSet rsFz2 = stm2.executeQuery( query2 );
//                double totalProiect = 0.00;
//                try {
//                    while (rsFz2.next()) {
//                        totalProiect = rsFz2.getDouble( "totalProiect" );
//                    }
//                    labelTotalRealizatFz.setText( String.valueOf( totalProiect ) );
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            else{
//                query2 += "furnizor='" + valueFz.toString() + "' AND contract='" + valueCtr.toString() + "'";
//                Statement stm2 = connection.createStatement();
//                ResultSet rsFz2 = stm2.executeQuery( query2 );
//                double totalProiect = 0.00;
//                try {
//                    while (rsFz2.next()) {
//                        totalProiect = rsFz2.getDouble( "totalProiect" );
//                    }
//                    labelTotalRealizatFz.setText( String.valueOf( totalProiect ) );
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
        calcOrgDownStage();
        calcProjDownStage();
        calcFzDownStage();

        if (comboBoxButtonOrg.getValue() !=null){
                setOrg.setText( comboBoxButtonOrg.getValue().toString() );
        }
        if(comboBoxButtonProj.getValue()!=null){
                setProj.setText( comboBoxButtonProj.getValue().toString() );
        }
        if ( comboBoxButtonFz.getValue()!=null){
        setFz.setText( comboBoxButtonFz.getValue().toString() );
        }

    }

    public void comboBoxActOrg ( ActionEvent actionEvent ) throws SQLException {
        String selectProj = "SELECT nrProiect FROM invTbl WHERE org ='"+ comboBoxButtonOrg.getValue()+"' ";
        String selectFz = "SELECT furnizor FROM invTbl WHERE org ='"+ comboBoxButtonOrg.getValue()+"' ";

        Object valueProj = comboBoxButtonProj.getValue();
        Object valueFz = comboBoxButtonFz.getValue();

        List<String> myListProj =new ArrayList<>();
        List<String> myListFz =new ArrayList<>();

        Statement stm=connection.createStatement();

        ResultSet rsOrgtProj = stm.executeQuery( selectProj );
        try {
            while (rsOrgtProj.next()) {
                myListProj.add( rsOrgtProj.getString( "nrProiect"));
            }
            List<String> noDuplicatesList = myListProj.stream().distinct().collect(Collectors.toList());
            if(valueProj==null){
                comboBoxButtonProj.setItems(FXCollections.observableList( noDuplicatesList ) );}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        ResultSet rsOrgFz = stm.executeQuery( selectFz );
        try {
            while (rsOrgFz.next()) {
                myListFz.add( rsOrgFz.getString( "furnizor"));
            }
            List<String> noDuplicatesList = myListFz.stream().distinct().collect(Collectors.toList());
            if(valueFz==null){
                comboBoxButtonFz.setItems(FXCollections.observableList( noDuplicatesList ) );}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void comboBoxActOrgYear ( ActionEvent event ) throws SQLException {
        Statement stm= connection.createStatement();
        ResultSet rsOrgBugetInitial = stm.executeQuery("Select valInitiala FROM bugetOrg WHERE org = '"+comboBoxButtonOrg.getValue()+"'  AND anulBugetar='"+comboBoxButtonOrgYear.getValue()+"'");

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        double bugetEstimatInitial = 0;
        try {
            while (rsOrgBugetInitial.next()){
                bugetEstimatInitial=rsOrgBugetInitial.getDouble("valInitiala");
            }
            double bugetEstimatInitialD =  bugetEstimatInitial * 100 / 100;

            labelEstimatInitOrg.setText(df.format(bugetEstimatInitialD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsOrgBugetRectificat = stm.executeQuery("Select valRectificata FROM bugetOrg WHERE org = '"+comboBoxButtonOrg.getValue()+"'  AND anulBugetar='"+comboBoxButtonOrgYear.getValue()+"'");
        double bugetEstimatRectificat = 0;
        try {
            while (rsOrgBugetRectificat.next()){
                bugetEstimatRectificat=rsOrgBugetRectificat.getDouble("valRectificata");
            }
            double bugetEstimatRectificatD =  bugetEstimatRectificat * 100 / 100;

            labelAditionalOrg.setText(df.format(bugetEstimatRectificatD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsOrgBugetFinal = stm.executeQuery("Select valFinala FROM bugetOrg WHERE org = '"+comboBoxButtonOrg.getValue()+"'  AND anulBugetar='"+comboBoxButtonOrgYear.getValue()+"'");
        double bugetEstimatFinal = 0;
        try {
            while (rsOrgBugetFinal.next()){
                bugetEstimatFinal=rsOrgBugetFinal.getDouble("valFinala");
            }
            double bugetEstimatFinalD =  bugetEstimatFinal * 100 / 100;

            labetTotalEstimatOrg.setText(df.format(bugetEstimatFinalD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            String query1 = "SELECT round(SUM(valInitiala), 2) as 'totalAnRealizatOrg' FROM invTBL WHERE org = '"+comboBoxButtonOrg.getValue().toString()+"' AND EXTRACT(YEAR FROM dataContabilizarii) = '"+comboBoxButtonOrgYear.getValue()+ "' ";

        ResultSet rsOrg=stm.executeQuery( query1 );
            double totalAnRealizatOrg=0.00;
            try {
                while ((rsOrg.next())){
                    totalAnRealizatOrg=rsOrg.getDouble( "totalAnRealizatOrg" );
                }
                double totalAnRealizatOrgD =  totalAnRealizatOrg * 100 / 100;

                labelTotalRealizatOrg.setText( df.format(totalAnRealizatOrgD)  );


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        //Calcul coloana de diferenta
        String estimatFinalOrg = labetTotalEstimatOrg.getText();
        estimatFinalOrg = estimatFinalOrg.replace(".", "");
        estimatFinalOrg = estimatFinalOrg.replace(",", "");

        String totalRealizatOrg = labelTotalRealizatOrg.getText();
        totalRealizatOrg = totalRealizatOrg.replace(",", "");
        totalRealizatOrg = totalRealizatOrg.replace(".","");

        double difOrg = Integer.parseInt(estimatFinalOrg)-Integer.parseInt(totalRealizatOrg)/100;
        labelDiferentaOrg.setText(df.format(difOrg));
    }


    public void comboBoxActProj ( ActionEvent actionEvent ) throws SQLException {
        String selectFz = "SELECT furnizor FROM invTbl WHERE nrProiect ='"+ comboBoxButtonProj.getValue()+"' ";
        String selectOrg = "SELECT org FROM invTbl WHERE nrProiect ='"+ comboBoxButtonProj.getValue()+"' ";

        Object valueFz = comboBoxButtonFz.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        List<String> myListFz =new ArrayList<>();
        List<String> myListOrg =new ArrayList<>();

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        Statement stm=connection.createStatement();

    ResultSet rsProjFz = stm.executeQuery( selectFz );
        try {
            while (rsProjFz.next()) {
                myListFz.add(rsProjFz.getString( "furnizor"));
            }
            List<String> noDuplicatesList = myListFz.stream().distinct().collect(Collectors.toList());
            if (valueFz==null){
            comboBoxButtonFz.setItems(FXCollections.observableList( noDuplicatesList ) );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    ResultSet rsProjOrg = stm.executeQuery( selectOrg );
        try {
            while (rsProjOrg.next()) {
                myListOrg.add( rsProjOrg.getString( "org"));
            }
            List<String> noDuplicatesOrg = myListOrg.stream().distinct().collect( Collectors.toList());
            if (valueOrg==null){
                comboBoxButtonOrg.setItems(FXCollections.observableList( noDuplicatesOrg ) );}

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsProjBugetInitial = stm.executeQuery("Select valInitiala FROM bugetProj WHERE nrProiect = '"+comboBoxButtonProj.getValue()+"'");

        double bugetEstimatInitial = 0;
        try {
            while (rsProjBugetInitial.next()){
                bugetEstimatInitial=rsProjBugetInitial.getDouble("valInitiala");
            }
            double bugetEstimatInitialD =  bugetEstimatInitial * 100 / 100;

            labelProjEstimatInit.setText(df.format(bugetEstimatInitialD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsProjBugetRectificat = stm.executeQuery("Select valRectificare FROM bugetProj WHERE nrProiect = '"+comboBoxButtonProj.getValue()+"' ");
        double bugetEstimatRectificat = 0;
        try {
            while (rsProjBugetRectificat.next()){
                bugetEstimatRectificat=rsProjBugetRectificat.getDouble("valRectificare");
            }
            double bugetEstimatRectificatD =  bugetEstimatRectificat * 100 / 100;
            labelAditionalProj.setText(df.format(bugetEstimatRectificatD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsProjBugetFinal = stm.executeQuery("Select valFinala FROM bugetProj WHERE nrProiect = '"+comboBoxButtonProj.getValue()+"'");
        double bugetEstimatFinal = 0;
        try {
            while (rsProjBugetFinal.next()){
                bugetEstimatFinal=rsProjBugetFinal.getDouble("valFinala");
            }
            double bugetEstimatFinalD =  bugetEstimatFinal * 100 / 100;

            labelTotalEstimatProj.setText(df.format(bugetEstimatFinalD));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String totalRealizatProjQuery = "SELECT round(SUM(valInitiala), 2) as 'totalRealizatProj' FROM invTBL WHERE nrProiect = '"+comboBoxButtonProj.getValue().toString()+"' ";

        ResultSet rsOrg=stm.executeQuery( totalRealizatProjQuery );
        double totalRealizatProj=0.00;
        try {
            while ((rsOrg.next())){
                totalRealizatProj=rsOrg.getDouble( "totalRealizatProj" );
            }
            double totalRealizatProjD =  totalRealizatProj * 100 / 100;
            labelTotalRealizatProj.setText( df.format(totalRealizatProjD)  );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String estimatFinalProj = labelTotalEstimatProj.getText();
        estimatFinalProj = estimatFinalProj.replace(".", "");
        estimatFinalProj = estimatFinalProj.replace(",", ".");

        String totalRealizatProjBrut = labelTotalRealizatProj.getText();
        totalRealizatProjBrut = totalRealizatProjBrut.replace(".", "");
        totalRealizatProjBrut = totalRealizatProjBrut.replace(",",".");


        double totalRealizatDouble = Double.parseDouble(totalRealizatProjBrut);

        int difProj = Integer.parseInt(estimatFinalProj)-((int) totalRealizatDouble);
        labelDiferentaProj.setText(df.format(difProj));


    }


    public void comboBoxFzAct ( ActionEvent actionEvent ) throws SQLException {
        String selectProj = "SELECT nrProiect FROM invTbl WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";
        String selectOrg = "SELECT org FROM invTbl WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";


        Object valueProj = comboBoxButtonProj.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        List<String> myListProj =new ArrayList<>();
        List<String> myListOrg =new ArrayList<>();

        Statement stm=connection.createStatement();

        ResultSet rsFzProj = stm.executeQuery( selectProj );
        try {
            while (rsFzProj.next()) {
                myListProj.add( rsFzProj.getString( "nrProiect"));
            }

            List<String> noDuplicatesList = myListProj.stream().distinct().collect(Collectors.toList());
            if (valueProj==null){
                comboBoxButtonProj.setItems(FXCollections.observableList( noDuplicatesList ) );}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        ResultSet rsFzOrg = stm.executeQuery( selectOrg );
        try {
            while (rsFzOrg.next()) {
                myListOrg.add( rsFzOrg.getString( "org"));
            }
            List<String> noDuplicatesOrg = myListOrg.stream().distinct().collect( Collectors.toList());
            if(valueOrg==null){
                comboBoxButtonOrg.setItems(FXCollections.observableList( noDuplicatesOrg ) );}

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboBoxButtonFzContract.setDisable( false );

        String populeazaComboBoxFzContract = "SELECT nrContract AS 'nrContract' FROM bugetContract WHERE furnizor='"+ comboBoxButtonFz.getValue()+"'";

        List <String> nrContractList = new ArrayList<>();
        ResultSet rsNrContract = stm.executeQuery(populeazaComboBoxFzContract);
        try {
            while (rsNrContract.next()){
                nrContractList.add( rsNrContract.getString( "nrContract" ));
            }
            nrContractList.add( "Total" );
            List <String> nrContract =nrContractList.stream().distinct().collect( Collectors.toList());
            comboBoxButtonFzContract.setItems( FXCollections.observableList( nrContract ) );


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void comboBoxFzContractAct ( ActionEvent event ) throws SQLException {

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        if (comboBoxButtonFzContract.getValue()=="Total"){
            String valInitFzTotal = "SELECT ROUND(SUM(valInitiala),2) AS 'valInitialaFzTotal' FROM bugetContract WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";
            String valRectificareFzTotal = "SELECT ROUND(SUM(valRectificare),2) AS 'valRectificareFzTotal' FROM bugetContract WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";
            String valFinalaFzTotal = "SELECT ROUND(SUM(valFinala),2) AS 'valFinalaFzTotal' FROM bugetContract WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";
            String ValRealizataTotalFz = "SELECT round(SUM(valInitiala), 2) as 'totalProiect' FROM invTBL WHERE  furnizor ='"+ comboBoxButtonFz.getValue()+"'";


            ResultSet rsValInitialaCtr = stm.executeQuery(valInitFzTotal);
            double valInit = 0;
            try {
                while (rsValInitialaCtr.next()){
                    valInit=rsValInitialaCtr.getDouble( "valInitialaFzTotal" );
                }
                labelEstimatInitFz.setText(df.format(  valInit) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsValRectificareCtr = stm.executeQuery(valRectificareFzTotal);
            double valRectificare = 0;
            try {
                while (rsValRectificareCtr.next()){
                    valRectificare=rsValRectificareCtr.getDouble( "valRectificareFzTotal" );
                }
                labelAditionalFz.setText(df.format(  valRectificare) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsValFinalaCtr = stm.executeQuery(valFinalaFzTotal);
            double valFinala = 0;
            try {
                while (rsValFinalaCtr.next()){
                    valFinala=rsValFinalaCtr.getDouble( "valFinalaFzTotal" );
                }
                labelTotalEstimatFz.setText(df.format(  valFinala) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsProjFz1 = stm.executeQuery( ValRealizataTotalFz );
            double totalProiect = 0.00;
            try {
                while (rsProjFz1.next()) {
                    totalProiect = rsProjFz1.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelTotalRealizatFz.setText( df.format( totalProiect ) );

            String estimatFinalFz = labelTotalEstimatFz.getText();
            estimatFinalFz = estimatFinalFz.replace(".", "");
            estimatFinalFz = estimatFinalFz.replace(",", "");

            String totalRealizatFz = labelTotalRealizatFz.getText();
            totalRealizatFz = totalRealizatFz.replace(",", "");
            totalRealizatFz = totalRealizatFz.replace(".","");

            double difFz = Integer.parseInt(estimatFinalFz)-Integer.parseInt(totalRealizatFz)/100;
            labelDiferentafz.setText(df.format(difFz));


        }
        else {
            String valInitFzContract = "SELECT valInitiala AS 'valInitialaFzContractDB' FROM bugetContract WHERE nrContract ='" + comboBoxButtonFzContract.getValue() + "'AND nrContract='"+ comboBoxButtonFzContract.getValue()+"' ";
            String valRectificareFzContract = "SELECT valRectificare AS 'valRectificareFzContractDB' FROM bugetContract WHERE nrContract ='" + comboBoxButtonFzContract.getValue() + "' AND nrContract='"+ comboBoxButtonFzContract.getValue()+"'";
            String valFinalaFzContract = "SELECT valFinala AS 'valFinalaFzContractDB' FROM bugetContract WHERE nrContract ='" + comboBoxButtonFzContract.getValue() + "' AND nrContract='"+ comboBoxButtonFzContract.getValue()+"' ";
            String ValRealizataTotalFz = "SELECT round(SUM(valInitiala), 2) as 'totalProiect' FROM invTBL WHERE  furnizor ='"+ comboBoxButtonFz.getValue()+"' AND contract='"+ comboBoxButtonFzContract.getValue()+"' ";

            ResultSet rsValInitialaCtr = stm.executeQuery( valInitFzContract );
            double valInit = 0;
            try {
                while (rsValInitialaCtr.next()) {
                    valInit = rsValInitialaCtr.getDouble( "valInitialaFzContractDB" );
                }
                labelEstimatInitFz.setText( df.format( valInit ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsValRectificareCtr = stm.executeQuery( valRectificareFzContract );
            double valRectificare = 0;
            try {
                while (rsValRectificareCtr.next()) {
                    valRectificare = rsValRectificareCtr.getDouble( "valRectificareFzContractDB" );
                }
                labelAditionalFz.setText( df.format( valRectificare ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsValFinalaCtr = stm.executeQuery( valFinalaFzContract );
            double valFinala = 0;
            try {
                while (rsValFinalaCtr.next()) {
                    valFinala = rsValFinalaCtr.getDouble( "valFinalaFzContractDB" );
                }
                labelTotalEstimatFz.setText( df.format( valFinala ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            ResultSet rsProjFz1 = stm.executeQuery( ValRealizataTotalFz );
            double totalProiect = 0.00;
            try {
                while (rsProjFz1.next()) {
                    totalProiect = rsProjFz1.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelTotalRealizatFz.setText( df.format( totalProiect ) );

            String estimatFinalFz = labelTotalEstimatFz.getText();
            estimatFinalFz = estimatFinalFz.replace(".", "");
//            estimatFinalFz = estimatFinalFz.replace(",", "");

            String totalRealizatFz = labelTotalRealizatFz.getText();
            totalRealizatFz = totalRealizatFz.replace(",", "");
            totalRealizatFz = totalRealizatFz.replace(".","");


            double difFz = Integer.parseInt(estimatFinalFz)-Integer.parseInt(totalRealizatFz)/100;
            labelDiferentafz.setText(df.format(difFz));

        }


    }

    //todo tabelul de jos

    private void calcOrgDownStage () throws SQLException {
        Object valueOrg = comboBoxButtonOrg.getValue();

        if(valueOrg == null){

        }
        else{
        String queryCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
        String queryEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
        String queryEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
        String queryAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

        queryCM += "org = '" +valueOrg.toString()+"' ";
        queryEchip += "org = '" +valueOrg.toString()+"' ";
        queryEchipInDep += "org = '" +valueOrg.toString()+"' ";
        queryAlteInv += "org = '" +valueOrg.toString()+"' ";

            NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
            nf.setMaximumFractionDigits( 2 );
            DecimalFormat df = (DecimalFormat) nf;

        Statement stm2 = connection.createStatement();
        ResultSet rsProjCM = stm2.executeQuery( queryCM );

        double totalProiect = 0.00;
        try {
            while (rsProjCM.next()) {
                totalProiect = rsProjCM.getDouble( "totalProiect" );
            }
            labelCMOrg.setText( df.format (totalProiect)  );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rsProjEchip = stm2.executeQuery( queryEchip );
            try {
                while (rsProjEchip.next()){
                    totalProiect=rsProjEchip.getDouble( "totalProiect" );
                }
                labelEchipamenteOrg.setText( df.format (totalProiect ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsProjEchipInDep = stm2.executeQuery(queryEchipInDep);
            try {
                while(rsProjEchipInDep.next()){
                    totalProiect=rsProjEchipInDep.getDouble( "totalProiect" );
                }
                labelMagazieOrg.setText( df.format (totalProiect ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsProjAlteInv = stm2.executeQuery( queryAlteInv );
            try{
                while (rsProjAlteInv.next()){
                    totalProiect = rsProjAlteInv.getDouble( "totalProiect" );
                }
                labelAlteInvOrg.setText( df.format ( totalProiect ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void calcProjDownStage() throws SQLException {
            Object valueProj = comboBoxButtonProj.getValue();
            Object valueOrg = comboBoxButtonOrg.getValue();

            NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
            nf.setMaximumFractionDigits( 2 );
            DecimalFormat df = (DecimalFormat) nf;
            if (valueProj == null){

        }
        else {
            if (valueOrg==null){
                String queryProjCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryProjEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryProjEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryProjAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

    //        Object valueFz = comboBoxButtonFz.getValue();

                queryProjCM += "nrProiect = '" + valueProj.toString() + "' ";
                queryProjEchip += "nrProiect = '" + valueProj.toString() + "' ";
                queryProjEchipInDep += "nrProiect = '" + valueProj.toString() + "' ";
                queryProjAlteInv += "nrProiect = '" + valueProj.toString() + "' ";

                Statement stm2 = connection.createStatement();
                ResultSet rsProjCM = stm2.executeQuery( queryProjCM );


                double totalProiect = 0.00;
                try {
                    while (rsProjCM.next()) {
                        totalProiect = rsProjCM.getDouble( "totalProiect" );
                    }
                    labelCMProj.setText( df.format ( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjEchip = stm2.executeQuery( queryProjEchip );
                try {
                    while (rsProjEchip.next()) {
                        totalProiect = rsProjEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjEchipInDep = stm2.executeQuery( queryProjEchipInDep );
                try {
                    while (rsProjEchipInDep.next()) {
                        totalProiect = rsProjEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjAlteInv = stm2.executeQuery( queryProjAlteInv );
                try {
                    while (rsProjAlteInv.next()) {
                        totalProiect = rsProjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                String queryProjCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryProjEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryProjEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryProjAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

                //        Object valueFz = comboBoxButtonFz.getValue();

                queryProjCM += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjEchip += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjEchipInDep += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjAlteInv += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";

                Statement stm2 = connection.createStatement();
                ResultSet rsProjCM = stm2.executeQuery( queryProjCM );

                double totalProiect = 0.00;
                try {
                    while (rsProjCM.next()) {
                        totalProiect = rsProjCM.getDouble( "totalProiect" );
                    }
                    labelCMProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjEchip = stm2.executeQuery( queryProjEchip );
                try {
                    while (rsProjEchip.next()) {
                        totalProiect = rsProjEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjEchipInDep = stm2.executeQuery( queryProjEchipInDep );
                try {
                    while (rsProjEchipInDep.next()) {
                        totalProiect = rsProjEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjAlteInv = stm2.executeQuery( queryProjAlteInv );
                try {
                    while (rsProjAlteInv.next()) {
                        totalProiect = rsProjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvProj.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    private void calcFzDownStage () throws SQLException {
        Object valueFz = comboBoxButtonFz.getValue();
        Object valueProj = comboBoxButtonProj.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        if (valueFz == null){

        }
        else {
            if(valueOrg == null && valueProj == null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' ";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' ";

                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble( "totalProiect" );
                    }
                    labelCMFz.setText( df.format( totalProiect ) );

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(valueOrg == null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND nrProiect = '" + valueProj.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";

                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble( "totalProiect" );
                    }
                    labelCMFz.setText( df.format( totalProiect ) );

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(valueOrg != null && valueProj == null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND org = '" + valueOrg.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'";

                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble( "totalProiect" );
                    }
                    labelCMFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(valueOrg != null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND org = '" + valueOrg.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'AND nrProiect = '" + valueProj.toString() + "'";

                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble( "totalProiect" );
                    }
                    labelCMFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }
                    labelEchipamenteFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }
                    labelMagazieFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                    labelAlteInvFz.setText( df.format( totalProiect ) );
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


    public void goOnStage2Rapoarte ( ActionEvent event ) throws IOException {
            Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
            Scene tableViewScene = new Scene( stage3Intro );
            Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowStage1Intro.setScene( tableViewScene );
            windowStage1Intro.show();
    }

    public void goToStage4Pif ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4Pif.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void goOnStage1Intro ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

}
