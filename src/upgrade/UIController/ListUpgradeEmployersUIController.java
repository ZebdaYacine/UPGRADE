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
import model.Employer;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ListUpgradeEmployersUIController implements Initializable {

    @FXML
    private TableColumn FnameColumn, LnameColumn, idColumn, statusColumn,phoneColumn,dateNColumn
            ,dateRColumn,childrenColumn,formtionColumn,experienceColumn,noteColumn,datePColumn
            ,gradeColumn,officeColumn,deplomeColumn,desciplineColumn;

    @FXML
    private TableView EmplTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2, Column3, Column4, Column5,Column6, Column7, Column8, Column9, Column10,
            Column11, Column12, Column13, Column14,Column15,Column16;
    public static TableView table;

    public void loadData(Employer empl,String searchArg) {
        try {
            SuperController.refrechEmployers(table, Column1, Column2, Column3, Column4, Column5, Column6, Column7,
                    Column8, Column9, Column10, Column11, Column12, Column13, Column14,Column15,Column16, empl,searchArg,1);
        } catch (SQLException ex) {
            Logger.getLogger(ListUpgradeEmployersUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1 = idColumn;
        Column2 = FnameColumn;
        Column3 = LnameColumn;
        Column4 = phoneColumn;
        Column5 = dateNColumn;
        Column6 = dateRColumn;
        Column7 = statusColumn;
        Column8 = childrenColumn;
        Column9 = formtionColumn;
        Column10 = experienceColumn;
        Column11 = deplomeColumn;
        Column12 = noteColumn ;
        Column13 = gradeColumn;
        Column14 = officeColumn;
        Column15=datePColumn;
        Column16=desciplineColumn;
        table = EmplTable;
        loadData(new Employer(),"upgrade");
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Employer(),"upgrade");
        } else {
            try {
                loadData(new Employer(searchText.getText()),"office");
            } catch (Exception ex) {
                System.err.println("Data not valid");
            }
        }
    }

   
    @FXML
    private void selectEmpl(MouseEvent event) throws IOException {
        Employer empl = (Employer) EmplTable.getSelectionModel().getSelectedItem();
        if (empl == null) {
            Options.information("aucun Employeur  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/UpgradeEmployerUI.fxml"));
            Parent root = loader.load();
            UpgradeEmployerUIController controller = loader.getController();
            controller.intiFileds(empl);
            Stage stage = new Stage();
            stage.setTitle("information de Employeur");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    
}
