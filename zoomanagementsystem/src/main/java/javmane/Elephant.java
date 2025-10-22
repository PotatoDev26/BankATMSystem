package javmane;

public class Elephant extends Animal {

    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("EURGHHHHH!");
    }

    public void sprayWater() {
        System.out.println("NO MORE SUN!");
    }

}
