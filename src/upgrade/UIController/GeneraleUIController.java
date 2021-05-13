/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class GeneraleUIController implements Initializable {

    @FXML
    private BorderPane main;

    @FXML
    private JFXButton logoutBtn;

    private void loadOffice() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListOfficesUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadOffice();
        } catch (IOException ex) {
            Logger.getLogger(GeneraleUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadOfficeUI(ActionEvent event) throws IOException {
        loadOffice();
    }

    @FXML
    private void loadSpecialitiesUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListSpecialitiesUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadNeedsUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListNeedsUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadWorkingDateUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListWorkingDateUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadGrades(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListGradesUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadWork(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListWorkUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadEmpl(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListEmployerUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadUpgrade(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/ListUpgradeEmployerUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }

}
