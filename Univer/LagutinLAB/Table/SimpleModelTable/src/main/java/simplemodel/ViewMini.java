package simplemodel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class ViewMini {

    private Organization org;
    private VBox pane;
    private Label personnelOrg;
    private Label nameOrg;

    private void createPane() {
        pane = new VBox(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(25, 25, 25, 25));

        nameOrg = new Label();
        nameOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        nameOrg.setMinWidth(100);
        nameOrg.setAlignment(Pos.CENTER);

        personnelOrg = new Label();
        personnelOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        pane.getChildren().addAll(nameOrg, personnelOrg);
    }

    private  void addListenersOrg(){
        nameOrg.textProperty().bind(org.nameProperty());
        personnelOrg.textProperty().bind(org.personnelProperty().asString());
    }

    public void setOrganization(Organization org) {
        this.org = org;
        addListenersOrg();
    }

    public VBox getPane(){
        return pane;
    }

    public ViewMini(Organization org) {
        if (org == null)
            createPane();
        else {
            this.org = org;
            createPane();
            addListenersOrg();
        }
    }
}
