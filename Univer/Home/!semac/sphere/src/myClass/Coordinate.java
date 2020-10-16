package myClass;

import javafx.css.Match;
import java.util.Arrays;

public class Coordinate {
    private int x, y, z;//координаты

    public Coordinate(Coordinate a) {//конструктор копирования
        this.x = a.x;
        this.y = a.y;
        this.z = a.z;
    }

    public Coordinate(int x, int y, int z) {//просто конструктор
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public double range(int x, int y, int z) {//расстояние между двумя точками
        double i;
        i = Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2);
        return Math.sqrt(i);
    }

    @Override
    public String toString(){
        return String.format("(%d,%d,%d)",x,y,z);
    }

    @Override
    public int hashCode() {
        return x+y+z;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof Coordinate)) return false;
        Coordinate z = (Coordinate) object;
        if (this.x != z.x) return false;
        if (this.y != z.y) return false;
        if (this.z != z.z) return false;
        return true;
    }
}
