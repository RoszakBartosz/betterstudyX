import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    public String readFirstLine(String fileName) throws FileNotFoundException {
        final File file = new File(fileName);
        final StringBuilder stringBuilder = new StringBuilder();


        try (Scanner scanner = new Scanner(file);) {
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.next());
            }
        } catch (FileNotFoundException e){
        e.printStackTrace();
            System.err.println("file not found: "+fileName);
        }


        return stringBuilder.toString();
    }

    public void createFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
    }
    public void writeToFile(String fileName, String text) throws IOException {
        final File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(text);
        }catch (IOException e){
            System.err.println("Failed write to file: "+fileName);
        }

        }
        }



