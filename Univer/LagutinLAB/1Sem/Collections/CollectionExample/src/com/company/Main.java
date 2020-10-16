package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
//        arrayListInteger();
//        listPerson();
//        linkedListInteger();
//        hashSetExample();
        collectionsCompare1();
        collectionsCompare2();
    }
    public  static  void arrayListInteger(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 11, 77, 22, 98, 33, 12, 13, 44, 55);
        System.out.println(list);
        list.add(66);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer%2 == 0;
            }
        });
        System.out.println(list);
        int k=0;
        for(int i=1; i<list.size(); i++)
            if (list.get(i)>list.get(i-1))
                k++;

        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.rotate(list, 3);
        System.out.println(list);
        System.out.println("\nmin: " + Collections.min(list));
        System.out.println("\nmax: " + Collections.max(list));

        int min = Collections.min(list);
        //list.remove(min);
        list.remove(Integer.valueOf(min));
        System.out.println(list);
    }

    public  static  void listPerson(){
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Ann", 25));
        String name = "Pit";
        int age = 13;
        Person x= new Person(name, age);
        persons.add(x);
        System.out.println(persons);
        int indexPit = persons.indexOf(new Person("Pit", 14));
        System.out.println(indexPit);
        persons.add(new Person("Bill", 28));
        System.out.println(persons);
        Collections.sort(persons);
        System.out.println(persons);
        Person max = Collections.max(persons);
        System.out.println(max);
        Collections.sort(persons, new PersonAgeComparator());
        System.out.println(persons);
    }

    public  static  void linkedListInteger(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        Collections.addAll(list, 1, 2, 3, 4, 5);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        Collections.rotate(list, 3);
        System.out.println(list);
        System.out.println("\nmin: " + Collections.min(list));
        System.out.println("\nmax: " + Collections.max(list));
        int min = Collections.min(list);
        //list.remove(min);
        list.remove(Integer.valueOf(min));
        System.out.println(list);
        list.addFirst(11);
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
    }

    public  static  void hashSetExample(){
        Set<String> popularCities = new HashSet<>();

        System.out.println("Is popularCities set empty? : " + popularCities.isEmpty());

        popularCities.add("London");
        popularCities.add("New York");
        popularCities.add("Paris");
        popularCities.add("Dubai");
        popularCities.add("New York");

        System.out.println(popularCities);

        System.out.println("Number of cities in the HashSet " + popularCities.size());

        String cityName = "Paris";
        if(popularCities.contains(cityName)) {
            System.out.println(cityName + " is in the popular cities set.");
        } else {
            System.out.println(cityName + " is not in the popular cities set.");
        }
    }

    public  static  void collectionsCompare1() {
        try {
            ArrayList<Integer> collection1 = new ArrayList<Integer>();
            HashSet<Integer> collection2 = new HashSet<Integer>();
            TreeSet<Integer> collection3 = new TreeSet<Integer>();
            Scanner in = new Scanner(new File("data.txt"));
            while (in.hasNextInt()) {
                int x = in.nextInt();
                collection1.add(x);
                collection2.add(x);
                collection3.add(x);
            }
            System.out.println("ArrayList for Integer");
            System.out.println(collection1);
            System.out.println("HashSet for Integer");
            System.out.println(collection2);
            System.out.println("TreeSet for Integer");
            System.out.println(collection3);
        } catch (IOException e) {
            System.out.println("In/out error");
        }
    }

    public  static  void collectionsCompare2(){
        try {
            ArrayList<Point> collection1 = new ArrayList<Point>();
            HashSet<Point> collection2 = new HashSet<Point>();
            TreeSet<Point> collection3 = new TreeSet<Point>();
            Scanner in = new Scanner(new File("dataPoint.txt"));
            while (in.hasNextInt()) {
                double x = in.nextInt();
                if (in.hasNextInt()) {
                    double y = in.nextInt();
                    collection1.add(new Point(x, y));
                    collection2.add(new Point(x, y));
                    collection3.add(new Point(x, y));
                }
            }
            System.out.println("ArrayList for Point");
            System.out.println(collection1);
            System.out.println("HashSet for Point");
            System.out.println(collection2);
            System.out.println("TreeSet for Point");
            System.out.println(collection3);
        } catch (IOException e) {
            System.out.println("In/out points error");
        }

    }

}
