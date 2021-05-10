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
import model.Grade;
import model.Office;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */

public class ListGradesUIController implements Initializable {

    @FXML
    private TableColumn nameColumn, idColumn,deplomaColumn,noteColumn ;

    @FXML
    private TableView GradesTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2,Column3,Column4;
    public static TableView table;
    

    public void loadData(Grade grade) {  
        try {
            SuperController.refrechGrades(GradesTable,idColumn,nameColumn,deplomaColumn,noteColumn,grade);
        } catch (SQLException ex) {
            Logger.getLogger(ListGradesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        Column3=deplomaColumn;
        Column4=noteColumn;
        table=GradesTable;
        loadData(new Grade());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Grade());
        } else {
            loadData(new Grade(searchText.getText()));
        }
    }

    @FXML
    private void loadOfficeUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddGradeUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle Grade");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListGradesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        Grade grd = (Grade) GradesTable.getSelectionModel().getSelectedItem();
        if (grd == null) {
            Options.information("aucun Grade  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditGradeUI.fxml"));
            Parent root = loader.load();
            EditGradeUIController controller = loader.getController();
            controller.intiFileds(grd);
            Stage stage = new Stage();
            stage.setTitle("information de grade");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    
}
