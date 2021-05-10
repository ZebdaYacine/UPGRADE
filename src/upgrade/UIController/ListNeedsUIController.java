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
import model.Need;
import model.Office;
import model.Speciality;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ListNeedsUIController implements Initializable {

    @FXML
    private TableColumn idColumn, officeColumn, spcialiteColumn;

    @FXML
    private TableView needsTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2, Column3;
    public static TableView table;

    public void loadData(Need need) {
        try {
            SuperController.refrechNeed(needsTable,idColumn,officeColumn,spcialiteColumn,need);
        } catch (SQLException ex) {
            Logger.getLogger(ListNeedsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1 = idColumn;
        Column2 = officeColumn;
        Column3 = spcialiteColumn;
        table = needsTable;
        loadData(new Need());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Need());
        } else {
            loadData(new Need(searchText.getText()));
        }
    }

    @FXML
    private void loadNeedUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddNeedUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouveu Besion");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListNeedsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        Need need = (Need) needsTable.getSelectionModel().getSelectedItem();
        if (need == null) {
            Options.information("aucun Besion  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditNeedUI.fxml"));
            Parent root = loader.load();
            EditNeedUIController controller = loader.getController();
            controller.intiFileds(need);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("information de Besions de Bureau");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
