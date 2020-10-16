package myClass;

import myException.SphereException;

import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.io.File;

public class Main {
    public static void main(String[] argv) throws SphereException {
        int x, y, z;
        double r,k;
        try {
            //считывание информации о сфере
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите координаты сферы.");
            if (sc.hasNextInt()) x = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            if (sc.hasNextInt()) y = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            if (sc.hasNextInt()) z = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            Sphere ap1 = new Sphere(x, y, z, 0);//"Cоздание" сферы
            System.out.println("Введите координаты точки, лежащей на сфере.");
            if (sc.hasNextInt()) x = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            if (sc.hasNextInt()) y = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            if (sc.hasNextInt()) z = sc.nextInt();
            else throw new SphereException("Координаты сферы - число");
            ap1.setR(ap1.range(x, y, z));
            System.out.printf(ap1.toString(), ap1.getX(), ap1.getY(), ap1.getZ(), ap1.getR());

            File f = new File("C:\\Users\\Михаил\\IdeaProjects\\sphere\\src\\koord.txt");

            int size = 0;
            //создание коллекции
            ArrayList<Sphere> collection = new ArrayList<Sphere>();
            Scanner reader = new Scanner(new FileReader("C:\\Users\\Михаил\\IdeaProjects\\sphere\\src\\koord.txt"));
            //считывание координат из файла
            while (reader.hasNext()) {
                x = reader.nextInt();
                y = reader.nextInt();
                z = reader.nextInt();
                r = ap1.range(x, y, z); 
                collection.add(new Sphere(x, y, z, r));
                size++;
            }
            SphereComparator radius = new SphereComparator();
            Comparator<Sphere> comp = radius;
            Collections.sort(collection, comp);
            //Вывод
            while (true) {
                boolean flag = false;
                System.out.println("\n\tточки на сфере: ");
                for (int i = 0; i < size; i++) {
                    if (collection.get(i).sign_of_difference(ap1.getR()) == 0) {
                        System.out.println(collection.get(i).string());
                        flag = true;
                    }
                }
                if (!flag) System.out.println("NO");
                else flag = false;
                System.out.println("\tточки внутри сферы: ");
                for (int i = 0; i < size; i++) {
                    if (collection.get(i).sign_of_difference(ap1.getR()) < 0) {
                        System.out.println(collection.get(i).string());
                        flag = true;
                    }
                }
                if (!flag) System.out.println("NO");
                else flag = false;
                System.out.println("\tточки вне сферы: ");
                for (int i = 0; i < size; i++) {
                    if (collection.get(i).sign_of_difference(ap1.getR()) > 0) {
                        System.out.println(collection.get(i).string());
                        flag = true;
                    }
                }
                if (!flag) System.out.println("NO");
                else flag = false;
                System.out.println("\nВведите множитель радиуса:");
                if(sc.hasNextDouble()) k=sc.nextDouble();
                else break;
                r=ap1.getR()*k;
                System.out.println(String.format("Радиус: %.2f --> %.2f",ap1.getR(),r));
                ap1.setR(r);
            }
        } catch (IOException e) {
            System.out.println("Error data");
        } catch (NoSuchElementException e) {//Количество чисел в файле должно быть кратно 3
            System.out.println("Не достаточно данных в файле,либо они некорректны");
        } catch (SphereException e) {
            System.out.println("\n" + e);
        }
    }
}