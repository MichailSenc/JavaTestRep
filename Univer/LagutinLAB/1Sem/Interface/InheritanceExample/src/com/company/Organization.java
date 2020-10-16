package com.company;

public class Organization extends Entity {
    private int personal;
    private double salary;
    public Organization(String name, double proceeds, double expense, int personal, double salary){
        super(name, proceeds, expense);
        this.personal = personal;
        this.salary = salary;
    }
    public void setPersonal (int personal) {
        this.personal = personal;
    }
    public int getPersonal() {
        return personal;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }
    private double pay(){
        return personal*salary;
    }
    @Override
    public double profit() {
//        return getProceeds() - getExpense() - pay();
        return super.profit() - pay();
    }
//    @Override
//    public double allExpense(){
//        return getExpense()-pay();
//    }
}

