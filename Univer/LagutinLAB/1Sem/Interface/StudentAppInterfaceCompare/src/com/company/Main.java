package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> listStud = new ArrayList<>();
        System.out.println("Enter data: ");
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();
        while (!str.isEmpty()) {
            String a[] = str.trim().split(" +");
            Student t = new Student(a[0]);
            for (int i = 1; i < a.length; i++) {
                t.addRating(Integer.parseInt(a[i]));
            }
            listStud.add(t);
            str = sc.nextLine();
        }

        Collections.sort(listStud);
        System.out.println(listStud);

        System.out.println("Max ball - "+Collections.max(listStud, new StudentSumBallComparator()));


    }
}
