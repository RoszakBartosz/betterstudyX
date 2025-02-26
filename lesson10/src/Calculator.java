import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {


    public void divideFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number to divide. ");
        int a = scanner.nextInt();
        System.out.println("Enter second number to divide. ");
        int b = scanner.nextInt();
        System.out.println("Result of divide: " + divide(a,b));
    }
    public int divide(int a, int b) {
    return a/b;
    }
}
