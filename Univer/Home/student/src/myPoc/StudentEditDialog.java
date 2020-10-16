package myPoc;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class   StudentEditDialog {
    private Student student;
    private Stage dialog;
    private GridPane root;
    private Font font;
    private TextField nameEdit;
    private Spinner<Integer> firstEdit;
    private Spinner<Integer> secondEdit;
    private Spinner<Integer> thirdEdit;


    public void createName(){
        Label name = new Label("Name:");
        name.setFont(font);
        root.add(name,0,0);
        nameEdit = new TextField(student.getName());
        nameEdit.setFont(Font.font(24));
        root.add(nameEdit,1,0);
    }

    public void createSubject(){
        Label first = new Label(student.getFirst().getName());
        Label second = new Label(student.getSecond().getName());
        Label third = new Label(student.getThird().getName());

        first.setFont(font);
        second.setFont(font);
        third.setFont(font);

        root.add(first,0,1);
        root.add(second,0,2);
        root.add(third,0,3);

        firstEdit = new Spinner<>(0,100,student.getFirst().getScore());
        secondEdit = new Spinner<>(0,100,student.getSecond().getScore());
        thirdEdit = new Spinner<>(0,100,student.getThird().getScore());

        //Для удобства можно раскоментировать, но тогда пользователь сможет ввести ошибку
        //firstEdit.setEditable(true);
        //secondEdit.setEditable(true);
        //thirdEdit.setEditable(true);

        firstEdit.setStyle("-fx-font-size: 24 pt");
        secondEdit.setStyle("-fx-font-size: 24 pt");
        thirdEdit.setStyle("-fx-font-size: 24 pt");

        root.add(firstEdit,1,1);
        root.add(secondEdit,1,2);
        root.add(thirdEdit,1,3);
    }

    public void createButton(){
        Button btn = new Button("Change exam subject");
        btn.setFont(Font.font(20));
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setOnAction((event) -> {
            SubjectEditDialog subjectEditDialogs = new SubjectEditDialog(student);
        });
        root.add(btn,0,4,2,1);

        Button btnOk = new Button("OK");
        btnOk.setFont(Font.font(20));
        btnOk.setMaxWidth(Double.MAX_VALUE);
        btnOk.setOnAction((event) -> {
            if (isInputValid())
                ifOk();
            else message();
        });
        root.add(btnOk,0,5);


        Button btnCancel = new Button("Cancel");
        btnCancel.setFont(Font.font(20));
        btnCancel.setMaxWidth(Double.MAX_VALUE);
        btnCancel.setMaxHeight(Double.MAX_VALUE);
        btnCancel.setOnAction((event) -> {
            dialog.close();
        });
        root.add(btnCancel,1,5);
    }

    public void ifOk(){
        student.setName(nameEdit.getText());
        student.setFirst(new Subject(student.getFirst().getName(),firstEdit.getValue()));
        student.setSecond(new Subject(student.getSecond().getName(),secondEdit.getValue()));
        student.setThird(new Subject(student.getThird().getName(),thirdEdit.getValue()));
        dialog.close();
    }

    public boolean isInputValid() {
        if (nameEdit.getText().matches("[a-zA-Zа-яА-Я]+"))
            return true;
        return false;
    }

    private void message(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data entry error");
        alert.setHeaderText("Name entry error");
        alert.setContentText("\"Имя дожно стостоять только из русских/английских букв   \"");
        alert.showAndWait();
    }

    public StudentEditDialog(Student student){
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
        createName();
        createSubject();
        createButton();

        Scene scene = new Scene(root, 500,400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
