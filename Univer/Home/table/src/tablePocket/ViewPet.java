package tablePocket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewPet {
    private Pet pet;
    private GridPane grid;
    private Text spec;
    private Text name;
    private Text age;
    private Text owner;


    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Font font = Font.font("Tahoma", FontWeight.NORMAL, 24);
        Text text;

        spec = new Text(pet.getSpecies());
        spec.setFont(font);
        grid.add(spec, 1, 0);

        text = new Text("Spec:");
        text.setFont(font);
        grid.add(text, 0, 0);

        name = new Text(pet.getName());
        name.setFont(font);
        grid.add(name, 1, 1);

        text = new Text("Name:");
        text.setFont(font);
        grid.add(text, 0, 1);

        age = new Text(Integer.toString(pet.getAge()));
        age.setFont(font);
        grid.add(age, 1, 2);

        text = new Text("Age:");
        text.setFont(font);
        grid.add(text, 0, 2);

        owner = new Text(pet.getOwner());
        owner.setFont(font);
        grid.add(owner, 1, 3);

        text = new Text("Owner:");
        text.setFont(font);
        grid.add(text, 0, 3);

    }

    private  void addListenersPet(){
        spec.textProperty().bind(pet.speciesProperty());
        name.textProperty().bind(pet.nameProperty());
        age.textProperty().bind(pet.ageProperty().asString());
        owner.textProperty().bind((pet.ownerProperty()));
    }

    public  GridPane getPane(){
        return  grid;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        addListenersPet();
    }

    public ViewPet(Pet pet) {
        this.pet = pet;
        createPane();
        addListenersPet();
    }
}



