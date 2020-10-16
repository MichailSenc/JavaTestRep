package myPoc;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Student {
    private StringProperty name;
    private ObjectProperty<Subject> first;
    private ObjectProperty<Subject> second;
    private ObjectProperty<Subject> third;
    private ObjectProperty<ArrayList<Subject>> list;
    //Поседнее - это список всех факультетов, его можно будет изменить(доп задание)

//LIST
    public ObjectProperty<ArrayList<Subject>> listProperty(){
        if (list == null){
            list = new SimpleObjectProperty<>();
        }
        return list;
    }

    public final void setList(ArrayList<Subject> value) { listProperty().set(value); }

    public final ArrayList<Subject> getList() { return listProperty().get(); }

//NAME
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

//FIRST
    public ObjectProperty<Subject> firstProperty() {
        if (first == null) {
            first = new SimpleObjectProperty<>();
        }
        return first;
    }

    public final void setFirst(Subject value) {
        firstProperty().set(value);
    }

    public final Subject getFirst() {
        return firstProperty().get();
    }

//SECOND
    public ObjectProperty<Subject> secondProperty() {
        if (second == null) {
            second = new SimpleObjectProperty<>();
        }
        return second;
    }

    public final void setSecond(Subject value) { secondProperty().set(value); }

    public final Subject getSecond() { return secondProperty().get(); }

//THIRD
    public ObjectProperty<Subject> thirdProperty() {
        if (third == null) {
            third = new SimpleObjectProperty<>();
        }
        return third;
    }

    public final void setThird(Subject value) {
        thirdProperty().set(value);
    }

    public final Subject getThird() {
        return thirdProperty().get();
    }


    public int totalScore(){
        return getFirst().getScore() + getSecond().getScore() + getThird().getScore();
    }

    public ArrayList<Subject> possibleSubject(){
        return new ArrayList<>(getList().stream().filter(element ->
                element.getScore() <= totalScore()).collect(Collectors.toList()));
    }

    public int subSize(){
        return possibleSubject().size();
    }

    public String listToString(){
        String s = "";
        ArrayList<Subject> subjects = possibleSubject();
        if (subjects.size()==0) return "Увы, но у вас не достатоно баллов";
        Iterator<Subject> it = subjects.iterator();
        while (it.hasNext()) s += String.format("%s\n", it.next());
        return s;
    }

    public String subjectToString(){
        return String.format("%s\n%s\n%s",getFirst(),getSecond(),getThird());
        //return getFirst() + "\n" + getSecond() + "\n" + getThird();
    }

    public Student(String name, int first, int second, int third){
        setName(name);
        setFirst(new Subject("матендра",first));
        setSecond(new Subject("пыхтелка",second));
        setThird(new Subject("очумелые ручки",third));
        ArrayList<Subject> array = new ArrayList<>();
        array.add(new Subject("Прикладной мыслитель", 210));
        array.add(new Subject("Фундаментальный лентяй", 234));
        array.add(new Subject("Кабалистика и ворожба", 203));
        setList(array);
    }

    public Student(){
        setName("...");
        setFirst(new Subject("матендра",1));
        setSecond(new Subject("пыхтелка",2));
        setThird(new Subject("очумелые ручки",3));
        ArrayList<Subject> array = new ArrayList<>();
        array.add(new Subject("Прикладной мыслитель", 210));
        array.add(new Subject("Фундаментальный лентяй", 234));
        array.add(new Subject("Кабалистика и ворожба", 203));
    }

}




