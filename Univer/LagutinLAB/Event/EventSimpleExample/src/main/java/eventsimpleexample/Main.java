package eventsimpleexample;

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

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Tab simpleEvent = new Tab("Simple event", createVBoxPane());
        Tab eventHandler = new Tab("EventHandler", createAnchorPane());
        Tab eventRoute = new Tab("Event route", createVBox());
        TabPane root =new TabPane(simpleEvent, eventHandler, eventRoute);
        root.setStyle("-fx-font-size: 2o");

        Scene scene = new Scene(root);

        primaryStage.setTitle("Event example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createVBoxPane(){
        VBox pane = new VBox();
        pane.setPadding(new Insets(20));
        pane.setSpacing(30);
        pane.getChildren().addAll(circleNode(), textBoxNode(), textNode());
        return  pane;
    }

    private Circle circleNode(){
        double radius = 150;

        Circle circle = new Circle(radius, Color.RED);
        circle.setFocusTraversable(true);

        circle.setOnMouseEntered((MouseEvent me) -> {
            System.out.println("Mouse entered");
            circle.setFill(Color.AQUA);
            circle.requestFocus();
        });

        circle.setOnMouseExited((MouseEvent me) -> {
            System.out.println("Mouse exited");
            circle.setFill(Color.RED);
        });

        circle.setOnMousePressed((MouseEvent me) -> {
            System.out.println("Mouse pressed");
            circle.setFill(Color.CORAL);
        });

        circle.setOnMouseMoved((MouseEvent me) -> {
            System.out.println("Mouse moved");
            circle.setFill(Color.GRAY);
        });
        circle.setOnKeyPressed((KeyEvent ke) -> {
            System.out.println("Circle Key Pressed: " + ke.getText());
        });

        return circle;
    }

    private TextField textBoxNode(){
        TextField textBox = new TextField();
        textBox.setPrefSize(300, 100);
        textBox.setFont(Font.font(24));
        textBox.setPromptText("Write here");

        textBox.setOnKeyPressed((KeyEvent ke) -> {
            System.out.println("Key Pressed: " + ke.getText());
            if (ke.getText().equals("a") && ke.isAltDown()) {
                textBox.setFont(Font.font("Arial", 30));
            }
            if (ke.getCode() == KeyCode.T && ke.isAltDown()) {
                textBox.setFont(Font.font("Times New Roman", 20));
            }
        });

        textBox.setOnKeyReleased((KeyEvent ke) -> {
            System.out.println("Key Released: " + ke.getText());
        });
        return textBox;
    }

    private Text textNode(){
        Text text = new Text("TEXT");
        text.setFont(Font.font(24));
        text.prefWidth(200);
        text.prefWidth(50);
        text.setFocusTraversable(true);
        text.setOnMouseClicked((e)->text.requestFocus());
        text.setOnKeyPressed((KeyEvent ke) -> {
            System.out.println("Label Key Pressed: " + ke.getText());
            if(ke.getText().equals("g")) {
                text.setFill(Color.GREEN);
            }
            if(ke.getCode()==KeyCode.B) {
                text.setFill(Color.BLACK);
            }
        });
        return text;
    }

    private Random random = new Random();
    private Point2D delta;
    private boolean isRotate;

    private AnchorPane createAnchorPane() {
        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-font-size: 22");
        for (int i = 0; i < 5; i++) {
            pane.getChildren().add(createLabel());
        }
        return pane;
    }

    private Label createLabel() {
        Label label = new Label("EventExample");
        label.setPrefWidth(200);
        label.setPrefHeight(60);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
        label.setLayoutY(random.nextDouble() * 700);
        label.setLayoutX(random.nextDouble() * 700);
        label.setRotate(random.nextDouble() * 180);
        label.setFocusTraversable(true);
        addTranslateListener(label);
        return label;
    }

    private void addTranslateListener(Label node) {
        node.addEventHandler(MouseEvent.ANY, (e)->node.requestFocus());
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            node.toFront();
            delta = new Point2D((mouseEvent.getSceneX() - node.getLayoutX()),
                    (mouseEvent.getSceneY() - node.getLayoutY()));
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                isRotate = true;
            }
        });

        node.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent mouseEvent) -> {
            isRotate = false;
        });

        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent mouseEvent) -> {
            if (isRotate) {
                double dx1 = mouseEvent.getSceneX() - node.getLayoutX();
                double dy1 = mouseEvent.getSceneY() - node.getLayoutY();
                double angle = Math.acos(dx1/Math.sqrt(dx1 * dx1 + dy1 * dy1));
                if (dy1 < 0) {
                    angle = Math.PI - angle;
                }
                node.setRotate(Math.toDegrees(angle));
            } else {
                node.setLayoutX(mouseEvent.getSceneX() - delta.getX());
                node.setLayoutY(mouseEvent.getSceneY() - delta.getY());
            }
        });

        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            if (mouseEvent.getClickCount() == 2)
                node.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, aqua 0%, red 100%);\n" +
                    "    -fx-border-color: black;\n" +
                    "    -fx-border-style: dashed;\n" +
                    "    -fx-border-width: 2;");
        });

        node.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.A) {
                node.setText("KeyEvent_A");
            }
            if (keyEvent.getCode() == KeyCode.A && keyEvent.isShiftDown()) {
                node.setText("KeyEvent_A_Shift");
            }
            System.out.println(keyEvent.getText());
        });
    }

    private VBox createVBox(){
        Label source = new Label("Source");
        Label target = new Label("Target");
        Button btn = new Button();
        btn.setText("button");
        btn.setOnAction((event) -> {
            source.setText("Source: "+event.getSource().toString());
            target.setText("Target: "+event.getTarget().toString());
        });
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        btn1.setOnAction(event-> Event.fireEvent(btn2, event));
        btn2.setOnAction((event) -> {
            source.setText("Source: "+event.getSource().toString());
            target.setText("Target: "+event.getTarget().toString());
        });

        Button btn3 = new Button("btn3");
        btn3.setOnAction((event) -> {
            source.setText("Source: "+event.getSource().toString());
            target.setText("Target: "+event.getTarget().toString());
            System.out.println("btn3.setOnAction");
        });
        VBox box = new VBox(btn3);
        box.setAlignment(Pos.CENTER);
        box.addEventFilter(ActionEvent.ACTION, (event) -> {
            source.setText("Source: "+event.getSource().toString());
            target.setText("Target: "+event.getTarget().toString());
            System.out.println("box.addEventFilter");
            event.consume();
        });

        VBox pane = new VBox(20);
        pane.setStyle("-fx-font-size: 24");
        pane.setPrefSize(800, 600);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(source, target, btn, btn1, btn2, box);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

