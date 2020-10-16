package myClass;
import myExeption.MissLenghtException;

import java.util.InputMismatchException;
import java.util.Scanner;
public class MainClass {
    public static void main(String[] argv) {
        try {
            int arr[][], size = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите размерность матрицы: ");
            size = sc.nextInt();
            arr = new int[size][size];
            SpiralMatrix ap1 = new SpiralMatrix(arr, size);
            ap1.fullArr();
            ap1.printMatrix();
        } catch (MissLenghtException e) {
            System.out.println("Выход за границы массива");
            return;
        }
        catch (InputMismatchException e) {
            System.out.println("Некорректное значение разера матрицы");
            return;
        }

    }
}
