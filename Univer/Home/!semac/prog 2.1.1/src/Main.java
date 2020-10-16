import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] argv) {
//#1
        ArrayList<Integer> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введике количество элементов");
        int k = scan.nextInt();
        for (int i = 0; i < k; i++) {
            list.add((int) (Math.random() * 200 - 100));
        }
        System.out.println("Исходный список: " + list);
        System.out.println("Максимальный элемент: " + Collections.max(list));
        Collections.sort(list);
        System.out.println("Список, отсортированный по возрастанию " + list);
        System.out.println("Номер последнего элемента, равного максимальному: " + list.indexOf(Collections.max(list)));
        list.removeIf(new Predicate<Integer>()  {
            @Override
            public boolean test(Integer integer) {
                return integer < 0;
            }
        });
        System.out.println("Список без отрицательных элементов: " + list);
    }
}
