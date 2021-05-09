/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employer;
import model.Work;
import upgrade.BackEnd.EmployerController;
import upgrade.BackEnd.WorkController;

/**
 *
 * @author Zed-Yacine
 */
public class UPGRADE extends Application {

    public static Connection con;
    public CountDownLatch count = new CountDownLatch(1);
    public static Scene scene;
    public static AnchorPane root;
    public static Stage loginStage;

    public UPGRADE() {
        try {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String url = "jdbc:mysql://localhost:3306/UPGRADE";
                        String user = "root";
                        String Password = "0658185867";
                        Class.forName("com.mysql.jdbc.Driver");
                        con = (Connection) DriverManager.getConnection(url, user, Password);
                        System.out.println("Connection successfly");
                        count.countDown();
                    } catch (Exception ex) {
                        ex.getStackTrace();
                        count.countDown();
                    }
                }
            });
            th.start();
            count.await();
            th.stop();
        } catch (InterruptedException ex) {
            Logger.getLogger(UPGRADE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void alterIdTable(int id, String table) throws SQLException {
        String sql = "ALTER TABLE `" + table + "` AUTO_INCREMENT =" + id + "";
        com.mysql.jdbc.PreparedStatement stm = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static int getObjectIdFromName(String name, String table) {
        String query = "SELECT id FROM " + table + " where name = '" + name + "'";
        int id = 0;
        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public static String getObjectAttFromId(int id, String att, String table) {
        String query = "SELECT " + att + " FROM " + table + " where id =" + id;
        String attValue = "";
        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                attValue = rs.getString(att);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return attValue;
    }

    public static java.util.Date getDateFromId(int id, String att, String table) {
        String query = "SELECT " + att + " FROM " + table + " where id =" + id;
        Date attValue = null;
        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                attValue = rs.getDate(att);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return attValue;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/upgrade/FrontEnd/Root.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        new UPGRADE();

//        System.out.println(OfficeController.addOffice(new Office("office1")));
//        System.out.println(OfficeController.updateOffice(new Office(1," first office")));
//        System.out.println(OfficeController.deleteOffice(new Office(1)));
//        System.out.println(SpecialityController.addSpeciality(new Speciality("sp1")));
//        System.out.println(SpecialityController.updateSpeciality(new Speciality(1,"ality")));
//        System.out.println(SpecialityController.deleteSpeciality(new Speciality(2)));
//        System.out.println(NeedController.addNeed(new Need(1, 1)));
//        System.out.println(NeedController.updateNeed(new Need(1, 1, 2)));
//        System.out.println(NeedController.deleteNeed(new Need(1)));
//          ObservableList<Need> listNeed  =   (ObservableList<Need>) NeedController.getNeed(new Need());
//          
//          for(Need need  :listNeed){
//              System.err.println(need.getId()+" "+need.getOfficeName()+" "+need.getSpecialityName());
//          }
//        System.out.println(GradeController.addGrade(new Grade("grade 3"," Master 2 contabilite",40)));
//        System.out.println(GradeController.updateGrade(new Grade(1,"grade 1","Master informatique ",40)));
//        System.out.println(GradeController.deleteGrade(new Grade(4)));
//          ObservableList<Grade> listGrade  =   (ObservableList<Grade>) GradeController.getGrade(new  Grade("grade 1"));
//          
//          for(Grade grade  :listGrade){
//              System.err.println(grade.getId()+" "+grade.getDeploma()+" "+grade.getName()+" "+grade.getNote());
//          }
//        System.out.println(DateWorkingController.addDateWorking(new WorkingDate(Date.valueOf("2012-12-21"))));
//        System.out.println(DateWorkingController.updateDateWorking(new WorkingDate(4,Date.valueOf("2012-12-22"))));
//        System.out.println(DateWorkingController.deleteDateWorking(new WorkingDate(4)));
//          ObservableList<WorkingDate> listGrade  =   (ObservableList<WorkingDate>) DateWorkingController.getDateWorking(new  WorkingDate());
//          
//          for(WorkingDate date  :listGrade){
//              System.err.println(date.getId()+" "+date.getDateWork());
//          }
//        System.out.println(WorkController.addWork(new Work(3, 3,"present")));
//        System.out.println(WorkController.updateWork(new Work(4, 2, 2, "absent")));
//        System.out.println(WorkController.deleteWork(new Work(2)));
//          ObservableList<Work> listWorke  =   (ObservableList<Work>) WorkController.getWork(new Work());
//         
//          for(Work wrk  :listWorke){
//              System.err.println(wrk.getId()+" "+wrk.getLname()+" "+wrk.getfName()+" "+wrk.getWorkingDate()+" "+wrk.getStatus());
//          }
//        System.out.println(EmployerController.addEmployer(new Employer("med", "med","0558185869", Date.valueOf("1990-12-12")
//                , Date.valueOf("2019-12-12"),"single","informatique", 0, 14, 0, 0, 1, 2)));
//        
//        ObservableList<Employer> listEmployers = (ObservableList<Employer>) EmployerController.getEmployers(new Employer("of"),"office");
//        for (Employer wrk : listEmployers) {
//            System.err.println(wrk.getId() + " " + wrk.getFirstName() + " " + wrk.getLastName() + " " + wrk.getGradeName() + " " + wrk.getOfficeName());
//        }
//        System.out.println(EmployerController.updateEmployer(new Employer()));
//          double period= Date.valueOf("2012-12-30").getTime()-Date.valueOf("2012-12-10").getTime();
//          period=(period/86400000);
//          System.err.println(period);
    }

}
