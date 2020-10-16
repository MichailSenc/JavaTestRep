package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Simple Model-View");
        Organization org = new Organization("Horns&Hoof",10,"International Women's Day", LocalDate.of(2016, 3, 9),0);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        ViewOrganization viewOrg = new ViewOrganization(org);
        root.getChildren().add(viewOrg.createPane());
        Button btn = new Button("+");
        btn.setPrefSize(50, 50);
        btn.setOnAction((event) -> {
            org.increaseDrinkVolume();
            viewOrg.setInform();
        });
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
