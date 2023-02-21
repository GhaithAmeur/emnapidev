package GUI;

import Services.CRUDLaboratoire;
import entités.Laboratoire;
import Tools.MyConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LaboController {
    
    @FXML
    private TextField nomTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TableView<Laboratoire> laboTableView;

    @FXML
    private TableColumn<Laboratoire, String> nomCol;

    @FXML
    private TableColumn<Laboratoire, String> adresseCol;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button showButton;
    @FXML
    private ObservableList<Laboratoire> laboratoiresList = FXCollections.observableArrayList();
    
    // Create an instance of the CRUDLaboratoire class
    private CRUDLaboratoire crud = new CRUDLaboratoire();

    // Initialize the controller class
    public void initialize() {
    // Set up the columns in the table view
    nomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameProperty()));
    adresseCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresseProperty()));

    // Clear the table view
    laboTableView.getItems().clear();

    // Disable automatic sorting on the table view
    laboTableView.setSortPolicy(null);

    

    // Populate the table view with an empty list
    laboTableView.setItems(FXCollections.emptyObservableList());
}


    // Event handler for the add button
 @FXML
private void handleAdd(ActionEvent event) {
    if (nomTextField.getText().isEmpty() || adresseTextField.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Champ(s) vide(s)");
        alert.setHeaderText("Veuillez remplir tout les champ !");
        alert.showAndWait();
    } else {
        String anom = nomTextField.getText();
        String aadresse = adresseTextField.getText() ;
        
        try {
            for (char c : anom.toCharArray()) {
    if (!Character.isAlphabetic(c)){
        
        System.out.println("INVALID");
        break;}
    }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("création");
            alert.setHeaderText(null);
            alert.setContentText("Le nom doit être une chaine de charachtères!");
            alert.showAndWait();
            return; }
       
        Laboratoire laboratoire = new Laboratoire(nomTextField.getText(), adresseTextField.getText());
        crud.addLaboratoire(laboratoire);
        //display a message indicating that no test was found
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Laboratoire Ajouté !");
        alert.showAndWait();
        laboratoiresList.add(laboratoire);
        laboTableView.setItems(laboratoiresList);
    }
}


    // Event handler for the edit button
    @FXML
    private void handleEdit(ActionEvent event) {
        // Get the selected laboratory object from the table view
        Laboratoire selectedLabo = laboTableView.getSelectionModel().getSelectedItem();

       if (selectedLabo == null) {
          Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un Labo à modifier !");
            alert.showAndWait();
        
    }
    if (nomTextField.getText() == null || adresseTextField.getText() == null) {
        Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        
    }
     selectedLabo.setName(nomTextField.getText());
     selectedLabo.setAdresse(adresseTextField.getText());
    crud.editLaboratoire(selectedLabo);
    Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Labo Modifié!");
            alert.showAndWait();
    }

    // Event handler for the delete button
    @FXML
private void handleDelete(ActionEvent event) {
    int selectedIndex = laboTableView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Laboratoire selectedLaboratoire = laboTableView.getItems().get(selectedIndex);
        String selectedAdresse = selectedLaboratoire.getAdresse();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de " + selectedLaboratoire.getName());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer le laboratoire " + selectedLaboratoire.getName() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CRUDLaboratoire cr = new CRUDLaboratoire();
            cr.deleteLaboratoire(selectedAdresse);
            laboTableView.getItems().remove(selectedIndex);
        }
    } else {
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText("Aucun laboratoire sélectionné");
        alert.setContentText("Veuillez sélectionner un laboratoire dans la table.");

        alert.showAndWait();
    }
}
@FXML
private void handleShow(ActionEvent event) {
    ObservableList<Laboratoire> laboList = crud.showLaboratoire();
    laboTableView.setItems(laboList);
}


}
