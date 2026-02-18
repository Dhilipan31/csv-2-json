import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        CsvParser csv = new CsvParser();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the read path");
        String filePath = sc.nextLine();

        System.out.println("Enter the write path");
        String writePath = sc.nextLine();

        csv.parser(filePath, writePath);
    }
}