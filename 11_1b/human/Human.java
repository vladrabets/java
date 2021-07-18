package human;

public class Human {
    private String name;
    private int age;
    private static int id;

    public Human()
    {
        name = "Unnamed"+id;
        id++;
        age = (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age
                ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
