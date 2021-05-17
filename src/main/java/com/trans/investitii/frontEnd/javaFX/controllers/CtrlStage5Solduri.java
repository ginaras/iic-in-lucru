package main.java.com.trans.investitii.frontEnd.javaFX.controllers;


import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class CtrlStage5Solduri implements Initializable {

    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonOrg;

    public Button resetButton;
    public Button selectButton;

    public Button buttonBackSt0;
    public Button butonStage1Intro;
    public Button butonStage2Rapoarte;
    public Button butonStage3Rapoarte;
    public Button goToStage4Pif;

    public DatePicker dataSold;

    public Label setOrg;
    public Label setProj;
    public Label setFz;

    public Label labelCMOrg;
    public Label labelEchipamenteOrg;
    public Label labelMagazieOrg;
    public Label labelAlteInvOrg;

    public Label labelCMProj;
    public Label labelEchipamenteProj;
    public Label labelMagazieProj;
    public Label labelAlteInvProj;

    public Label labelCMFz;
    public Label labelEchipamenteFz;
    public Label labelMagazieFz;
    public Label labelAlteInvFz;

    public Label labelTotalOrg;
    public Label labelTotalProj;
    public Label labelTotalFz;
    public Button buttonStage6AnalizaPif;

    Connection connection = DriverManager.getConnection(Investitii.URL, Investitii.USER, Investitii.PASSWORD );

    public CtrlStage5Solduri() throws SQLException {
    }


    private void calcSoldOrg() throws SQLException {

        Object valueOrg = comboBoxButtonOrg.getValue();
        if (dataSold.getValue()==null){
            dataSold.setValue(LocalDate.now());
        }
        Object data = dataSold.getValue();
        Statement stm2 = connection.createStatement();


        if(valueOrg == null){

        }
        else{
            String queryCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND dataContabilizarii <= '"+data+"' AND ";
            String queryCMFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldOrgFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND valoare= '0' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND org = '"+valueOrg+"' ";

            String queryEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND dataContabilizarii <= '"+data+"' AND ";
            String queryEchipFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldOrgFactPifuite0201' FROM invTBL WHERE contInv = '231.02.01.01' AND valoare= '0' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND org = '"+valueOrg+"' ";

            String queryEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND dataContabilizarii <= '"+data+"' AND ";
            String queryEchipInDepFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldOrgFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND valoare= '0' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND org = '"+valueOrg+"' ";

            String queryAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND dataContabilizarii <= '"+data+"' AND ";
            String queryAlteInvFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldOrgFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND valoare= '0' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND org = '"+valueOrg+"' ";

            String querySoldTotalOrg = "SELECT round(SUM(valoare), 2) as 'totalSoldOrg' FROM invTBL WHERE dataContabilizarii <= '"+data+"' AND org = '" +valueOrg+"' ";
            String querySoldProjFactPifuite= "SELECT round(SUM(valInitiala), 2) as 'soldOrgFactPifuite' FROM invTBL WHERE valoare= '0' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"'  AND org = '"+valueOrg+"' ";

            queryCM += "org = '" +valueOrg.toString()+"' ";
            queryEchip += "org = '" +valueOrg.toString()+"' ";
            queryEchipInDep += "org = '" +valueOrg.toString()+"' ";
            queryAlteInv += "org = '" +valueOrg.toString()+"' ";

            NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
            nf.setMaximumFractionDigits( 2 );
            DecimalFormat df = (DecimalFormat) nf;
//01
            ResultSet rsProjCM = stm2.executeQuery( queryCM );
            double cMOrg = 0.00;
            try {
                while (rsProjCM.next()) {
                    cMOrg = rsProjCM.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rsProjCMPifuite = stm2.executeQuery( queryCMFactPifuite );
            double cMOrgPifuite = 0.00;
            try {
                while (rsProjCMPifuite.next()) {
                    cMOrgPifuite = rsProjCMPifuite.getDouble( "soldOrgFactPifuite01" );
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelCMOrg.setText( df.format (cMOrg+cMOrgPifuite)  );

//02.01
            ResultSet rsProjEchip = stm2.executeQuery( queryEchip );
            double echipamenteOrg = 0;
            try {
                while (rsProjEchip.next()){
                    echipamenteOrg =rsProjEchip.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rsProjEchipPifuite = stm2.executeQuery( queryEchipFactPifuite );
            double echipamenteOrgPifuite = 0;
            try {
                while (rsProjEchipPifuite.next()){
                    echipamenteOrgPifuite =rsProjEchipPifuite.getDouble( "soldOrgFactPifuite0201" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelEchipamenteOrg.setText( df.format (echipamenteOrg+echipamenteOrgPifuite ) );



//02.02
            ResultSet rsProjEchipInDep = stm2.executeQuery(queryEchipInDep);
           double echipInDep=0;
            try {
                while(rsProjEchipInDep.next()){
                    echipInDep=rsProjEchipInDep.getDouble( "totalProiect" );
                }
                labelMagazieOrg.setText( df.format (echipInDep ) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rsProjEchipInDepFactPifuite = stm2.executeQuery(queryEchipInDepFactPifuite);
            double echipInDepFactPifuite = 0;
            try {
                while (rsProjEchipInDepFactPifuite.next()){
                    echipInDepFactPifuite = rsProjEchipInDepFactPifuite.getDouble("soldOrgFactPifuite0202");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelMagazieOrg.setText( df.format (echipInDep+echipInDepFactPifuite ) );

//03
            ResultSet rsProjAlteInv = stm2.executeQuery( queryAlteInv );
           double alteInvOrg=0;
            try{
                while (rsProjAlteInv.next()){
                    alteInvOrg = rsProjAlteInv.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet rsProjAlteInvFactPifiute = stm2.executeQuery(queryAlteInvFactPifuite);

           double alteInvOrgFactPifuite=0;
            try {
                while (rsProjAlteInvFactPifiute.next()) {
                    alteInvOrgFactPifuite = rsProjAlteInvFactPifiute.getDouble("soldOrgFactPifuite03");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelAlteInvOrg.setText( df.format (alteInvOrg+ alteInvOrgFactPifuite ) );

//sold
            ResultSet rsOrgSoldTotal =stm2.executeQuery(querySoldTotalOrg);
            double soldOrgFactPifuite=0;
            double soldTotalOrg =0;
            try {
                while (rsOrgSoldTotal.next()) {
                    soldTotalOrg = rsOrgSoldTotal.getDouble("totalSoldOrg");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsSoldProjFactPifiute = stm2.executeQuery(querySoldProjFactPifuite);
            try {
               while (rsSoldProjFactPifiute.next()) {
                   soldOrgFactPifuite = rsSoldProjFactPifiute.getDouble("soldOrgFactPifuite");
               }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelTotalOrg.setText(df.format(soldTotalOrg+soldOrgFactPifuite));
        }

    }

    public void calcSoldProj() throws SQLException {
        Object valueProj = comboBoxButtonProj.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();
        if (dataSold.getValue()==null){
            dataSold.setValue(LocalDate.now());
        }
        Object data = dataSold.getValue();

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;
        if (valueProj == null){

        }
        else {
            if (valueOrg==null){
                String queryProjCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND dataContabilizarii <= '"+data+"' AND ";
                String queryProjCMFFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND nrProiect = '"+valueProj+"' ";

                String queryProjEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND dataContabilizarii <= '"+data+"' AND ";
                String queryProjEchipFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataContabilizarii <= '"+data+"' AND nrProiect = '"+valueProj+"' AND dataPIF> '"+data+"'  ";

                String queryProjEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND dataContabilizarii <= '"+data+"' AND ";
                String queryProjEchipInDepFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataContabilizarii <= '"+data+"' AND nrProiect = '"+valueProj+"' AND dataPIF> '"+data+"' ";

                String queryProjAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND dataContabilizarii <= '"+data+"'AND ";
                String queryProjAlteInvFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataContabilizarii <= '"+data+"' AND nrProiect = '"+valueProj+"' AND dataPIF> '"+data+"' ";

                String querySoldProj= "SELECT round(SUM(valoare), 2) as 'soldProiect' FROM invTBL WHERE nrProiect = '"+valueProj+"' AND dataContabilizarii <= '"+data+"'";
                String querySoldProjFactPifuite= "SELECT round(SUM(valInitiala), 2) as 'soldProiectFactPifuite' FROM invTBL WHERE nrProiect = '"+valueProj+"' AND dataPIF> '"+data+"'  AND dataContabilizarii <= '"+data+"'";

                queryProjCM += "nrProiect = '" + valueProj+ "' ";
                queryProjEchip += "nrProiect = '" + valueProj + "' ";
                queryProjEchipInDep += "nrProiect = '" + valueProj + "' ";
                queryProjAlteInv += "nrProiect = '" + valueProj + "' ";

                Statement stm2 = connection.createStatement();

//01
                ResultSet rsProjCM = stm2.executeQuery( queryProjCM );
                double totalProiect = 0.00;
                try {
                    while (rsProjCM.next()) {
                        totalProiect = rsProjCM.getDouble( "totalProiect" );
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                double soldProjCMFactPifuite=0;
                ResultSet rsProjCMFacturiPifuite= stm2.executeQuery(queryProjCMFFacturiPifuite);
                try {
                    while (rsProjCMFacturiPifuite.next()){
                        soldProjCMFactPifuite = rsProjCMFacturiPifuite.getDouble("soldProjFactPifuite01");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelCMProj.setText( df.format( totalProiect + soldProjCMFactPifuite ) );

//02.01
                ResultSet rsProjEchip = stm2.executeQuery( queryProjEchip );
                try {
                    while (rsProjEchip.next()) {
                        totalProiect = rsProjEchip.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsProjEchipFactPifuite = stm2.executeQuery(queryProjEchipFacturiPifuite);
                double soldProjEchipFactPifuite=0;
                try {
                    while (rsProjEchipFactPifuite.next()){
                        soldProjEchipFactPifuite = rsProjEchipFactPifuite.getDouble("soldProjFactPifuite02");
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelEchipamenteProj.setText( df.format( totalProiect +soldProjEchipFactPifuite) );


//.02.02
                ResultSet rsProjEchipInDep = stm2.executeQuery( queryProjEchipInDep );
                try {
                    while (rsProjEchipInDep.next()) {
                        totalProiect = rsProjEchipInDep.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsProjEchipInDepFactPifuite = stm2.executeQuery( queryProjEchipInDepFacturiPifuite );
                double soldProjEchipInDepFactPifuite=0;
                try {
                    while (rsProjEchipInDepFactPifuite.next()) {
                        soldProjEchipInDepFactPifuite= rsProjEchipInDepFactPifuite.getDouble("soldProjFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieProj.setText( df.format( totalProiect + soldProjEchipInDepFactPifuite) );


//03
                ResultSet rsProjAlteInv = stm2.executeQuery( queryProjAlteInv );
                try {
                    while (rsProjAlteInv.next()) {
                        totalProiect = rsProjAlteInv.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjAlteInvFactPifuite = stm2.executeQuery( queryProjAlteInvFacturiPifuite );
                double soldProjAlteInvFactPifuite =0;
                try {
                    while(rsProjAlteInvFactPifuite.next()){
                        soldProjAlteInvFactPifuite = rsProjAlteInvFactPifuite.getDouble("soldProjFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvProj.setText( df.format( totalProiect+ soldProjAlteInvFactPifuite) );


//SoldTotal
                ResultSet rsSoldProj = stm2.executeQuery(querySoldProj);
                double soldProiect=0;
                try {
                    while (rsSoldProj.next()) {
                        soldProiect = rsSoldProj.getDouble("soldProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsSoldProjFactPifiute = stm2.executeQuery(querySoldProjFactPifuite);
                double soldProiectFactPifuite=0;
                try{
                while (rsSoldProjFactPifiute.next()){
                        soldProiectFactPifuite= rsSoldProjFactPifiute.getDouble("soldProiectFactPifuite");
                    }
                    labelTotalProj.setText(df.format(soldProiect+soldProiectFactPifuite));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                String queryProjCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryProjCMFFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND nrProiect = '"+valueProj+"' AND org = '"+valueOrg+"'";

                String queryProjEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryProjEchipFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND nrProiect = '"+valueProj+"' AND org = '"+valueOrg+"'";

                String queryProjEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryProjEchipInDepFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataContabilizarii <= '"+data+"' AND dataPIF> '"+data+"' AND nrProiect = '"+valueProj+"' AND org = '"+valueOrg+"'";

                String queryProjAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String queryProjAlteInvFacturiPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldProjFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataContabilizarii <= '"+data+"' AND dataPIF>= '"+data+"' AND nrProiect = '"+valueProj+"' AND org = '"+valueOrg+"'";

                String querySoldProj= "SELECT round(SUM(valoare), 2) as 'soldProiect' FROM invTBL WHERE nrProiect = '"+valueProj+"' AND org ='"+valueOrg+"' ";
                String querySoldProjFactPifuite= "SELECT round(SUM(valInitiala), 2) as 'soldProiectFactPifuite' FROM invTBL WHERE nrProiect = '"+valueProj+"' AND org ='"+valueOrg+"' AND dataPIF> '"+data+"' ";

                queryProjCM += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjEchip += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjEchipInDep += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";
                queryProjAlteInv += "nrProiect = '" + valueProj.toString() + "' AND org = '"+valueOrg.toString()+"' ";

                Statement stm2 = connection.createStatement();
//01
                ResultSet rsProjCM = stm2.executeQuery( queryProjCM );
                double totalProiect = 0.00;
                try {
                    while (rsProjCM.next()) {
                        totalProiect = rsProjCM.getDouble( "totalProiect" );
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                double soldProjCMFactPifuite=0;
                ResultSet rsProjCMFacturiPifuite= stm2.executeQuery(queryProjCMFFacturiPifuite);
                try {
                    while (rsProjCMFacturiPifuite.next()){
                        soldProjCMFactPifuite = rsProjCMFacturiPifuite.getDouble("soldProjFactPifuite01");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelCMProj.setText( df.format( totalProiect + soldProjCMFactPifuite ) );
//02.01
                ResultSet rsProjEchip = stm2.executeQuery( queryProjEchip );
                try {
                    while (rsProjEchip.next()) {
                        totalProiect = rsProjEchip.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsProjEchipFactPifuite = stm2.executeQuery(queryProjEchipFacturiPifuite);
                double soldProjEchipFactPifuite=0;
                try {
                    while (rsProjEchipFactPifuite.next()){
                        soldProjEchipFactPifuite = rsProjEchipFactPifuite.getDouble("soldProjFactPifuite02");
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelEchipamenteProj.setText( df.format( totalProiect +soldProjEchipFactPifuite) );

//02.02
                ResultSet rsProjEchipInDep = stm2.executeQuery( queryProjEchipInDep );
                try {
                    while (rsProjEchipInDep.next()) {
                        totalProiect = rsProjEchipInDep.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsProjEchipInDepFactPifuite = stm2.executeQuery( queryProjEchipInDepFacturiPifuite );
                double soldProjEchipInDepFactPifuite=0;
                try {
                    while (rsProjEchipInDepFactPifuite.next()) {
                        soldProjEchipInDepFactPifuite= rsProjEchipInDepFactPifuite.getDouble("soldProjFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieProj.setText( df.format( totalProiect + soldProjEchipInDepFactPifuite) );
//03.01
                ResultSet rsProjAlteInv = stm2.executeQuery( queryProjAlteInv );
                try {
                    while (rsProjAlteInv.next()) {
                        totalProiect = rsProjAlteInv.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsProjAlteInvFactPifuite = stm2.executeQuery( queryProjAlteInvFacturiPifuite );
                double soldProjAlteInvFactPifuite =0;
                try {
                    while(rsProjAlteInvFactPifuite.next()){
                       soldProjAlteInvFactPifuite = rsProjAlteInvFactPifuite.getDouble("soldProjFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvProj.setText( df.format( totalProiect+ soldProjAlteInvFactPifuite) );


//sold
                ResultSet rsSoldProj = stm2.executeQuery(querySoldProj);

                double soldProiect=0;
                double soldProiectFactPifuite=0;
                try {
                    while (rsSoldProj.next()) {
                        soldProiect = rsSoldProj.getDouble( "soldProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsSoldProjFactPifiute = stm2.executeQuery(querySoldProjFactPifuite);
                try {
                    while (rsSoldProjFactPifiute.next()){
                        soldProiectFactPifuite= rsSoldProjFactPifiute.getDouble("soldProiectFactPifuite");
                    }
                    labelTotalProj.setText(df.format(soldProiect+soldProiectFactPifuite));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    private void calcSoldFz() throws SQLException {
        Object valueFz = comboBoxButtonFz.getValue();
        Object valueProj = comboBoxButtonProj.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        if (dataSold.getValue()==null){
            dataSold.setValue(LocalDate.now());
        }
        Object data = dataSold.getValue();

        NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
        nf.setMaximumFractionDigits( 2 );
        DecimalFormat df = (DecimalFormat) nf;

        if (valueFz == null){

        }
        else {
            if(valueOrg == null && valueProj == null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzCMFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND dataContabilizarii <'"+data+"' ";

                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND dataContabilizarii <'"+data+"' ";

                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzEchipInDepFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND dataContabilizarii <'"+data+"' ";

                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String queryFzAlteInvFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND dataContabilizarii <'"+data+"' ";

                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' ";
                String querySoldTotalFzFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite' FROM invTBL WHERE furnizor = '"+valueFz+"' AND dataPIF > '"+data+"' AND dataContabilizarii <'"+data+"' ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' ";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' ";
//01
                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFZCMFactPifite = stm2.executeQuery( queryFzCMFactPifuite );
                double soldFzCMFactPifuite=0;
                try {
                    while (rsFZCMFactPifite.next()){
                        soldFzCMFactPifuite = rsFZCMFactPifite.getDouble("soldFzFactPifuite01");
                    }
            } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } labelCMFz.setText( df.format( totalProiect+soldFzCMFactPifuite ));

///02.01
                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipFactPifite = stm2.executeQuery( queryFzEchipFactPifuite );
                double soldFzEchipFactPifuite=0;
                try {
                    while (rsFzEchipFactPifite.next()){
                        soldFzEchipFactPifuite = rsFzEchipFactPifite.getDouble("soldFzFactPifuite02");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                    labelEchipamenteFz.setText( df.format( totalProiect +soldFzEchipFactPifuite) );
//02.02
                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipInDepFactPifite = stm2.executeQuery( queryFzEchipInDepFactPifuite );
                double soldFzEchipFactInDepPifuite=0;
                try {
                    while (rsFzEchipInDepFactPifite.next()){
                        soldFzEchipFactInDepPifuite = rsFzEchipInDepFactPifite.getDouble("soldFzFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieFz.setText( df.format( totalProiect+soldFzEchipFactInDepPifuite ) );
//03.01
                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzAlteInvFactPifuite = stm2.executeQuery(queryFzAlteInvFactPifuite);
                double soldFzAlteInvFactPifuite=0;
                try {
                    while (rsFzAlteInvFactPifuite.next()){
                        soldFzAlteInvFactPifuite =rsFzAlteInvFactPifuite.getDouble("soldFzFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvFz.setText( df.format( totalProiect+soldFzAlteInvFactPifuite ) );

//sold
                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsSoldFzFactPifuite= stm2.executeQuery(querySoldTotalFzFactPifuite);
                double soldFzFactPifuite = 0;
                try {
                    while (rsSoldFzFactPifuite.next()){
                        soldFzFactPifuite = rsSoldFzFactPifuite.getDouble("soldFzFactPifuite");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelTotalFz.setText(df.format(soldFz+soldFzFactPifuite));


            }
            if(valueOrg == null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzCMFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "'AND dataContabilizarii <'"+data+"' ";

                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "' AND dataContabilizarii <'"+data+"' ";

                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzEchipInDepFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "'AND dataContabilizarii <'"+data+"' ";

                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String queryFzAlteInvFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "' AND dataContabilizarii <'"+data+"' ";

                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' AND nrProiect = '" + valueProj + "' ";
                String querySoldTotalFzFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite' FROM invTBL WHERE furnizor = '"+valueFz+"' AND dataPIF > '"+data+"' AND nrProiect = '" + valueProj + "' AND dataContabilizarii <'"+data+"' ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND nrProiect = '" + valueProj.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
//01
                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFZCMFactPifite = stm2.executeQuery( queryFzCMFactPifuite );
                double soldFzCMFactPifuite=0;
                try {
                    while (rsFZCMFactPifite.next()){
                        soldFzCMFactPifuite = rsFZCMFactPifite.getDouble("soldFzFactPifuite01");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } labelCMFz.setText( df.format( totalProiect+soldFzCMFactPifuite ));

///02.01
                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipFactPifite = stm2.executeQuery( queryFzEchipFactPifuite );
                double soldFzEchipFactPifuite=0;
                try {
                    while (rsFzEchipFactPifite.next()){
                        soldFzEchipFactPifuite = rsFzEchipFactPifite.getDouble("soldFzFactPifuite02");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelEchipamenteFz.setText( df.format( totalProiect +soldFzEchipFactPifuite) );
//02.02
                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipInDepFactPifite = stm2.executeQuery( queryFzEchipInDepFactPifuite );
                double soldFzEchipFactInDepPifuite=0;
                try {
                    while (rsFzEchipInDepFactPifite.next()){
                        soldFzEchipFactInDepPifuite = rsFzEchipInDepFactPifite.getDouble("soldFzFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieFz.setText( df.format( totalProiect+soldFzEchipFactInDepPifuite ) );
//03.01
                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzAlteInvFactPifuite = stm2.executeQuery(queryFzAlteInvFactPifuite);
                double soldFzAlteInvFactPifuite=0;
                try {
                    while (rsFzAlteInvFactPifuite.next()){
                        soldFzAlteInvFactPifuite =rsFzAlteInvFactPifuite.getDouble("soldFzFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvFz.setText( df.format( totalProiect+soldFzAlteInvFactPifuite ) );

//sold
                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsSoldFzFactPifuite= stm2.executeQuery(querySoldTotalFzFactPifuite);
                double soldFzFactPifuite = 0;
                try {
                    while (rsSoldFzFactPifuite.next()){
                        soldFzFactPifuite = rsSoldFzFactPifuite.getDouble("soldFzFactPifuite");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelTotalFz.setText(df.format(soldFz+soldFzFactPifuite));


            }
            if(valueOrg != null && valueProj == null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzCMFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND org = '" + valueOrg + "'AND dataContabilizarii <'"+data+"' ";

                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND org = '" + valueOrg + "'AND dataContabilizarii <'"+data+"' ";

                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzEchipInDepFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND org = '" + valueOrg + "'AND dataContabilizarii <'"+data+"' ";

                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String queryFzAlteInvFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND org = '" + valueOrg + "' AND dataContabilizarii <'"+data+"' ";

                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' AND org = '" + valueOrg + "' ";
                String querySoldTotalFzFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite' FROM invTBL WHERE furnizor = '"+valueFz+"' AND dataPIF > '"+data+"' AND org = '" + valueOrg + "' AND dataContabilizarii <'"+data+"' ";

                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "' ";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND org = '" + valueOrg.toString() + "' ";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'";

//01
                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFZCMFactPifite = stm2.executeQuery( queryFzCMFactPifuite );
                double soldFzCMFactPifuite=0;
                try {
                    while (rsFZCMFactPifite.next()){
                        soldFzCMFactPifuite = rsFZCMFactPifite.getDouble("soldFzFactPifuite01");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } labelCMFz.setText( df.format( totalProiect+soldFzCMFactPifuite ));

///02.01
                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipFactPifite = stm2.executeQuery( queryFzEchipFactPifuite );
                double soldFzEchipFactPifuite=0;
                try {
                    while (rsFzEchipFactPifite.next()){
                        soldFzEchipFactPifuite = rsFzEchipFactPifite.getDouble("soldFzFactPifuite02");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelEchipamenteFz.setText( df.format( totalProiect +soldFzEchipFactPifuite) );
//02.02
                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipInDepFactPifite = stm2.executeQuery( queryFzEchipInDepFactPifuite );
                double soldFzEchipFactInDepPifuite=0;
                try {
                    while (rsFzEchipInDepFactPifite.next()){
                        soldFzEchipFactInDepPifuite = rsFzEchipInDepFactPifite.getDouble("soldFzFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieFz.setText( df.format( totalProiect+soldFzEchipFactInDepPifuite ) );
//03.01
                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzAlteInvFactPifuite = stm2.executeQuery(queryFzAlteInvFactPifuite);
                double soldFzAlteInvFactPifuite=0;
                try {
                    while (rsFzAlteInvFactPifuite.next()){
                        soldFzAlteInvFactPifuite =rsFzAlteInvFactPifuite.getDouble("soldFzFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvFz.setText( df.format( totalProiect+soldFzAlteInvFactPifuite ) );

//sold
                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsSoldFzFactPifuite= stm2.executeQuery(querySoldTotalFzFactPifuite);
                double soldFzFactPifuite = 0;
                try {
                    while (rsSoldFzFactPifuite.next()){
                        soldFzFactPifuite = rsSoldFzFactPifuite.getDouble("soldFzFactPifuite");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelTotalFz.setText(df.format(soldFz+soldFzFactPifuite));

            }
            if(valueOrg != null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzCMFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite01' FROM invTBL WHERE contInv = '231.01.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "' AND org = '" + valueOrg+"' AND dataContabilizarii <'"+data+"' ";

                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite02' FROM invTBL WHERE contInv = '231.02.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "'AND org = '" + valueOrg+"' AND dataContabilizarii <'"+data+"' ";

                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzEchipInDepFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite0202' FROM invTBL WHERE contInv = '231.02.02.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "'AND org = '" + valueOrg+"' AND dataContabilizarii <'"+data+"' ";

                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String queryFzAlteInvFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite03' FROM invTBL WHERE contInv = '231.03.01.01' AND dataPIF> '"+data+"' AND furnizor = '" + valueFz + "' AND nrProiect = '" + valueProj + "'AND org = '" + valueOrg+"' AND dataContabilizarii <'"+data+"' ";

                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' AND org= '"+valueOrg+"'AND nrProiect = '"+valueProj+"'  ";
                String querySoldTotalFzFactPifuite = "SELECT round(SUM(valInitiala), 2) as 'soldFzFactPifuite' FROM invTBL WHERE furnizor = '"+valueFz+"' AND dataPIF > '"+data+"' AND nrProiect = '" + valueProj + "'AND org = '" + valueOrg+"' AND dataContabilizarii <'"+data+"' ";


                queryFzCM += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchip += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzEchipInDep += "furnizor = '" + valueFz.toString() + "'AND org = '" + valueOrg.toString() + "' AND nrProiect = '" + valueProj.toString() + "'";
                queryFzAlteInv += "furnizor = '" + valueFz.toString() + "' AND org = '" + valueOrg.toString() + "'AND nrProiect = '" + valueProj.toString() + "'";

                //01
                Statement stm2 = connection.createStatement();
                ResultSet rsFZCM = stm2.executeQuery( queryFzCM );

                double totalProiect = 0.00;
                try {
                    while (rsFZCM.next()) {
                        totalProiect = rsFZCM.getDouble("totalProiect");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFZCMFactPifite = stm2.executeQuery( queryFzCMFactPifuite );
                double soldFzCMFactPifuite=0;
                try {
                    while (rsFZCMFactPifite.next()){
                        soldFzCMFactPifuite = rsFZCMFactPifite.getDouble("soldFzFactPifuite01");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } labelCMFz.setText( df.format( totalProiect+soldFzCMFactPifuite ));

///02.01
                ResultSet rsFzEchip = stm2.executeQuery( queryFzEchip );
                try {
                    while (rsFzEchip.next()) {
                        totalProiect = rsFzEchip.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipFactPifite = stm2.executeQuery( queryFzEchipFactPifuite );
                double soldFzEchipFactPifuite=0;
                try {
                    while (rsFzEchipFactPifite.next()){
                        soldFzEchipFactPifuite = rsFzEchipFactPifite.getDouble("soldFzFactPifuite02");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelEchipamenteFz.setText( df.format( totalProiect +soldFzEchipFactPifuite) );
//02.02
                ResultSet rsFzEchipInDep = stm2.executeQuery( queryFzEchipInDep );
                try {
                    while (rsFzEchipInDep.next()) {
                        totalProiect = rsFzEchipInDep.getDouble( "totalProiect" );
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsFzEchipInDepFactPifite = stm2.executeQuery( queryFzEchipInDepFactPifuite );
                double soldFzEchipFactInDepPifuite=0;
                try {
                    while (rsFzEchipInDepFactPifite.next()){
                        soldFzEchipFactInDepPifuite = rsFzEchipInDepFactPifite.getDouble("soldFzFactPifuite0202");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                labelMagazieFz.setText( df.format( totalProiect+soldFzEchipFactInDepPifuite ) );
//03.01
                ResultSet rsFzjAlteInv = stm2.executeQuery( queryFzAlteInv );
                try {
                    while (rsFzjAlteInv.next()) {
                        totalProiect = rsFzjAlteInv.getDouble( "totalProiect" );
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet rsFzAlteInvFactPifuite = stm2.executeQuery(queryFzAlteInvFactPifuite);
                double soldFzAlteInvFactPifuite=0;
                try {
                    while (rsFzAlteInvFactPifuite.next()){
                        soldFzAlteInvFactPifuite =rsFzAlteInvFactPifuite.getDouble("soldFzFactPifuite03");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelAlteInvFz.setText( df.format( totalProiect+soldFzAlteInvFactPifuite ) );

//sold
                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ResultSet rsSoldFzFactPifuite= stm2.executeQuery(querySoldTotalFzFactPifuite);
                double soldFzFactPifuite = 0;
                try {
                    while (rsSoldFzFactPifuite.next()){
                        soldFzFactPifuite = rsSoldFzFactPifuite.getDouble("soldFzFactPifuite");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                labelTotalFz.setText(df.format(soldFz+soldFzFactPifuite));
            }
        }
    }


    public void comboBoxActOrg ( ActionEvent actionEvent ) throws SQLException {
        Object valueOrg = comboBoxButtonOrg.getValue();
        Statement stm2 = connection.createStatement();

        if (valueOrg!=null){
            ResultSet rsDenOrg = stm2.executeQuery("SELECT denumireOrg FROM bugetOrg WHERE org='"+valueOrg+"'");
            String denOrg=null;
            try {
                while (rsDenOrg.next()){
                    denOrg = rsDenOrg.getString("denumireOrg");
                }
                setOrg.setText(denOrg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void comboBoxActProj ( ActionEvent actionEvent ) throws SQLException {
        Object valueProj = comboBoxButtonProj.getValue();
        Statement stm2 = connection.createStatement();

        String selectFz = "SELECT furnizor FROM invTbl WHERE nrProiect ='" + comboBoxButtonProj.getValue() + "' ";
        String selectOrg = "SELECT org FROM invTbl WHERE nrProiect ='" + comboBoxButtonProj.getValue() + "' ";

        Object valueFz = comboBoxButtonFz.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();

        List<String> myListFz = new ArrayList<>();
        List<String> myListOrg = new ArrayList<>();

            ResultSet rsDenProj = stm2.executeQuery("SELECT denProiect FROM bugetProj WHERE nrProiect='" + valueProj + "'");
            String denProj = null;
            try {
                while (rsDenProj.next()) {
                    denProj = rsDenProj.getString("denProiect");
                }
                setProj.setText(denProj);
            } catch (Exception e) {
                e.printStackTrace();
            }
// restrangere lista furnizori

            ResultSet rsProjFz = stm2.executeQuery(selectFz);
            try {
                while (rsProjFz.next()) {
                    myListFz.add(rsProjFz.getString("furnizor"));
                }
                List<String> noDuplicatesList = myListFz.stream().distinct().collect(Collectors.toList());
                if (valueFz == null) {
                    comboBoxButtonFz.setItems(FXCollections.observableList(noDuplicatesList));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            ResultSet rsProjOrg = stm2.executeQuery(selectOrg);
            try {
                while (rsProjOrg.next()) {
                    myListOrg.add(rsProjOrg.getString("org"));
                }
                List<String> noDuplicatesOrg = myListOrg.stream().distinct().collect(Collectors.toList());
                if (valueOrg == null) {
                    comboBoxButtonOrg.setItems(FXCollections.observableList(noDuplicatesOrg));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

    }

    public void comboBoxFzAct ( ActionEvent actionEvent ) throws SQLException {
        Object valueFz = comboBoxButtonFz.getValue();
        Statement stm2 = connection.createStatement();

        if (valueFz!=null){
            ResultSet rsDenFz = stm2.executeQuery("SELECT furnizor FROM bugetContract WHERE furnizor='"+valueFz+"'");
            String denFz=null;
            try {
                while (rsDenFz.next()){
                    denFz = rsDenFz.getString("furnizor");
                }
                setFz.setText(denFz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void resetAct ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage5Solduri.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void apliFiter ( ActionEvent actionEvent ) throws SQLException {
        calcSoldOrg();
        calcSoldProj();
        calcSoldFz();
    }

      @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
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


    public void goToStage6AnalizaPif ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPIF.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
}

