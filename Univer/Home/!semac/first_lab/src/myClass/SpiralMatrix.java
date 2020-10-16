package myClass;
import myExeption.MissLenghtException;

import java.util.Arrays;

public class SpiralMatrix {
    private int arr[][];
    private int size;

    public SpiralMatrix(int arr[][], int size) throws MissLenghtException {
        if (size < 0) throw new MissLenghtException("некорректная длина матрицы");
        this.size = size;
        this.arr = Arrays.copyOf(arr, size);
    }

    public SpiralMatrix(SpiralMatrix ap) throws MissLenghtException{
        if (ap.size < 0) throw new MissLenghtException("некорректная длина матрицы");
        this.size = ap.size;
        this.arr = Arrays.copyOf(ap.arr, ap.size);
    }

    public void printMatrix() {
        System.out.print("size: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++)
                System.out.print(arr[i][j] + " ");
        }
    }

    public void fullArr() {
        int i = 1, j, k, p = size / 2;
        for (k = 1; k <= p; k++)/*/Цикл по номеру витка*/ {
            for (j = k - 1; j < size - k + 1; j++) arr[k - 1][j] = i++;
            for (j = k; j < size - k + 1; j++) arr[j][size - k] = i++;
            for (j = size - k - 1; j >= k - 1; --j) arr[size - k][j] = i++;
            for (j = size - k - 1; j >= k; j--) arr[j][k - 1] = i++;
        }
        if (size % 2 == 1) arr[p][p] = size * size;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(arr);
    }

    @Override
    public int hashCode() {
        int h = Arrays.hashCode(arr[0]);
            ;
        for (int i = 1; i < size; i++) {
            h += Arrays.hashCode(arr[i]);
        }
        return h;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (this == object)
            return true;
        if (!(object instanceof SpiralMatrix))
            return false;
        SpiralMatrix z = (SpiralMatrix) object;
        if (this.size != z.size)
            return false;
        for (int i = 0; i < z.size; i++) {
            if (!Arrays.equals(this.arr[i], z.arr[i]))
                return false;
        }
        return true;
    }
}