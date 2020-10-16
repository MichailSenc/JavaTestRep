package myPoc;

import javafx.beans.property.*;

public class Human {
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty status;
    private IntegerProperty money;

    //NAME
    public StringProperty nameStringProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    //AGE
    public IntegerProperty ageIntegerProperty() {
        if (age == null) {
            age = new SimpleIntegerProperty();
        }
        return age;
    }

    //STATUS
    public StringProperty statusStringProperty() {
        if (status == null) {
            status = new SimpleStringProperty();
        }
        return status;
    }

    //MONEY
    public IntegerProperty moneyIntegerProperty() {
        if (money == null) {
            money = new SimpleIntegerProperty();
        }
        return money;
    }


    public Human(String name, int age, String status, int money) {
        setName(name);
        setAge(age);
        setMoney(money);
        setStatus(status);
    }

    public String findStatus() {
        String s = "";
        if (getAge() >= 0 && getAge() <= 11)  s += "детство ";
        if (getAge() > 11 && getAge() <= 19)  s += "юность ";
        if (getAge() > 19 && getAge() <= 35)  s += "молодость ";
        if (getAge() > 35) s += "зрелость ";
        if (getMoney() > 800000000) s += "миллиардер";
        else if (getMoney() > 100000000) s += "мультимиллионер";
        else if (getMoney() > 1000000) s += "миллионер";
        else if (getMoney() > 100000) s += "средний класс";
        else if (getMoney() > 10000) s += "достаток";
        else if (getMoney() > 1000) s += "бедняк";
        else if (getMoney() > 0) s += "банкрот";
        return s;
    }



    public final void setAge(int value) {
        ageIntegerProperty().set(value);
    }

    public final int getAge() {
        return ageIntegerProperty().get();
    }

    public final void setName(String value) {
        nameStringProperty().set(value);
    }

    public final String getName() {
        return nameStringProperty().get();
    }

    public final void setStatus(String value) {
        statusStringProperty().set(value);
    }

    public final String getStatus() { return statusStringProperty().get(); }

    public final void setMoney(int value) {
        moneyIntegerProperty().set(getMoney() + value);
    }

    public final int getMoney() {
        return moneyIntegerProperty().get();
    }


}
