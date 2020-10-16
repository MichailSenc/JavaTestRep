package myClass;

import javafx.css.Match;

public class Coordinate {
    private double x, y, z;

    public Coordinate(Coordinate a) {
        this.x = a.x;
        this.y = a.y;
        this.z = a.z;
    }

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double pointSize(Coordinate a) {
        double i;
        i = Math.pow(x - a.x, 2) + Math.pow(y - a.y, 2) + Math.pow(z - a.z, 2);
        return Math.sqrt(i);
    }

    @Override
    public String toString(){
        return "X:" + this.x + ",, "
                + "Y:" + this.y + ",, "
                + "Z:" + this.z;
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