package cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;

public class MainController {
    @FXML private Label popUp;
    @FXML private TextField nameField;
    @FXML private TextField languageField;

    @FXML private TableView<ProgrammingLanguages> tableView;
    @FXML private TableColumn<ProgrammingLanguages, String> langCol;

    @FXML
    private void initialize() {
        if (tableView != null) {
            langCol.setCellValueFactory(new PropertyValueFactory<>("programmingLanguage"));

            ObservableList<ProgrammingLanguages> langs = FXCollections.observableArrayList(DataStore.getList());
            Comparator<ProgrammingLanguages> langComparator = Comparator.comparing
                    (ProgrammingLanguages::getProgrammingLanguage, String.CASE_INSENSITIVE_ORDER);

            langs.sort(langComparator);

            tableView.setItems(langs);
            langCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.5));
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        if (popUp != null) popUp.setText("Welcome to KnowledgeTrack Homepage!");
    }

    @FXML
    protected void onNavigateButtonClick(ActionEvent event) { swapScene(event, "/cs151/application/programming_languages.fxml", 640, 420, "Profile Page"); }

    @FXML
    private void goBackToHome(ActionEvent event) { swapScene(event, "/cs151/application/hello-view.fxml", 320, 240, "KnowledgeTrack Home"); }

    @FXML
    protected void programmingLanguagesTable(ActionEvent event){
        swapScene(event, "/cs151/application/program_table.fxml", 400, 300, "Saved Languages");
    }
    //saves user input
    @FXML
    private void onSave() {
        if (languageField == null) return;
        String lang  = languageField.getText() == null ? "" : languageField.getText().trim();
        if (!lang.isEmpty()) {
            DataStore.getList().add(new ProgrammingLanguages(lang));
            DataStore.save();
            languageField.clear();
        }
    }

    private void swapScene(ActionEvent event, String fxml, int w, int h, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, w, h));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

