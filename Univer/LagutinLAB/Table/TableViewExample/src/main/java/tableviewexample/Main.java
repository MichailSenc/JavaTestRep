package tableviewexample;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class Main extends Application {

    private TableView<Organization> table = new TableView<Organization>();
    private TableColumn nameCol = new TableColumn("Name");
    private TableColumn bossNameCol = new TableColumn("Boss Name");
    private TableColumn personnelCol = new TableColumn("Personnel");

    private ObservableList<Organization> data
            = FXCollections.observableArrayList(
            new Organization("Kalipso", "Smith", 1200),
            new Organization("DoggyStyle", "Johnson", 150),
            new Organization("Galaxy", "Williams", 25),
            new Organization("Bubble", "Jones", 0),
            new Organization("Kangaroo", "Brown", 560));
    HBox hb = new HBox();

    private void createTable() {

        table.setEditable(true);

        TableColumn<Organization, Number> indexColumn = new TableColumn<Organization, Number>("#");
        indexColumn.setMinWidth(50);
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())));

        nameCol.setMinWidth(170);
        nameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("name"));
//        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(new Callback<TableColumn, TableCell>() {

            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Organization, String>()
                {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {
//                            setTextFill(Color.RED);
                            setFont(Font.font ("Verdana", 20));
                            setText(item);
                        }
                    }
                };
            }
        });
        nameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Organization, String>>() {
                    @Override
                    public void handle(CellEditEvent<Organization, String> t) {
                        ((Organization) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );

        bossNameCol.setMinWidth(200);
        bossNameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("bossName"));
        bossNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        bossNameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Organization, String>>() {
                    @Override
                    public void handle(CellEditEvent<Organization, String> t) {
                        ((Organization) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setBossName(t.getNewValue());
                    }
                }
        );

        personnelCol.setMinWidth(100);
        personnelCol.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("personnel"));
        personnelCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        personnelCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Organization, Integer>>() {
                    @Override
                    public void handle(CellEditEvent<Organization, Integer> t) {
                        ((Organization) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPersonnel(t.getNewValue());
                    }
                }
        );

        TableColumn dataCol = new TableColumn("Data");
        dataCol.getColumns().addAll(personnelCol, bossNameCol);

        table.setItems(data);
        table.getColumns().addAll(indexColumn, nameCol, dataCol);
    }

    private void dataEditGroup() {
        TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(nameCol.getMinWidth());
        TextField addBossName = new TextField();
        addBossName.setMaxWidth(bossNameCol.getMinWidth());
        addBossName.setPromptText("Boss Name");
        TextField addPersonnel = new TextField();
        addPersonnel.setMaxWidth(personnelCol.getMinWidth());
        addPersonnel.setPromptText("Personnel");

        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Organization(
                    addName.getText(),
                    addBossName.getText(),
                    Integer.parseInt(addPersonnel.getText())));
            addName.clear();
            addBossName.clear();
            addPersonnel.clear();
        });
        hb.getChildren().addAll(addName, addBossName, addPersonnel, addButton);
        hb.setSpacing(3);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Table View Example");
        primaryStage.setWidth(550);
        primaryStage.setHeight(550);

        createTable();

        Label label = new Label("Organizations List");
        label.setFont(new Font("Arial", 20));

        dataEditGroup();

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 0, 0, 10));
        root.getChildren().addAll(label, table, hb);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

