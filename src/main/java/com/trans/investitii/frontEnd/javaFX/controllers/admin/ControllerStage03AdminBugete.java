package com.trans.investitii.frontEnd.javaFX.controllers.admin;


import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ControllerStage03AdminBugete implements Initializable {


    public ComboBox comboOrg;
    public ComboBox comboBProj;
    public ComboBox comboBContract;
    public Button buttonAplicaBugetOrg;
    public Button buttonAplicaBugetProj;
    public Button buttonAplicaBugetContract;
    public Button butonStage0;
    public CheckBox checkOrg;
    public CheckBox checkProj;
    public CheckBox checkContract;
    public Text txtDenProj;
    public Text txtDenFurnizor;
    public Text txtValoareContractInitial;
    public Text txtValoarareRectificareContract;
    public Text txtValoareFinalaContract;
    public Text txtValoareOrgInitial;
    public Text txtValoareRectificareOrg;
    public Text txtValoareFinalaOrg;
    public Text txtValoareFinalaProj;
    public Text txtValoarareRectificareProj;
    public Text txtValoareProjInitial;
    public TextField textFieldRectificareOrg;
    public TextField textFieldRectificareContract;
    public TextField textFieldRectificareProj;
    public ComboBox comboBoxYearChose;
    public Text txtDenOrg;
    public CheckBox checkOrgBugetInitial;
    public CheckBox checkOrgRectificareBuget;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();

    public ControllerStage03AdminBugete () throws SQLException {
    }


    public void comboOrgAction ( ActionEvent event )  {
        comboBoxYearChose.setDisable( false );
    }

    public void comboBYearAction ( ActionEvent event ) throws SQLException {
        checkOrg.setDisable( false );

        String denOrg = "SELECT denumireOrg AS 'denumire' from bugetORG WHERE org= '"+comboOrg.getValue()+"'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";
        int an= parseInt(comboBoxYearChose.getValue().toString())-1;

        String denOrg1 = "SELECT denumireOrg AS 'denumire1' from bugetORG WHERE org= '"+comboOrg.getValue()+"'AND anulBugetar='"+an+"'";
        String valInitialaOrg ="SELECT valInitiala AS 'viOrg' from bugetORG  WHERE org= '"+comboOrg.getValue()+"'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";
        String valRectificataOrg ="SELECT valRectificata AS 'vrOrg' from bugetORG  WHERE org= '"+comboOrg.getValue()+"'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";
        String valFinalaOrg = "SELECT valFinala AS 'vfOrg' from bugetORG  WHERE org= '"+comboOrg.getValue()+"'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";

        ResultSet rsDenOrg = stm.executeQuery( denOrg );
        try {
            String denumireOrg = null;
            while (rsDenOrg.next()){
                denumireOrg= (String) rsDenOrg.getObject( "denumire" );
            }
            txtDenOrg.setText( denumireOrg );
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (txtDenOrg.getText().isEmpty()){
            ResultSet rsDenOrg1 = stm.executeQuery( denOrg1 );
            try {
                String denumireOrg1 = null;
                while (rsDenOrg1.next()){
                    denumireOrg1= (String) rsDenOrg1.getObject( "denumire1" );
                }
                txtDenOrg.setText( denumireOrg1 );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ResultSet rsValInitOrg = stm.executeQuery( valInitialaOrg );
        try {
            String viOrg = null;
            while (rsValInitOrg.next()){
                viOrg= (String) rsValInitOrg.getObject( "viOrg" );
            }
            txtValoareOrgInitial.setText( viOrg );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValRectificareOrg = stm.executeQuery( valRectificataOrg );
        try {
            String vrOrg = null;
            while (rsValRectificareOrg.next()){
                vrOrg= (String) rsValRectificareOrg.getObject( "vrOrg" );
            }
            txtValoareRectificareOrg.setText( vrOrg );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValFinalaOrg = stm.executeQuery( valFinalaOrg );
        try {
            String vfOrg = null;
            while (rsValFinalaOrg.next()){
                vfOrg= (String) rsValFinalaOrg.getObject( "vfOrg" );
            }
            txtValoareFinalaOrg.setText( vfOrg );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comboBProjAction ( ActionEvent event ) throws SQLException {
        checkProj.setDisable( false );

        String denProj = "SELECT denProiect AS 'denumire' from bugetPROJ WHERE nrProiect= '"+comboBProj.getValue()+"'";
        String valInitialaProj ="SELECT valInitiala AS 'viProj' from bugetPROJ  WHERE nrProiect= '"+comboBProj.getValue()+"'";
        String valRectificataProj ="SELECT valRectificare AS 'vrProj' from bugetPROJ  WHERE nrProiect= '"+comboBProj.getValue()+"'";
        String valFinalaProj = "SELECT valFinala AS 'vfProj' from bugetPROJ  WHERE nrProiect= '"+comboBProj.getValue()+"'";


        Statement stm2 = connection.createStatement();
        ResultSet rsDenProj = stm2.executeQuery( denProj );
        try {
            String denProject = null;
            while (rsDenProj.next()){
                denProject= (String) rsDenProj.getObject( "denumire" );
            }
            txtDenProj.setText( denProject );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValInitProj = stm2.executeQuery( valInitialaProj );
        try {
            String viProj = null;
            while (rsValInitProj.next()){
                viProj= (String) rsValInitProj.getObject( "viProj" );
            }

            txtValoareProjInitial.setText( viProj);
        } catch (Exception e) {
            e.printStackTrace();        }

        ResultSet rsValRectificareProj = stm2.executeQuery( valRectificataProj );
        try {
            String vrProj = null;
            while (rsValRectificareProj.next()){
                vrProj= (String) rsValRectificareProj.getObject( "vrProj" );
            }
            if(vrProj==null)
                vrProj="0";


            txtValoarareRectificareProj.setText( vrProj );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValFinalaProj = stm2.executeQuery( valFinalaProj );
        try {
            String vfProj = null;
            while (rsValFinalaProj.next()){
                vfProj= (String) rsValFinalaProj.getObject( "vfProj" );
                System.out.println(vfProj);
            }
            assert vfProj !=null;
            double vfprojD = parseDouble( vfProj);
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("ro","RO"));
            nf.setMaximumFractionDigits(2);
            DecimalFormat df = (DecimalFormat)nf;
            txtValoareFinalaProj.setText( df.format(vfprojD ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comboBContractAction ( ActionEvent event ) throws SQLException {
        checkContract.setDisable( false );

        String denFurnizor = "SELECT furnizor AS 'denumireFz' from bugetCONTRACT WHERE nrContract= '"+comboBContract.getValue()+"'";
        String valInitialaContract ="SELECT valInitiala AS 'viContract' from bugetCONTRACT  WHERE nrContract= '"+comboBContract.getValue()+"'";
        String valRectificataContract ="SELECT valRectificare AS 'vrContract' from bugetCONTRACT  WHERE nrContract= '"+comboBContract.getValue()+"'";
        String valFinalaContract = "SELECT valFinala AS 'vfContract' from bugetCONTRACT  WHERE nrContract= '"+comboBContract.getValue()+"'";

        Statement stm2 = connection.createStatement();
        ResultSet rsDenContract = stm2.executeQuery( denFurnizor );
        try {
            String furnizor = null;
            while (rsDenContract.next()){
                furnizor= (String) rsDenContract.getObject( "denumireFz" );
            }
                txtDenFurnizor.setText( furnizor );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValInit = stm2.executeQuery( valInitialaContract );
        try {
            String viContract = null;
            while (rsValInit.next()){
                viContract= (String) rsValInit.getObject( "viContract" );
            }
            txtValoareContractInitial.setText( viContract );
        } catch (Exception e) {
            e.printStackTrace();
        }


        ResultSet rsValRectificare = stm2.executeQuery( valRectificataContract );
        try {
            String vrContract = null;
            while (rsValRectificare.next()){
                vrContract= (String) rsValRectificare.getObject( "vrContract" );
            }
            txtValoarareRectificareContract.setText( vrContract );
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultSet rsValFinala = stm2.executeQuery( valFinalaContract );
        try {
            String vfContract = null;
            while (rsValFinala.next()){
                vfContract= (String) rsValFinala.getObject( "vfContract" );
            }
            assert vfContract != null;
            double vfContractD = parseDouble( vfContract);
            NumberFormat nf = NumberFormat.getNumberInstance(new Locale("ro","RO"));
            nf.setMaximumFractionDigits(2);
            DecimalFormat df = (DecimalFormat)nf;
            txtValoareFinalaContract.setText( df.format(vfContractD ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void buttonAplicaBugetOrgAction ( ActionEvent event ) throws SQLException {
        if (comboBoxYearChose.getValue()==null){
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege anul!" );
            alert.showAndWait();
            return;
        }
        if (!checkOrgRectificareBuget.isSelected() && !checkOrgBugetInitial.isSelected()){
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege ce vrei sa modifici!" );
            alert.showAndWait();
            return;
        }

    if (checkOrgRectificareBuget.isSelected() ) {
        double valIniOrg = (parseDouble( txtValoareOrgInitial.getText() ) * 100) / 100;
        double valRectConf = (parseDouble( textFieldRectificareOrg.getText() ) * 100) / 100;

        if (txtValoareRectificareOrg.getText().isEmpty()){txtValoareRectificareOrg.setText( "0" );}
        double valRectificareAnterioaraOrg = (parseDouble( txtValoareRectificareOrg.getText() ) * 100) / 100;
        double valrRectificareTotalaOrg = valRectConf + valRectificareAnterioaraOrg;
        double valFinConfirnata = valIniOrg + valRectConf + valRectificareAnterioaraOrg;

        Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
        alert.setTitle( "Rectificare!" );
        alert.setHeaderText( "Ai modificat cu: " + valRectConf + "?" + "          Modificarea fata de bugetul initial este: " + valrRectificareTotalaOrg + "?" );
        alert.setContentText( "Valoarea finala a proiectului este: " + valFinConfirnata + "?   Este bine?" );

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            String addRectificareBugetProj = "UPDATE bugetORG SET valRectificata = '" + valrRectificareTotalaOrg + "' WHERE org = '" + comboOrg.getValue() + "' AND anulBugetar='"+comboBoxYearChose.getValue()+"'";

            try (PreparedStatement statement = connection.prepareStatement( addRectificareBugetProj )) {
                statement.executeUpdate( "UPDATE bugetORG SET valRectificata='" + valrRectificareTotalaOrg + "' WHERE org = '" + comboOrg.getValue() + "' AND anulBugetar='"+comboBoxYearChose.getValue()+"'" );
                statement.executeUpdate( "UPDATE bugetORG SET valFinala= '" + valFinConfirnata + "' WHERE org = '" + comboOrg.getValue() + "'  AND anulBugetar='"+comboBoxYearChose.getValue()+"'" );

            } catch (Exception e) {
                e.printStackTrace();
            }
            textFieldRectificareOrg.clear();
            buttonAplicaBugetOrg.setDisable( true );

            String valInitialaOrg = "SELECT valInitiala AS 'viOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "' AND anulBugetar='"+comboBoxYearChose.getValue()+"'";
            String valRectificataOrg = "SELECT valRectificata AS 'vrOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";
            String valFinalaOrg = "SELECT valFinala AS 'vfOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "'AND anulBugetar='"+comboBoxYearChose.getValue()+"'";


            Statement stm2 = connection.createStatement();

            ResultSet rsValInitOrg = stm2.executeQuery( valInitialaOrg );
            try {
                String viOrg = null;
                while (rsValInitOrg.next()) {
                    viOrg = (String) rsValInitOrg.getObject( "viOrg" );
                }
                txtValoareOrgInitial.setText( viOrg );
            } catch (Exception e) {
                e.printStackTrace();
            }

            ResultSet rsValRectificareOrg = stm2.executeQuery( valRectificataOrg );
            try {
                String vrOrg = null;
                while (rsValRectificareOrg.next()) {
                    vrOrg = (String) rsValRectificareOrg.getObject( "vrOrg" );
                }
                txtValoareRectificareOrg.setText( vrOrg );
            } catch (Exception e) {
                e.printStackTrace();
            }

            ResultSet rsValFinalaOrg = stm2.executeQuery( valFinalaOrg );
            try {
                String vfOrg = null;
                while (rsValFinalaOrg.next()) {
                    vfOrg = (String) rsValFinalaOrg.getObject( "vfOrg" );
                }
                txtValoareFinalaOrg.setText( vfOrg );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            textFieldRectificareOrg.clear();
        }
    }

        if(checkOrgBugetInitial.isSelected() && txtValoareOrgInitial.getText().isEmpty()){

            Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
            alert.setTitle( "Buget Initial!" );
            alert.setHeaderText( "Bugetul Initial al: "+comboOrg.getValue()+" pe anul "+ comboBoxYearChose.getValue()+" este de " + textFieldRectificareOrg.getText() + "?"  );
            alert.setContentText( "Valoarea initiala a proiectului este: " + textFieldRectificareOrg.getText() + "?   Este bine?" );

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                stm.executeUpdate(  "INSERT INTO  bugetORG (org, denumireOrg, anulBugetar, valInitiala,valRectificata, valFinala) VALUES ('"+comboOrg.getValue()+"','"+txtDenOrg.getText()+"','"+comboBoxYearChose.getValue()+"','"+ textFieldRectificareOrg.getText() + "','0','"+ textFieldRectificareOrg.getText() + "') ");

                textFieldRectificareOrg.clear();
                buttonAplicaBugetOrg.setDisable( true );
                checkOrg.isSelected();

                String valInitialaOrg = "SELECT valInitiala AS 'viOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "' AND anulBugetar ='"+comboBoxYearChose.getValue()+"'";
                String valRectificataOrg = "SELECT valRectificata AS 'vrOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "' AND anulBugetar ='"+comboBoxYearChose.getValue()+"'";
                String valFinalaOrg = "SELECT valFinala AS 'vfOrg' from bugetORG  WHERE org= '" + comboOrg.getValue() + "' AND anulBugetar ='"+comboBoxYearChose.getValue()+"'";


                Statement stm2 = connection.createStatement();

                ResultSet rsValInitOrg = stm2.executeQuery( valInitialaOrg );
                try {
                    String viOrg = null;
                    while (rsValInitOrg.next()) {
                        viOrg = (String) rsValInitOrg.getObject( "viOrg" );
                    }
                    txtValoareOrgInitial.setText( viOrg );
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ResultSet rsValRectificareOrg = stm2.executeQuery( valRectificataOrg );
                try {
                    String vrOrg = null;
                    while (rsValRectificareOrg.next()) {
                        vrOrg = (String) rsValRectificareOrg.getObject( "vrOrg" );
                    }
                    txtValoareRectificareOrg.setText( vrOrg );
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ResultSet rsValFinalaOrg = stm2.executeQuery( valFinalaOrg );
                try {
                    String vfOrg = null;
                    while (rsValFinalaOrg.next()) {
                        vfOrg = (String) rsValFinalaOrg.getObject( "vfOrg" );
                    }

                    double vfOrgD = parseDouble( vfOrg ) * 100 / 100;
                    NumberFormat nfOrg = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
                    nfOrg.setMaximumFractionDigits( 2 );
                    DecimalFormat dfOrg = (DecimalFormat) nfOrg;

                    txtValoareFinalaOrg.setText( dfOrg.format(vfOrgD ));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                textFieldRectificareOrg.clear();
            }
        }
        checkOrg.setSelected( false );
        checkOrgBugetInitial.setSelected( false );
        checkOrgRectificareBuget.setSelected( false );
        checkOrgRectificareBuget.setDisable( true );
        checkOrgBugetInitial.setDisable( true );
    }

    public void buttonAplicaBugetProjAction ( ActionEvent event ) throws SQLException {

        if (txtValoareProjInitial.getText().isEmpty()) {
            Alert alertComboBContract = new Alert( Alert.AlertType.CONFIRMATION );
            alertComboBContract.setTitle( "Atentionare" );
            alertComboBContract.setHeaderText( "Alege un contract!" );
            alertComboBContract.setContentText( "OK?" );
            Optional<ButtonType> result = alertComboBContract.showAndWait();
        } else {
            double valIniProj = (parseDouble( txtValoareProjInitial.getText() ) * 100) / 100;
            if (textFieldRectificareProj.getText().isEmpty()) {
                Alert alertRectificare = new Alert( Alert.AlertType.CONFIRMATION );
                alertRectificare.setTitle( "Confirmation Dialog" );
                alertRectificare.setHeaderText( "Pune valoarea de modificare a contractului" );
                alertRectificare.setContentText( "OK?" );
                Optional<ButtonType> result = alertRectificare.showAndWait();
            } else {
                double valRectConf = ( parseDouble( textFieldRectificareProj.getText() ) * 100 ) / 100;
                double valRectificareAnterioaraProj = ( parseDouble( txtValoarareRectificareProj.getText() ) * 100 ) / 100;
                double valrRectificareTotala = valRectConf + valRectificareAnterioaraProj;
                double valFinConfirnata = valIniProj + valRectConf + valRectificareAnterioaraProj;

                Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
                alert.setTitle( "Confirmation Dialog" );
                alert.setHeaderText( "Ai modificat cu: " + valRectConf + "?" + "          Modificarea fata de bugetul initial este: " + valrRectificareTotala + "?" );
                alert.setContentText( "Valoarea finala a proiectului este: " + valFinConfirnata + "?   Este bine?" );

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    String addRectificareBugetProj = "UPDATE bugetPROJ SET valRectificare = '" + valrRectificareTotala + "' WHERE nrProiect = '" + comboBProj.getValue() + "'";

                    try (PreparedStatement statement = connection.prepareStatement( addRectificareBugetProj )) {
                        statement.executeUpdate( "UPDATE bugetPROJ SET valRectificare='" + valrRectificareTotala + "' WHERE nrProiect = '" + comboBProj.getValue() + "'" );
                        statement.executeUpdate( "UPDATE bugetPROJ SET valFinala= '" + valFinConfirnata + "' WHERE nrProiect = '" + comboBProj.getValue() + "'" );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    textFieldRectificareProj.clear();
                    buttonAplicaBugetProj.setDisable( true );

                    String valInitialaProj = "SELECT valInitiala AS 'viProj' from bugetPROJ  WHERE nrProiect= '" + comboBProj.getValue() + "'";
                    String valRectificataProj = "SELECT valRectificare AS 'vrProj' from bugetPROJ  WHERE nrProiect= '" + comboBProj.getValue() + "'";
                    String valFinalaProj = "SELECT valFinala AS 'vfProj' from bugetPROJ  WHERE nrProiect= '" + comboBProj.getValue() + "'";


                    Statement stm2 = connection.createStatement();

                    ResultSet rsValInitProj = stm2.executeQuery( valInitialaProj );
                    try {
                        String viProj = null;
                        while (rsValInitProj.next()) {
                            viProj = (String) rsValInitProj.getObject( "viProj" );
                        }
                        txtValoareProjInitial.setText( viProj );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    ResultSet rsValRectificareProj = stm2.executeQuery( valRectificataProj );
                    try {
                        String vrProj = null;
                        while (rsValRectificareProj.next()) {
                            vrProj = (String) rsValRectificareProj.getObject( "vrProj" );
                        }
                        txtValoarareRectificareProj.setText( vrProj );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    ResultSet rsValFinalaProj = stm2.executeQuery( valFinalaProj );
                    try {
                        String vfProj = null;
                        while (rsValFinalaProj.next()) {
                            vfProj = (String) rsValFinalaProj.getObject( "vfProj" );
                        }

                        double vfProjD = parseDouble( vfProj ) * 100 / 100;
                        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
                        nf.setMaximumFractionDigits( 2 );
                        DecimalFormat df = (DecimalFormat) nf;
                        txtValoareFinalaProj.setText( df.format( vfProjD ) );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    textFieldRectificareProj.clear();
                }
            }
        }
        checkProj.setSelected( false );
    }

    public void buttonAplicaBugetContractAction ( ActionEvent event ) throws SQLException {
        if (txtValoareContractInitial.getText().isEmpty()) {
            Alert alertComboBContract = new Alert( Alert.AlertType.CONFIRMATION );
            alertComboBContract.setTitle( "Atentionare" );
            alertComboBContract.setHeaderText( "Alege un contract!" );
            alertComboBContract.setContentText( "OK?" );
            Optional<ButtonType> result = alertComboBContract.showAndWait();
        } else {
            double valIniContract = (parseDouble( txtValoareContractInitial.getText() ) * 100) / 100;
            if (textFieldRectificareContract.getText().isEmpty()) {
                Alert alertRectificare = new Alert( Alert.AlertType.CONFIRMATION );
                alertRectificare.setTitle( "Confirmation Dialog" );
                alertRectificare.setHeaderText( "Pune valoarea de modificare a contractului" );
                alertRectificare.setContentText( "OK?" );
                Optional<ButtonType> result = alertRectificare.showAndWait();

            }
            else {
                double valRectConf = (parseDouble( textFieldRectificareContract.getText() ) * 100) / 100;
                double valRectificareAnterioaraContract = (parseDouble( txtValoarareRectificareContract.getText() ) * 100) / 100;
                double valrRectificareTotala = valRectConf + valRectificareAnterioaraContract;
                double valFinConfirnata = valIniContract + valRectConf + valRectificareAnterioaraContract;

                Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
                alert.setTitle( "Confirmation Dialog" );
                alert.setHeaderText( "Ai modificat cu: " + valRectConf + "?" + "          Modificarea fata de bugetul initial este: " + valrRectificareTotala + "?" );
                alert.setContentText( "Valoarea finala a proiectului este: " + valFinConfirnata + "?   Este bine?" );
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    String addRectificareBugetContract = "UPDATE bugetCONTRACT SET valRectificare = '" + valrRectificareTotala + "' WHERE nrContract = '" + comboBContract.getValue() + "'";

                    try (PreparedStatement statement = connection.prepareStatement( addRectificareBugetContract )) {
                        statement.executeUpdate( "UPDATE bugetCONTRACT SET valRectificare='" + valrRectificareTotala + "' WHERE nrContract = '" + comboBContract.getValue() + "'" );
                        statement.executeUpdate( "UPDATE bugetCONTRACT SET valFinala= ('" + valFinConfirnata + "') WHERE nrContract = '" + comboBContract.getValue() + "'" );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    textFieldRectificareContract.clear();
                    buttonAplicaBugetContract.setDisable( true );

                    String valInitialaContract = "SELECT valInitiala AS 'viContract' from bugetCONTRACT  WHERE nrContract= '" + comboBContract.getValue() + "'";
                    String valRectificataContract = "SELECT valRectificare AS 'vrContract' from bugetCONTRACT  WHERE nrContract= '" + comboBContract.getValue() + "'";
                    String valFinalaContract = "SELECT valFinala AS 'vfContract' from bugetCONTRACT  WHERE nrContract= '" + comboBContract.getValue() + "'";

                    Statement stm2 = connection.createStatement();

                    ResultSet rsValInit = stm2.executeQuery( valInitialaContract );
                    try {
                        String viContract = null;
                        while (rsValInit.next()) {
                            viContract = (String) rsValInit.getObject( "viContract" );
                        }
                        txtValoareContractInitial.setText( viContract );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    ResultSet rsValRectificare = stm2.executeQuery( valRectificataContract );
                    try {
                        String vrContract = null;
                        while (rsValRectificare.next()) {
                            vrContract = (String) rsValRectificare.getObject( "vrContract" );
                        }
                        txtValoarareRectificareContract.setText( vrContract );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    ResultSet rsValFinala = stm2.executeQuery( valFinalaContract );
                    try {
                        String vfContract = null;
                        while (rsValFinala.next()) {
                            vfContract = (String) rsValFinala.getObject( "vfContract" );
                        }
                        double vfContractD = parseDouble( vfContract );
                        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
                        nf.setMaximumFractionDigits( 2 );
                        DecimalFormat df = (DecimalFormat) nf;
                        txtValoareFinalaContract.setText( df.format( vfContractD ) );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    textFieldRectificareContract.clear();
                }
            }
        }
        checkContract.setSelected( false );
    }

    public void goOnStage0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void checkOrgAction ( ActionEvent event ) {

        if (checkOrg.isSelected()){
                    if  (txtValoareOrgInitial.getText()=="0" || txtValoareOrgInitial.getText()==null || txtValoareOrgInitial.getText()==""){
                        checkOrgBugetInitial.setDisable( false );
                        checkOrgRectificareBuget.setDisable( true );
                        buttonAplicaBugetOrg.setDisable( false );
                    }
                    else {
                        checkOrgBugetInitial.setDisable( true );
                        checkOrgRectificareBuget.setDisable( false );
                        buttonAplicaBugetOrg.setDisable( false );
                    }
        }
        else {
            checkOrgBugetInitial.setSelected( false );
            checkOrgRectificareBuget.setSelected( false );
            checkOrgRectificareBuget.setDisable( true );
            checkOrgBugetInitial.setDisable( true );
            buttonAplicaBugetOrg.setDisable( true );
        }
    }

    public void checkProjAction ( ActionEvent event ) {
        if (checkProj.isSelected()){
            buttonAplicaBugetProj.setDisable( false );
        }else{
            buttonAplicaBugetProj.setDisable( true );
        }
    }

    public void checkContractAction ( ActionEvent event ) {
        if (checkContract.isSelected()){
            buttonAplicaBugetContract.setDisable( false );
        }else{
            buttonAplicaBugetContract.setDisable( true );
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        List<String> myListContract = null;
        List<String> myListProj = null;
        List<String> myListOrg = null;

        try {
            myListContract = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/contract" )) );
            myListOrg = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/org" )) );
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" )) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> contracteActive = myListContract.stream()
                .filter( contract -> !contract.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBContract.setItems( FXCollections.observableArrayList(contracteActive));

        List<String> orgActive = myListOrg.stream()
                .filter( org -> !org.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboOrg.setItems( FXCollections.observableArrayList(orgActive));

        List<String> projActiv = myListProj.stream()
                .filter( proj -> !proj.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBProj.setItems( FXCollections.observableArrayList(projActiv));

//        comboOrgType.getItems().addAll( "Buget Initial", "Rectificare" );
//        comboOrgType.getItems().add( 0, "Buget Initial" );
//        comboOrgType.getItems().add( 1, "Rectificare" );
//
//        DefaultListSelectionModel model = new DefaultListSelectionModel();
//        model.addSelectionInterval( 0, 0 );
//        model.addSelectionInterval( 1,1 );
//

        String orgExistenteInBDOrg = "SELECT org AS 'orgExistentInBD' from bugetOrg";



        comboBoxYearChose.getItems().addAll( "2021", "2022","2023","2024","2025","2026","2027","2028","2029","2030" );

//        comboOrgType.setDisable( true );
        checkOrgBugetInitial.setDisable( true );
        checkOrgRectificareBuget.setDisable( true );
        buttonAplicaBugetContract.setDisable( true );
        buttonAplicaBugetOrg.setDisable( true );
        buttonAplicaBugetProj.setDisable( true );

        checkOrg.setDisable( true );
        checkProj.setDisable( true );
        checkContract.setDisable( true );
        comboBoxYearChose.setDisable( true );
    }


    public void checkOrgBugetInitialAction ( ActionEvent event ) {
    }

    public void checkOrgRectificareBugetAction ( ActionEvent event ) {
    }
}
