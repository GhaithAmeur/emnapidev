package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entités.Biologiste;
import Tools.MyConnection;

public class CRUDBiologiste {
    
    public void addBiologiste(Biologiste a) {
        try {
            String requete = "INSERT INTO biologiste (Name, Titre, Phone, Photo, Email, Adresse) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, a.getName());
            pst.setString(2, a.getTitre());
            pst.setInt(3, a.getPhone());          
            pst.setInt(4, a.getPhoto());
            pst.setString(5, a.getEmail());
            pst.setString(6, a.getAdresse());
            pst.executeUpdate();

            System.out.println("Biologiste ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDBiologiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Biologiste> showBiologiste() {
        ObservableList<Biologiste> list = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM Biologiste";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Biologiste(rs.getString("Name"), rs.getString("Titre"), rs.getInt("Phone"), rs.getInt("Photo"), rs.getString("Email"), rs.getString("Adresse")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUDBiologiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void editBiologiste(Biologiste a) {
        try {
            String requete = "UPDATE Biologiste SET Name = ?, Titre = ?, Photo = ?, Email = ?, Adresse = ? WHERE Phone = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, a.getName());
            pst.setString(2, a.getTitre());
            pst.setInt(3, a.getPhoto());
            pst.setString(4, a.getEmail());
            pst.setString(5, a.getAdresse());
            pst.setFloat(6, a.getPhone());
            pst.executeUpdate();

            System.out.println("Biologiste modifiée!");

        } catch (SQLException ex) {
            Logger.getLogger(CRUDBiologiste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteBiologiste(int Phone) {
        try {
            String requete = "DELETE FROM Biologiste WHERE Phone = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, Phone);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDBiologiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
