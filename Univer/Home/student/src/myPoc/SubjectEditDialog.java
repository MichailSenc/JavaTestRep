package myPoc;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class SubjectEditDialog {
    private Student student;
    private Stage dialog;
    private GridPane root;
    private Font font;
    private TextField nameEdit;
    private Label name;
    public ArrayList<TextField> textFields;

    public void createSubList(){
        textFields = new ArrayList<>();
        Iterator<Subject> it = student.getList().iterator();
        int position=0; Subject subject;
        while (it.hasNext()){
            subject = it.next();
            name = new Label(subject.getName());
            name.setFont(font);
            root.add(name,0,position);
            nameEdit = new TextField(String.valueOf(subject.getScore()));
            nameEdit.setFont(Font.font(24));
            root.add(nameEdit,1,position);
            textFields.add(nameEdit);
            position++;
        }
    }

    public void createButton(){
        Button btnOk = new Button("OK");
        btnOk.setFont(Font.font(20));
        //btnOk.setMaxWidth(Double.MAX_VALUE);
        //btnOk.setMaxHeight(Double.MAX_VALUE);
        btnOk.setOnAction((event) -> {
            if (isInputValid())
                ifOk();
            else message();
        });
        root.add(btnOk,0,textFields.size());

        Button btnCancel = new Button("Cancel");
        btnCancel.setFont(Font.font(20));
        //btnCancel.setMaxWidth(Double.MAX_VALUE);
        //btnCancel.setMaxHeight(Double.MAX_VALUE);
        btnCancel.setOnAction((event) -> {
            dialog.close();
        });
        root.add(btnCancel,1,textFields.size());
    }

    public void ifOk(){
        Iterator<TextField> it1 = textFields.iterator();
        Iterator<Subject> it2 = student.getList().iterator();
        ArrayList<Subject> arr = new ArrayList<>();
        while (it1.hasNext() && it2.hasNext())
        arr.add(new Subject(it2.next().getName(),Integer.parseInt(it1.next().getText())));
        student.setList(arr);
        dialog.close();
    }

    public boolean isInputValid() {
        String s;
        Iterator<TextField> iterator = textFields.iterator();
        while (iterator.hasNext()){
            s = iterator.next().getText();
            if(!s.matches("[0-9]+"))
                return false;
            if(Integer.parseInt(s) > 300 || Integer.parseInt(s) < 0)
                return false;
        }
        return true;
    }

    private void message(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data entry error");
        alert.setHeaderText("Subject entry error");
        alert.setContentText("\"Вводимый балл должен состоять из чисел от 0 до 300\"");
        alert.showAndWait();
    }

    public SubjectEditDialog(Student student){
        this.student = student;
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Edit organization");
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        //root.setGridLinesVisible(true);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);

        createSubList();
        createButton();

        Scene scene = new Scene(root, 600,400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
