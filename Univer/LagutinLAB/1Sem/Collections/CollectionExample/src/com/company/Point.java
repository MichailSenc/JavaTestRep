package com.company;

public class Point implements Comparable<Point> {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        if (x != ((Point) obj).x) {
            return false;
        }
        if (y != ((Point) obj).y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (int) x;
        result = 37 * result + (int) y;
        return result;
    }

    @Override
    public int compareTo(Point p) {
        double diff = (x * x + y * y) - (p.x * p.x + p.y * p.y);
        return (int) (Math.signum(diff));
    }
}

