package myPoc;

public class Subject {
    private String name;
    private int score;

    public Subject(String name, int score){
        this.name= name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString(){
        return name + " (" + score + ")";
    }

    @Override
    public int hashCode(){
        return name.hashCode() + score;
    }

    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof Subject)) return false;
        Subject z = (Subject) object;
        if (this.score != z.score) return false;
        return this.name.equals(z.name);
    }
}
