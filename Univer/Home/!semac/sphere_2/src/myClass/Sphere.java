package myClass;

import javafx.css.Match;

public class Sphere {
    private double r;
    private Coordinate a,b;

    public Sphere(Sphere ap) {
        this.r = ap.r;
        this.a=ap.a;
        this.b=ap.b;
    }

    public double findRadius(){
        return a.pointSize(b);
    }

    @Override
    public String toString() {
        return  a.toString() + ",, "
                + "R:" + this.r;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof Sphere)) return false;
        Sphere z = (Sphere) object;
        if (this.r != z.r) return false;
        if(!(a.equals(((Sphere) object).a))) return false;
        if(!(b.equals(((Sphere) object).b))) return false;
        return true;
    }
}
