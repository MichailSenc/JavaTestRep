package com.company;

public class Entity {
    private  String name;
    private double proceeds, expense;
//    public Entity(){
//        name = "";
//        proceeds = 0;
//        expense = 0;
//    }
    public Entity(String name, double proceeds, double expense){
        this.name = name;
        this.proceeds = proceeds;
        this.expense = expense;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setProceeds(double proceeds){
        this.proceeds = proceeds;
    }
    public double getProceeds() {
        return proceeds;
    }
    public void setExpense(double expense){
        this.expense = expense;
    }
    public double getExpense() {
        return expense;
    }
    public double profit(){
        return proceeds - expense;
    }
//    abstract protected double allExpense();
//    public double profit(){
//        return proceeds - allExpense();
//    }
    public String toString(){
        return String.format("%-20s  %10.2f", name, profit());
    }
}

