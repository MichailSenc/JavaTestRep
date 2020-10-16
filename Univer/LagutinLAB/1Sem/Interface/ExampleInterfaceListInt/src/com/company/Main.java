package com.company;

public class Main {

    public static void main(String[] args) {

        MyArray superArray = new Array();
        superArray = new LinkedList();
        method(superArray);
        superArray = new Array();
        method(superArray);
    }
    public static void method(MyArray a) {
        a.add(5);
        a.add(2);
        a.add(4);
        a.add(3);
        System.out.println();
        System.out.println(a.get(2));
        a.printKonsole();
    }
}
