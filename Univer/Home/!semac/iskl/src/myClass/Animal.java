package myClass;

public abstract class Animal {
    String name;
    int age;

    public Animal(String name, int age){
        this.name=name;
        this.age=age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return name + " " + age + " ";
    }

    public abstract String speak();
}
