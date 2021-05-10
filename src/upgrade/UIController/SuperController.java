/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import upgrade.BackEnd.*;


/**
 *
 * @author Zed-Yacine
 */
public class SuperController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void refrechOffices(TableView table, TableColumn Column1, TableColumn Column2, 
            Office office)
            throws SQLException {
        ObservableList<Office> offices = (ObservableList<Office>) OfficeController.getOffices(office);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(offices);
    }
    
    public static void refrechSpecialites(TableView table, TableColumn Column1, TableColumn Column2, 
            Speciality sp)
            throws SQLException {
        ObservableList<Speciality> spcialites = (ObservableList<Speciality>) SpecialityController.getSpecialities(sp);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(spcialites);
    }
    
    public static void refrechNeed(TableView table, TableColumn Column1, TableColumn Column2,TableColumn Column3, 
            Need need)
            throws SQLException {
        ObservableList<Need> nds = (ObservableList<Need>) NeedController.getNeed(need);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("officeName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("specialityName")
        );
        table.setItems(nds);
    }
    
    public static void refrechWorkingDate(TableView table, TableColumn Column1, TableColumn Column2, 
            WorkingDate dte)
            throws SQLException {
        ObservableList<WorkingDate> days = (ObservableList<WorkingDate>) DateWorkingController.getDateWorking(dte);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("dateWork")
        );
        table.setItems(days);
    }
    
    public static void refrechGrades(TableView table, TableColumn Column1, TableColumn Column2, 
             TableColumn Column3, TableColumn Column4,Grade grade)
            throws SQLException {
        ObservableList<Grade> grades = (ObservableList<Grade>) GradeController.getGrade(grade);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("deploma")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("note")
        );
        table.setItems(grades);
    }
    
    
    public static void refrechWork(TableView table, TableColumn Column1, TableColumn Column2, 
             TableColumn Column3, TableColumn Column4,TableColumn Column5,Work work)
            throws SQLException {
        ObservableList<Work> works = (ObservableList<Work>) WorkController.getWork(work);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("Fname")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("Lname")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("workingDate")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        table.setItems(works);
    }
    

}
