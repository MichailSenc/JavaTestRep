package simplemodel;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Organization  {

    private StringProperty name;

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

    private IntegerProperty personnel;

    public IntegerProperty personnelProperty() {
        if (personnel == null) {
            personnel = new SimpleIntegerProperty();
        }
        return personnel;
    }

    public final void setPersonnel(int value) {
        personnelProperty().set(value);
    }

    public final int getPersonnel() {
        return personnelProperty().get();
    }

    private StringProperty holiday;

    public StringProperty holidayProperty() {
        if (holiday == null) {
            holiday = new SimpleStringProperty();
        }
        return holiday;
    }

    public final void setHoliday(String value) {
        holidayProperty().set(value);
    }

    public final String getHoliday() {
        return holidayProperty().get();
    }

    private ObjectProperty <LocalDate> date;

    public ObjectProperty <LocalDate> dateProperty() {
        if (date == null) {
            date = new SimpleObjectProperty<>();
        }
        return date;
    }

    public final void setDate(LocalDate value) {
        dateProperty().set(value);
    }

    public final LocalDate getDate() {
        return dateProperty().get();
    }

    private DoubleProperty drinkVolume;

    public DoubleProperty drinkVolumeProperty() {
        if (drinkVolume == null) {
            drinkVolume = new SimpleDoubleProperty();
        }
        return drinkVolume;
    }

    public final void setDrinkVolume(Double value) {
        drinkVolumeProperty().set(value);
    }

    public final Double getDrinkVolume() {
        return drinkVolumeProperty().get();
    }

    public Organization(String name, int personnel, String holiday, LocalDate date, double drinkVolume) {
        nameProperty().set(name);
        personnelProperty().set(personnel);
        holidayProperty().set(holiday);
        dateProperty().set(date);
        drinkVolumeProperty().set(drinkVolume);
    }

    public double getDrunkPerPerson() {
        return drinkVolumeProperty().get() / personnelProperty().get();
    }

}

