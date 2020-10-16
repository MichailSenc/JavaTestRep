package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> phoneBook = new HashMap<String, Integer>();

        phoneBook.put("John", 234154);
        phoneBook.put("Peter", 320967);
        phoneBook.put("Mary", 251718);
        phoneBook.put("Ann", 657112);
        phoneBook.put("Mary", 251719);

        System.out.println("Phone Mary - " + phoneBook.get("Mary"));
        System.out.println("Phone Alex - " + phoneBook.get("Alex"));

        System.out.println("\nSIZE: " + phoneBook.size());

        System.out.println("\nKEYS:");
        for (String s : phoneBook.keySet()) {
            System.out.println(s);
        }

        System.out.println("\nVALUES:");
        for (int val : phoneBook.values()) {
            System.out.println(val);
        }

        System.out.println("\nALL:");
        for (Map.Entry entry : phoneBook.entrySet()) {
            System.out.println("Phone " + entry.getKey() + " - " + entry.getValue());
        }

        phoneBook.remove("Ann");

        System.out.println("\nALL:");
        for (Map.Entry entry : phoneBook.entrySet()) {
            System.out.println("Phone " + entry.getKey() + " - " + entry.getValue());
        }

    }
}
