package pocet;

import javafx.application.Application;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    GridPane root;
    Text actiontarget;
    TextField valueWeight;
    Button btn;

    public void initRootLayout() {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Калории");
        Font fontBig = Font.font("Tahoma", FontWeight.NORMAL, 26);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        root.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Вес:");
        userName.setFont(fontBig);
        root.add(userName, 0, 1);

        valueWeight = new TextField();
        valueWeight.setFont(fontBig);
        root.add(valueWeight, 1, 1);


        btn = new Button("Go");
        btn.setFont(fontBig);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        root.add(hbBtn, 1, 4);

        actiontarget = new Text();
        actiontarget.setFont(fontBig);

        root.add(actiontarget, 1, 6);
        btn.setOnAction((ActionEvent e) -> {
            calculateWeight();
        });
    }

    private void calculateWeight() {
        String weight = valueWeight.getText();
        if (weight.matches("[0-9]+")) {
            float kal = (float) 3.01 * Float.parseFloat(weight);
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText(valueWeight.getText() + " grams " + "is " + kal + " calories");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калории");
        initRootLayout();
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

