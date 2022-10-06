package resources;

public class TestResource {
    private String name;
    private int age;

    public TestResource() {
        this.name = "";
        this.age = 0;
    }

    public TestResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestResource{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
