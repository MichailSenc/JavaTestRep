package com.company;

import java.util.Comparator;

public class Student implements Comparable<Student>{

    private String name;
    private final int MAXLEN = 40;
    private int rating[] = new int[MAXLEN], size;

    public Student() {
        name = "";
        size = 0;
    }

    public Student(String name) {
        size = 0;
        this.name = name;
    }

    public Student(String name, int rating[]) {
        size = rating.length;
        System.arraycopy(rating, 0, this.rating, 0, size);
        this.name = name;
    }

    public Student(Student student) {
        size = student.size;
        System.arraycopy(student.rating, 0, this.rating, 0, size);
        this.name = student.name;
    }

    public String getName() {
        return new String(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRating(int value) {
        if (value > 1 && value < 6 && size < MAXLEN) {
            rating[size] = value;
            size++;
        }
    }

    private boolean isExcellent() {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (rating[i] != 5) {
                return false;
            }
        }
        return true;
    }

    private boolean isLoser() {
        for (int i : rating) {
            if (i == 2) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if (isExcellent()) {
            return name + " - Excellent";
        }
        if (isLoser()) {
            return name + " - Loser";
        }
        return name + " - Normal";
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }

    public int summBall(){
        int s=0;
        for(int b:rating)
            s+=b;
        return s;
    }
}

class StudentSumBallComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        //return o1.summBall()-o2.summBall();
        return Integer.compare(o1.summBall(),o2.summBall());
    }

}

