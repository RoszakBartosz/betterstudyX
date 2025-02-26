package Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class IteratorExample {

    public static void main(String[] args) {
        final Collection<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        System.out.println(strings);


        System.out.println("forEach: ");
        for (String s: strings) {
            System.out.println(s);
        }

        System.out.println("\n \n stream forEach: ");
        strings.stream().forEach(s -> System.out.println(s));


        System.out.println("\n \n iterator: ");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());


            ArrayList<Integer> ints = new ArrayList<>();
            ints.add(1);
            ints.add(2);
            ints.add(3);
            ints.add(4);

            System.out.println(ints);
            List<Integer> collect = ints.stream()
                    .filter(integer -> integer % 2 == 0)
                    .collect(Collectors.toList());
            System.out.println(collect);


            List<String> stringsStream = new ArrayList<>();
            strings.stream()
                    .filter(s -> s.contains("3"))
                    .forEach(s -> stringsStream.add(s));
            System.out.println(stringsStream);


        }

    }

}
