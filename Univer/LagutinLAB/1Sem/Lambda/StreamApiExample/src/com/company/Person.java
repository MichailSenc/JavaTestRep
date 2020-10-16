package com.company;

public class Person implements Comparable<Person>
{
    public enum Sex {
        MAN,
        WOMEN
    }

    private String name;
    public String getName() { return name; }
    private int age;
    public int getAge() { return age; }
    private Sex sex;
    public Sex getSex() { return sex; }

    public Person(String name, int age, Sex sex) { this.name = name; this.age = age; this.sex = sex;}

    @Override
    public String toString() { return name + " (" + age + ")"; }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person))
            return false;
        return ((Person)obj).name.equals(name) && ((Person)obj).age == age;
    }

    @Override
    public int hashCode() { return name.hashCode() + age; }

    @Override
    public int compareTo(Person p) { return name.compareTo(p.name); }
}

