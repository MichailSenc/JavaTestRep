package ru.ac.uniyar.apps.fxappone;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Simple Model-View Data Binding");
        Organization org = new Organization("Horns&Hoof",10,"International Women's Day", LocalDate.of(2016, 3, 9),0);
        HBox root = new HBox(10);

        ViewOrganization viewOrg = new ViewOrganization(org);
        root.getChildren().add(viewOrg.getPane());

        root.getChildren().add(new Separator(Orientation.VERTICAL));

        root.getChildren().add(editBlock(org));

        Scene scene = new Scene(root, 900, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox editBlock(Organization org){
        VBox editPane = new VBox(10);
        editPane.setPadding(new Insets(20));

        Label labelTitle = new Label("Enter name organization\n and drink volume");
        labelTitle.setFont(Font.font(20));

        TextField editName = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        Spinner<Double> editDrink = new Spinner<>(0, 100, 0, 0.5);
        editDrink.setPrefSize(100, 40);
        editDrink.setStyle("-fx-font-size: 20");

        Button btn = new Button("Edit");
        btn.setFont(Font.font(20));
        btn.setOnAction((ActionEvent event) -> {
            org.setName(editName.getText());
            org.setDrinkVolume(editDrink.getValue());

        });

        editPane.getChildren().addAll(labelTitle, editName, editDrink, btn);
        return editPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
