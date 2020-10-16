package evPoc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Event");
        GridPane root = createButtons();

        root.setAlignment(Pos.CENTER);

        System.out.println(primaryStage);

        Scene scene = new Scene(root, 800 ,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addListener(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent event)->{
            if(event.getButton().equals(MouseButton.PRIMARY))
                button.setTextFill(Color.RED);
            if(event.getButton().equals(MouseButton.SECONDARY))
                button.setTextFill(Color.BLACK);
            if(event.getClickCount() == 2) {
                int min = 'a';
                int max = 'z';
                int diff = max - min;
                Random random = new Random();
                int i = random.nextInt(diff + 1);
                i += min;
                String s = "";
                button.setText(s += (char) i);
            }
        });
        button.setOnMouseEntered(event -> {
            button.setText(button.getText().toUpperCase());
        });
        button.setOnMouseExited(event ->  button.setText(button.getText().toLowerCase()));

        button.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent)->{
            if(button.getText().equalsIgnoreCase(keyEvent.getText()))
                button.setStyle("-fx-background-color: #90EE90");
        });
    }

    private GridPane createButtons(){
        GridPane pane = new GridPane();
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setStyle("-fx-font-size: 24");
        pane.setPrefSize(800, 600);
        String[] key1 = "computer".split("");
        String[] key = "keyboard".split("");

        for (int i = 0; i<key1.length; i++){
            Button btn = new Button(key1[i]);
            addListener(btn);
            pane.add(btn,i,0);
        }
        for (int i = 0; i<key.length; i++){
            Button btn = new Button(key[i]);
            addListener(btn);
            pane.add(btn,i,1);
        }
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

