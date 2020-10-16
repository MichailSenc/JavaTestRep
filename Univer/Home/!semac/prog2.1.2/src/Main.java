import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
//#2
        LinkedList<Double> list = new LinkedList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введике количество элементов");
        int k = scan.nextInt();
        System.out.println(String.format("Введите %d элементов", k));
        for (int i=0;i<k;i++){
            //list.add(scan.nextDouble());
            String s = String.format("%.2f",(Math.random() * 100 - 50));
            list.add(Double.parseDouble(s.replaceAll(" ","").replace(',','.')));
        }
        System.out.println("Исходный список:");
        System.out.println(list.toString());
        Collections.sort(list);     //сортировка по возрастанию
        Collections.reverse(list);  //поменять порядок элементов на обратный (сортировка по убыванию)
        System.out.println("Список, отсортированный по убыванию:");
        System.out.println(list.toString());
        int position = list.indexOf(0.0);
        if (position < 0)
            System.out.println("В данном списке нет ненулевых элементов");
        else
            System.out.println("В данном списке есть ненулевые элементы");
        double average = (list.getFirst() + list.getLast())/2;  //найти средне арифметическое между первым и последним элементами
        int middle = list.size()/2;                             //индекс середины списка
        list.add(middle,average);
        System.out.println("Список с средне арифметическим элементом в середине:");
        System.out.println(list.toString());

    }

}