import java.util.*;

public class main {
    public static void main(String[] argv) {
//#1
       /* ArrayList<Integer> test = new ArrayList();
        Collections.addAll(test, -2, -5, -2, -4, 3, -6, -2,
                -1, 5, 1, 1, 0, -1, 0, 3, -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11);
        //1 найти количество дней с отрицательной температурой
        long k = test.stream().filter(p -> p < 0).count();
        System.out.println(String.format("Количество дней с отрицательной температурой: %d", k));
        k = test.stream().filter(p -> p > 10).count();
        if (k == 0)
            System.out.println("Дней,c температурой выше 10 градусов не было");
        else
            System.out.println(String.format("Количество дней c температурой выше 10 градусов: %d", k));
        k = test.stream().limit(7).max(Integer::compareTo).get();
        System.out.println(String.format("Максимальная температура в первую неделю марта: %d", k));*/

//#2
        String text = "They used 233 features including 227 " +
                "stylometric features and six novel social network-specific features " +
                "like character-based ones numbers of alphabets, uppercase " +
                "characters, special characters, word-based ones the total number of " +
                "words, average word length, the number of words with 1 char, " +
                "syntactic ones numbers of punctuation marks and functional " +
                "words, the total number of sentences and many others";
        System.out.println("Количество es: " + Arrays.stream(text.split("[ ]+")).filter(s -> s.endsWith("es")).count());
        Arrays.stream(text.split("[ ]+")).sorted((s1, s2) -> Integer.compare(s1.length(), s2.length())).forEach(e -> System.out.print(" " + e));
        System.out.println("\nВсе похо " + text);
        ArrayList<String> test = new ArrayList();
        Collections.addAll(test, text.split("(-|\\+)?[^0-9]+"));
        int t = 0;
        for (Iterator<String> it = test.iterator(); it.hasNext(); )
            t += Integer.parseInt(it.next());
        System.out.println(t);
    }
}