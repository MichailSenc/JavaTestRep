package myClass;

import myInterface.Game;

public class Cat extends Animal implements Game {
    public Cat(String name, int age){
        super(name, age);
    }

    public String speak(){
        return "...мяу...";
    }

    @Override
    public void play(){
        System.out.println("укаска");
    }
}
