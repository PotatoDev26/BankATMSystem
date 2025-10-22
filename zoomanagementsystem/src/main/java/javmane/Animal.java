package javmane;

public abstract class Animal {
    private String name;
    private int age;

    public Animal(String nanamme, int aaage) {
        this.name = nanamme;
        this.age = aaage;
    }

    public abstract void makeSound();
    
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
