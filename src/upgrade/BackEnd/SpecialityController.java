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
public class SpecialityController {

    public static Results.Rstls addSpeciality(Speciality sp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into speciality (name) values (?)");
            stm.setString(1, sp.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateSpeciality(Speciality sp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " speciality SET name = ? WHERE id = ? ");
            stm.setString(1, sp.getName());
            stm.setInt(2, sp.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }
    
    public static Results.Rstls deleteSpeciality(Speciality sp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " speciality WHERE id = ?");
            stm.setInt(1, sp.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(sp.getId(),"speciality");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }
    
    public static Object getSpecialities(Speciality sp) {
        String query;
        if (sp.getName() == null) {
            query = "SELECT * FROM speciality";
        } else {
            query = "SELECT * FROM speciality where name LIKE '"+sp.getName()+"%'";
        }
        ObservableList<Speciality> listSpecialities = FXCollections.observableArrayList(new Speciality());
        listSpecialities.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Speciality splty = new Speciality();
                splty.setId(rs.getInt("id"));
                splty.setName(rs.getString("name"));
                listSpecialities.add(splty);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listSpecialities;
    }
    
    
}
