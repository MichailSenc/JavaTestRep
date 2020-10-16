package myClass;

import java.util.Comparator;

public class SphereComparator implements Comparator<Sphere> {
    @Override
    public int compare(Sphere o1, Sphere o2) {
        double diff = o1.getR() - o2.getR();
        return (int) (Math.signum(diff));
    }
}