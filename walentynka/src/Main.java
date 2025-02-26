import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        System.out.println("Czy zostaniesz moją walentynką?");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        if (next.equals("tak".toLowerCase())){
            System.out.println("twoja odpowiedź to: tak");
            System.out.println("Kocham Cię, spędzimy walentynki razem!");
        } else {
            System.out.println("twoja odpowiedź to: tak");
            System.out.println("Kocham Cię, spędzimy te walentynki jeszcze bardziej razem!");
        }
    }
    }
