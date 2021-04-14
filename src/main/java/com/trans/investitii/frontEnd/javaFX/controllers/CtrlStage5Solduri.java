package com.trans.investitii.frontEnd.javaFX.controllers;


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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage5Solduri implements Initializable {

    public ComboBox comboBoxButtonProj;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonOrg;
    public ComboBox comboBoxButtonOrgYear;
    public ComboBox comboBoxButtonFzContract;

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

    Connection connection = DriverManager.getConnection(Investitii.URL, Investitii.USER, Investitii.PASSWORD );

    public CtrlStage5Solduri() throws SQLException {
    }


    private void calcSoldOrg() throws SQLException {
        Object valueOrg = comboBoxButtonOrg.getValue();
        Statement stm2 = connection.createStatement();


        if(valueOrg == null){

        }
        else{
            String queryCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
            String queryEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
            String queryEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
            String queryAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
            String querySoldTotalOrg = "SELECT round(SUM(valoare), 2) as 'totalSoldOrg' FROM invTBL";

            queryCM += "org = '" +valueOrg.toString()+"' ";
            queryEchip += "org = '" +valueOrg.toString()+"' ";
            queryEchipInDep += "org = '" +valueOrg.toString()+"' ";
            queryAlteInv += "org = '" +valueOrg.toString()+"' ";

            NumberFormat nf = NumberFormat.getNumberInstance( new Locale( "ro", "RO" ) );
            nf.setMaximumFractionDigits( 2 );
            DecimalFormat df = (DecimalFormat) nf;

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

            ResultSet rsOrgSoldTotal =stm2.executeQuery(querySoldTotalOrg);
            double soldTotalOrg =0;
            try {
                while (rsOrgSoldTotal.next()){
                    soldTotalOrg =  rsOrgSoldTotal.getDouble("totalSoldOrg");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelTotalOrg.setText(df.format(soldTotalOrg));
        }

    }

    public void calcSoldProj() throws SQLException {
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
                String querySoldProj= "SELECT round(SUM(valoare), 2) as 'soldProiect' FROM invTBL WHERE nrProiect = '"+valueProj+"'";

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

                ResultSet rsSoldProj = stm2.executeQuery(querySoldProj);
                double soldProiect=0;
                try {
                    while (rsSoldProj.next()){
                        soldProiect= rsSoldProj.getDouble("soldProiect");
                    }
                    labelTotalProj.setText(df.format(soldProiect));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else{
                String queryProjCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryProjEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryProjEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryProjAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String querySoldProj= "SELECT round(SUM(valoare), 2) as 'soldProiect' FROM invTBL WHERE nrProiect = '"+valueProj+"' AND org ='"+valueOrg+"' ";

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

                ResultSet rsSoldProj = stm2.executeQuery(querySoldProj);
                double soldProiect=0;
                try {
                    while (rsSoldProj.next()){
                        soldProiect= rsSoldProj.getDouble("soldProiect");
                    }
                    labelTotalProj.setText(df.format(soldProiect));
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
                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' ";

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

                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                    labelTotalFz.setText(df.format(soldFz));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if(valueOrg == null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' AND nrProiect = '" + valueProj.toString() + "' ";

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

                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                    labelTotalFz.setText(df.format(soldFz));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



            }
            if(valueOrg != null && valueProj == null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+" AND org = '" + valueOrg.toString() + "' ";

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

                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                    labelTotalFz.setText(df.format(soldFz));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(valueOrg != null && valueProj != null) {
                String queryFzCM = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.01.01.01' AND ";
                String queryFzEchip = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.01.01' AND ";
                String queryFzEchipInDep = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.02.02.01' AND ";
                String queryFzAlteInv = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE contInv = '231.03.01.01' AND ";
                String querySoldTotalFz = "SELECT round(SUM(valoare), 2) as 'totalSoldFz' FROM invTBL WHERE furnizor = '"+valueFz+"' AND org= '"+valueOrg+"'AND nrProiect = '"+valueProj+"'  ";


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
                ResultSet rsSoldFz = stm2.executeQuery(querySoldTotalFz);
                double soldFz = 0;
                try {
                    while (rsSoldFz.next()){
                        soldFz = rsSoldFz.getDouble("totalSoldFz");
                    }
                    labelTotalFz.setText(df.format(soldFz));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void comboBoxActOrgYear ( ActionEvent actionEvent ) {
    }

    public void comboBoxFzContractAct ( ActionEvent actionEvent ) {
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

//        if (valueProj != null) {
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
//        }
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
        comboBoxButtonOrgYear.getItems().addAll("2020","2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030" );


    }
}

