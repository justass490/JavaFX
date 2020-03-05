package fxLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;

public class Controller {
    @FXML
    javafx.scene.control.Button button1;
    @FXML
    TextField usernameInput;
    @FXML
    TextField passwordInput;
    @FXML
    TextField emailInput;
    @FXML
    TextField usernameLoginInput;
    @FXML
    TextField passwordLoginInput;
    @FXML
    TextField pavadinimas;
    @FXML
    TextField vardasPavarde;
    @FXML
    TextField masinosNumeris;
    @FXML
    TextField masinosMarke;
    @FXML
    TextField searchid;
    @FXML
    ComboBox dropDown;
    @FXML
    CheckBox checkbox1;
    @FXML
    CheckBox checkbox2;
    @FXML
    CheckBox checkbox3;
    @FXML
    CheckBox checkbox4;
    @FXML
    CheckBox checkbox5;
    @FXML
    CheckBox checkbox6;
    @FXML
    CheckBox checkbox7;
    @FXML
    CheckBox checkbox8;
    @FXML
    CheckBox adminCheckbox;


    @FXML
    Label notice;
    @FXML
    Label noticeLogin;
    @FXML
    Label noticeDashboard;
    @FXML
    Label nameDashboard;

    public String usernameTable;
    public String passwordTable;
    public String emailTable;

    @FXML
    private void exitBtn() {
        System.exit(0);
    }
    @FXML
    private void dashboardRefresh(ActionEvent event){ goToDashboard(event); }
    @FXML
    private void goToDashboard(ActionEvent event) {
        try {
            Parent dashboard = FXMLLoader.load(getClass().getResource("View/dashboard.fxml"));

            Scene scene2 = new Scene(dashboard);

            Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dashboardStage.setScene(scene2);
            dashboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToLogin(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("View/Login.fxml"));

            Scene scene = new Scene(login);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        try {
            Parent register = FXMLLoader.load(getClass().getResource("View/Register.fxml"));

            Scene scene3 = new Scene(register);

            Stage Register = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Register.setScene(scene3);
            Register.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void registerValidation() {
        if (Valid.isValidUsername(usernameInput.getText()) && Valid.isValidPassword(passwordInput.getText()) && Valid.isValidEmail(emailInput.getText())) {
            notice.setTextFill(Color.GREEN);
            notice.setText("Registration successful");
            if (adminCheckbox.isSelected()){adminCheckbox.setText("true");}
            else adminCheckbox.setText("false");
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("insert into `users`(username, password, email, is_admin)" + "values (?,?,?,?)");
                preparedStatement.setString(1, usernameInput.getText());
                preparedStatement.setString(2, passwordInput.getText());
                preparedStatement.setString(3, emailInput.getText());
                preparedStatement.setString( 4, adminCheckbox.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            notice.setTextFill(Color.RED);
            notice.setText("Incorrect username or password!");
        }
    }
    public void loginValidation(ActionEvent event) {
        noticeLogin.setText(null);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `users` where username = '" + usernameLoginInput.getText() + "' and password = '" + passwordLoginInput.getText() + "' or email = '" + usernameLoginInput.getText() + "' and password = '" + passwordLoginInput.getText() + "'");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                usernameTable = result.getString("username");
                passwordTable = result.getString("password");
                emailTable = result.getString("email");
            }
            if (Valid.isValidUsername(usernameLoginInput.getText())) {
                if (usernameLoginInput.getText().equals(usernameTable)) {
                    if (passwordLoginInput.getText().equals(passwordTable)) {
                        noticeLogin.setTextFill(Color.GREEN);
                        noticeLogin.setText("Authentication successful!");
                        connection.close();
                        goToDashboard(event);
                    } else {
                        noticeLogin.setTextFill(Color.RED);
                        noticeLogin.setText("Incorrect password");
                        connection.close();
                    }
                } else {
                    noticeLogin.setTextFill(Color.RED);
                    noticeLogin.setText("User does not exist");
                    connection.close();
                }
            } else if (Valid.isValidEmail(usernameLoginInput.getText())) {
                if (usernameLoginInput.getText().equals(emailTable)) {
                    if (passwordLoginInput.getText().equals(passwordTable)) {
                        noticeLogin.setTextFill(Color.GREEN);
                        noticeLogin.setText("Authentication successful!");
                        connection.close();
                        goToDashboard(event);
                    } else {
                        noticeLogin.setTextFill(Color.RED);
                        noticeLogin.setText("Incorrect password");
                        connection.close();
                    }
                } else {
                    noticeLogin.setTextFill(Color.RED);
                    noticeLogin.setText("User does not exist");
                    connection.close();
                }
            }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        @FXML
        public void createTable(){
            ArrayList<String> list = new ArrayList<String>();
            if (checkbox1.isSelected()){list.add(checkbox1.getText());}
            if (checkbox2.isSelected()){list.add(checkbox2.getText());}
            if (checkbox3.isSelected()){list.add(checkbox3.getText());}
            if (checkbox4.isSelected()){list.add(checkbox4.getText());}
            if (checkbox5.isSelected()){list.add(checkbox5.getText());}
            if (checkbox6.isSelected()){list.add(checkbox6.getText());}
            if (checkbox7.isSelected()){list.add(checkbox7.getText());}
            if (checkbox8.isSelected()){list.add(checkbox8.getText());}
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `lentele`(pavadinimas, vardas_pavarde, masinos_numeris, masinos_marke, nariu_skaicius, sponsoriai)" + "VALUES (?,?,?,?,?,?)");
            if ((!pavadinimas.getText().isEmpty()) && (!vardasPavarde.getText().isEmpty()) && (!masinosNumeris.getText().isEmpty()) && (!masinosMarke.getText().isEmpty())){
                preparedStatement.setString(1, pavadinimas.getText());
                preparedStatement.setString(2, vardasPavarde.getText());
                preparedStatement.setString(3, masinosNumeris.getText());
                preparedStatement.setString(4, masinosMarke.getText());
                preparedStatement.setString(5, (String) dropDown.getValue());
                preparedStatement.setString(6, list.toString());
                preparedStatement.executeUpdate();
                noticeDashboard.setTextFill(Color.GREEN);
                noticeDashboard.setText("Entry added!");
                connection.close();
            } else {
                noticeDashboard.setTextFill(Color.RED);
                noticeDashboard.setText("Please fill out all fields");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void searchAction()throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userinfo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `lentele` WHERE id =" + searchid.getText());
        preparedStatement.executeUpdate();
        connection.close();
    }
}