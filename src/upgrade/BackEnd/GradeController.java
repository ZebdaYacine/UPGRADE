/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upgrade.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import static upgrade.UPGRADE.con;

/**
 *
 * @author Zed-Yacine
 */
public class GradeController {

    public static Results.Rstls addGrade(Grade grade) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into grade (name,deploma,note) values (?,?,?)");
            stm.setString(1, grade.getName());
            stm.setString(2, grade.getDeploma());
            stm.setInt(3, grade.getNote());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateGrade(Grade grade) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " grade SET name = ? , deploma= ? , note=? WHERE id = ? ");
            stm.setString(1, grade.getName());
            stm.setString(2, grade.getDeploma());
            stm.setInt(3, grade.getNote());
            stm.setInt(4, grade.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteGrade(Grade grade) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " grade WHERE id = ?");
            stm.setInt(1, grade.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(grade.getId(), "grade");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Object getGrade(Grade grade) {
        String query;
        if (grade.getName() == null) {
            query = "SELECT * FROM grade";
        } else {
            query = "SELECT * FROM grade where name LIKE '" + grade.getName() + "%'";
        }
        ObservableList<Grade> listGrades = FXCollections.observableArrayList(new Grade());
        listGrades.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade grd = new Grade();
                grd.setId(rs.getInt("id"));
                grd.setName(rs.getString("name"));
                grd.setDeploma(rs.getString("deploma"));
                grd.setNote(rs.getInt("note"));
                listGrades.add(grd);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listGrades;
    }

}
