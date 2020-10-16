package simplemodel;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrganizationEditDialog {

    private Organization org;
    private Stage dialog;
    private TextField nameEdit;
    private Spinner<Integer> personnelEdit;
    private ComboBox<String> holidayEdit;
    private DatePicker dateEdit;
    private Spinner<Double> drinkVolumeEdit;
    private Font font;
    private GridPane root;

    private void createNameText() {
        Label nameOrg = new Label("Name:");
        nameOrg.setFont(font);
        root.add(nameOrg, 0, 0);
        nameEdit = new TextField();
        nameEdit.setFont(Font.font(24));
        nameEdit.setText(org.getName());
        root.add(nameEdit, 1, 0);
    }

    private void createDatePicker() {
        Label dateOrg = new Label("Date:");
        dateOrg.setFont(font);
        root.add(dateOrg, 0, 3);
        dateEdit = new DatePicker(org.getDate());
        dateEdit.setStyle("-fx-font-size: 24 pt");
        root.add(dateEdit, 1, 3);
    }

    private void createComboBox() {
        Label holidayOrg = new Label("Holiday:");
        holidayOrg.setFont(font);
        root.add(holidayOrg, 0, 2);
        holidayEdit = new ComboBox<>();
        holidayEdit.setStyle("-fx-font-size: 24 pt");
        holidayEdit.getItems().addAll(
                "New Year",
                "Defender's Day",
                "International Women's Day",
                "Boss birthday"
        );
        holidayEdit.setValue(org.getHoliday());
        root.add(holidayEdit, 1, 2);
    }

    private void createSpinner() {
        Label personnelOrg = new Label("Personnel:");
        personnelOrg.setFont(font);
        root.add(personnelOrg, 0, 1);
        personnelEdit = new Spinner<>(1, 100, org.getPersonnel());
        personnelEdit.setStyle("-fx-font-size: 24 pt");
        //personnelEdit.setEditable(true);
        root.add(personnelEdit, 1, 1);
        Label drinkVolumeOrg = new Label("Drink volume:");
        drinkVolumeOrg.setFont(font);
        root.add(drinkVolumeOrg, 0, 4);
        drinkVolumeEdit = new Spinner<>(0, 200, org.getDrinkVolume(), 0.1);
        drinkVolumeEdit.setStyle("-fx-font-size: 24 pt");
//        drinkVolumeEdit.setEditable(true);
        root.add(drinkVolumeEdit, 1, 4);

    }

    private void createButtons() {
        Button btnOk = new Button("Ok");
        btnOk.setFont(Font.font(24));
        root.add(btnOk, 0, 5);
        btnOk.setOnAction((ActionEvent e) -> {
            if (isInputValid())
                handleOk();
            else message();
        });
        Button btnCancel = new Button("Cancel");
        btnCancel.setFont(Font.font(24));
        root.add(btnCancel, 1, 5);
        btnCancel.setOnAction((ActionEvent e) -> {
            handleCancel();
        });
    }

    private void message(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Data entry error");
        alert.setHeaderText("Name entry error");
        alert.setContentText("\"The name of organization consists of letters, numbers, spaces, +, -!!!\\n \"");
        alert.showAndWait();
    }

    public OrganizationEditDialog(Organization org) {
        this.org = org;
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit organization");
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        createNameText();
        createSpinner();
        createComboBox();
        createDatePicker();
        createButtons();
        Scene scene = new Scene(root, 600, 500);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private boolean isInputValid() {
        return nameEdit.getText().matches("[a-zA-Z0-9&\\-+ ]+");
    }

    private void handleOk() {
        org.setName(nameEdit.getText());
        org.setPersonnel(personnelEdit.getValue());
        org.setHoliday(holidayEdit.getValue().toString());
        org.setDate(dateEdit.getValue());
        org.setDrinkVolume(drinkVolumeEdit.getValue());
        dialog.close();
    }

    private void handleCancel() {
        dialog.close();
    }

}

