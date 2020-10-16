package ru.ac.uniyar.apps.fxappone;

import java.time.format.DateTimeFormatter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewOrganization
        implements Listener {

    private Organization org;
    private GridPane grid;
    private Label drinkVol;

    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text nameOrg = new Text(org.getName());
        nameOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setHalignment(nameOrg, HPos.CENTER);
        grid.add(nameOrg, 0, 0, 2, 1);

        Text holidayOrg = new Text(org.getHoliday() + " - " + org.getDate().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));
        holidayOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(holidayOrg, 0, 1, 2, 1);

        Text drinkVolumeTitle = new Text("Drink volume");
        drinkVolumeTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(drinkVolumeTitle, 0, 2);

        drinkVol = new Label(Double.toString(org.getDrinkVolume()));
        drinkVol.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(drinkVol, 1, 2);
    }

    public  GridPane getPane(){
        return  grid;
    }

    public void setOrganization(Organization org) {
        this.org = org;
        this.org.addListener(this);
    }

    public ViewOrganization(Organization org) {
        setOrganization(org);
        createPane();
    }

    @Override
    public void dataChanged() {
        drinkVol.setText(Double.toString(org.getDrinkVolume()));
    }
}
