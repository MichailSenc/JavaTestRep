package myPoc;

public class FullName implements Comparable<FullName> {
    private String name, surname, patronymic;

    public FullName(FullName element) {
        this.name = element.name.trim();
        this.surname = element.surname.trim();
        this.patronymic = element.patronymic.trim();
    }

    public FullName(){
        this.name = "";
        this.surname = "";
        this.patronymic = "";
    }

    public FullName(String surname, String name, String patronymic){
        this.name = name.trim();
        this.surname = surname.trim();
        this.patronymic = patronymic.trim();
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode() + patronymic.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%-36s", surname.trim() + " " + name.trim() + " " + patronymic.trim());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof FullName)) return false;
        FullName z = (FullName) object;
        if (!this.name.equals(z.name)) return false;
        if (!this.surname.equals(z.surname)) return false;
        if (!this.patronymic.equals(z.patronymic)) return false;
        return true;
    }

    @Override
    public int compareTo(FullName o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) result = this.surname.compareTo(o.surname);
        if (result == 0) result = this.patronymic.compareTo(o.patronymic);
        return result;
    }

    public void upCase(){
        if (!name.isEmpty())
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (!surname.isEmpty())
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
        if (!patronymic.isEmpty())
            patronymic = patronymic.substring(0, 1).toUpperCase() + patronymic.substring(1).toLowerCase();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

