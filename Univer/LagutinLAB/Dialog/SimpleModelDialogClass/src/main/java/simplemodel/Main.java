package simplemodel;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Model Dialog");
        Organization org = new Organization("Horns&Hoof",10,"International Women's Day", LocalDate.of(2016, 3, 9),0);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        ViewOrganization viewOrg = new ViewOrganization(org);
        root.getChildren().add(viewOrg.getPane());
        Button btn = new Button();
        btn.setText("Edit");
        btn.setFont(Font.font(20));
        btn.setOnAction((ActionEvent event) -> {
            OrganizationEditDialog orgEditDialog = new OrganizationEditDialog(org, "Edit organization");
            if(orgEditDialog.getDialog().showAndWait().isPresent())
                System.out.println("model has changed");
        });
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
