
package Services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entités.Laboratoire;
import Tools.MyConnection;

public class CRUDLaboratoire {
public void addLaboratoire(Laboratoire a) {
        try {
            String requete = "INSERT INTO Laboratoire (Name,Adresse)" + "VALUES (?,?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, a.getName());
            pst.setString(2, a.getAdresse());
            pst.executeUpdate();

            System.out.println("Laboratoire ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDLaboratoire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   public ObservableList<Laboratoire> showLaboratoire() {
        ObservableList<Laboratoire> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM Laboratoire";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Laboratoire(rs.getString("Name"),rs.getString("Adresse")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDLaboratoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void editLaboratoire(Laboratoire labo) {
    try {
        String requete = "UPDATE Laboratoire SET Adresse = ? WHERE Name = ?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
        pst.setString(1, labo.getAdresse());
        pst.setString(2, labo.getName());
        pst.executeUpdate();
        System.out.println("Laboratoire modifié !");
    } catch (SQLException ex) {
        Logger.getLogger(CRUDLaboratoire.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    public void deleteLaboratoire(String Adresse) {

        try {
            String requete = "DELETE FROM Laboratoire WHERE Adresse = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, Adresse);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDLaboratoire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
