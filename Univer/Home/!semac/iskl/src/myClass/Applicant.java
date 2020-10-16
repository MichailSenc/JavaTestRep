package myClass;

import myInterface.Game;

public class Applicant implements Game {
    private String surname;
    private int totalScore;
    private static int maxTotalScore=400;

    public Applicant(){
        this("Иванов",200);
    }

    public Applicant(String surname, int totalScore){
        this.surname = surname;
        this.totalScore = totalScore;
    }

    public Applicant(Applicant ap){
        surname = ap.surname;
        totalScore = ap.totalScore;
    }

    public void set(String surname){
        this.surname=surname;
    }
    public void set(int totalScore){
        this.totalScore=totalScore;
    }

    public String getSurname(){
        return surname;
    }
    public int getTotalScore(){
        return totalScore;
    }

    public void printApplicant(){
        System.out.println(surname+" "+totalScore);
    }

    public boolean admission(int passingScore) {
        if (totalScore >= passingScore) return true;
        else return false;
    }
    @Override
    public String toString(){
        return surname+" "+totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if(!(o instanceof Applicant))
            return false;
        Applicant a = (Applicant) o;
        return surname.equals(a.surname)&&totalScore==a.totalScore;
    }
    @Override
    public int hashCode() {
        return surname.hashCode()+totalScore;
    }

    public static void setMaxTotalScore(int maxTotalScore){
        Applicant.maxTotalScore=maxTotalScore;
    }

    @Override
    public void play(){
        System.out.println("Компьютерная игра...");
    }
}