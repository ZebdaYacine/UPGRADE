/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import com.jfoenix.controls.JFXDatePicker;
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
import model.Office;
import model.WorkingDate;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ListWorkingDateUIController implements Initializable {

    @FXML
    private TableColumn dayColumn, idColumn;

    @FXML
    private TableView daysTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2;
    public static TableView table;

    public void loadData(WorkingDate dte) {
        try {
            SuperController.refrechWorkingDate(daysTable, idColumn, dayColumn, dte);
        } catch (SQLException ex) {
            Logger.getLogger(ListWorkingDateUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1 = idColumn;
        Column2 = dayColumn;
        table = daysTable;
        loadData(new WorkingDate());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) {
        if (searchText.getText().isEmpty()) {
            loadData(new WorkingDate());
        } else {
            try {
                loadData(new WorkingDate(Date.valueOf(searchText.getText())));
            } catch (Exception ex) {
                //System.err.println("Date not valid");
            }
        }
    }

    @FXML
    private void loadDayUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddWorkingDateUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle jour de travialle");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListWorkingDateUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        WorkingDate dte = (WorkingDate) daysTable.getSelectionModel().getSelectedItem();
        if (dte == null) {
            Options.information("aucun jour  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditWorkingDateUI.fxml"));
            Parent root = loader.load();
            EditWorkingDateUIController controller = loader.getController();
            controller.intiFileds(dte);
            Stage stage = new Stage();
            stage.setTitle("information de group de recherech");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
