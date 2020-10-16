package tableviewexample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Organization {

    private SimpleStringProperty name;
    private SimpleStringProperty bossName;
    private SimpleIntegerProperty personnel;

    public Organization(String name, String bossName, int personnel) {
        this.name = new SimpleStringProperty(name);
        this.bossName = new SimpleStringProperty(bossName);
        this.personnel = new SimpleIntegerProperty(personnel);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBossName() {
        return bossName.get();
    }

    public void setBossName(String bossName) {
        this.bossName.set(bossName);
    }

    public int getPersonnel() {
        return personnel.get();
    }

    public void setPersonnel(int personnel) {
        this.personnel.set(personnel);
    }

}

