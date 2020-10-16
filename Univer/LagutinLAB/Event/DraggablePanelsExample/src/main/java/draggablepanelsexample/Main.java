package draggablepanelsexample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private BooleanProperty dragModeActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);

    private static class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }

    @Override
    public void start(Stage stage) {
        Node loginPanel = makeDraggable(createLoginPanel());
        Node confirmationPanel = makeDraggable(createConfirmationPanel());
        Node progressPanel = makeDraggable(createProgressPanel());

        loginPanel.relocate(0, 0);
        confirmationPanel.relocate(0, 116);
        progressPanel.relocate(0, 180);

        Pane panelsPane = new Pane();
        panelsPane.getChildren().addAll(loginPanel, confirmationPanel, progressPanel);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 24");

        BorderPane.setAlignment(panelsPane, Pos.TOP_LEFT);
        root.setCenter(panelsPane);

        CheckBox dragModeCheckbox = new CheckBox("Drag mode");
        BorderPane.setMargin(dragModeCheckbox, new Insets(6));
        root.setBottom(dragModeCheckbox);

        dragModeActiveProperty.bind(dragModeCheckbox.selectedProperty());

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Draggable Panels Example");
        stage.show();
    }

    private Node makeDraggable(Node node) {
        DragContext dragContext = new DragContext();
        Group wrapGroup = new Group(node);

        wrapGroup.addEventFilter(MouseEvent.ANY, (MouseEvent mouseEvent) -> {
            if (dragModeActiveProperty.get()) {
                mouseEvent.consume();
            }
        });

        wrapGroup.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            if (dragModeActiveProperty.get()) {
                dragContext.mouseAnchorX = mouseEvent.getX();
                dragContext.mouseAnchorY = mouseEvent.getY();
                dragContext.initialTranslateX = node.getTranslateX();
                dragContext.initialTranslateY = node.getTranslateY();
            }
        });

        wrapGroup.addEventFilter(MouseEvent.MOUSE_DRAGGED, (MouseEvent mouseEvent) -> {
            if (dragModeActiveProperty.get()) {
                node.setTranslateX(dragContext.initialTranslateX + mouseEvent.getX() - dragContext.mouseAnchorX);
                node.setTranslateY(dragContext.initialTranslateY + mouseEvent.getY() - dragContext.mouseAnchorY);
            }
        });
        return wrapGroup;
    }

    private static Node createLoginPanel() {
        ToggleGroup toggleGroup = new ToggleGroup();

        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText("Your name");

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        passwordField.setPromptText("Your password");

        ChoiceBox<String> choiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(
                        "English", "\u0420\u0443\u0441\u0441\u043a\u0438\u0439", "Fran\u00E7ais"));
        choiceBox.setTooltip(new Tooltip("Your language"));
        choiceBox.getSelectionModel().select(0);

        HBox panel = createHBox(6, createVBox(2, createRadioButton("High", toggleGroup, true),
                                createRadioButton("Medium", toggleGroup, false),
                                createRadioButton("Low", toggleGroup, false)),
                                createVBox(2, textField, passwordField),
                                choiceBox);
        panel.setAlignment(Pos.BOTTOM_LEFT);
        configureBorder(panel);

        return panel;
    }

    private static RadioButton createRadioButton(String text, ToggleGroup toggleGroup, boolean selected) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setSelected(selected);

        return radioButton;
    }

    private static Node createConfirmationPanel() {
        Label acceptanceLabel = new Label("Not Available");

        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction((ActionEvent event) -> {
            acceptanceLabel.setText("Accepted");
        });

        Button declineButton = new Button("Decline");
        declineButton.setOnAction((ActionEvent event) -> {
            acceptanceLabel.setText("Declined");
        });

        HBox panel = createHBox(6, acceptButton, declineButton, acceptanceLabel);
        panel.setAlignment(Pos.CENTER_LEFT);
        configureBorder(panel);

        return panel;
    }

    private static Node createProgressPanel() {
        Slider slider = new Slider();

        ProgressIndicator progressIndicator = new ProgressIndicator(0);
        progressIndicator.progressProperty().bind(Bindings.divide(slider.valueProperty(), slider.maxProperty()));

        HBox panel = createHBox(6, new Label("Progress:"), slider, progressIndicator);
        configureBorder(panel);

        return panel;
    }

    private static void configureBorder(Region region) {
        region.setStyle("-fx-background-color: white;"
                + "-fx-border-color: black;"
                + "-fx-border-width: 1;"
                + "-fx-border-radius: 6;"
                + "-fx-padding: 6;");
    }

    private static HBox createHBox(double spacing, Node... children) {
        HBox hbox = new HBox(spacing);
        hbox.getChildren().addAll(children);
        return hbox;
    }

    private static VBox createVBox(double spacing, Node... children) {
        VBox vbox = new VBox(spacing);
        vbox.getChildren().addAll(children);
        return vbox;
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
