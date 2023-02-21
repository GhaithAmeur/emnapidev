
package Tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


    
public class MyConnection {

    public String url = "jdbc:mysql://localhost:3306/pighaith";
    public String login = "root";
    public String pwd = "";
    public Connection cn;

    public MyConnection() {
        try {
            cn =  DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion.");
            System.out.println(ex.getMessage());
        }
    }

    
    public PreparedStatement prepareStatement(String requete) throws SQLException {
    return cn.prepareStatement(requete);
}

    //public PreparedStatement prepareStatement(String requete) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
    

