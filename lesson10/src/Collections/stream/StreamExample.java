package Collections.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jan", 20000, 9, Position.DEVELOPER));
        employees.add(new Employee("Paweł", 3000, 7, Position.MANAGER));
        employees.add(new Employee("Jarosław", 2000, 10, Position.RECRUITER));
        employees.add(new Employee("Krzysztof", 40000, 3, Position.TECH_LEAD));
        employees.add(new Employee("Tomek", 25000, 10 , Position.DEVELOPER));
        employees.add(new Employee("Artur", 30000, 10 , Position.DEVELOPER));


        List<String> names = employees
                .stream()
                .filter(employee -> employee.getSalary() > 20000)
                .filter(employee -> employee.getPosition().isIT())
                .map(employee -> employee.getName())
                .collect(Collectors.toList());
        System.out.println(names);
        List<String> findYourTeamApp = employees
                .stream()
              //  .limit(1) - this method put a limit for max.1 element
                .filter(employee -> employee.getNote() >= 8)
                .filter(employee -> employee.getSalary() < 26000)
                .peek(s-> System.out.println("Filtered Employee >20000: "+s))
                .map(employee -> employee.getName())
                .peek(s -> System.out.println("Mapped employee name: "+s))
                .collect(Collectors.toList());
        System.out.println(findYourTeamApp);


        List<String> noITNotesUpperThan7 = employees
                .stream()
                .filter(employee -> employee.getNote() > 7)
                .filter(employee -> !employee.getPosition().isIT())
                .map(employee -> employee.getName())
                .collect(Collectors.toList());

        List<Double> salaries = employees
                .stream()
                .filter(employee -> !employee.getPosition().isIT())
                .map(Employee::getSalary)
                .collect(Collectors.toList());
        System.out.println("\n \n"+salaries);


        Optional<Double> reduce = salaries
                .stream()
                .reduce(Double::sum);

                reduce.orElse(0d);


        Double v = salaries
                .stream()
                .max((o1, o2) -> (int) (o1-o2))
                .orElse(0d);
        System.out.println(v);

        long numberOfDev = employees
                .stream()
                .filter(employee -> employee.getPosition() == Position.DEVELOPER)
                .count();

        Optional<Employee> maxSalaryOfDev = employees
                .stream()
                .filter(employee -> employee.getPosition() == Position.DEVELOPER)
                .max((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()));
        System.out.println(maxSalaryOfDev);

    }


}
