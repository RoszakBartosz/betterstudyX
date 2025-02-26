package Collections.stream;

import java.util.List;
import java.util.function.Consumer;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = List.of("Marcin", "Adam", "Kasia");
         names.forEach(s -> System.out.println("Hello "+s));

    }
public static String modify(String s, StringModifier modifier){
        return modifier.modify(s);


}
}
