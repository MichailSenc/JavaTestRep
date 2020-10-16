package myClass;

import myInterface.Game;

public class Dog extends Animal implements Game {
    String breed;
    public Dog(String name,int age, String breed){
        super(name, age);
        this.breed=breed;
    }
    public String toString(){
        return super.toString() + "  " + breed;
    }

    public String speak(){
        return "...gay...";
    }

    @Override
    public void play(){
        System.out.println("мячь");
    }
}
