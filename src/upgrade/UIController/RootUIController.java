/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import upgrade.User;

/**
 *
 * @author Zed-Yacine
 */
public class RootUIController implements Initializable {

    @FXML
    private Label error;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Button Login;

    @FXML
    private void login(ActionEvent event) {
        User user = new User(username.getText(), password.getText());
        if (user.getPassword().isEmpty() || user.getUserName().isEmpty()) {
            error.setText("username or password is empty");
            error.setVisible(true);
        } else {
            if (user.isauthentificated(user)) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/GeneraleUI.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("SuperManager");
                    stage.show();
                    upgrade.UPGRADE.loginStage.hide();
                } catch (IOException ex) {
                    Logger.getLogger(RootUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                error.setText("Login field try agin");
                error.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
