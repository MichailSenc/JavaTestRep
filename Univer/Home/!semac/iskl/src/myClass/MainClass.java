package myClass;

public class TotalScoreComparator implements Comparator<Applicant> {
    @Override
    public int compare(Applicant ap, Applicant ap1){
        return ap.getTotalScore().compareTo(ap1.getTotalScore());
    }
}

    Это новый пакет myInterface

package myInterface;

public interface Game {
    void play();
}

    И пакет с ошибками

package myException;

public class BadTotalScoreException extends Exception{
    public BadTotalScoreException(String s){
        super(s);
    }
}