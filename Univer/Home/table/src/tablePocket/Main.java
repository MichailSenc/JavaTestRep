package tablePocket;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import static javafx.application.Application.launch;

public class Main extends Application {

    private MenuBar menuBar;
    private ObservableList<Pet> petList;
    private TableView<Pet> petTableView;

    HBox hb = new HBox();

    private void handleFileOpen() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select the desired file");
        File file = fileChooser.showOpenDialog(null);
        //File file = new File("./src/resourses/tablePocket/data.txt");
        if (file == null)
            return;
        String spec = "", name = "", owner = "";
        int age =  0;

        Scanner scanner = new Scanner(file);

        if(!scanner.hasNextLine())
            throw new IOException("no data in file (main class 53 строка)");

        petList.clear();
        while(scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(",");
            if (split.length != 4)
                throw new IOException("Incorrect file data (main class 58 строка)");

            if (split[0].matches("[a-zA-Zа-яА-Я ]+"))
                spec = split[0].trim();
            else
                throw new IOException("spec");

            if (split[1].matches("[a-zA-Zа-яА-Я ]+"))
                name = split[1].trim();
            else
                throw new IOException("name");

            if (split[2].matches("[0-9 ]+"))
                age = Integer.parseInt(split[2].trim());
            else
                throw new IOException("age");

            if (split[3].matches("[a-zA-Zа-яА-Яё ]+"))
                owner = split[3].trim();
            else
                throw new IOException("owner");

            Pet pet = new Pet();
            pet.setSpecies(spec);
            pet.setName(name);
            pet.setAge(age);
            pet.setOwner(owner);
            petList.add(pet);
        }
        scanner.close();
    }

    private void handleFileSave() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data File");
        File file = fileChooser.showSaveDialog(null);
        //File file = new File("./src/resourses/tablePocket/output.txt");
        if (file == null) {
            return;
        }
        FileWriter out = new FileWriter(file);
        ListIterator<Pet> iterator = petList.listIterator();
        while (iterator.hasNext()) {
            Pet pet = iterator.next();
            out.write(String.format("%s,%s,%d,%s\n", pet.getSpecies(), pet.getName(), pet.getAge(), pet.getOwner()));
        }
        out.close();
    }

    private void createFileMenu() {
        Menu fileMenu = new Menu("_File");
        MenuItem open = new MenuItem("Open");
        open.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");
        fileMenu.getItems().addAll(open, save, new SeparatorMenuItem(), exit);
        open.setOnAction((ActionEvent event) -> {
            try {
                handleFileOpen();
            } catch (IOException e) {
                showMessage("Input  file error: "+e.getMessage());
                System.out.println("Input file error");
            }
        });
        save.setOnAction((ActionEvent event) -> {
            try {
                handleFileSave();
            } catch (IOException e) {
                showMessage("Output file  error");
                System.out.println("Output file  error");
            }
        });
        exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        menuBar.getMenus().add(fileMenu);
    }


    private void createEditMenu() {
        Menu editMenu = new Menu("Tools");
        MenuItem edit = new MenuItem("Edit pet");
        MenuItem add = new MenuItem("Add");
        MenuItem delete = new MenuItem("Delete");
        MenuItem show = new MenuItem("Show");

        editMenu.getItems().add(edit);
        editMenu.getItems().add(add);
        editMenu.getItems().add(delete);
        editMenu.getItems().add(show);

        edit.setOnAction((ActionEvent event) -> handleButtonEdit());
        add.setOnAction((ActionEvent event) -> handleButtonAdd());
        delete.setOnAction((ActionEvent event) -> handleButtonDelete());
        show.setOnAction((ActionEvent event) -> handleButtonShow());

        menuBar.getMenus().add(editMenu);
    }

    private void createTableView(){
        TableColumn<Pet, String> specCol = new TableColumn<>("Species");
        specCol.setCellValueFactory(new PropertyValueFactory("species"));

        TableColumn<Pet, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<Pet, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));

        TableColumn<Pet, String> ownCol = new TableColumn<>("Owner");
        ownCol.setCellValueFactory(new PropertyValueFactory("owner"));

        petTableView.getColumns().setAll(specCol,nameCol,ageCol,ownCol);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showPet(Pet pet) {
        Stage stage = new Stage();
        stage.setTitle("Show selected pet");
        BorderPane pane = new BorderPane();
        ViewPet viewPet = new ViewPet(pet);
        pane.setCenter(viewPet.getPane());
        Scene scene = new Scene(pane, 400, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void handleButtonEdit() {
        Pet pet = petTableView.getSelectionModel().getSelectedItem();
        if (pet != null) {
            PetEditDialog petEditDialog = new PetEditDialog(pet, "Edit pet");
            petEditDialog.getDialog().showAndWait();
        } else {
            showMessage("No selected item!");
        }
    }

    private void handleButtonAdd() {
        PetEditDialog orgEditDialog = new PetEditDialog(new Pet(), "Add pet");
        Optional<Pet> result = orgEditDialog.getDialog().showAndWait();
        if (result.isPresent()) {
            petList.add(result.get());
        }
    }

    private void handleButtonDelete() {
        int selectedIndex = petTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            petTableView.getItems().remove(selectedIndex);
        } else {
            showMessage("No deleted item!");
        }
    }

    private void handleButtonShow() {
        Pet pet = petTableView.getSelectionModel().getSelectedItem();
        if (pet != null) {
            showPet(pet);
        } else {
            showMessage("No selected item!");
        }
    }

    private void dataEditGroup() {
        TextField sortSpec = new TextField();
        sortSpec.setPromptText("Species");
        final Button sortedButton = new Button("Sorted");
        sortedButton.setOnAction((ActionEvent event) -> {
            petTableView.setItems(petList.filtered(e -> e.getSpecies().matches(sortSpec.getText().trim())));
            sortSpec.clear();
        });

        final Button reloadButton = new Button("Reload");
        reloadButton.setOnAction((ActionEvent event) -> {
            petTableView.setItems(petList);
        });

        hb.getChildren().addAll(sortSpec,sortedButton,reloadButton);
        hb.setSpacing(3);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List of organizations");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        petList = FXCollections.<Pet>observableArrayList();
        petTableView = new TableView<>();

        petList.addAll(new Pet(),new Pet(), new Pet());

        menuBar = new MenuBar();
        createFileMenu();
        createEditMenu();
        root.setTop(menuBar);

        dataEditGroup();

        createTableView();
        petTableView.setItems(petList);
        root.setCenter(petTableView);

        hb.setSpacing(30);
        hb.setPadding(new Insets(10, 130, 10, 130));

       root.setBottom(hb);

        Scene scene = new Scene(root, 800, 500, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
