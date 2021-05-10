/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Office;
import model.Speciality;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */

public class ListSpecialitiesUIController implements Initializable {

    @FXML
    private TableColumn nameColumn, idColumn ;

    @FXML
    private TableView SpTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2;
    public static TableView table;
    

    public void loadData(Speciality sp) {  
        try {
            SuperController.refrechSpecialites(SpTable,idColumn,nameColumn,sp);
        } catch (SQLException ex) {
            Logger.getLogger(ListSpecialitiesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        table=SpTable;
        loadData(new Speciality());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Speciality());
        } else {
            loadData(new Speciality(searchText.getText()));
        }
    }

    @FXML
    private void loadOfficeUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddSpecialiteUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle Specialite");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListSpecialitiesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        Speciality sp = (Speciality) SpTable.getSelectionModel().getSelectedItem();
        if (sp == null) {
            Options.information("aucun Specialite  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditSpecialiteUI.fxml"));
            Parent root = loader.load();
            EditSpecialiteUIController controller = loader.getController();
            controller.intiFileds(sp);
            Stage stage = new Stage();
            stage.setTitle("information de Specialite");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    
}
