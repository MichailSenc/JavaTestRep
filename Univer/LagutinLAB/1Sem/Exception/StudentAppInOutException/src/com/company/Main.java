package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Student> listStud = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("student.txt"));
            String w;
            while ((w = reader.readLine()) != null) {
//                try {
                    String[] a = w.trim().split(" +");
                    if (a.length < 2) {
                        throw new Exception("No data");
                    }
                    Student st = new Student(a[0]);
                    for (int i = 1; i < a.length; i++) {
                        st.addRating(Integer.parseInt(a[i]));
                    }
                    listStud.add(st);
//                } catch (Exception ex) {
//                    System.out.println(ex.getMessage());
//                }
            }
            System.out.println(listStud);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");

        } catch (IOException ex) {
            System.out.println("Error data file");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
