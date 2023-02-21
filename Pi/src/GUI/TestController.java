package GUI;

import Services.CRUDTest;
import Tools.MyConnection;
import entités.Test;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class TestController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField txtRef;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAfficher;
    @FXML
    private TableView<Test> tableView;
    @FXML
    private TableColumn<Test, String> refColumn;
    @FXML
    private TableColumn<Test, Date> dateColumn;
    @FXML
    private TableColumn<Test, String> typeColumn;
    @FXML
    private TableColumn<Test, String> resultatColumn;
    @FXML
    private ComboBox<String> referenceComboBox;
    @FXML
    private Label errorMessage;
    @FXML
    private Label successMessage;

    private CRUDTest crud;
    
    public void showErrorMessage(String message) {
    errorMessage.setText(message);
    errorMessage.setVisible(true);
}
    public void showSuccessMessage(String message) {
    successMessage.setText(message);
    successMessage.setVisible(true);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud = new CRUDTest();
        ObservableList<String> types = crud.showTypes();
        cmbType.setItems(types);

        refColumn.setCellValueFactory((TableColumn.CellDataFeatures<Test, String> cellData) -> cellData.getValue().référenceProperty());

        dateColumn.setCellValueFactory((TableColumn.CellDataFeatures<Test, Date> cellData) -> cellData.getValue().dteTestProperty());
         
        typeColumn.setCellValueFactory((TableColumn.CellDataFeatures<Test, String> cellData) -> cellData.getValue().typeProperty());
        
        
        resultatColumn.setCellValueFactory((TableColumn.CellDataFeatures<Test, String> cellData) -> cellData.getValue().resultatProperty());
        
        btnAjouter.setOnAction(this::handleAjouter);
        btnModifier.setOnAction(this::handleModifier);
        btnSupprimer.setOnAction(this::handleSupprimer);
        btnAfficher.setOnAction(this::handleAfficher);
        
        tableView.setItems(crud.showTest(""));
         try {
        // Get all references from the database
        String query = "SELECT Réference FROM test";
        PreparedStatement pst = new MyConnection().cn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Add each reference to the ComboBox
        while (rs.next()) {
            referenceComboBox.getItems().add(rs.getString("Réference"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        
    
    

    @FXML
    private void handleAjouter(ActionEvent event) {
        if (txtRef.getText().isEmpty() || cmbType.getValue() == null || datePicker.getValue() == null) {
            //display a message indicating that no test was found
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Veuillez remplir tous les champs!.");
            alert.showAndWait();
} else {
        Test test = new Test();
        test.setRéference(txtRef.getText());
        test.setType(cmbType.getValue());
        test.setDteTest(Date.valueOf(datePicker.getValue()));
        test.setRésultat("N/A");
        crud.addTest(test);
        //display a message indicating that no test was found
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Test Ajouté !");
            alert.showAndWait();}
    }

 @FXML
private void handleModifier(ActionEvent event) {
    Test selectedTest = tableView.getSelectionModel().getSelectedItem();
    if (selectedTest == null) {
          Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un test à modifier !");
            alert.showAndWait();
        
    }
    if (cmbType.getValue() == null || datePicker.getValue() == null) {
        Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        
    }
    selectedTest.setType(cmbType.getValue());
    selectedTest.setDteTest(Date.valueOf(datePicker.getValue()));
    selectedTest.setRéference(txtRef.getText());
    selectedTest.setRésultat("N/A");
    crud.editTest(selectedTest);

    // Debug messages
    System.out.println("Before refresh:");
    tableView.getItems().forEach(System.out::println);
    tableView.refresh();
    // Debug messages
    System.out.println("After refresh:");
    tableView.getItems().forEach(System.out::println);
Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Test Modifié!");
            alert.showAndWait();
    
}


    

@FXML
private void handleSupprimer(ActionEvent event) {
    Test selectedTest = tableView.getSelectionModel().getSelectedItem();
    if (selectedTest == null) {
        // Show an error message if no row is selected
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner un test à supprimer !");
        alert.showAndWait();
        return;
    }

    // Show a confirmation dialog
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Voulez-vous vraiment supprimer le test sélectionné ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        System.out.println("Before delete:");
        tableView.getItems().forEach(System.out::println);
        crud.deleteTest(selectedTest.getRéference());
        System.out.println("After delete:");
        tableView.getItems().forEach(System.out::println);
        Alert alert2 = new Alert(AlertType.INFORMATION);
            alert.setContentText("Test supprimé!");
            alert.showAndWait();
        showSuccessMessage("Test supprimé !");
    }
}

   @FXML
  private void handleAfficher(ActionEvent event) {
    String ref = txtRef.getText();
    ObservableList<Test> tests = FXCollections.observableArrayList();
    if (!ref.isEmpty()) {
       tests = crud.showTest(ref);
        if (!tests.isEmpty()) {
           Test selectedTest = tests.get(0);
            txtRef.setText(selectedTest.getRéference());
            cmbType.setValue(selectedTest.getType());
            datePicker.setValue(selectedTest.getDteTest().toLocalDate());
            resultatColumn.setCellValueFactory(cellData -> cellData.getValue().resultatProperty());
            tableView.setItems(tests);
        } else {
             //display a message indicating that no test was found
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("No test was found with the specified reference number.");
            alert.showAndWait();
       }
    } else {
         //display a message indicating that no reference number was entered
       Alert alert = new Alert(AlertType.WARNING);
       alert.setContentText("Entrez une réference svp !");
      alert.showAndWait();
    }
}


}
