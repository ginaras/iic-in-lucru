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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public Label labelDiferentafz;


    public Label labelEstimatInitOrg;
    public Label labelAditionalOrg;
    public Label labetTotalEstimatOrg;
    public Label labelTotalRealizatOrg;
    public Label labelDiferentaOrg;

    public Label setOrg;
    public Label setProj;
    public Label setFz;

    public Button buttonBackSt0;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonOrg;
    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonOrgYear;
    public Button resetButton;
    public Button butonStage2Rapoarte;
    public Button goToStage4Pif;
    public Button butonStage1Intro;
    public ComboBox comboBoxButtonFzContract;
    public Button buttonStage5Solduri;
    public Button buttonStage6AnalizaPif;

    public Button buttonMachetaTrimestriala;
    public DatePicker laDataMachetaTrimestriala;
    public DatePicker deLaDataMachetaTrimestriala;

    public Label labelTotalRealizatOrg1;
    public Label labelDiferentaOrg1;
    public Label labelTotalRealizatProj1;
    public Label labelDiferentaProj1;
    public Label labelTotalRealizatFz1;
    public Label labelDiferentafz1;


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
        List <String> myListAni = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));
            myListAni = Files.readAllLines((Paths.get("C:/Investitii/resurse/ani")));


        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxButtonFz.setItems( FXCollections.observableArrayList(myListFz));
        comboBoxButtonOrg.setItems( FXCollections.observableArrayList(myListOrg));
        comboBoxButtonProj.setItems( FXCollections.observableArrayList(myListProj));
        comboBoxButtonOrgYear.setItems(FXCollections.observableArrayList(myListAni));
        comboBoxButtonOrgYear.setDisable(true);

    }

    public void resetAct ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void comboBoxActOrg ( ActionEvent actionEvent ) throws SQLException {
        String selectProj = "SELECT nrProiect FROM invTbl WHERE org ='"+ comboBoxButtonOrg.getValue()+"' ";
        String selectFz = "SELECT furnizor FROM invTbl WHERE org ='"+ comboBoxButtonOrg.getValue()+"' ";
        String findOrgDen = "SELECT denumireOrg AS 'denOrg' FROM bugetOrg WHERE org='"+comboBoxButtonOrg.getValue()+"' ";

        Object valueProj = comboBoxButtonProj.getValue();
        Object valueFz = comboBoxButtonFz.getValue();

        List<String> myListProj =new ArrayList<>();
        List<String> myListFz =new ArrayList<>();

        Statement stm=connection.createStatement();

           ResultSet rsfindOrg = stm.executeQuery(findOrgDen);
           String  findOrg1 = null;
           try {
               while(rsfindOrg.next()){
                  findOrg1= rsfindOrg.getString("denOrg");
               }
               setOrg.setText(findOrg1);
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }


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
        comboBoxButtonOrgYear.setDisable(false);

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
                double totalAnRealizatOrgD1 =  totalAnRealizatOrg * 100 / 100;

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
        if (estimatFinalOrg!=null) {
            double difOrg1 = ( Double.parseDouble(estimatFinalOrg) - Double.parseDouble(totalRealizatOrg) / 100 ) * 100 / Double.parseDouble(estimatFinalOrg);
            labelDiferentaOrg1.setText(df.format(difOrg1) + " %");

            double totalAnRealizatOrgD = ( ( Double.parseDouble(totalRealizatOrg) / 100 ) ) * 100 / Double.parseDouble(estimatFinalOrg);
            labelTotalRealizatOrg1.setText(df.format(totalAnRealizatOrgD)+" %");
        }
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
        if (estimatFinalProj !=null) {
            double totalRealizatDouble1 = Double.parseDouble(totalRealizatProjBrut) * 100 / Double.parseDouble(estimatFinalProj);
            labelTotalRealizatProj1.setText(df.format(totalRealizatDouble1)+" %");

            double difProj1 = (Double.parseDouble(estimatFinalProj)-Double.parseDouble(String.valueOf(totalRealizatDouble)))*100/Double.parseDouble(estimatFinalProj);
            labelDiferentaProj1.setText(df.format(difProj1)+" %");
        }
        int difProj = Integer.parseInt(estimatFinalProj)-((int) totalRealizatDouble);
        labelDiferentaProj.setText(df.format(difProj));

        if(comboBoxButtonProj.getValue()!=null){

            String denProj="SELECT denProiect from bugetProj WHERE nrProiect = '"+comboBoxButtonProj.getValue()+"' ";
            ResultSet rsDenProj= stm.executeQuery(denProj);
            try {
                while (rsDenProj.next()){
                    String denProjInTbl = rsDenProj.getString("denProiect");
                    setProj.setText( denProjInTbl);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    public void comboBoxFzAct ( ActionEvent actionEvent ) throws SQLException {
        String selectProj = "SELECT nrProiect FROM invTbl WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";
        String selectOrg = "SELECT org FROM invTbl WHERE furnizor ='"+ comboBoxButtonFz.getValue()+"' ";

        if(comboBoxButtonProj.getValue()!=null){
            setProj.setText( comboBoxButtonProj.getValue().toString() );
        }

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

            double difFz = Integer.parseInt(estimatFinalFz)-Integer.parseInt(totalRealizatFz);
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

            String totalRealizatFz = labelTotalRealizatFz.getText();
            totalRealizatFz = totalRealizatFz.replace(",", "");
            totalRealizatFz = totalRealizatFz.replace(".","");


            double difFz = Integer.parseInt(estimatFinalFz)-Integer.parseInt(totalRealizatFz);
            if (estimatFinalFz!=null) {
                double difFz1 = ( Double.parseDouble(estimatFinalFz) - Double.parseDouble(totalRealizatFz)  ) * 100 / Double.parseDouble(estimatFinalFz);
                labelDiferentafz1.setText(df.format(difFz1)+" %");

                double totalRealizatFz1 = ((Double.parseDouble(totalRealizatFz))*100/Double.parseDouble(estimatFinalFz));
                labelTotalRealizatFz1.setText(df.format(totalRealizatFz1)+" %");
            }
            labelDiferentafz.setText(df.format(difFz));

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

    public void goOnStage1Intro ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
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
    public void goToStage5Solduri ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage6AnalizaPif ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPIF.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToMachetaTrimestriala(ActionEvent actionEvent) {
        if(deLaDataMachetaTrimestriala.getValue()==null || laDataMachetaTrimestriala.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Trebuie completate ambele campuri de date!");
            alert.showAndWait();
            return;
        }
        else{
        try {
            Connection connection = DriverManager.getConnection(Investitii.URL, Investitii.USER, Investitii.PASSWORD );
            Statement st = connection.createStatement();

            String situatiaImobilizarilor = "SELECT bugetProj.nrCrt, bugetProj.denProiect, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS soldInitial FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect AND invTBL.dataContabilizarii<= '" +deLaDataMachetaTrimestriala.getValue()+"' GROUP BY bugetProj.nrProiect) AS soldInitial, " +
                    "(SELECT ROUND(SUM(invTBL.valoare),2) AS soldFinal FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect and dataContabilizarii<='"+laDataMachetaTrimestriala.getValue()+"') AS soldFinal, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS intrari FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect AND '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataContabilizarii  AND invTBL.dataContabilizarii <='"+laDataMachetaTrimestriala.getValue()+"') AS intrari, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiri FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect AND '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"') AS iesiri, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiriDinSold FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect AND '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"' and dataContabilizarii<='"+deLaDataMachetaTrimestriala.getValue()+"') AS iesiriDinSold, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiriDinPerioada FROM invTBL WHERE bugetProj.nrProiect=invTBL.nrProiect AND '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"' AND dataContabilizarii>='"+deLaDataMachetaTrimestriala.getValue()+"') AS iesiriDinPerioada, " +

                    "bugetProj.nrProiect FROM invTBL, bugetProj WHERE bugetProj.nrProiect=invTBL.nrProiect GROUP BY bugetProj.nrProiect ";

            ResultSet rs = st.executeQuery( situatiaImobilizarilor);

//Print - Crearea si prima linie a fisierilui de raport
            LocalDate date0 = laDataMachetaTrimestriala.getValue();
            LocalDateTime date1 = LocalDateTime.now();
            DateTimeFormatter date02 = DateTimeFormatter.ofPattern( "yyyy-MM" );
            DateTimeFormatter date01 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );

            String replaceNumeData2 = date0.format( date02 );
            String replaceNumeData1 = date1.format( date01 );

            BufferedWriter writer0 = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\SituatiaImobilizarilorLaData-" + replaceNumeData2 + "rulat la "+replaceNumeData1+".csv", false ) );
            writer0.append( "nrCrt; denProiect; sold initial la "+deLaDataMachetaTrimestriala.getValue()+";  Total intrari; Iesiri Din existent la "+deLaDataMachetaTrimestriala.getValue()+"; Iesiri din intrarile perioadei; Total Iesiri; Sold la "+laDataMachetaTrimestriala.getValue()+"; Nr proiect" );
            writer0.close();
//Parcurgerea BD si extragerea datelor iterate through the java resultset
            while (rs.next()) {
                Integer nrCrtPrint = rs.getInt( "nrCrt" );
                String denProiectPrint = rs.getString( "denProiect" );
                String soldInitialPrint = rs.getString( "soldInitial" );
                String iesiriPrint = rs.getString( "iesiri" );
                String iesiriDinSoldPrint = rs.getString( "iesiriDinSold" );
                String iesiriDinPerioadaPrint = rs.getString( "iesiriDinPerioada" );
                String intrariPrint = rs.getString( "intrari" );
                String soldFinalPrint = rs.getString( "soldFinal" );
                String nrProiectPrint = rs.getString( "bugetProj.nrProiect" );
//                String dinSursePropriiPrint = rs.getString( "dinSurseProprii" );
//                String totalIesiriPrint = rs.getString( "totalIesiri" );

                if(soldInitialPrint==null){soldInitialPrint = "0";}
                if(iesiriPrint==null){iesiriPrint = "0";}
                if(iesiriDinSoldPrint==null){ iesiriDinSoldPrint = "0";}
                if(iesiriDinPerioadaPrint==null){ iesiriDinPerioadaPrint = "0";}
                if(intrariPrint==null){ intrariPrint = "0";}

//print - adaugarea datelor in fisier
                String datele =  nrCrtPrint+";"+ denProiectPrint + ";" + soldInitialPrint+";"+intrariPrint+";"+iesiriDinSoldPrint+";"+iesiriDinPerioadaPrint+";"+iesiriPrint+";"+soldFinalPrint+";"+nrProiectPrint;//" +dinSursePropriiPrint+";"+ totalIesiriPrint;
                BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\SituatiaImobilizarilorLaData-" + replaceNumeData2 + "rulat la "+replaceNumeData1+".csv", true ) );
                writer.append( " \n" );
                writer.append( datele );
                writer.close();
            }
            String situatiaImobilizarilorTotal = "SELECT bugetProj.nrCrt, bugetProj.denProiect, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS soldInitial FROM invTBL WHERE invTBL.dataContabilizarii<= '" +deLaDataMachetaTrimestriala.getValue()+"' GROUP BY bugetProj.nrProiect) AS soldInitial, " +
                    "(SELECT ROUND(SUM(invTBL.valoare),2) AS soldFinal FROM invTBL WHERE dataContabilizarii<='"+laDataMachetaTrimestriala.getValue()+"') AS soldFinal, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS intrari FROM invTBL WHERE  '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataContabilizarii  AND invTBL.dataContabilizarii <='"+laDataMachetaTrimestriala.getValue()+"') AS intrari, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiri FROM invTBL WHERE '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"') AS iesiri, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiriDinSold FROM invTBL WHERE '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"' and dataContabilizarii<='"+deLaDataMachetaTrimestriala.getValue()+"') AS iesiriDinSold, " +
                    "(SELECT ROUND(SUM(invTBL.valInitiala),2) AS iesiriDinPerioada FROM invTBL WHERE '"+deLaDataMachetaTrimestriala.getValue()+"' <= invTBL.dataPIF <= '"+laDataMachetaTrimestriala.getValue()+"' AND dataContabilizarii>='"+deLaDataMachetaTrimestriala.getValue()+"') AS iesiriDinPerioada, " +
                    "bugetProj.nrProiect, org FROM invTBL, bugetProj Group BY invtbl.org ";

                ResultSet rsTotal = st.executeQuery( situatiaImobilizarilorTotal);


                while (rsTotal.next()) {
                    String orgPrintTotal = rsTotal.getString( "org" );
                    String soldInitialPrintTotal = rsTotal.getString( "soldInitial" );
                    String iesiriPrintTotal = rsTotal.getString( "iesiri" );
                    String iesiriDinSoldPrintTotal = rsTotal.getString( "iesiriDinSold" );
                    String iesiriDinPerioadaPrintTotal = rsTotal.getString( "iesiriDinPerioada" );
                    String intrariPrintTotal = rsTotal.getString( "intrari" );
                    String soldFinalPrintTotal = rsTotal.getString( "soldFinal" );

                    String totaluri =  ";Total  "+orgPrintTotal+";" + soldInitialPrintTotal+";"+intrariPrintTotal+";"+iesiriDinSoldPrintTotal+";"+iesiriDinPerioadaPrintTotal+";"+iesiriPrintTotal+";"+soldFinalPrintTotal+";";//" +dinSursePropriiPrint+";"+ totalIesiriPrint;
                    BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\SituatiaImobilizarilorLaData-" + replaceNumeData2 + "rulat la "+replaceNumeData1+".csv", true ) );
                    writer.append( " \n" );
                    writer.append( totaluri );
                    writer.close();
                }

            Desktop desktop = null;
            desktop.getDesktop().open( new File( "c:\\Investitii\\rapoarte\\SituatiaImobilizarilorLaData-" +  replaceNumeData2 + "rulat la "+replaceNumeData1+".csv" ) );

            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
