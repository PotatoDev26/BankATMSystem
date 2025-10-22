package javmane;

public class Lion extends Animal {

    public Lion(String name, int age) {
        super(name, age);
    }

    public void hunt() {
        System.out.println("You get mauled by lion!");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " ROAR!");
    }
}
