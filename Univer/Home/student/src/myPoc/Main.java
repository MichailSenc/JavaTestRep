package myPoc;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Student");
        Student student = new Student("Улукбек",80,60,90);
        HBox root = new HBox(10);

        root.setAlignment(Pos.CENTER);

        ViewStudent viewStudent = new ViewStudent(student);
        root.getChildren().add(viewStudent.getPane());

        System.out.println(primaryStage);

        Scene scene = new Scene(root, 700 ,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
