
package Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entités.Test;
import Tools.MyConnection;

public class CRUDTest {
    public void addTest(Test a) {
        try {
            String requete = "INSERT INTO Test (Type,Réference,DteTest,Résultat)" + "VALUES (?,?,?,?)";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, a.getType());
            pst.setString(2, a.getRéference());
            pst.setDate(3, a.getDteTest());          
            pst.setString(4, a.getRésultat());
            pst.executeUpdate();

            System.out.println("Test ajouté!");
        } catch (SQLException ex) {
            Logger.getLogger(CRUDTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ObservableList<Test> showTest(String ref) {
    ObservableList<Test> list = FXCollections.observableArrayList();
   try {
        String requete = "SELECT * FROM Test WHERE Réference = ?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
        pst.setString(1, ref);
      ResultSet rs = pst.executeQuery();

        while (rs.next()) {
          list.add(new Test(rs.getString("Type"), rs.getString("Réference"), rs.getDate("DteTest"), rs.getString("Résultat")));
      }

   } catch (SQLException ex) {
       Logger.getLogger(CRUDTest.class.getName()).log(Level.SEVERE, null, ex);
   }
   return list;
}

     public void editTest(Test a) {
         try {
        String requete = "UPDATE Test SET Type = ?, DteTest = ?, Résultat = ? WHERE Réference = ? ";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
        pst.setString(1, a.getType());
        pst.setDate(2, a.getDteTest());
        pst.setString(3, a.getRésultat());
        pst.setString(4, a.getRéference());
           
        System.out.println("Executing SQL query...");
        pst.executeUpdate();
        System.out.println("SQL query executed.");

    } catch (SQLException ex) {
        Logger.getLogger(CRUDTest.class.getName()).log(Level.SEVERE, null, ex);
    }
}
     public void deleteTest(String Réference) {

        try {
            String requete = "DELETE FROM Test WHERE Réference = ?";
            PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, Réference);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public Test getTest(String Réference) {
    Test test = null;
    try {
        String requete = "SELECT * FROM Test WHERE Réference = ?";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(requete);
        pst.setString(1, Réference);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            test = new Test(rs.getString("Type"), rs.getString("Réference"), rs.getDate("DteTest"), rs.getString("Résultat"));
        }

    } catch (SQLException ex) {
        Logger.getLogger(CRUDTest.class.getName()).log(Level.SEVERE, null, ex);
    }
    return test;
}

   public ObservableList<String> showTypes() {
    ObservableList<String> list = FXCollections.observableArrayList();
    list.add("Test Covid");
    list.add("Test sang");
    list.add("Test urine");
    list.add("Prélèvements sur les muqueuses");
    list.add("Analyse des selles");
    list.add("Ponctions de tissus");
    return list;
}

    


   
}
