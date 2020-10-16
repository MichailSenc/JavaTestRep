package myClass;

import javafx.css.Match;
import java.util.Arrays;

public class Sphere {
    private double r;//радиус сферы
    private int x, y, z;

    public Sphere(int x, int y, int z, double r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int sign_of_difference(double t) {
        return (int) Math.signum(this.r - t);
    }///!!!!!!!!!!!!!!!!!!!!!!!!!!

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public
    double range(int x, int y, int z) {//расстояние между двумя точками
        double i;
        i = Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2);
        return Math.sqrt(i);
    }

    public String string() {
        return String.format("(%d,%d,%d), %.2f", x, y, z, r);
    }

    @Override
    public String toString() {
        String s1, s2, s3, s;
        int a = Math.abs(x), b = Math.abs(y), c = Math.abs(z);
        if (x < 0) s1 = "(x + %d)^2";
        else s1 = "(x - %d)^2";
        if (y < 0) s2 = "(y + %d)^2";
        else s2 = "(y - %d)^2";
        if (z < 0) s3 = "(z + %d)^2";
        else s3 = "(z - %d)^2";
        s = s1 + " + " + s2 + " + " + s3 + " = (%.1f)^2";
        return String.format(s, a, b, c, r);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof Sphere)) return false;
        Sphere z = (Sphere) object;
        if (this.r != z.r) return false;
        if (this.x != z.x) return false;
        if (this.y != z.y) return false;
        if (this.z != z.z) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return x + y + z + (int) (Math.round(r));
    }
}
