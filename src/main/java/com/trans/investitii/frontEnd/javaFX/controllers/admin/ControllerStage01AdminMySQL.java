package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerStage01AdminMySQL implements Initializable {


    public Button backupFacturiXLS;
    public Button backupOrgXLS;
    public Button backupProjXLS;
    public Button backupContracteXLS;

    public Button CreateBackupMySQLButton;

    public Button confirmLoadBackupButton;
    public Button mySQLBackupFacturaButton;
    public Button mySQLBackupOrgButton;
    public Button mySQLBacKupProjButton;
    public Button mySQLBacKupContractButton;

    public Button back;

    public ControllerStage01AdminMySQL() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mySQLBacKupContractButton.setDisable(true);
        mySQLBackupFacturaButton.setDisable(true);
        mySQLBackupOrgButton.setDisable(true);
        mySQLBacKupProjButton.setDisable(true);

    }



    public void backAct(ActionEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        Stage window = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void backupFacturiXLSButtonAct(ActionEvent actionEvent) {
    }

    public void backupOrgXLSButtonAct(ActionEvent actionEvent) {
    }

    public void backupProjXLSButtonAct(ActionEvent actionEvent) {
    }

    public void backupContracteXLSButtonAct(ActionEvent actionEvent) {
    }

    public void confirmLoadBackupButtonAct(ActionEvent actionEvent) {
        Alert alertConfirmLoad = new Alert(Alert.AlertType.WARNING);


        mySQLBacKupContractButton.setDisable(false);
        mySQLBackupFacturaButton.setDisable(false);
        mySQLBackupOrgButton.setDisable(false);
        mySQLBacKupProjButton.setDisable(false);

    }

    public void mySQLBackupFacturiButtonAct(ActionEvent actionEvent) {
    }

    public void mySQLBackupOrgButtonAct(ActionEvent actionEvent) {
    }

    public void mySQLBackupProjButton(ActionEvent actionEvent) {
    }

    public void mySQLBackupContractButtonAct(ActionEvent actionEvent) {
    }

    public void createBackupMySQLButtonAct(ActionEvent actionEvent) {
    }
}
