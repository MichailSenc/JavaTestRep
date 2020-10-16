package tablePocket;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pet {
    private StringProperty species;
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty owner;

    public StringProperty speciesProperty() {
        if (species == null) {
            species = new SimpleStringProperty();
        }
        return species;
    }

    public final void setSpecies(String value) {
        speciesProperty().set(value);
    }

    public final String getSpecies() {
        return speciesProperty().get();
    }

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public final void setName(String value) {
        nameProperty().set(value);
    }

    public final String getName() {
        return nameProperty().get();
    }

    public IntegerProperty ageProperty() {
        if (age == null) {
            age= new SimpleIntegerProperty();
        }
        return age;
    }

    public final void setAge(int value) {
        ageProperty().set(value);
    }

    public final int getAge() {
        return ageProperty().get();
    }

    public StringProperty ownerProperty() {
        if (owner == null) {
            owner = new SimpleStringProperty();
        }
        return owner;
    }

    public final void setOwner(String value) {
        ownerProperty().set(value);
    }

    public final String getOwner() {
        return ownerProperty().get();
    }

    public Pet(){
        speciesProperty().set("spec");
        nameProperty().set("name");
        ageProperty().set(0);
        ownerProperty().set("owner");
    }


}
