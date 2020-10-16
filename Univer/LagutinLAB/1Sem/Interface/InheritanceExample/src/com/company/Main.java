package com.company;

public class Main {

    public static void main(String[] args) {

        Entity ent = new Entity("NoName", 100, 50);
        System.out.println(ent.getName());
        System.out.println(ent.profit());

        Businessman bm1 = new Businessman("Ivan", 100, 50);
        System.out.println(bm1.getName());
        System.out.println(bm1.profit());
        System.out.println(bm1.pay());

        Organization org = new Organization("ABC", 1000, 300, 10, 20);
        System.out.println(org.getName());
        System.out.println(org.profit());
        System.out.println(org.getSalary());

        Entity[] a = new Entity[5];
        a[0] = new Businessman("Ann", 200, 140);
        a[1] = new Organization("Concern", 35000, 14000, 100, 25);
        a[2] = org;
        a[3] = bm1;
        a[4] = new Businessman("Sidor", 125, 61);

        System.out.println();
        for (Entity e : a){
            System.out.println(e.getName());
            System.out.println("Entity: " + (e instanceof Entity));
            System.out.println("Businessman: " + (e instanceof Businessman));
            System.out.println("Organization: " + (e instanceof Organization));
        }

        register(a);
    }

    public static void register(Entity[] list) {
        double sum = 0;
        System.out.println();
        for (Entity e : list) {
            System.out.println(e);
            sum += e.profit();
        }
        System.out.printf("%-20s  %10.2f", "Total:", sum);
        System.out.println();
    }

}
