package myPoc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Human");
        Human human = new Human("...",0,"...",0);
        HBox root = new HBox(10);

        ViewHuman viewHuman = new ViewHuman(human);
        root.getChildren().add(viewHuman.getPane());

        root.getChildren().add(new Separator(Orientation.VERTICAL));

        root.getChildren().add(editBlock(human));

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox editBlock(Human human){
        VBox editPane = new VBox(10);
        editPane.setPadding(new Insets(20));

        Label labelTitle = new Label("Введите имя");
        labelTitle.setFont(Font.font(20));

        TextField editName = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        Label labelTitle2 = new Label("Введите возраст");
        labelTitle2.setFont(Font.font(20));

        TextField editAge = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        Label labelTitle3 = new Label("Введите количество денег");
        labelTitle3.setFont(Font.font(20));

        TextField editMoney = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        Button btn = new Button("Edit");
        btn.setFont(Font.font(20));
        btn.setOnAction((event) -> {
            human.setName(editName.getText());
            human.setAge(Integer.parseInt(editAge.getText()));
            human.setStatus(human.findStatus());

        });

        Button button=new Button("+Money");
        button.setFont(Font.font(20));
        button.setOnAction((event)->{
            human.setMoney(Integer.parseInt(editMoney.getText()));
        });

        editPane.getChildren().addAll(labelTitle, editName, labelTitle2,editAge,labelTitle3,editMoney, btn,button);
        return editPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
