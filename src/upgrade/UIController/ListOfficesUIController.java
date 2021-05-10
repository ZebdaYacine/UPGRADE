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

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */

public class ListOfficesUIController implements Initializable {

    @FXML
    private TableColumn nameColumn, idColumn ;

    @FXML
    private TableView OfficesTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2;
    public static TableView table;
    

    public void loadData(Office office) {  
        try {
            SuperController.refrechOffices(OfficesTable,idColumn,nameColumn,office);
        } catch (SQLException ex) {
            Logger.getLogger(ListOfficesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        table=OfficesTable;
        loadData(new Office());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Office());
        } else {
            loadData(new Office(searchText.getText()));
        }
    }

    @FXML
    private void loadOfficeUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddOfficeUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle Bureau");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListOfficesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectOffice(MouseEvent event) throws IOException {
        Office office = (Office) OfficesTable.getSelectionModel().getSelectedItem();
        if (office == null) {
            Options.information("aucun Bureau  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditOfficeUI.fxml"));
            Parent root = loader.load();
            EditOfficeUIController controller = loader.getController();
            controller.intiFileds(office);
            Stage stage = new Stage();
            stage.setTitle("information de group de recherech");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    
}
