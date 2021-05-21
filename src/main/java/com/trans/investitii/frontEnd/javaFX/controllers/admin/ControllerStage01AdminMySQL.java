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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerStage01AdminMySQL implements Initializable {


    public TextField userN;
    public PasswordField pass;
    public Button login;
    public Button deleteLogData;
    public Button back;
    public TextArea textBox;
    String pathSQL = "src/main/resources/txt/login/MySQL";
    String pathSQLerr = "src/main/resources/txt/login/MySQLerr";
    public static String USER_SQL;
    public static String PASS_SQL;


    public ControllerStage01AdminMySQL() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userN.clear();
        pass.clear();
        try {
            Files.deleteIfExists(Paths.get(pathSQLerr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File mySQL = new File(pathSQL);
        File mySQLerr = new File(pathSQLerr);

        if(mySQL.exists()) {
            int n = 0;

            try {
                FileReader reader = new FileReader(pathSQL);
                BufferedReader bufferedReader=new BufferedReader(reader);
                for (n=0; n<2; n++){
                    if (n==0){
                        USER_SQL =bufferedReader.readLine();
                        continue;
                    }
                    if (n==1){
                        PASS_SQL = String.valueOf(bufferedReader.readLine());                    }
                    bufferedReader.close();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(USER_SQL+"+ USER +"+PASS_SQL);
            System.out.println(userN.toString()+"  "+pass.toString());

        }
//        }
    }

    public void loginButtonAct(ActionEvent event) throws IOException, SQLException {
        String userName = userN.getText();
        String passText = pass.getText();

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

                USER_SQL= (String) userN.getCharacters();
                PASS_SQL = (String) pass.getCharacters();
                System.out.println( USER_SQL + " + "+PASS_SQL);

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
