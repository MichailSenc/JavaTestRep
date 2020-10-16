package com.company;

import java.util.Comparator;

public class Person implements Comparable<Person> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        return ((Person) obj).name.equals(name) && ((Person) obj).age == age;
    }

    @Override
    public int compareTo(Person p) {
        return name.compareTo(p.name);
    }
}

class PersonAgeComparator implements Comparator<Person>
{
    public int compare(Person p1, Person p2)
    {
	return Integer.compare(p1.getAge(), p2.getAge());
    }
}

