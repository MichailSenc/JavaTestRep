package listviewexample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main extends Application {

    private ObservableList<Organization> data = FXCollections.observableArrayList();
    private ListView<Organization> dataView;
    private ViewOrg view = new ViewOrg();
    private Font font = Font.font(18);

    String beginMessage = "This is an example of working with a list of objects. ";

    @Override
    public void init(){
        try{
            Scanner in = new Scanner(new File("data.txt"));
            while (in.hasNextLine()) {
                String []str = in.nextLine().split(" +");
                if(str.length!=3 || !str[2].matches("[0-9]+"))
                    throw new IOException("error file format");
                data.add(new Organization(str[0], str[1], Integer.parseInt(str[2])));
            }
            in.close();
            data.sort((o1,o2)->o1.getName().compareToIgnoreCase(o2.getName()));
        } catch (IOException e) {
            beginMessage += "\nUnfortunately, the data from the file did not load.";
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(createListViewOrg());
        root.setRight(view.getPane());
        root.setLeft(createButtons());
        root.setTop(createMenu());
        primaryStage.setTitle("List of organizations");
        primaryStage.setScene(new Scene(root, 700, 600));
        showBeginMessage();
        primaryStage.show();
//        showBeginMessage();
    }

    private void showBeginMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hello");
        alert.setHeaderText(null);
        alert.setContentText(beginMessage);
        alert.getDialogPane().setStyle("-fx-font-size: 16px;");
        alert.showAndWait();
    }

    private ListView<Organization> createListViewOrg(){
        dataView = new ListView<>(data);
        dataView.setStyle("-fx-font-size: 20px;");
        dataView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                if(newValue!=null) view.setOrganization(newValue);
        });
        return dataView;
    }

    private VBox createButtons() {
        VBox boxButtons = new VBox();
        boxButtons.setPadding(new Insets(10));
        boxButtons.setSpacing(10);
        boxButtons.setAlignment(Pos.CENTER);
        Button filterBossName = new Button("Filter same boss");
        filterBossName.setFont(font);
        filterBossName.setOnAction((ActionEvent e) -> {
            Organization org = dataView.getSelectionModel().getSelectedItem();
            if (org != null) {
                ObservableList<Organization> dataFilter = FXCollections.observableArrayList();
                dataFilter.setAll(data.stream().filter(organization -> organization.isTheSameBoss(org)).collect(Collectors.toList()));
                dataFilterView(dataFilter);
            } else {
                showMessage("No selected item!");
            }
        });
        Button showBossName = new Button("Show same boss");
        showBossName.setFont(font);
        showBossName.setOnAction((ActionEvent e) -> {
            Organization org = dataView.getSelectionModel().getSelectedItem();
            if (org != null) {
                ObservableList<Organization> dataShow = FXCollections.observableArrayList();
                dataShow.setAll(data.stream().filter(organization -> organization.isTheSameBoss(org)).collect(Collectors.toList()));
                dataView.setItems(dataShow);
            } else {
                showMessage("No selected item!");
            }
        });
        Button showAll = new Button("Show all");
        showAll.setFont(font);
        showAll.setOnAction((ActionEvent e) -> dataView.setItems(data));
        boxButtons.getChildren().addAll(filterBossName, showBossName, showAll);
        return boxButtons;
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font-size: 20px;");
        alert.showAndWait();
    }

    private MenuBar createMenu(){
        Menu editMenu = new Menu("Edit");
        MenuItem edit = new MenuItem("Edit organization");
        editMenu.getItems().add(edit);
        edit.setOnAction((ActionEvent event) -> {
            handleButtonEdit();
        });
        MenuItem add = new MenuItem("Add organization");
        editMenu.getItems().add(add);
        add.setOnAction((ActionEvent event) -> {
            handleButtonAdd();
        });

        Menu exitMenu = new Menu("Exit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e-> Platform.exit());
        exitMenu.getItems().add(exitItem);

        return new MenuBar(editMenu,exitMenu);
    }

    private void handleButtonEdit() {
        Organization organization = dataView.getSelectionModel().getSelectedItem();
        if (organization != null) {
            DialogEditOrg orgEditDialog = new DialogEditOrg(organization);
            data.sort((o1,o2)->o1.getName().compareToIgnoreCase(o2.getName()));
        } else {
            showMessage("No selected item!");
        }
    }

    private void handleButtonAdd() {
        DialogEditOrg orgEditDialog = new DialogEditOrg(null);
        Organization organization = orgEditDialog.getOrg();
        if(organization!= null) {
            data.add(organization);
            data.sort((o1,o2)->o1.getName().compareToIgnoreCase(o2.getName()));
        }
    }

    private   void dataFilterView (ObservableList<Organization> dataFilter){
        Stage view = new Stage();
        ListView<Organization> dataFilterView = new ListView<>(dataFilter);
        dataFilterView.setStyle("-fx-font-size: 20px;");
        Button ok = new Button("Ok");
        ok.setOnAction( e-> view.close());
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(dataFilterView, ok);
        view.setScene(new Scene(root, 200, 450));
        view.show();
    }

    @Override
    public void stop() {
        try {
            PrintWriter out = new PrintWriter("data.txt");
            for(Organization org: data)
                out.println(org.getName()+" "+org.getBossName()+ " "+ org.getPersonnel());
            out.close();
        } catch (IOException e) {
            System.out.println("Error input data");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

