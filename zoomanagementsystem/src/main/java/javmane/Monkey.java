package javmane;

public class Monkey extends Animal {

    public Monkey(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("OOHH-OOHH-AHH-AHH!");
    }

    public void throwBanana() {
        System.out.println("LUNGDAKTENGLANGTENGLETLUKASDIE!");
    }

}