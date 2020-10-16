package com.company;

public class Person {

    private String name;

    public String getName() {
        return name;
    }
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws PersonAgeException{
        if(age<0)
            throw new PersonAgeException(age);
	   this.age = age;
    }

    public Person(String name, int age) throws Exception{
        this.name = name;
        if(age<0)
            throw new Exception("Error age");
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

class PersonAgeException extends Exception {
    int ageError;
    public PersonAgeException(int ageError){
        this.ageError = ageError;
    }
    public int getValueError(){
        return ageError;
    }
}

