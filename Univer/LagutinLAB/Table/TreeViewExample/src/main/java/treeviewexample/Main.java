package treeviewexample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private class ClassDescription {
        private String nameClass;
        public String toString() {return nameClass;}
        private String descriptionClass;
        public String getDescription() {return descriptionClass;}
        public  ClassDescription(String name, String description) {
            nameClass = name;
            descriptionClass = description;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20));
        Label labelSingleSelect = new Label();
        TreeView <ClassDescription> tvSingleSelect = createTreeView();
        SelectionModel<TreeItem<ClassDescription>> selectionModel = tvSingleSelect.getSelectionModel();
        selectionModel.selectedItemProperty().
                addListener((observable, oldValue, newValue) -> labelSingleSelect.setText(newValue.getValue().getDescription()));
        root.add(tvSingleSelect, 0, 0);
        root.add(labelSingleSelect, 0, 1);
        Label labelMultiSelect = new Label();
        TreeView <ClassDescription> tvMultiSelect = createTreeView();
        MultipleSelectionModel<TreeItem<ClassDescription>> mselectionModel = tvMultiSelect.getSelectionModel();
        mselectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        mselectionModel.selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    String selected = "";
                    for(TreeItem<ClassDescription> item : mselectionModel.getSelectedItems())
                        selected += item.getValue().getDescription() + "\n";
                    labelMultiSelect.setText(selected);
                });
        root.add(tvMultiSelect, 1, 0);
        root.add(labelMultiSelect, 1, 1);
        primaryStage.setTitle("Input/Output Java");
        primaryStage.setScene(new Scene(root, 600, 700));
        primaryStage.show();
    }

    private TreeView <ClassDescription> createTreeView(){
        TreeItem <ClassDescription> byteos = new TreeItem<>(new ClassDescription("OutputStream", "Байтовый поток вывода"));
        TreeItem <ClassDescription> filteros = new TreeItem<>(new ClassDescription("FilterOutputStream", "Специализированные потоки вывода"));
        TreeItem <ClassDescription> bufos = new TreeItem<>(new ClassDescription("BufferedOutputStream", "Вывод через буфер"));
        TreeItem <ClassDescription> prints = new TreeItem<>(new ClassDescription("PrintStream", "Форматный вывод"));
        filteros.getChildren().addAll(bufos, prints);
        TreeItem <ClassDescription> fileos = new TreeItem<>(new ClassDescription("FileOutputStream", "Вывод данных в файл"));
        TreeItem <ClassDescription> objos = new TreeItem<>(new ClassDescription("ObjectOutputStream", "Запись объектов в поток"));
        byteos.getChildren().addAll(filteros, fileos, objos);

        TreeItem <ClassDescription> writer = new TreeItem<>(new ClassDescription("Writer", "Символьный поток вывода"));
        TreeItem <ClassDescription> bw = new TreeItem<>(new ClassDescription("BufferedWriter", "Вывод через буфер"));
        TreeItem <ClassDescription> osw = new TreeItem<>(new ClassDescription("OutputStreamWriter", "Кодировка символов"));
        TreeItem <ClassDescription> fw = new TreeItem<>(new ClassDescription("FileWriter", "Вывод данных в файл"));
        osw.getChildren().add(fw);
        TreeItem <ClassDescription> pw = new TreeItem<>(new ClassDescription("PrintWriter", "Форматный вывод"));
        writer.getChildren().addAll(bw, osw, pw);

        TreeItem <ClassDescription> byteis = new TreeItem<>(new ClassDescription("InputStream", "Байтовый поток ввода"));
        TreeItem <ClassDescription> filteris = new TreeItem<>(new ClassDescription("FilterInputStream", "Специализированные потоки ввода"));
        TreeItem <ClassDescription> bufis = new TreeItem<>(new ClassDescription("BufferedInputStream", "Ввод через буфер"));
        filteris.getChildren().add(bufis);
        TreeItem <ClassDescription> fileis = new TreeItem<>(new ClassDescription("FileInputStream", "Ввод данных из файла"));
        TreeItem <ClassDescription> objis = new TreeItem<>(new ClassDescription("ObjectInputStream", "Чтение объектов из потока"));
        byteis.getChildren().addAll(filteris, fileis, objis);

        TreeItem <ClassDescription> reader = new TreeItem<>(new ClassDescription("Reader", "Символьный поток ввода"));
        TreeItem <ClassDescription> br = new TreeItem<>(new ClassDescription("BufferedReader", "Ввод через буфер"));
        TreeItem <ClassDescription> isr = new TreeItem<>(new ClassDescription("InputStreamReader", "Кодировка символов"));
        TreeItem <ClassDescription> fr = new TreeItem<>(new ClassDescription("FileReader", "Ввод данных из файла"));
        isr.getChildren().add(fr);
        reader.getChildren().addAll(br, isr);

        TreeItem<ClassDescription> root = new TreeItem<>(new ClassDescription("In/Out", "Классы ввода/вывода в Java"));
        root.getChildren().addAll(byteos, writer, byteis, reader);

        TreeView <ClassDescription> io = new TreeView<>(root);
        io.setPrefSize(250, 550);
        return io;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
