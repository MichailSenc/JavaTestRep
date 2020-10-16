package simplemodel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

    private MenuBar mb;
    private ToolBar tb;
    private Organization org;
    private ViewOrganization viewOrg;

    private void handleFileOpen() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open  Data   File");
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            return;
        }
        String name, holiday, date;
        int personnel;
        double drinkVolume;
        Scanner in = new Scanner(file);
        if (in.hasNextLine()) {
            name = in.nextLine();
        } else {
            throw new IOException("name");
        }
        if (in.hasNextInt()) {
            personnel = in.nextInt();
        } else {
            throw new IOException("personnel");
        }
        in.nextLine();
        if (in.hasNextLine()) {
            holiday = in.nextLine();
        } else {
            throw new IOException("holiday");
        }
        if (in.hasNextLine()) {
            date = in.nextLine();
        } else {
            throw new IOException("date");
        }
        if (in.hasNextDouble()) {
            drinkVolume = in.nextDouble();
        } else {
            throw new IOException("drink");
        }
        org.setName(name);
        org.setPersonnel(personnel);
        org.setHoliday(holiday);
        org.setDate(LocalDate.parse(date));
        org.setDrinkVolume(drinkVolume);
        in.close();
    }

    private void handleFileSave() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data File");
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            return;
        }
        FileWriter out = new FileWriter(file);
        out.write(org.getName() + "\n" + org.getPersonnel() + " \n"
                + org.getHoliday() + "\n" + org.getDate().toString()
                + "\n" + org.getDrinkVolume());
        out.close();
    }

    private void showMessage(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Data file error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
                showMessage("Input  file error "+e.getMessage());
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
        mb.getMenus().add(fileMenu);
    }

        private void createEditMenu() {
            Menu editMenu = new Menu("Edit");
            MenuItem edit = new MenuItem("Edit organization");
            editMenu.getItems().add(edit);
            edit.setOnAction((ActionEvent event) -> {
                OrganizationEditDialog orgEditDialog = new OrganizationEditDialog(org);
            });
            mb.getMenus().add(editMenu);
        }

    private void createAboutMenu() {
        Menu aboutMenu = new Menu("About");
        MenuItem about = new MenuItem("About");
        aboutMenu.getItems().add(about);
        about.setOnAction((ActionEvent event) -> {
            Alert msg = new Alert(AlertType.INFORMATION);
            msg.setTitle("About");
            msg.setHeaderText(null);
            msg.setContentText("Example menu");
            msg.show();
        });
        mb.getMenus().add(aboutMenu);
    }

    private void createToolBar() {
        Image fileOpenImage = new Image(getClass().getResourceAsStream("images/fileopen.jpeg"));
        ImageView imvF = new ImageView(fileOpenImage);
        Button btnFileOpen = new Button("Open file", imvF);
        btnFileOpen.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnFileOpen.setTooltip(new Tooltip("Open file"));
        btnFileOpen.setOnAction((ActionEvent event) -> {
            try {
                handleFileOpen();
            } catch (IOException e) {
                showMessage("Input file error");
                System.out.println("Input file error");
            }
        });

        Image fileSaveImage = new Image(getClass().getResourceAsStream("images/filesave.png"));
        imvF = new ImageView(fileSaveImage);
        Button btnFileSave = new Button("Save file", imvF);
        btnFileSave.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnFileSave.setTooltip(new Tooltip("Save file"));
        btnFileSave.setOnAction((ActionEvent event) -> {
            try {
                handleFileSave();
            } catch (IOException e1) {
                showMessage("Output file error");
                System.out.println("Output file error");
            }
        });
        tb = new ToolBar(btnFileOpen, btnFileSave);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Model Menu");
        org = new Organization("Horns&Hoof", 10, "International Women's Day", LocalDate.of(2016, 3, 9), 0);
        BorderPane root = new BorderPane();
        mb = new MenuBar();
        createFileMenu();
        createEditMenu();
        createAboutMenu();
        root.setTop(mb);
        createToolBar();
        root.setBottom(tb);
        viewOrg = new ViewOrganization(org);
        root.setCenter(viewOrg.getPane());

        Scene scene = new Scene(root, 700, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

