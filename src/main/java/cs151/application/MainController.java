package cs151.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;

public class MainController {
    @FXML private Label popUp;
    @FXML private TextField nameField;
    @FXML private TextField languageField;

    @FXML private TableView<ProgrammingLanguages> tableView;
    @FXML private TableView<StudentProfile> nameTable;

    @FXML private TableColumn<ProgrammingLanguages, String> langCol;
    @FXML private TableColumn<StudentProfile, String> nameCol;

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
    private void initializeProf() {
        if (nameTable != null) {
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Student Profile"));

            ObservableList<StudentProfile> fullName = FXCollections.observableArrayList(DataStore.getFullName());
            Comparator<StudentProfile> nameCompare = Comparator.comparing
                    (StudentProfile::getName, String.CASE_INSENSITIVE_ORDER);

            fullName.sort(nameCompare);

            nameTable.setItems(fullName);
           nameCol.prefWidthProperty().bind(nameTable.widthProperty().multiply(0.5));
        }
    }

    //front page
    @FXML
    protected void onNavigateButtonClick(ActionEvent event) {
        swapScene(event, "/cs151/application/hello-view.fxml", 320, 240, "KnowledgeTrack");
    }
    //homepage, contains: define lang, profile, back to front
    @FXML
    private void goBackToHome(ActionEvent event) {
        swapScene(event, "/cs151/application/home.fxml", 320, 240, "KnowledgeTrack Home");
    }
    //saved lang table, contains: table, back to define lang
    @FXML
    protected void programmingLanguagesTable(ActionEvent event){
        swapScene(event, "/cs151/application/program_table.fxml", 400, 300, "Saved Languages");
    }
    //student profile, add name, contains: save, edit, view saved profiles, back to home
    @FXML
    protected void studentProfile(ActionEvent event) {
        swapScene(event, "/cs151/application/student.fxml", 600, 400, "Student Profile");
    }
    //saved profiles, contains: list of profiles, back to making student profile
    @FXML
    protected void savedProfile(ActionEvent event) {
        swapScene(event, "/cs151/application/saved_profile.fxml", 400, 300, "Saved Profiles");
    }
    //define languages, add langs, contains: save, edit, view saved langs, back to home
    @FXML
    protected void programmingLang(ActionEvent event) {
        swapScene(event, "/cs151/application/programming_languages.fxml", 640, 420, "Programming Languages");
    }

    //saves languages
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

    //saves profile
    @FXML
    private void save() {
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

