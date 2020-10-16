package com.company;

public class Businessman extends Entity {
    public Businessman(String name, double proceeds, double expense){
        super(name, proceeds, expense);
//        setName(name);
//        setProceeds(proceeds);
//        setExpense(expense);
    }
    public double pay(){
        return 0.5*profit();
    }
//    @Override
//    public double allExpense(){
//        return getExpense();
//    }
}

