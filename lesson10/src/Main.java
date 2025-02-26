import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {


    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//       // calculator.divide(2,0);
//       try {
//           calculator.divideFromCommandLine();
//       } catch (InputMismatchException e) {
//           System.out.println("This value isn't a number!");
//           e.printStackTrace();
//       }  catch (ArithmeticException e) {
//        System.out.println("Impossible divide by 0! ");
//        e.printStackTrace();
//    } finally {
//           System.out.println("{Importing Data by DataBase:}");
//       }
        FileManager fileManager = new FileManager();
        try {
            String file = "xd.txt";

            fileManager.createFile(file);
            fileManager.writeToFile(file, "xdx3");
            String s = fileManager.readFirstLine(file);
            System.out.println(s);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IO Exception! ");
        }
    }
    }
