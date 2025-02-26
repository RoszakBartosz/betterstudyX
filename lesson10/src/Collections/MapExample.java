package Collections;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(58302, "Mariusz");
        map.put(38295, "Jarosław");
        map.put(50292, "Bartosz");
        map.put(34829, "Jan");
        System.out.println(map.get(34829));

        Map<DayOfWeek, String> dayOfWeekActivity = new EnumMap<>(DayOfWeek.class);
        dayOfWeekActivity.put(DayOfWeek.MONDAY, "Work hard");
        dayOfWeekActivity.put(DayOfWeek.TUESDAY, "Work hard");
        dayOfWeekActivity.put(DayOfWeek.WEDNESDAY, "Work");
        dayOfWeekActivity.put(DayOfWeek.THURSDAY, "Work");
        dayOfWeekActivity.put(DayOfWeek.FRIDAY, "Work");
        dayOfWeekActivity.put(DayOfWeek.SATURDAY, "Chill");
        dayOfWeekActivity.put(DayOfWeek.SUNDAY, "Chill");


        dayOfWeekActivity
                .entrySet()
                .stream()
                .filter(entry -> entry
                        .getKey()
                        .isWorkingDay())
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));

    }

//    public Map<String, String> mapBully(Map<String, String> map) {
//        if (map.containsKey("a")) {
//        final String aValue = map.get("a");
//        map.put("b", aValue);

 //       }


   // }
}
