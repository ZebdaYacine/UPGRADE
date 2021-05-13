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
import javafx.collections.ObservableList;
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
import model.Office;
import upgrade.BackEnd.EmployerController;
import upgrade.BackEnd.OfficeController;
import static upgrade.UIController.ListUpgradeEmployersUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ListEmployersUIController implements Initializable {

    @FXML
    private TableColumn FnameColumn, LnameColumn, idColumn, statusColumn, phoneColumn, dateNColumn, dateRColumn, childrenColumn, formtionColumn, experienceColumn, noteColumn, gradeColumn, officeColumn, deplomeColumn;

    @FXML
    private TableView EmplTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2, Column3, Column4, Column5, Column6, Column7, Column8, Column9, Column10,
            Column11, Column12, Column13, Column14;
    public static TableView table;

    public void loadData(Employer empl, String searchArg) {
        try {
            SuperController.refrechEmployers(table, Column1, Column2, Column3, Column4, Column5,
                    Column6, Column7, Column8, Column9, Column10, Column11,
                    Column12, Column13, Column14, new TableColumn(), new TableColumn(), empl, searchArg, 0);
        } catch (SQLException ex) {
            Logger.getLogger(ListEmployersUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int calculeExperience(String date) {
        String currentDate = upgrade.UPGRADE.getCurrentDate();
        double period = java.sql.Date.valueOf(currentDate).getTime()
                - java.sql.Date.valueOf(date).getTime();
        period = (period / 86400000);
        int exper = (int) (period / 365);
        return exper;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Employer> empls = (ObservableList<Employer>) EmployerController.getEmployers(new Employer(), "");
        for (Employer empl : empls) {
            EmployerController.updateExperience(new Employer(empl.getId(),calculeExperience(empl.getRecruitmentDate()+"")
                    , "Exper"));
        }
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
        Column12 = noteColumn;
        Column13 = gradeColumn;
        Column14 = officeColumn;
        table = EmplTable;
        loadData(new Employer(), "");
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Employer(), "");
        } else {
            try {
                loadData(new Employer(searchText.getText()), "phone");
            } catch (Exception ex) {
                System.err.println("Data not valid");
            }
        }
    }

    @FXML
    private void loadEmployerUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/AddEmployerUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("nouvelle Employeur");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListEmployersUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectEmpl(MouseEvent event) throws IOException {
        Employer empl = (Employer) EmplTable.getSelectionModel().getSelectedItem();
        if (empl == null) {
            Options.information("aucun Employeur  sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upgrade/FrontEnd/EditEmployerUI.fxml"));
            Parent root = loader.load();
            EditEmployerUIController controller = loader.getController();
            controller.intiFileds(empl);
            Stage stage = new Stage();
            stage.setTitle("information de Employeur");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

}
