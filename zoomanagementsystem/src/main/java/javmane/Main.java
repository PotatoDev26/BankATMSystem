package javmane;

import java.util.ArrayList;

public class Main {

    private static void Execute() {
        LoadMain();
    }

    private static void LoadMain() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Lion("MUFASA", 15));
        animals.add(new Elephant("PUMBA", 21));
        animals.add(new Monkey("JULIEN", 12));
        for (Animal animal : animals) {
            animal.makeSound();
        }
        ((Lion) animals.get(0)).hunt();
        ((Elephant) animals.get(1)).sprayWater();
        ((Monkey) animals.get(2)).throwBanana();
    }

    public static void main(String[] args) {
        Execute();
    }
}