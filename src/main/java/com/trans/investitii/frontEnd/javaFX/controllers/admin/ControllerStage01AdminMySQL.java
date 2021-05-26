package main.java.com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerStage01AdminMySQL implements Initializable {


    public TextField userN;
    public PasswordField pass;
    public Button login;
    public Button deleteLogData;
    public Button back;
    public TextArea textBox;
    String pathSQL = "C:\\Investitii\\resurse\\log\\MySQL";
    String pathSQLerr = "C:\\Investitii\\resurse\\log\\MySQLerr01";
    public static String USER_SQL;
    public static String PASS_SQL;


    public ControllerStage01AdminMySQL() throws SQLException {
    }//; hdhjdv

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userN.clear();
        pass.clear();

        File mySQL = new File(pathSQL);
        File mySQLerr1 = new File(pathSQLerr);

        if(mySQL.exists()) {

            try {
                userN.setText(Files.readAllLines(Paths.get(pathSQL)).get(0));
                pass.setText(Files.readAllLines(Paths.get(pathSQL)).get(1));

                USER_SQL= Files.readAllLines( Paths.get( pathSQL ) ).get( 0 );
                PASS_SQL= Files.readAllLines( Paths.get( pathSQL ) ).get( 1 );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        textBox.setText("Pentru siguranta trebuie sa introduceti username-ul si parola de mySQL. " +
                "Este de ajuns sa le puneti la prima utilizare. Daca doriti confidentialitate, iainte de iesire din program trebuie sa reveniti pe aceasta pagina si sa apadati butonul: Delete log data");
    }

    public void loginButtonAct(ActionEvent event) throws IOException, SQLException {
        String userName = userN.getText();
        String passText = pass.getText();
        File mySQL = new File(pathSQL);


        if (!userName.isEmpty() || !passText.isEmpty()) {

            try {
                Connection conn = DriverManager.getConnection(main.java.com.trans.investitii.backEnd.DBase.Investitii.URL0, userName, passText);
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathSQL, false));
                writer.append(userName + " \n");
                writer.append(passText);
                writer.close();

                PrintStream out = new PrintStream(new FileOutputStream(pathSQLerr));
                System.setOut(out);
                System.setErr(out);

                USER_SQL= userN.getText();
                PASS_SQL = pass.getText();
                System.out.println( USER_SQL + " + "+PASS_SQL);

                     mySQL.createNewFile();
                     BufferedWriter bufferedWriter =new BufferedWriter( new FileWriter(pathSQL));
                     bufferedWriter.append(userN.getText()+"\n"+pass.getText()+"\n");
                     bufferedWriter.close();

                Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
                Scene tabeleViewScene = new Scene(tableView);
                Stage window = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
                window.setScene(tabeleViewScene);
                window.show();

            } catch (SQLException throwables) {
                Alert fail = new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Atentie!");
                fail.setContentText("Datele de logare in MySQL nu se potrivesc!");
                fail.showAndWait();
            }


        }
        else {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Atentie!");
            fail.setContentText("Introduceti datele de logare in MySQL");
            fail.showAndWait();
        }
    }

    public void deleteLogDataAct(ActionEvent actionEvent) throws IOException {
        userN.clear();
        pass.clear();
        Files.deleteIfExists(Paths.get(pathSQL));
    }

    public void backAct(ActionEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene tabeleViewScene = new Scene(tableView);
        Stage window = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
        window.setScene(tabeleViewScene);
        window.show();
    }

    public void rememberAct(ActionEvent actionEvent) {
    }
}
