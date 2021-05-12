/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import model.Work;
import model.WorkingDate;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ListWorkUIController implements Initializable {

    @FXML
    private TableColumn FnameColumn, LnameColumn, idColumn, statusColumn, dateColumn;

    @FXML
    private TableView workTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2, Column3, Column4, Column5;
    public static TableView table;

    public void loadData(Work work) {
        try {
            SuperController.refrechWork(workTable, idColumn, FnameColumn, LnameColumn, dateColumn, statusColumn, work);
        } catch (SQLException ex) {
            Logger.getLogger(ListWorkUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1 = idColumn;
        Column2 = FnameColumn;
        Column3 = LnameColumn;
        Column4 = dateColumn;
        Column5 = statusColumn;
        table = workTable;
        loadData(new Work());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Work());
        } else {
            try {
                loadData(new Work(Date.valueOf(searchText.getText())));
            } catch (Exception ex) {
                //System.err.println("Date not valid");
            }
        }
    }

    @FXML
    private void loadWorkUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddWorkUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle presence");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListWorkUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        Work wrk = (Work) workTable.getSelectionModel().getSelectedItem();
        if (wrk == null) {
            Options.information("aucun Date de travialle  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditWorkUI.fxml"));
            Parent root = loader.load();
            EditWorkUIController controller = loader.getController();
            controller.intiFileds(wrk);
            Stage stage = new Stage();
            stage.setTitle("information de Date de travialle");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    
}
