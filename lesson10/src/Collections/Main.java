package Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal bella = new Animal("Bella", 0);
        Animal tymek = new Animal("Tymek", 10);
        Animal luna = new Animal("Luna", 4);
        Animal atos = new Animal("Atos", 3);

        List<Animal> animals = new ArrayList<>();
        animals.add(bella);
        animals.add(tymek);
        animals.add(luna);
        animals.add(0,luna);
        animals.add(atos);
        System.out.println(animals);
        animals.sort(Comparator.comparingInt(Animal::getAge));
        System.out.println(animals);



        }
    }

