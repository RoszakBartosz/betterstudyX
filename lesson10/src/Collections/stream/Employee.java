package Collections.stream;

import java.util.Comparator;
import java.util.Objects;

public class Employee{
    private String name;
    private double salary;
     private int note;
    private Position position;


    public Employee(String name, double salary, int note, Position position) {
        this.name = name;
        this.salary = salary;
        this.note = note;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getNote() {
        return note;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 && note == employee.note && Objects.equals(name, employee.name) && position == employee.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, note, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", note=" + note +
                ", position=" + position +
                '}';


    }

}
