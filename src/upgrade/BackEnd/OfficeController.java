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
import model.Office;
import static upgrade.UPGRADE.con;


/**
 *
 * @author Zed-Yacine
 */
public class OfficeController {

    public static Results.Rstls addOffice(Office office) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into office (name) values (?)");
            stm.setString(1, office.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateOffice(Office office) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " office SET name = ? WHERE id = ? ");
            stm.setString(1, office.getName());
            stm.setInt(2, office.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }
    
    public static Results.Rstls deleteOffice(Office office) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " office WHERE id = ?");
            stm.setInt(1, office.getId());
            stm.executeUpdate();
            upgrade.UPGRADE.alterIdTable(office.getId(),"office");
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }
    
    public static Object getOffices(Office office) {
        String query;
        if (office.getName() == null) {
            query = "SELECT * FROM office";
        } else {
            query = "SELECT * FROM office where name LIKE '"+office.getName()+"%'";
        }
        ObservableList<Office> listOffices = FXCollections.observableArrayList(new Office());
        listOffices.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Office ofc = new Office();
                ofc.setId(rs.getInt("id"));
                ofc.setName(rs.getString("name"));
                listOffices.add(ofc);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOffices;
    }
    
    
}
