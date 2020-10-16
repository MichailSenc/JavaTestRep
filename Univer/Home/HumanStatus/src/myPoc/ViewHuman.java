package myPoc;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class ViewHuman {
    private Human human;
    private GridPane grid;
    private Label moneyVol;

    Text name, age , status;

    private void createPane(){
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text titleName = new Text("Имя: ");
        titleName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titleName, 0, 0);

        name = new Text(human.getName());
        name.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        name.textProperty().bind(human.nameStringProperty());
        GridPane.setHalignment(name, HPos.CENTER);
        grid.add(name, 1, 0);

        Text ageTitle = new Text("Возраст: ");
        ageTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(ageTitle, 0, 1);

        age = new Text(Integer.toString(human.getAge()));
        age.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        age.textProperty().bind(human.ageIntegerProperty().asString());
        GridPane.setHalignment(age, HPos.CENTER);
        grid.add(age, 1, 1);

        Text statusTitle = new Text("Your status: ");
        statusTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(statusTitle, 0, 2);

        status = new Text(human.findStatus());
        status.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        status.textProperty().bind(human.statusStringProperty());
        GridPane.setHalignment(status, HPos.CENTER);
        grid.add(status, 1, 2);

        Text moneyTitle=new Text("Деньги: ");
        moneyTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
        grid.add(moneyTitle,0,3);

        moneyVol=new Label();
        moneyVol.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
        moneyVol.textProperty().bind(human.moneyIntegerProperty().asString());
        grid.add(moneyVol,1,3);
    }

    public  GridPane getPane(){
        return  grid;
    }

    public ViewHuman(Human human) {
        this.human = human;
        createPane();
    }
}