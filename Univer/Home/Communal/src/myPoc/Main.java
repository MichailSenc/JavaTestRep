package myPoc;

import myException.AccountException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
     public static void main(String[] argv) throws FileNotFoundException, AccountException {
        try {
            Scanner read = new Scanner(new FileReader("src//myPoc//data.txt"));
            Scanner console = new Scanner(System.in);

            ArrayList<Account> com = new ArrayList<>(); //Список
            Iterator<Account> iterator = com.iterator();

            String s = "", type;
            boolean key = true;
            int t;
            long kel;

            while (read.hasNext()) {//Считывание данных из файла
                s = read.nextLine();
                String string[] = s.split("[^а-яА-Я1-90]+");
                s = "";
                Account a1 = new Account();
                a1.setFullName(new FullName(string[0], string[1], (string[2])));
                a1.setDebt_count(Integer.parseInt(string[3]));
                a1.setPaid_count(Integer.parseInt(string[4]));
                for (int i = 5; i < string.length; i++)  s += string[i] + " ";
                a1.setPay_type(s);
                a1.upCase();
                com.add(a1);
                if (string[0] == "" || string[1] == "" || string[2] == "" || s.trim() == "")
                    throw new AccountException("В файле не достаточно данных");
            }

            System.out.println("В представленном меню выберите команду: ");
            s = "\t1 - добавление платежа \n\t2 - удаление платежа \n\t3 - вывод на экран всех счетов по имени " +
                    "человека с подсчетом общей суммы платежей \n\t4 - вывод на экран списка должников, " +
                    "отсортированного по алфавиту \n\t5 - вывод на экран списка должников с заданным диапазоном " +
                    "размера долга \n\t6 - оплата выбранного счёта";
            System.out.println(s);


            while (key) {
                System.out.print("Введите команду: ");
                t = console.nextInt();
                switch (t) {
                    case 1: {//добавление платежа
                        System.out.println("Введите ФИО, сумму, внесённую оплату и тип платежа");
                        FullName fullName = new FullName(console.next(), console.next(), console.next());
                        fullName.upCase();
                        com.add(new Account(fullName, console.nextInt(), console.nextInt(), console.nextLine().trim().toLowerCase()));
                        System.out.println("Успех!");
                        break;
                    }
                    case 2: {//удаление платежа
                        System.out.println("Введите ФИО и тип платежа");
                        FullName a1 = new FullName(console.next(), console.next(), console.next());
                        type = console.nextLine().trim().toLowerCase();
                        String finalType = type;
                        a1.upCase();
                        if (com.removeIf((e -> e.getFullName().equals(a1) && e.getPay_type().equals(finalType))))
                            System.out.println("Счёт, с указанными параметрами был успешно удалён");
                        else
                            System.out.println("Error! Совпадений не найдено. ");
                        break;
                    }
                    case 3: {//вывод на экран всех счетов по имени человека с подсчетом общей суммы платежей
                        System.out.print("Введите Имя: ");
                        String s1 = console.next();
                        int sum = 0;
                        List<Account> list = com.stream().filter(element -> element.getFullName().getName().equals(s1))
                                .peek(System.out::println).collect(Collectors.toList());
                        if(list.size()==0){
                            System.out.println("Совпадений не найдено.");
                            break;
                        }
                        Iterator <Account> it = list.iterator();
                        while (it.hasNext()) sum += it.next().getPaid_count();
                        System.out.println("Общая сумма платежей " + sum);
                        break;
                    }
                    case 4: {//вывод на экран списка должников, отсортированного по алфавиту
                        com.stream().filter(Account::is_debtor).sorted().forEach(System.out::println);
                        break;
                    }
                    case 5: {//вывод на экран списка должников с заданным диапазоном размера долга
                        System.out.print("Введите диапозон размера долга.\nMIN:");
                        int min = console.nextInt();
                        System.out.print("MAX:");
                        int max = console.nextInt();
                        if (min>max || min<0 ||max <0) throw new AccountException("min,max - должны иметь неотрицательное значение, где max>min");
                        kel = com.stream().filter(e -> e.obligation(min, max)).sorted().peek(System.out::println).count();
                        if (kel==0) System.out.println("Совпадений не найдено!");
                        break;
                    }
                    case 6: {//оплата выбранного счёта
                        System.out.println("Введите ФИО и тип платежа");
                        FullName fullName = new FullName(console.next(), console.next(), console.next());
                        fullName.upCase();
                        type = console.nextLine().trim().toLowerCase();
                        String finalType1 = type;
                        kel = com.stream().filter(e -> e.getFullName().equals(fullName) && e.getPay_type().equals(finalType1)).count();
                        if (kel == 0) {
                            System.out.println("Совпадений не найдено, повторите попытку");
                            break;
                        }
                        System.out.println("Оплатите счет.");
                        int k1 = console.nextInt();
                        iterator = com.iterator();
                        while (iterator.hasNext()) {
                            Account element = iterator.next();
                            if (element.getFullName().equals(fullName) && element.getPay_type().equals(finalType1))
                                element.setPaid_count(element.getPaid_count() + k1);
                        }
                        System.out.println("Успех!");
                        break;
                    }
                    case 7: {//вывод меню
                        System.out.println(s);
                        break;
                    }
                    case 100: {//завершение работы
                        key = false;
                        break;
                    }
                    default: {
                        System.out.println("Некорректный ввод! Повторите попытку.");
                        break;
                    }
                }
            }
            //Не забыть внести изменения!!!
            FileOutputStream out = new FileOutputStream("src//myPoc//data.txt", false); // вывод в файл
            iterator = com.iterator();
            while (iterator.hasNext()) {
                Account element = iterator.next();
                byte[] buffer = (element.toString() + '\n').getBytes();
                out.write(buffer, 0, buffer.length);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка! Некорректный ввод. " + e);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! В файле некорректные данные. " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("В файле недостаточно данных. " + e);
        } catch (AccountException e) {
            System.out.println(e);
        }
            
    }
}