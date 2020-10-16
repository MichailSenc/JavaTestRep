package myClass;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Applicant> {
    @Override
    public int compare(Applicant ap,Applicant ap1){
        return ap.getSurname().compareTo(ap1.getSurname());
    }
}