package Collections;

import java.util.HashSet;
import java.util.TreeSet;

public class SetExample {
    public static void main(String[] args) {
        HashSet<Animal> animals = new HashSet<>();

        Animal bella = new Animal("Bella", 0);
        Animal tymek = new Animal("Tymek", 10);
        Animal luna = new Animal("Luna", 4);
        Animal atos = new Animal("Atos", 3);

        animals.add(tymek);
        animals.add(atos);
        animals.add(luna);
        animals.add(bella);

        animals.remove(atos);

        System.out.println(animals);
    final TreeSet<Animal> treeAnimals = new TreeSet<>();
        treeAnimals.add(atos);
        treeAnimals.add(luna);
        treeAnimals.add(bella);

        treeAnimals.ceiling(new Animal("Bella", 0));
        treeAnimals.pollFirst();
        treeAnimals.remove(new Animal("Bella",0));
        treeAnimals.add(new Animal("Bella", 0));
        treeAnimals.ceiling(new Animal("Bella", 0));
        treeAnimals.last();


        System.out.println(treeAnimals);
    }
}
