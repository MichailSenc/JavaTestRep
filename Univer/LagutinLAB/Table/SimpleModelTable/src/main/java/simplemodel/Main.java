package simplemodel;

import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private ObservableList<Organization> organizationsList;
    private TableView<Organization> dataTableView;
    ViewMini viewMiniOrg;

    private void createTableView() {
        TableColumn<Organization, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<Organization, String> personnelCol = new TableColumn<>("Personnel");
        personnelCol.setCellValueFactory(new PropertyValueFactory("personnel"));

        TableColumn<Organization, String> holidayCol = new TableColumn<>("Holiday");
        holidayCol.setCellValueFactory(new PropertyValueFactory("holiday"));

        TableColumn<Organization, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<Organization, String> drinkVolumeCol = new TableColumn<>("Drink Volume");
        drinkVolumeCol.setCellValueFactory(new PropertyValueFactory("drinkVolume"));

        dataTableView.getColumns().setAll(nameCol, personnelCol, holidayCol, dateCol, drinkVolumeCol);

    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showOrganization(Organization org) {
        Stage stage = new Stage();
        stage.setTitle("Show selected organization");
        BorderPane pane = new BorderPane();
        ViewOrganization viewOrg = new ViewOrganization(org);
        pane.setCenter(viewOrg.getPane());
        Scene scene = new Scene(pane, 700, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showOrganizationDetails(Organization org) {
        if(org!=null)
            viewMiniOrg.setOrganization(org);
    }

    private void handleButtonEdit() {
        Organization organization = dataTableView.getSelectionModel().getSelectedItem();
        if (organization != null) {
            OrganizationEditDialog orgEditDialog = new OrganizationEditDialog(organization, "Edit organization");
            orgEditDialog.getDialog().showAndWait();
        } else {
            showMessage("No selected item!");
        }
    }

    private void handleButtonAdd() {
        Organization organization = new Organization("", 0, "", LocalDate.of(2020, 4, 1), 0);
        OrganizationEditDialog orgEditDialog = new OrganizationEditDialog(organization, "Add organization");
        Optional<Organization> result = orgEditDialog.getDialog().showAndWait();
        if (result.isPresent()) {
            organizationsList.add(result.get());
        }
    }

    private void handleButtonDelete() {
        int selectedIndex = dataTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            dataTableView.getItems().remove(selectedIndex);
        } else {
            showMessage("No deleted item!");
        }
    }

    private void handleButtonShow() {
        Organization organization = dataTableView.getSelectionModel().getSelectedItem();
        if (organization != null) {
            showOrganization(organization);
        } else {
            showMessage("No selected item!");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List of organizations");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        organizationsList = FXCollections.<Organization>observableArrayList();
        dataTableView = new TableView<>();

        Organization org1 = new Organization("Horns&Hoof", 10, "International Women's Day", LocalDate.of(2016, 3, 9), 0);
        Organization org2 = new Organization("ABS", 100, "International Women's Day", LocalDate.of(2016, 3, 9), 20);
        organizationsList.addAll(org1, org2);

        createTableView();
        dataTableView.setItems(organizationsList);
        root.setCenter(dataTableView);

        viewMiniOrg = new ViewMini(null);
        root.setRight(viewMiniOrg.getPane());
        dataTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrganizationDetails(newValue));

        HBox btnBox = new HBox();
        btnBox.setSpacing(30);
        btnBox.setPadding(new Insets(10, 130, 10, 130));

        Button btnEdit = new Button("Edit");
        btnBox.getChildren().add(btnEdit);
        btnEdit.setOnAction((ActionEvent e) -> handleButtonEdit());

        Button btnAdd = new Button("Add");
        btnBox.getChildren().add(btnAdd);
        btnAdd.setOnAction((ActionEvent e) ->handleButtonAdd());

        Button btnDel = new Button("Delete");
        btnBox.getChildren().add(btnDel);
        btnDel.setOnAction((ActionEvent e) -> handleButtonDelete());

        Button btnShow = new Button("Show");
        btnBox.getChildren().add(btnShow);
        btnShow.setOnAction((ActionEvent e) ->handleButtonShow());
        root.setBottom(btnBox);

        Scene scene = new Scene(root, 800, 500, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
