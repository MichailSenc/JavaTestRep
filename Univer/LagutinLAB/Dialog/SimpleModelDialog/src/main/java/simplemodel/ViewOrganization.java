package simplemodel;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewOrganization {
    private Organization org;
    private GridPane grid;
    private Label drinkVol;
    private Text nameOrg;
    private Text holidayOrg;
    private Text dateOrg;

    private void createPane() {
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Font font = Font.font("Tahoma", FontWeight.NORMAL, 24);

        nameOrg = new Text(org.getName());
        nameOrg.setFont(font);
        GridPane.setHalignment(nameOrg, HPos.CENTER);
        grid.add(nameOrg, 0, 0, 2, 1);

        holidayOrg = new Text(org.getHoliday());
        holidayOrg.setFont(font);
        grid.add(holidayOrg, 0, 1);

        dateOrg = new Text(org.getHoliday());
        dateOrg.setFont(font);
        grid.add(dateOrg, 1, 1);

        Text drinkVolumeTitle = new Text("Drunk by one person");
        drinkVolumeTitle.setFont(font);
        grid.add(drinkVolumeTitle, 0, 2);

        drinkVol = new Label(Double.toString(org.getDrunkPerPerson()));
        drinkVol.setFont(font);
        grid.add(drinkVol, 1, 2);
    }

    private  void addListenersOrg(){
        nameOrg.textProperty().bind(org.nameProperty());
        holidayOrg.textProperty().bind(org.holidayProperty());
        dateOrg.textProperty().bind(org.dateProperty().asString());
//        org.drinkVolumeProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                drinkVol.setText(Double.toString(org.getDrunkPerPerson()));
//            }
//        });
        org.drinkVolumeProperty().addListener((observable, oldValue, newValue) ->
                drinkVol.setText(Double.toString(org.getDrunkPerPerson())));
        org.personnelProperty().addListener((observable, oldValue, newValue) ->
                drinkVol.setText(Double.toString(org.getDrunkPerPerson())));
    }

    public  GridPane getPane(){
        return  grid;
    }

    public void setOrganization (Organization org) {
        this.org = org;
        addListenersOrg();
    }

    public ViewOrganization(Organization org) {
        this.org = org;
        createPane();
        addListenersOrg();
    }
}

