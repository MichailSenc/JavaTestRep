package com.company;

public class Student{

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

    public Student(String name, int rating[]) throws Exception {
        if(rating.length>MAXLEN)
            throw new Exception("Length>MAX");
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
            try {
                if(!(value > 1 && value < 6))
                    throw new Exception("Incorrect rating "+value);
                else if(size >= MAXLEN)
                    throw new Exception("Incorrect size rating ");
                else {
                    rating[size] = value;
                    size++;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
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

}

