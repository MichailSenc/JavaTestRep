package myPoc;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.Iterator;

public class ViewStudent {
    private Student student;
    private GridPane gridPane;
    private Text nameStudent;
    private Text examSub;
    private Text studentSub;
    private Text points;


    public void createPane() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        //gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(300));
        gridPane.getColumnConstraints().add(new ColumnConstraints(300));


        Font font = Font.font("Tahoma", FontWeight.NORMAL, 20); //!!!!!!!


        nameStudent = new Text(student.getName());
        nameStudent.setFont(font);
        GridPane.setHalignment(nameStudent, HPos.CENTER);
        gridPane.add(nameStudent, 0, 0, 2, 1);


        points = new Text(String.format("Ваша сумма баллов - %d", student.totalScore()));
        points.setFont(font);
        GridPane.setHalignment(points, HPos.CENTER);
        gridPane.add(points, 0, 1, 2, 1);


        Text text = new Text("Возможные направления");
        text.setFont(font);
        GridPane.setHalignment(text, HPos.CENTER);
        gridPane.add(text, 1, 2);


        text = new Text("Ваши результаты");
        text.setFont(font);
        GridPane.setHalignment(text, HPos.CENTER);
        gridPane.add(text, 0, 2);


        examSub = new Text(student.listToString());
        examSub.setFont(Font.font(18));
        GridPane.setHalignment(examSub, HPos.CENTER);
        gridPane.add(examSub, 1, 3);


        studentSub = new Text(student.subjectToString());
        studentSub.setFont(Font.font(18));
        GridPane.setHalignment(studentSub, HPos.CENTER);
        gridPane.add(studentSub, 0, 3);

        Button btn = new Button("Edit");
        btn.setFont(Font.font(20));
        GridPane.setHalignment(btn, HPos.CENTER);
        btn.setOnAction((event) -> {
            StudentEditDialog studentEditDialog = new StudentEditDialog(student);
        });
        gridPane.add(btn, 0, 4, 2, 1);
    }

    public void addListenerStudent() {
        nameStudent.textProperty().bind(student.nameProperty());
        student.firstProperty().addListener((observable, oldValue, newValue) -> {
            points.setText(String.format("Ваша сумма баллов - %d", student.totalScore()));
            studentSub.setText(student.subjectToString());
            examSub.setText(student.listToString());

        });
        student.secondProperty().addListener((observable, oldValue, newValue) -> {
            points.setText(String.format("Ваша сумма баллов - %d", student.totalScore()));
            studentSub.setText(student.subjectToString());
            examSub.setText(student.listToString());
        });
        student.thirdProperty().addListener((observable, oldValue, newValue) -> {
            points.setText(String.format("Ваша сумма баллов - %d", student.totalScore()));
            studentSub.setText(student.subjectToString());
            examSub.setText(student.listToString());
        });
        student.listProperty().addListener((observable, oldValue, newValue) -> {
            examSub.setText(student.listToString());
            studentSub.setText(student.subjectToString());
        });

    }

    public GridPane getPane() {
        return gridPane;
    }

    public void setStudent(Student student) {
        this.student = student;
        addListenerStudent();
    }

    public ViewStudent(Student student) {
        this.student = student;
        createPane();
        addListenerStudent();
    }
}