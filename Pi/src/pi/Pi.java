package pi;

import Services.CRUDBiologiste;
import Services.CRUDTest;
import entités.Biologiste;
import Tools.MyConnection;
import entités.Test;
import GUI.TestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/bioImproved.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bienvenue à HealthHaven !");
        stage.show();
    }

    public static void main(String[] args) {
        //CRUDTest pst = new CRUDTest();
        //Test bio = new Test("ghaith", "docteur", 94225001, 1234, "ghjdg@fhvf.tn", "fhjgfhj");
        //pst.addBiologiste(bio);
        launch(args);
    }
}
