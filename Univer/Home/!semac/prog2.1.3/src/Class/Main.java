package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Main {
        public static void main(String[] argv) throws FileNotFoundException {
//#3
        HashSet<String> list = new HashSet<>();
        int k = 0;
        Scanner read = new Scanner(new FileReader("src//Class//text.txt"));
        while (read.hasNext()) {
            list.add(read.next().trim().replaceAll("\\.|,", "").toLowerCase());
            k++;
        }
        System.out.println("Множество: " + list);
        System.out.println("Исходное количество элементов: " + k);
        System.out.println("Итоговое количество элементов: " + list.size());
        System.out.println("Разность количества элементов: " + (k - list.size()));
        ArrayList<String> temp = new ArrayList<>();
        Collections.addAll(temp,"collection","represents","group","of","objects");
       if(list.containsAll(temp)){
           System.out.println("YES!");
       }
       else System.out.println("NO!");

    }
}
