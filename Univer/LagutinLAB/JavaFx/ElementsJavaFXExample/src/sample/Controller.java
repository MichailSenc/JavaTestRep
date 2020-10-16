package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ObservableList<String> options
            = FXCollections.observableArrayList(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    );
    @FXML
    ComboBox comboBox;
    @FXML
    Label labelCombo;
    @FXML
    Button btnCombo;
    @FXML
    public void handleButtonActionComboBox(ActionEvent actionEvent) {
        if (comboBox.getValue() != null && !comboBox.getValue().toString().isEmpty()) {
            labelCombo.setText("selected: " + comboBox.getValue());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.setItems(options);
        comboBox.setPrefSize(300, 50);
        comboBox.setStyle("-fx-font-size: 30px");
        labelCombo.setFont(Font.font(30));
        labelCombo.setPrefSize(400, 50);
        btnCombo.setPrefSize(300, 50);
        btnCombo.setFont(Font.font(30));
    }
}
