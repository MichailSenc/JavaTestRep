package ru.ac.uniyar.apps.fxappone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Organization {
    private String name;
    private int personnel;
    private String holiday;
    private LocalDate date;
    private double drinkVolume;
    private ArrayList<Listener> listeners;

    public Organization(String name, int personnel, String holiday, LocalDate date, double drinkVolume){
        this.name = name;
        this.personnel =personnel;
        this.holiday = holiday;
        this.date = date;
        this.drinkVolume = drinkVolume;
        listeners = new ArrayList<Listener>();
    }
    public String getName() {
        return name;
    }
    public int getPersonnel(){
        return personnel;
    }
    public String getHoliday() {
        return holiday;
    }
    public LocalDate getDate () {
        return date;
    }
    public double getDrinkVolume() {
        return drinkVolume;
    }
    public void increaseDrinkVolume() {
        drinkVolume++;
        fire();
    }
    public void addListener(Listener l) {
        listeners.add(l);
    }
    public void deleteListener(Listener l) {
        listeners.remove(l);
    }

    private void fire() {
        for(Listener l: listeners)
            l.dataChanged();
    }

}
