package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        listExample();
        stringExample();
        listObjectExample();
    }

    public static void listExample() {
        ArrayList<Integer> testValues = new ArrayList();
        Collections.addAll(testValues, 15, 1, 2, 100, 50);

        Optional<Integer> maxValue = testValues.stream().max(Integer::compareTo);
        System.out.println("MaxValue = " + maxValue.get());

        System.out.println("MinValue = " + testValues.stream().min(Integer::compareTo).get());

        Optional<Integer> maxValueOdd = testValues.stream().filter(p -> p % 2 != 0).max(Integer::compareTo);
        System.out.println("maxValueOdd = " + maxValueOdd.get());

        List<String> collection = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        System.out.println();
        collection.stream().map(String::toUpperCase).peek((e)
                -> System.out.print("," + e)).collect(Collectors.toList());
        System.out.println();

        List<String> newCollection =  collection.stream().filter(p->p.length()<4).collect(Collectors.toList());
        System.out.println(newCollection);

        String data = "1 2 3 4 5 6 7 8 9";
        System.out.println("Rezult = " + Arrays.stream(data.split("[ ]+")).map(s -> Integer.parseInt(s))
                .filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0));

        /*
        Stream<String> streamNumData = Arrays.stream(data.split("[ ]+"));
        Stream<Integer> streamNum = streamNumData.map(s -> Integer.parseInt(s));
        Integer sumOdd = streamNum.filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
        System.out.println("sumOdd = " + sumOdd);
         */

    }

    public static void stringExample() {
        String data = "Contains the collections framework, legacy collection classes, event model, date and time facilities, internationalization, and miscellaneous utility classes";
        Stream<String> streamStr = Arrays.stream(data.split("[, ]+"));
        long r = streamStr.filter(s -> s.startsWith("c")).count();
        System.out.println(r);

        System.out.println(Arrays.stream(data.split("[, ]+")).filter(s -> s.startsWith("c")).count());
    }

    public static void listObjectExample() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.add(new Person("Ivan", 31, Person.Sex.MAN));
        people.add(new Person("Petr", 45, Person.Sex.MAN));
        people.add(new Person("Sidor", 20, Person.Sex.MAN));

        System.out.println("Name = " + people.stream().max((p1, p2) -> p1.getAge() - p2.getAge()).get().getName());
        System.out.println("Name = " + people.stream().min((p1,p2) -> Integer.compare(p1.getAge(), p2.getAge())).get().getName());

        people.stream().sorted().forEach((p) -> System.out.print(" " + p));
        System.out.println();

        System.out.println(people.stream().filter((p) -> p.getAge() >= 18)
                .filter((p) -> (p.getSex() == Person.Sex.WOMEN && p.getAge() < 55)
                        || (p.getSex() == Person.Sex.MAN && p.getAge() < 60)).count());
    }
}
