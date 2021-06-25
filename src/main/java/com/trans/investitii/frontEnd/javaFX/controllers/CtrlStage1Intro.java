package main.java.com.trans.investitii.frontEnd.javaFX.controllers;

import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class CtrlStage1Intro implements Initializable {

    @FXML
    public Label furnizor;
    @FXML public Label nrFactura;
    @FXML public Label Data;
    public Label valoare;
    public Label proiect;
    public Label deviz;
    public Label utt;
    public Label Data1;
    public Label respProj;

    @FXML  public ComboBox comBoboxFz;
    public TextField fieldNrFact;
    public TextField fieldValFact;
    public DatePicker fieldDataFactura;
    public DatePicker fieldDataGL;
    public ComboBox comboBoxContract;
    public ComboBox comboBoxCtInv;
    public ComboBox cBCtFz;
    public ComboBox cBProjNr;
    public ComboBox comboBoxDeviz;
    public ComboBox comboBoxOrg;
    public ComboBox comboBoxRespProj;
    public TableColumn furnizorColumn;
    public TableColumn facturaColumn;
    public TableColumn valoareColumn;
    public TableColumn contInvColumn;
    public TableColumn respProjColumn;
    public TableColumn nrProjColumn;
    public TableColumn dataContabilizarii;
    public Button validFacturaButton;
    public Button ButtonSt2Rapoarte;
    public Button butonStage3Rapoarte;
    public Button goToStage4Pif;
    public TextField fieldDescriere;
    public Button buttonStage5Solduri;
    public Button buttonStage6AnalizaPif;
    public RadioButton radioFaraTVA;
    public RadioButton radioTVA5;
    public RadioButton radioTVA9;
    public RadioButton radioTVA19;
    public TableColumn nrCrt;
    public Button changeData;
    public Label labelSchimb;
    public Label labelSchimb1;


    @FXML
    TableView <Investitii> tableView;


    public Button buttonBackSt0;
    public Button addFacturaButtonId;

    LocalDateTime date0 = LocalDateTime.now();
    DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
    String replaceNume2 = date0.format( date2 );

    public CtrlStage1Intro () throws SQLException {
    }
    

    public ObservableList<Investitii> getInvestitii (){
        ObservableList <Investitii> investitii = FXCollections.observableArrayList() ;
        return investitii;
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
    public void goToStage2Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }
    public ObservableList <Investitii> invest;
    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    ResultSet rs1 =statement.executeQuery( "SELECT * FROM invTBL");// WHERE dataContablizarii > LOCALDATE " );
    int nrCrtAles =0;

    public void changeDataAct ( ActionEvent actionEvent ) throws SQLException {
        ObservableList<Investitii> selectedRows, allInvoice;
//        allInvoice = tableView.getItems();
//        selectedRows = tableView.getSelectionModel().getSelectedItems();
//
//        Investitii selectNrCrt = tableView.getSelectionModel().getSelectedItem();
        nrCrtAles = tableView.getSelectionModel().getSelectedItem().getNrCrt();



        System.out.println(replaceNume2 + "  Ai modificat nr curent  " +nrCrtAles+"  ");


        String selectToChange = "SELECT * FROM invTBL WHERE nrCrt= '"+nrCrtAles+"'";

        ResultSet rs4= statement.executeQuery(selectToChange);
        while (rs4.next()){
            Object myFz =  rs4.getObject( "furnizor" );
            String myNrFactura =rs4.getString( "nrFactura" );
            String myDataFacturii =rs4.getString( "dataFacturii" );
            String myDataContabilizarii = rs4.getString( "dataContabilizarii" );
            String myValoare =rs4.getString( "valoare" );
            Object myContract =rs4.getObject( "contract" );
            Object myContInv =rs4.getObject( "contInv" );
            Object myContFz =rs4.getObject( "contFz" );
            Object mynrProiect =rs4.getObject( "nrProiect" );
            Object myDeviz =rs4.getObject( "deviz" );
            Object myOrg =rs4.getObject( "org" );
            Object myRespProiect =rs4.getObject( "respProiect" );
            String myDescriereaFacturii =rs4.getString( "descriereFactura" );

            comBoboxFz.setValue( myFz );
            fieldNrFact.setText( myNrFactura );
            fieldValFact.setText( String.valueOf( myValoare ) );
            fieldDataFactura.setValue (LocalDate.parse(  myDataFacturii ));
            fieldDataGL.setValue( LocalDate.parse( myDataContabilizarii ) );
            comboBoxRespProj.setValue( myRespProiect );
            comboBoxContract.setValue( myContract );
            cBCtFz.setValue( myContFz );
            comboBoxCtInv.setValue( myContInv );
            cBProjNr.setValue( mynrProiect );
            comboBoxDeviz.setValue( myDeviz );
            comboBoxOrg.setValue( myOrg );
            fieldDescriere.setText( myDescriereaFacturii );
        }
        labelSchimb.setText( "se actualizeaza!" );
        labelSchimb1.setText( "" );

    }

    public void addFacturaButton ( ActionEvent event ) throws IOException {
        if (labelSchimb.getText() != "se actualizeaza!") {
            addFactToSQL( connection );
            clearData();
            addFacturaButtonId.setDisable( true );
        }
        else {
            updateFactura(connection);
            labelSchimb.setText( "Selecteaza o factura, apasa butonul " );
            labelSchimb1.setText( "si poti schimba datele facturii selectate" );
            clearData();
            addFacturaButtonId.setDisable( true );
        }

    }

    private void updateFactura ( Connection connection ) {
        String updateFactura = "INSERT INTO invTBL  (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereaFacturii)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ObservableList<Investitii> selectedRows, allInvoice;

        allInvoice = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        try (PreparedStatement statement = connection.prepareStatement(updateFactura) ){
            Investitii newInvestitii = new Investitii(
                    comBoboxFz.getValue(),
                    fieldNrFact.getText().toUpperCase(),
                    fieldValFact.getText(),
                    fieldDataFactura.getValue(),
                    fieldDataGL.getValue(),
                    comboBoxContract.getValue(),
                    comboBoxCtInv.getValue(),
                    cBCtFz.getValue(),
                    comboBoxRespProj.getValue(),
                    comboBoxDeviz.getValue(),
                    comboBoxOrg.getValue(),
                    cBProjNr.getValue(),
                    fieldDescriere.getText());

            if(!fieldValFact.getText().isEmpty() || !(fieldDataGL.getValue() ==null) || !(fieldDataFactura.getValue()==null))
            {
                if (radioFaraTVA.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva =  0;
                    double valTot = val ;

                    statement.executeUpdate( "UPDATE invTBL SET furnizor = '"+comBoboxFz.getValue()+"', nrFactura ='"+fieldNrFact.getText().toUpperCase()+"', dataFacturii = '" +fieldDataFactura.getValue()+ "', dataContabilizarii ='" +fieldDataGL.getValue() +"', valoare = '" +val+ "', valInitiala = '" +val+ "', tva = '" +tva+ "', valTot='"+valTot+"', contract ='"+ comboBoxContract.getValue()+ "', contInv='"+comboBoxCtInv.getValue()+"', contFz ='"+cBCtFz.getValue()+"', nrProiect='"+cBProjNr.getValue()+"', deviz='"+comboBoxDeviz.getValue()+"', org='"+comboBoxOrg.getValue()+"', respProiect='"+comboBoxRespProj.getValue()+"', descriereFactura='"+fieldDescriere.getText()+"' WHERE nrCrt='"+nrCrtAles+"' ");

                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost MODIFICATA" );
                    confirm.setContentText( "valoare: " +val+ "  TVA :  " + tva + "   Valoare Totala  : " + valTot );
                    confirm.show();

                    for (Investitii factura : selectedRows) {
                        allInvoice.remove( factura );
                    }
                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)

                }
                if (radioTVA19.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva0 = val * 0.19;
                    tva0 = Math.round( tva0 * 100 );
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round( valTot * 100 );
                    valTot = valTot / 100;

                    statement.executeUpdate( "UPDATE invTBL SET furnizor = '"+comBoboxFz.getValue()+"', nrFactura ='"+fieldNrFact.getText().toUpperCase()+"', dataFacturii = '" +fieldDataFactura.getValue()+ "', dataContabilizarii ='" +fieldDataGL.getValue() +"', valoare = '" +val+ "', valInitiala = '" +val+ "', tva = '" +tva+ "', valTot='"+valTot+"', contract ='"+ comboBoxContract.getValue()+ "', contInv='"+comboBoxCtInv.getValue()+"', contFz ='"+cBCtFz.getValue()+"', nrProiect='"+cBProjNr.getValue()+"', deviz='"+comboBoxDeviz.getValue()+"', org='"+comboBoxOrg.getValue()+"', respProiect='"+comboBoxRespProj.getValue()+"', descriereFactura='"+fieldDescriere.getText()+"' WHERE nrCrt='"+nrCrtAles+"' ");

                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost MODIFICATA" );
                    confirm.setContentText( "valoare: " +val+ "  TVA :  " + tva + "   Valoare Totala  : " + valTot );
                    confirm.show();

                    for (Investitii factura : selectedRows) {
                        allInvoice.remove( factura );
                    }
                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
                }
                if (radioTVA9.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva0 = val * 0.09;
                    tva0 = Math.round( tva0 * 100 );
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round( valTot * 100 );
                    valTot = valTot / 100;

                    statement.executeUpdate( "UPDATE invTBL SET furnizor = '"+comBoboxFz.getValue()+"', nrFactura ='"+fieldNrFact.getText().toUpperCase()+"', dataFacturii = '" +fieldDataFactura.getValue()+ "', dataContabilizarii ='" +fieldDataGL.getValue() +"', valoare = '" +val+ "', valInitiala = '" +val+ "', tva = '" +tva+ "', valTot='"+valTot+"', contract ='"+ comboBoxContract.getValue()+ "', contInv='"+comboBoxCtInv.getValue()+"', contFz ='"+cBCtFz.getValue()+"', nrProiect='"+cBProjNr.getValue()+"', deviz='"+comboBoxDeviz.getValue()+"', org='"+comboBoxOrg.getValue()+"', respProiect='"+comboBoxRespProj.getValue()+"', descriereFactura='"+fieldDescriere.getText()+"' WHERE nrCrt='"+nrCrtAles+"' ");
//
                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost MODIFICATA" );
                    confirm.setContentText( "valoare: " +val+ "  TVA :  " + tva + "   Valoare Totala  : " + valTot );
                    confirm.show();

                    for (Investitii factura : selectedRows) {
                        allInvoice.remove( factura );
                    }
                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)


                }
                if (radioTVA5.isSelected()) {
                    double val = parseDouble(fieldValFact.getText());
                    val = Math.round(val * 100);
                    val = val / 100;
                    double tva0 = val * 0.05;
                    tva0 = Math.round(tva0 * 100);
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round(valTot * 100);
                    valTot = valTot / 100;

                    statement.executeUpdate( "UPDATE invTBL SET furnizor = '"+comBoboxFz.getValue()+"', nrFactura ='"+fieldNrFact.getText().toUpperCase()+"', dataFacturii = '" +fieldDataFactura.getValue()+ "', dataContabilizarii ='" +fieldDataGL.getValue() +"', valoare = '" +val+ "', valInitiala = '" +val+ "', tva = '" +tva+ "', valTot='"+valTot+"', contract ='"+ comboBoxContract.getValue()+ "', contInv='"+comboBoxCtInv.getValue()+"', contFz ='"+cBCtFz.getValue()+"', nrProiect='"+cBProjNr.getValue()+"', deviz='"+comboBoxDeviz.getValue()+"', org='"+comboBoxOrg.getValue()+"', respProiect='"+comboBoxRespProj.getValue()+"', descriereFactura='"+fieldDescriere.getText()+"' WHERE nrCrt='"+nrCrtAles+"' ");

                    Alert confirm = new Alert(Alert.AlertType.INFORMATION);
                    confirm.setHeaderText( "Factura a fost MODIFICATA" );
                    confirm.setContentText( "valoare: " +val+ "  TVA :  " + tva + "   Valoare Totala  : " + valTot );
                    confirm.show();

                    for (Investitii factura : selectedRows) {
                        allInvoice.remove( factura );
                    }
                    tableView.getItems().addAll(newInvestitii); // adauga campuri in tabel)
                }
            }

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void onClickChangeItems (MouseEvent event ) throws SQLException {
        Investitii selectFact = tableView.getSelectionModel().getSelectedItem();
        if (selectFact !=null){
            changeData.setDisable( false );
        }

           int nrCrtAles0 = tableView.getSelectionModel().getSelectedItem().getNrCrt();
        System.out.println(replaceNume2 + "  Ai selectat nr curent  "+nrCrtAles0);
        if (nrCrtAles0==0) {
            changeData.setDisable( true );
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Nu poti modifica o factura de 2 ori in aceeasi sesiune" );
            alert.showAndWait();
            return;
        }
    }

    public void addFactToSQL (Connection connection){
        String addSql = "INSERT INTO invTBL  (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereaFacturii)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(addSql) ){
            Investitii newInvestitii = new Investitii(
                    comBoboxFz.getValue(),
                    fieldNrFact.getText().toUpperCase(),
                    fieldValFact.getText(),
                    fieldDataFactura.getValue(),
                    fieldDataGL.getValue(),
                    comboBoxContract.getValue(),
                    comboBoxCtInv.getValue(),
                    cBCtFz.getValue(),
                    comboBoxRespProj.getValue(),
                    comboBoxDeviz.getValue(),
                    comboBoxOrg.getValue(),
                    cBProjNr.getValue(),
                    fieldDescriere.getText());

           if(!fieldValFact.getText().isEmpty() || !(fieldDataGL.getValue() ==null) || !(fieldDataFactura.getValue()==null))
            {
                if (radioFaraTVA.isSelected()){
                       double val= parseDouble( fieldValFact.getText());
                            val = Math.round( val*100);
                            val = val/100;
                       double tva0 = val * 0;
                       tva0 = Math.round( tva0 * 100 );
                       double tva = tva0 / 100;
                       double valTot = val + tva;
                       valTot = Math.round( valTot * 100 );
                       valTot = valTot / 100;

                       statement.executeUpdate( "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('"+comBoboxFz.getValue()+"','" +fieldNrFact.getText().toUpperCase()+ "','" +fieldDataFactura.getValue()+ " ',' " +fieldDataGL.getValue() + "','" +val+ " ','" +val+ " ',' " +tva+ " ' , '" +valTot+ " ',' " +
                               comboBoxContract.getValue()+ "','" +comboBoxCtInv.getValue()+ "','" +cBCtFz.getValue()+ "','" +cBProjNr.getValue()+ "','" +comboBoxDeviz.getValue() +"','"+comboBoxOrg.getValue()+"','"+comboBoxRespProj.getValue()+"','"+fieldDescriere.getText()+"')" );

                       Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                       confirm.setHeaderText( "Factura a fost adaugata" );
                       confirm.setContentText( "TVA :  " + tva + "   Valoare Totala  : " + valTot );
                       confirm.show();

                       tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
                }
                if (radioTVA19.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva0 = val * 0.19;
                    tva0 = Math.round( tva0 * 100 );
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round( valTot * 100 );
                    valTot = valTot / 100;

                    statement.executeUpdate( "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('"+comBoboxFz.getValue()+"','" +fieldNrFact.getText().toUpperCase()+ "','" +fieldDataFactura.getValue()+ " ',' " +fieldDataGL.getValue() + "','" +val+ " ','" +val+ " ',' " +tva+ " ' , '" +valTot+ " ',' " +
                            comboBoxContract.getValue()+ "','" +comboBoxCtInv.getValue()+ "','" +cBCtFz.getValue()+ "','" +cBProjNr.getValue()+ "','" +comboBoxDeviz.getValue() +"','"+comboBoxOrg.getValue()+"','"+comboBoxRespProj.getValue()+"','"+fieldDescriere.getText()+"')" );
//
                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost adaugata" );
                    confirm.setContentText( "TVA:  " + tva + "    Valoare Totala:   " + valTot );
                    confirm.show();

                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
                }
                if (radioTVA9.isSelected()){
                    double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
                    double tva0 = val * 0.09;
                    tva0 = Math.round( tva0 * 100 );
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round( valTot * 100 );
                    valTot = valTot / 100;

                    statement.executeUpdate( "INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('"+comBoboxFz.getValue()+"','" +fieldNrFact.getText().toUpperCase()+ "','" +fieldDataFactura.getValue()+ " ',' " +fieldDataGL.getValue() + "','" +val+ " ','" +val+ " ',' " +tva+ " ' , '" +valTot+ " ',' " +
                            comboBoxContract.getValue()+ "','" +comboBoxCtInv.getValue()+ "','" +cBCtFz.getValue()+ "','" +cBProjNr.getValue()+ "','" +comboBoxDeviz.getValue() +"','"+comboBoxOrg.getValue()+"','"+comboBoxRespProj.getValue()+"','"+fieldDescriere.getText()+"')" );

                    Alert confirm = new Alert( Alert.AlertType.INFORMATION );
                    confirm.setHeaderText( "Factura a fost adaugata" );
                    confirm.setContentText( "TVA:  " + tva + "    Valoare Totala:   " + valTot );
                    confirm.show();

                    tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)

            }
                if (radioTVA5.isSelected()) {
                    double val = parseDouble(fieldValFact.getText());
                    val = Math.round(val * 100);
                    val = val / 100;
                    double tva0 = val * 0.05;
                    tva0 = Math.round(tva0 * 100);
                    double tva = tva0 / 100;
                    double valTot = val + tva;
                    valTot = Math.round(valTot * 100);
                    valTot = valTot / 100;

                    statement.executeUpdate("INSERT INTO invTBL (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, valInitiala, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect, descriereFactura) VALUES('" + comBoboxFz.getValue() + "','" + fieldNrFact.getText().toUpperCase() + "','" + fieldDataFactura.getValue() + " ',' " + fieldDataGL.getValue() + "','" + val + " ','" + val + " ',' " + tva + " ' , '" + valTot + " ',' " +
                            comboBoxContract.getValue() + "','" + comboBoxCtInv.getValue() + "','" + cBCtFz.getValue() + "','" + cBProjNr.getValue() + "','" + comboBoxDeviz.getValue() + "','" + comboBoxOrg.getValue() + "','" + comboBoxRespProj.getValue() + "','" + fieldDescriere.getText() + "')");

                    Alert confirm = new Alert(Alert.AlertType.INFORMATION);
                    confirm.setHeaderText("Factura a fost adaugata");
                    confirm.setContentText("TVA:  " + tva + "    Valoare Totala:   " + valTot);
                    confirm.show();

                    tableView.getItems().addAll(newInvestitii); // adauga campuri in tabel)
                }
            }

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        addFacturaButtonId.setDisable( true );
        changeData.setDisable( true );
        ToggleGroup toggleGroup = new ToggleGroup();

        radioFaraTVA.setToggleGroup(toggleGroup);
        radioTVA5.setToggleGroup(toggleGroup);
        radioTVA9.setToggleGroup(toggleGroup);
        radioTVA19.setToggleGroup(toggleGroup);

        labelSchimb.setText( "Selecteaza o factura, apasa butonul " );
        labelSchimb1.setText( "si poti schimba datele facturii selectate" );

        List<String> myListFz = null;
        List<String> myListContract = null;
        List<String> myListCtInvest = null;
        List<String> myListCtFz = null;
        List<String> myListProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListContract = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ));
            myListCtInvest = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctInv") ));
            myListCtFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctFz") ));
            myListDeviz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/deviz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListRespProj = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/respproj") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> furnizoriActivi = myListFz.stream()
                .filter( furnizor -> !furnizor.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comBoboxFz.setItems( FXCollections.observableArrayList(furnizoriActivi));

        List<String> contracteActive = myListContract.stream()
                .filter( contract -> !contract.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxContract.setItems( FXCollections.observableArrayList(contracteActive));

        List<String> ctInvestActive = myListCtInvest.stream()
                .filter( ctInvest -> !ctInvest.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxCtInv.setItems( FXCollections.observableArrayList(ctInvestActive));

        List<String> ctFzActivi = myListCtFz.stream()
                .filter( ctFz -> !ctFz.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        cBCtFz.setItems( FXCollections.observableArrayList(ctFzActivi));

        List<String> devizeActive = myListDeviz.stream()
                .filter( devize -> !devize.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxDeviz.setItems( FXCollections.observableArrayList(devizeActive));

        List<String> orgActive = myListOrg.stream()
                .filter( org -> !org.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxOrg.setItems( FXCollections.observableArrayList(orgActive));

        List<String> responsabiliActivi = myListRespProj.stream()
                .filter( resp -> !resp.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        comboBoxRespProj.setItems( FXCollections.observableArrayList(responsabiliActivi));

        List<String> proiecteActive = myListProj.stream()
                .filter( proiect -> !proiect.contains( "INACTIV-" ) )
                .collect( Collectors.toList() );
        cBProjNr.setItems( FXCollections.observableArrayList(proiecteActive));
//pt tabel in scena
        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        facturaColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareColumn.setCellValueFactory(  new PropertyValueFactory<>( "valoare" ) );
        contInvColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        nrProjColumn.setCellValueFactory( new PropertyValueFactory<>( "nrProiect" ) );
        respProjColumn.setCellValueFactory( new PropertyValueFactory<>( "respProiect" ) );
        dataContabilizarii.setCellValueFactory( new PropertyValueFactory<>( "dataContabilizarii" ) );
        nrCrt.setCellValueFactory( new PropertyValueFactory<>( "nrCrt" ) );
        tableView.setItems(getInvestitii()  );

        invest = FXCollections.observableArrayList();
        int lastNrCrt = 0;
        try {
            ResultSet rs2 =statement.executeQuery( "SELECT * FROM invTBL");
            while ((rs2.next())){
                if(rs2.isLast()) {
                    lastNrCrt = rs2.getInt("nrCrt")-19;
                }
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs3 =statement.executeQuery( "SELECT * FROM invTBL WHERE nrCrt>='"+lastNrCrt+"'");
            while (rs3.next()) {invest.addAll( new Investitii(
                    rs3.getString( "furnizor" ),
                    rs3.getString( "nrFactura" ),
                    rs3.getString( "valoare" ),
                    rs3.getObject( "contInv" ),
                    rs3.getObject( "nrProiect" ),
                    rs3.getObject( "respProiect" ),
                    rs3.getString("dataContabilizarii"),
                    rs3.getInt( "nrCrt" )));

                    tableView.setItems( invest);
            }
        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void clearData() {
        comBoboxFz.getSelectionModel().select( null);
//        fieldNrFact.setText( null );
        fieldNrFact.clear();
        fieldValFact.clear();
        fieldDataFactura.getEditor().clear();
        fieldDataGL.getEditor().clear();
        comboBoxRespProj.getSelectionModel().select( null);
        comboBoxContract.getSelectionModel().select( null);
        comboBoxCtInv.getSelectionModel().select( null);
        cBCtFz.getSelectionModel().select( null);
        cBProjNr.getSelectionModel().select( null);
        comboBoxDeviz.getSelectionModel().select( null);
        comboBoxOrg.getSelectionModel().select( null);
        fieldDescriere.clear();
    }

    public void validareCampuri() throws InterruptedException {
        if (comBoboxFz.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege Furnizorul");
            alert.showAndWait();
            return;
        }

        if (fieldNrFact.getText().isEmpty() || fieldNrFact.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adauga numarul de factura!");
            alert.showAndWait();
            return;
        }
        if (fieldValFact.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adauga VALOAREA facturii fara TVA!");
            alert.showAndWait();
            return;
        }

        try {
            double s = parseDouble(fieldValFact.getText());
            String f = fieldValFact.getText();

            if (f.matches("\\d+(\\.\\d\\d)")) {

            }

        } catch (NumberFormatException e) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText("Adauga VALOAREA ca numar! Atentie la virgula!");
            alert1.showAndWait();
            return;
        }

        if (fieldDataFactura.getValue() == null || fieldDataGL.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Adauga data!");
            alert.showAndWait();
            return;
        }
        if (comboBoxContract.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege numarul de contract");
            alert.showAndWait();
            return;
        }
        if (comboBoxCtInv.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege contul de investitii");
            alert.showAndWait();
            return;
        }
        if (cBCtFz.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege contul de furnizori");
            alert.showAndWait();
            return;
        }
        if (cBProjNr.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege numarul de proiect");
            alert.showAndWait();
            return;
        }
        if (comboBoxRespProj.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege responsabilul de proiect");
            alert.showAndWait();
            return;
        }
        if (comboBoxDeviz.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege numarul de deviz");
            alert.showAndWait();
            return;
        }
        if (comboBoxOrg.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege organizatia");
            alert.showAndWait();
            return;
        }
        if (fieldDescriere.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Completeaza obiectul facturii");
            alert.showAndWait();
            return;
        }
        if (!radioFaraTVA.isSelected() && !radioTVA5.isSelected() && !radioTVA19.isSelected() && !radioTVA9.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege TVA - ul");
            alert.showAndWait();
            return;
        }
        if ((radioFaraTVA.isSelected() && radioTVA5.isSelected() && radioTVA19.isSelected() && radioTVA9.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Alege TVA - ul");
            alert.showAndWait();
            return;
        }

        if (comBoboxFz.getValue() != null && !fieldNrFact.getText().isEmpty() && !fieldValFact.getText().isEmpty()
                && fieldDataFactura.getValue() != null && fieldDataGL.getValue() != null
                && !( comboBoxContract.getValue() == null ) && !( comboBoxCtInv.getValue() == null ) && !( cBCtFz.getValue() == null )
                && !( cBProjNr.getValue() == null ) && !( comboBoxDeviz.getValue() == null )
                && !( comboBoxOrg.getValue() == null ) && !( comboBoxRespProj.getValue() == null )
                && !fieldDescriere.getText().isEmpty()) {
            addFacturaButtonId.setDisable(false);
        }


        if (radioFaraTVA.isSelected()) {
            double val = parseDouble(fieldValFact.getText());
            val = Math.round(val * 100);
            val = val / 100;
            double tva0 = val * 0;
            tva0 = Math.round(tva0 * 100);
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round(valTot * 100);
            valTot = valTot / 100;

            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setHeaderText("Factura nu are TVA");
            confirm.setContentText("TVA   :" + tva + "     Valoare Totala:   " + valTot);
            confirm.show();
        }
        if (radioTVA5.isSelected()) {
            double val = parseDouble(fieldValFact.getText());
            val = Math.round(val * 100);
            val = val / 100;
            double tva0 = val * 0.05;
            tva0 = Math.round(tva0 * 100);
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round(valTot * 100);
            valTot = valTot / 100;

            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setHeaderText("Factura are TVA");
            confirm.setContentText("TVA :  " + tva + "    Valoare Totala : " + valTot);
            confirm.show();
        }
        if (radioTVA9.isSelected()) {
            double val = parseDouble(fieldValFact.getText());
            val = Math.round(val * 100);
            val = val / 100;
            double tva0 = val * 0.09;
            tva0 = Math.round(tva0 * 100);
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round(valTot * 100);
            valTot = valTot / 100;

            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setHeaderText("Factura are TVA");
            confirm.setContentText("TVA :  " + tva + "    Valoare Totala : " + valTot);
            confirm.show();
        }
        if (radioTVA19.isSelected()) {
            double val = parseDouble(fieldValFact.getText());
            val = Math.round(val * 100);
            val = val / 100;
            double tva0 = val * 0.19;
            tva0 = Math.round(tva0 * 100);
            double tva = tva0 / 100;
            double valTot = val + tva;
            valTot = Math.round(valTot * 100);
            valTot = valTot / 100;

            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setHeaderText("Factura are TVA");
            confirm.setContentText("TVA :  " + tva + "    Valoare Totala : " + valTot);
            confirm.show();
        }
    }

    public void goOnStage3Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage3Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage3RapoarteInv.fxml" ) );
        Scene tableViewScene = new Scene( stage3Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goToStage4Pif ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage4PIF.fxml" ) );
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
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/Stage6AnalizaPif.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }


}
